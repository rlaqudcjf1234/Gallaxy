package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import dto.AddressDTO;
import dto.BoardDTO;
import service.BoardService;
import service.NaverApiService;
import service.RetrieveNewAdress;
import service.impl.BoardServiceImpl;
import service.impl.NaverApiServiceImpl;
import service.impl.RetrieveNewAdressImpl;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import javax.swing.DropMode;

public class BoardWriteFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6032491971534575326L;

	BoardService bs = new BoardServiceImpl();
	NaverApiService nas = new NaverApiServiceImpl();
	RetrieveNewAdress rna = new RetrieveNewAdressImpl();

	String filePath = "temp/����Ư���� ���α� ���� 13-3 ���μ�����.jpg";
	String textInit = "������ �Է��ϼ���";
	String contentInit = "������ �Է��ϼ���";

	private JPanel contentPane;
	private JLabel addressLbl;
	private JTextField addressFld;
	private JButton searchBtn;
	private JLabel imageLbl;
	private JTextField textFld;
	private JTextArea contentFld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardWriteFrame frame = new BoardWriteFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BoardWriteFrame() {
		// ������ Ÿ��Ʋ��
		setTitle("BoardWriteFrame");
		// X��ư ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ������ ��ġ, ũ��(�ȼ�)
		setBounds(100, 100, 500, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		addressLbl = new JLabel("�ּ��Է�");
		contentPane.add(addressLbl);

		addressFld = new JTextField(30);
		contentPane.add(addressFld);

		searchBtn = new JButton("��ȸ");
		contentPane.add(searchBtn);

		// ��ư(searchBtn) Ŭ�� �̺�Ʈ
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String serarch = addressFld.getText();
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

		imageLbl = new JLabel(new ImageIcon(filePath));
		contentPane.add(imageLbl);
		/*
		 * textLbl = new JLabel("����"); contentPane.add(textLbl);
		 */
		textFld = new JTextField(textInit);
		textFld.setColumns(35);
		contentPane.add(textFld);

		// Ŭ�� �̺�Ʈ �⺻ �ؽ�Ʈ(textFld) ����
		textFld.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFld.getText().equals(textInit)) {
					textFld.setText(""); // Ŭ�� �� �ؽ�Ʈ �ʱ�ȭ
				}
			}
		});

		contentFld = new JTextArea(contentInit);
		contentFld.setColumns(35);
		contentFld.setRows(10);
		contentPane.add(contentFld);

		// Ŭ�� �̺�Ʈ �⺻ �ؽ�Ʈ(contentFld) ����
		contentFld.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (contentFld.getText().equals(contentInit)) {
					contentFld.setText(""); // Ŭ�� �� �ؽ�Ʈ �ʱ�ȭ
				}
			}
		});

		// �ۼ� �Ϸ� ��ư
		JButton writeBtn = new JButton("�ۼ� �Ϸ�");
		writeBtn.setBounds(250, 100, 100, 30);
		contentPane.add(writeBtn);

		// ��ư(writeBtn) Ŭ�� �̺�Ʈ
		writeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// �ۼ��� ���� ó��
				String title = textFld.getText();
				String content = contentFld.getText();

				if (title.isEmpty() || title.equals(textInit)) {
					JOptionPane.showMessageDialog(null, textInit, "�˸�", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (content.isEmpty() || content.equals(contentInit)) {
					JOptionPane.showMessageDialog(null, contentInit, "�˸�", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				BoardDTO dto = new BoardDTO();
				dto.setBoardTitle(title);
				dto.setBoardContent(content);
				dto.setBoardFilePath(filePath);

				int cnt = bs.insertBoard(dto);
				if (cnt > 0) {
					JOptionPane.showMessageDialog(null, "�ۼ��� �Ϸ�Ǿ����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "������ �߻��Ͽ����ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		setContentPane(contentPane);
	}
}
