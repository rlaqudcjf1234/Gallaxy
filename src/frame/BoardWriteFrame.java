package frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.AddressDTO;
import service.NaverApiService;
import service.RetrieveNewAdress;
import service.impl.NaverApiServiceImpl;
import service.impl.RetrieveNewAdressImpl;

public class BoardWriteFrame extends JFrame {

	RetrieveNewAdress rna = new RetrieveNewAdressImpl();
	NaverApiService nas = new NaverApiServiceImpl();
	
	String filePath = "temp/����Ư���� ���α� ���ϴ��17�� 42-4.jpg";

	/**
	 * 
	 */
	private static final long serialVersionUID = -9174227746682050944L;

	public BoardWriteFrame() {
		//������ Ÿ��Ʋ��
		setTitle("BoardWriteFrame");
		//������ ũ��(�ȼ�)
		setSize(500, 850);
		//X��ư ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �������� X Ŭ�� �� ����.

		// JFrame ���� ����.
		Container container = getContentPane();

		// JFrame ���� ���� ��ܿ� �� �ּ��Է�
		JPanel search = initSearch();
		container.add(BorderLayout.NORTH, search); // ��� pan ����
		
		JPanel input = initInput();
		container.add(BorderLayout.CENTER, input); // �ߴ� pan ����

		setVisible(true);

	}

	public BoardWriteFrame(Frame mainFrame) {
		// TODO Auto-generated constructor stub
	}

	private JPanel initInput() {
		// TODO Auto-generated method stub
		JPanel pan = new JPanel();
		
		
		
		return pan;
	}

	public JPanel initSearch() {
		JPanel pan = new JPanel();
		JLabel addressLbl = new JLabel("�ּ��Է�"); // �ּ� ��ȸ label
		pan.add(addressLbl);

		JTextField address = new JTextField(30); // �ּ� ��ȸ input
		pan.add(address);

		JButton btn = new JButton("Ŭ��"); // �ּ� ��ȸ ����
		pan.add(btn);
		
		ImageIcon img = new ImageIcon(filePath);
		JLabel imageLbl = new JLabel(img);
		pan.add(imageLbl);

		// pan�� ������ ��ư(btn) Ŭ�� �� ó���ϴ� �̺�Ʈ �ڵ鷯.
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String serarch = address.getText();
				List<String> addrs = rna.getAddress(serarch);

				if (addrs != null && addrs.size() > 0) {
					List<AddressDTO> list = nas.getGeocode(addrs.get(0));
					if (list != null && list.size() > 0) {
						File file = nas.getStatic(list.get(0));
						if (file != null && file.length() > 0) {
							filePath = file.getPath();
							ImageIcon img = new ImageIcon(filePath);
							imageLbl.setIcon(img);
						}
					}
				}
			}
		});

		return pan;
	}

	public static void main(String[] args) {
		new BoardWriteFrame();
	}

}
/*
 * package frame;

import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class WriteBoard {

	private Frame mainFrame;
	private Frame writeFrame;

	public WriteBoard(Frame ma) {
		this.mainFrame = ma;

		Frame writeFrame = new Frame("�� �ۼ�");
		writeFrame.setBounds(700, 100, 500, 850); // ��ġ�� ũ��
		writeFrame.setLayout(null); // ���� ���̾ƿ� ���

		// ���� �� ���� �Է�
		TextField titleField = new TextField("������ �Է��ϼ���");
		TextArea contentField = new TextArea("������ �Է��ϼ���");

		titleField.setBounds(50, 350, 400, 30);
		contentField.setBounds(50, 400, 400, 300);

		writeFrame.add(titleField);
		writeFrame.add(contentField);

		// ���콺 Ŭ�� �� �⺻ �ؽ�Ʈ �����
		titleField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (titleField.getText().equals("������ �Է��ϼ���")) {
					titleField.setText(""); // Ŭ�� �� �ؽ�Ʈ �ʱ�ȭ
				}
			}
		});

		contentField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (contentField.getText().equals("������ �Է��ϼ���")) {
					contentField.setText(""); // Ŭ�� �� �ؽ�Ʈ �ʱ�ȭ
				}
			}
		});

		// �ۼ� �Ϸ� ��ư
		Button btnWrite = new Button("�ۼ� �Ϸ�");
		btnWrite.setBounds(250, 100, 100, 30);
		writeFrame.add(btnWrite);

		// ��ư Ŭ�� �̺�Ʈ
		btnWrite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// �ۼ��� ���� ó��
				String title = titleField.getText();
				String content = contentField.getText();

				if (title.isEmpty() || content.isEmpty() 
				|| title.equals("������ �Է��ϼ���") || content.equals("������ �Է��ϼ���"))

				{
					JOptionPane.showMessageDialog(writeFrame, "����� ������ �Է��ϼ���!");
				} else {
					JOptionPane.showMessageDialog(writeFrame, "�ۼ� �Ϸ�: " + title);
					BoardNotice.addWrite(title, content); // ����� ������ NoticeBoard�� �߰�
					writeFrame.dispose(); // �� �ۼ� ������ �ݱ�
					mainFrame.setVisible(true); // ���� ������ ���̱�
				}
			}
		});

		// ���� �̺�Ʈ
		writeFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				writeFrame.dispose(); // ������ �ݱ�
			}
		});

		writeFrame.setVisible(true);
	}
}*/
