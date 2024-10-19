package frame;

import java.awt.BorderLayout;
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

public class BoardWriteFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6032491971534575326L;

	BoardService bs = new BoardServiceImpl();
	NaverApiService nas = new NaverApiServiceImpl();
	RetrieveNewAdress rna = new RetrieveNewAdressImpl();

	Map<String, Object> result;
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
	 * 
	 * @return
	 */
	public void BoardSearchFrame() {
		// 프레임 타이틀바
		jFrame.setTitle("BoardSearchFrame");
		// 프레임 위치, 크기(픽셀)
		jFrame.setBounds(600, 100, 500, 850);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JScrollPane listPane = new JScrollPane();
		listPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.X_AXIS));
		contentPane.add(listPane, BorderLayout.CENTER);

		if (addrs != null && addrs.size() > 0) {

		}

		jFrame.setVisible(true);
		jFrame.setContentPane(contentPane);
	}

	/**
	 * Create the frame.
	 */
	public BoardWriteFrame() {
		// 프레임 타이틀바
		setTitle("BoardWriteFrame");
		// X버튼 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 프레임 위치, 크기(픽셀)
		setBounds(100, 100, 500, 850);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel searchPane = new JPanel();
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(searchPane, BorderLayout.NORTH);

		JLabel addressLbl = new JLabel("주소");
		searchPane.add(addressLbl);

		addressFld = new JTextField(30);
		searchPane.add(addressFld);

		JButton searchBtn = new JButton("조회");
		searchPane.add(searchBtn);

		// 버튼(searchBtn) 클릭 이벤트
		searchBtn.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int page = 1;
				String serarch = addressFld.getText();
				if(serarch.length() < 2) {
					JOptionPane.showMessageDialog(null, "한 글자만으로는 검색이 불가합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if((result = rna.getAddress(serarch, page)) != null) {
					String errorCode = (String) result.get("errorCode");
					String errorMessage = (String) result.get("errorMessage");
					
					if(errorCode.equals("0")) {
						totalPage = (int) result.get("totalPage");
						if(totalPage > 0) {
							addrs = (List<String>) result.get("list");
						} else {
							JOptionPane.showMessageDialog(null, "조회 결과가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}else {
						JOptionPane.showMessageDialog(null, errorMessage, "알림", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				BoardSearchFrame();
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

		JPanel imagePane = new JPanel();
		imagePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		imagePane.setLayout(new BoxLayout(imagePane, BoxLayout.X_AXIS));
		contentPane.add(imagePane, BorderLayout.CENTER);

		imageLbl = new JLabel(new ImageIcon(filePath));
		imagePane.add(imageLbl, BorderLayout.CENTER);

		JPanel titlePane = new JPanel();
		titlePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.X_AXIS));
		contentPane.add(titlePane, BorderLayout.CENTER);

		JLabel textLbl = new JLabel("제목");
		textLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		titlePane.add(textLbl);

		textFld = new JTextField(textInit);
		titlePane.add(textFld);

		// 클릭 이벤트 기본 텍스트(textFld) 삭제
		textFld.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFld.getText().equals(textInit)) {
					textFld.setText(""); // 클릭 시 텍스트 초기화
				}
			}
		});

		JPanel datePane = new JPanel();
		datePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		datePane.setLayout(new BoxLayout(datePane, BoxLayout.X_AXIS));
		contentPane.add(datePane, BorderLayout.CENTER);

		JLabel dateLbl = new JLabel("일자");
		dateLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		datePane.add(dateLbl);

		Calendar cal = Calendar.getInstance();

		yyyyCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(yyyy));
		datePane.add(yyyyCbx);

		mmCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(mm));
		mmCbx.setSelectedIndex(cal.get(Calendar.MONTH));
		datePane.add(mmCbx);

		ddCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(dd));
		ddCbx.setSelectedIndex(cal.get(Calendar.DAY_OF_MONTH) - 1);
		datePane.add(ddCbx);

		JPanel timePane = new JPanel();
		timePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		timePane.setLayout(new BoxLayout(timePane, BoxLayout.X_AXIS));
		contentPane.add(timePane, BorderLayout.CENTER);

		JLabel timeLbl = new JLabel("시간");
		timeLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		timePane.add(timeLbl);

		apCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(ap));
		apCbx.setSelectedIndex(cal.get(Calendar.AM_PM));
		timePane.add(apCbx);

		hhCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(hh));
		hhCbx.setSelectedIndex(cal.get(Calendar.HOUR) - 1);
		timePane.add(hhCbx);

		hiCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(mi));
		timePane.add(hiCbx);

		JPanel detailPane = new JPanel();
		detailPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		detailPane.setLayout(new BoxLayout(detailPane, BoxLayout.X_AXIS));
		contentPane.add(detailPane, BorderLayout.CENTER);

		JLabel detailLbl = new JLabel("내용");
		detailLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		detailPane.add(detailLbl);

		detailFld = new JTextArea(detailInit);
		detailFld.setColumns(35);
		detailFld.setRows(10);
		detailPane.add(detailFld);

		// 클릭 이벤트 기본 텍스트(contentFld) 삭제
		detailFld.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (detailFld.getText().equals(detailInit)) {
					detailFld.setText(""); // 클릭 시 텍스트 초기화
				}
			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		contentPane.add(buttonPane, BorderLayout.CENTER);

		// 작성 완료 버튼
		JButton writeBtn = new JButton("작성 완료");
		buttonPane.add(writeBtn);

		// 작성 취소 버튼
		JButton cancelBtn = new JButton("작성 취소");
		cancelBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buttonPane.add(cancelBtn);

		// 버튼(writeBtn) 클릭 이벤트
		writeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 작성된 내용 처리
				String title = textFld.getText();
				String content = detailFld.getText();

				if (title.isEmpty() || title.equals(textInit)) {
					JOptionPane.showMessageDialog(null, textInit, "알림", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (content.isEmpty() || content.equals(detailInit)) {
					JOptionPane.showMessageDialog(null, detailInit, "알림", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				BoardDTO dto = new BoardDTO();
				dto.setBoardTitle(title);
				dto.setBoardContent(content);
				dto.setBoardFilePath(filePath);

				int cnt = bs.insertBoard(dto);
				if (cnt > 0) {
					JOptionPane.showMessageDialog(null, "작성이 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "오류가 발생하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		setContentPane(contentPane);
	}
}
