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

	String filePath = "temp/서울특별시 종로구 종로 13-3 구두수선대.jpg";
	String textInit = "제목을 입력하세요";
	String detailInit = "내용을 입력하세요";

	String yyyy[] = { "2024년", "2025년" };
	String mm[] = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
	String dd[] = { "1일", "2일", "3일", "4일", "5일", "6일", "7일", "8일", "9일", "10일", "11일", "12일", "13일", "14일", "15일",
			"16일", "17일", "18일", "19일", "20일", "21일", "22일", "23일", "24일", "25일", "26일", "27일", "28일", "29일", "30일",
			"31일" };
	String ap[] = { "오전", "오후" };
	String hh[] = { "1시", "2시", "3시", "4시", "5시", "6시", "7시", "8시", "9시", "10시", "11시", "12시" };
	String mi[] = { "00분", "30분" };

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
		// 프레임 타이틀바
		jFrame.setTitle("BoardSearchFrame");
		// 프레임 위치, 크기(픽셀)
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
		
		// 목록 추가
		JPanel buttonPane1 = new JPanel();
		buttonPane1.setLayout(new BorderLayout());
		listPane.add(buttonPane1);

		JLabel addrLbl1 = new JLabel(Integer.toString(10));
		buttonPane1.add(addrLbl1, BorderLayout.WEST);

		JPanel buttonPane2 = new JPanel();
		buttonPane2.setBorder(new EmptyBorder(15, 5, 15, 5));
		buttonPane2.setLayout(new BorderLayout());
		buttonPane1.add(buttonPane2, BorderLayout.EAST);
		
		JButton addrBtn1 = new JButton("선택");
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

		// 목록 전체 삭제
		listPane.removeAll();
		for (int i = 0; i < 9; i++) {

			// 목록 추가
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new BorderLayout());
			listPane.add(buttonPane);

			JLabel addrLbl = new JLabel(Integer.toString(i));
			buttonPane.add(addrLbl, BorderLayout.WEST);

			JButton addrBtn = new JButton("선택");
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
		listPane.revalidate(); // UI 업데이트
		listPane.repaint(); // UI 갱신

		jFrame.setVisible(true);
		jFrame.setContentPane(contentPane);
	}
}
