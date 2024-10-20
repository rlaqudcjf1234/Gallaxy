package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class BoardSearchFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6032491971534575326L;

	BoardService bs = new BoardServiceImpl();
	NaverApiService nas = new NaverApiServiceImpl();
	RetrieveNewAdress rna = new RetrieveNewAdressImpl();

	Map<String, Object> result;
	int currentPage = 1;
	int totalPage;
	List<String> addrs;

	String filePath = "temp/����Ư���� ���α� ���� 13-3 ���μ�����.jpg";
	String textInit = "������ �Է��ϼ���";
	String detailInit = "������ �Է��ϼ���";

	String yyyy[] = { "2024��", "2025��" };
	String mm[] = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��" };
	String dd[] = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��", "13��", "14��", "15��",
			"16��", "17��", "18��", "19��", "20��", "21��", "22��", "23��", "24��", "25��", "26��", "27��", "28��", "29��", "30��",
			"31��" };
	String ap[] = { "����", "����" };
	String hh[] = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��" };
	String mi[] = { "00��", "30��" };

	private JTextField addressFld;
	private JLabel imageLbl;
	private JTextField textFld;
	private JTextArea detailFld;
	private JComboBox<String> yyyyCbx;
	private JComboBox<String> mmCbx;
	private JComboBox<String> ddCbx;
	private JComboBox<String> apCbx;
	private JComboBox<String> hhCbx;
	private JComboBox<String> hiCbx;

	private JFrame jFrame = new JFrame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardSearchFrame frame = new BoardSearchFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @return
	 */
	public BoardSearchFrame() {
		// ������ Ÿ��Ʋ��
		jFrame.setTitle("BoardSearchFrame");
		// ������ ��ġ, ũ��(�ȼ�)
		jFrame.setBounds(600, 100, 500, 850);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel pagePane = new JPanel();
		pagePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		pagePane.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(pagePane, BorderLayout.NORTH);

		JButton prevButton = new JButton("<");
		pagePane.add(prevButton);

		textFld = new JTextField(Integer.toString(currentPage), 3);
		pagePane.add(textFld);

		JLabel totalLbl = new JLabel(" / " + Integer.toString(totalPage));
		totalLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		pagePane.add(totalLbl);

		JButton nextButton = new JButton(">");
		pagePane.add(nextButton);

		JPanel listPane = new JPanel();
		listPane.setLayout(new GridLayout(10, 1));
		JScrollPane scrollPane = new JScrollPane(listPane);
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		// ��� �߰�
		JPanel buttonPane1 = new JPanel();
		buttonPane1.setLayout(new BorderLayout());
		listPane.add(buttonPane1);

		JLabel addrLbl1 = new JLabel(Integer.toString(10));
		buttonPane1.add(addrLbl1, BorderLayout.WEST);

		JPanel buttonPane2 = new JPanel();
		buttonPane2.setBorder(new EmptyBorder(15, 5, 15, 5));
		buttonPane2.setLayout(new BorderLayout());
		buttonPane1.add(buttonPane2, BorderLayout.EAST);
		
		JButton addrBtn1 = new JButton("����");
		buttonPane2.add(addrBtn1, BorderLayout.CENTER);

		addrBtn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				if (addrs != null && addrs.size() > 0) {
					List<AddressDTO> list = nas.getGeocode(addrs.get(0));
				}
				if (list != null && list.size() > 0) {
					File file = nas.getStatic(list.get(0));
					if (file != null && file.length() > 0) {
						filePath = file.getPath();
						ImageIcon img = new ImageIcon(filePath);
						imageLbl.setIcon(img);
					}
				}*/
			}
		});

		// ��� ��ü ����
		listPane.removeAll();
		for (int i = 0; i < 9; i++) {

			// ��� �߰�
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new BorderLayout());
			listPane.add(buttonPane);

			JLabel addrLbl = new JLabel(Integer.toString(i));
			buttonPane.add(addrLbl, BorderLayout.WEST);

			JButton addrBtn = new JButton("����");
			buttonPane.add(addrBtn, BorderLayout.EAST);

			addrBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					/*
					if (addrs != null && addrs.size() > 0) {
						List<AddressDTO> list = nas.getGeocode(addrs.get(0));
					}
					if (list != null && list.size() > 0) {
						File file = nas.getStatic(list.get(0));
						if (file != null && file.length() > 0) {
							filePath = file.getPath();
							ImageIcon img = new ImageIcon(filePath);
							imageLbl.setIcon(img);
						}
					}*/
				}
			});
		}
		listPane.revalidate(); // UI ������Ʈ
		listPane.repaint(); // UI ����

		jFrame.setVisible(true);
		jFrame.setContentPane(contentPane);
	}
}
