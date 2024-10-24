package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import main.Main;
import service.BoardService;
import service.NaverApiService;
import service.RetrieveNewAdress;
import service.impl.BoardServiceImpl;
import service.impl.NaverApiServiceImpl;
import service.impl.RetrieveNewAdressImpl;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class BoardWriteFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6032491971534575326L;

	private BoardService bs = new BoardServiceImpl();
	private NaverApiService nas = new NaverApiServiceImpl();
	private RetrieveNewAdress rna = new RetrieveNewAdressImpl();

	private Map<String, Object> result;
	private String serarch = "";
	private int currentPage = 1;
	private int totalPage;
	private List<String> addrs;

	private String textInit = "제목을 입력하세요";
	private String detailInit = "내용을 입력하세요";

	private String yyyy[] = { "2024년", "2025년" };
	private String mm[] = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
	private String dd[] = { "1일", "2일", "3일", "4일", "5일", "6일", "7일", "8일", "9일", "10일", "11일", "12일", "13일", "14일",
			"15일", "16일", "17일", "18일", "19일", "20일", "21일", "22일", "23일", "24일", "25일", "26일", "27일", "28일", "29일",
			"30일", "31일" };
	private String apm[] = { "오전", "오후" };
	private String hh[] = { "1시", "2시", "3시", "4시", "5시", "6시", "7시", "8시", "9시", "10시", "11시", "12시" };
	private String mi[] = { "00분", "30분" };

	/* BoardWriteFrame */
	private JTextField addressFld;
	private JLabel imageLbl;
	private JTextField textFld;
	private JTextArea detailFld;
	private JComboBox<String> yyyyCbx;
	private JComboBox<String> mmCbx;
	private JComboBox<String> ddCbx;
	private JComboBox<String> apmCbx;
	private JComboBox<String> hhCbx;
	private JComboBox<String> miCbx;
	private String filePath = "temp/서울특별시 종로구 종로12길 15 종로코아.jpg";

	/* BoardSFrame */
	private JFrame searchFrame;
	private JLabel pageLbl;
	private JPanel listPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardWriteFrame frame = new BoardWriteFrame();
					// X버튼 종료
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		// 프레임 타이틀바
		setTitle("러닝 메이트 작성");
		// 프레임 위치, 크기(픽셀)
		setBounds(700, 100, 500, 850);
		// 종료시 프로그램 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		// 배경 흰색
		contentPane.setBackground(Color.white);

		JPanel searchPane = new JPanel();
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		// 배경 흰색
		searchPane.setBackground(Color.white);
		contentPane.add(searchPane);

		JLabel addressLbl = new JLabel("주소");
		searchPane.add(addressLbl);

		addressFld = new JTextField(30);
		searchPane.add(addressFld);

		JButton searchBtn = new JButton("조회");
		searchPane.add(searchBtn);

		// 버튼(searchBtn) 클릭 이벤트
		searchBtn.addActionListener(new ActionListener() {

			@Override
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				serarch = addressFld.getText();
				if (serarch.length() < 2) {
					JOptionPane.showMessageDialog(null, "검색어는 두글자 이상 입력되어야 합니다.", "알림",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if ((result = rna.getAddress(serarch, currentPage = 1)) != null) {
					String errorCode = (String) result.get("errorCode");
					String errorMessage = (String) result.get("errorMessage");

					if (errorCode.equals("0")) {
						totalPage = (int) result.get("totalPage");
						if (totalPage > 0) {
							addrs = (List<String>) result.get("list");
						} else {
							JOptionPane.showMessageDialog(null, "조회 결과가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, errorMessage, "알림", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				BoardSearchFrame();
			}
		});

		JPanel imagePane = new JPanel();
		imagePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		imagePane.setLayout(new BoxLayout(imagePane, BoxLayout.X_AXIS));
		// 배경 흰색
		imagePane.setBackground(Color.white);
		contentPane.add(imagePane);

		imageLbl = new JLabel(new ImageIcon(filePath));
		imagePane.add(imageLbl);

		JPanel titlePane = new JPanel();
		titlePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.X_AXIS));
		// 배경 흰색
		titlePane.setBackground(Color.white);
		contentPane.add(titlePane);

		JLabel textLbl = new JLabel("제목");
		textLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		titlePane.add(textLbl);

		textFld = new JTextField(textInit);
		textFld.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		titlePane.add(textFld);
		
		// 글자수 제한
		textFld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				int max = 30;
				if(textFld.getText().length() > max+1) {
					e.consume();
					String shortened = textFld.getText().substring(0, max);
					textFld.setText(shortened);
				}else if(textFld.getText().length() > max) {
					e.consume();
				}
			}
		});

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
		// 배경 흰색
		datePane.setBackground(Color.white);
		contentPane.add(datePane);

		JLabel dateLbl = new JLabel("일자");
		dateLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		datePane.add(dateLbl);

		Calendar cal = Calendar.getInstance();

		yyyyCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(yyyy));
		// 배경 흰색
		yyyyCbx.setBackground(Color.white);
		datePane.add(yyyyCbx);

		mmCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(mm));
		mmCbx.setSelectedIndex(cal.get(Calendar.MONTH));
		// 배경 흰색
		mmCbx.setBackground(Color.white);
		datePane.add(mmCbx);

		ddCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(dd));
		ddCbx.setSelectedIndex(cal.get(Calendar.DAY_OF_MONTH) - 1);
		// 배경 흰색
		ddCbx.setBackground(Color.white);
		datePane.add(ddCbx);

		JPanel timePane = new JPanel();
		timePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		timePane.setLayout(new BoxLayout(timePane, BoxLayout.X_AXIS));
		// 배경 흰색
		timePane.setBackground(Color.white);
		contentPane.add(timePane);

		JLabel timeLbl = new JLabel("시간");
		timeLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		timePane.add(timeLbl);

		apmCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(apm));
		apmCbx.setSelectedIndex(cal.get(Calendar.AM_PM));
		// 배경 흰색
		apmCbx.setBackground(Color.white);
		timePane.add(apmCbx);

		hhCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(hh));
		hhCbx.setSelectedIndex(cal.get(Calendar.HOUR) - 1);
		// 배경 흰색
		hhCbx.setBackground(Color.white);
		timePane.add(hhCbx);

		miCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(mi));
		// 배경 흰색
		miCbx.setBackground(Color.white);
		timePane.add(miCbx);
		
		JPanel detailPane = new JPanel();
		detailPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		detailPane.setLayout(new BoxLayout(detailPane, BoxLayout.X_AXIS));
		// 배경 흰색
		detailPane.setBackground(Color.white);
		contentPane.add(detailPane);

		JLabel detailLbl = new JLabel("내용");
		detailLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		detailPane.add(detailLbl);

		detailFld = new JTextArea(detailInit);
		detailFld.setRows(17);
		detailFld.setLineWrap(true);
		JScrollPane detailScrolPane = new JScrollPane(detailFld);
		detailPane.add(detailScrolPane);

		// 클릭 이벤트 기본 텍스트(contentFld) 삭제
		detailFld.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (detailFld.getText().equals(detailInit)) {
					detailFld.setText(""); // 클릭 시 텍스트 초기화
				}
			}
		});
		
		// 글자수 제한
		detailFld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				int max = 1000;
				if(detailFld.getText().length() > max+1) {
					e.consume();
					String shortened = detailFld.getText().substring(0, max);
					detailFld.setText(shortened);
				}else if(detailFld.getText().length() > max) {
					e.consume();
				}
			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new EmptyBorder(5, 5, 55, 5));
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		// 배경 흰색
		buttonPane.setBackground(Color.white);
		contentPane.add(buttonPane);

		// 작성 완료 버튼
		JButton writeBtn = new JButton("작성 완료");
		buttonPane.add(writeBtn);
		
		JLabel buttonLbl = new JLabel("      ");
		buttonPane.add(buttonLbl);

		// 작성 취소 버튼
		JButton cancelBtn = new JButton("작성 취소");
		buttonPane.add(cancelBtn);

		// 버튼(writeBtn) 클릭 이벤트
		writeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 작성된 내용 처리
				String sTitle = textFld.getText();
				String sContent = detailFld.getText();
				String sYyyy = yyyy[yyyyCbx.getSelectedIndex()];
				String sMm = mm[mmCbx.getSelectedIndex()];
				String sDd = dd[ddCbx.getSelectedIndex()];
				String sApm = apm[apmCbx.getSelectedIndex()];
				String sHh = hh[hhCbx.getSelectedIndex()];
				String sMi = mi[miCbx.getSelectedIndex()];

				if (sTitle.isEmpty() || sTitle.equals(textInit)) {
					JOptionPane.showMessageDialog(null, textInit, "알림", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (sContent.isEmpty() || sContent.equals(detailInit)) {
					JOptionPane.showMessageDialog(null, detailInit, "알림", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				BoardDTO dto = new BoardDTO();
				dto.setBoardTitle(sTitle);
				dto.setBoardContent(sContent);

				dto.setBoardWordYyyy(sYyyy);
				dto.setBoardWordMm(sMm);
				dto.setBoardWordDd(sDd);

				dto.setBoardWordApm(sApm);
				dto.setBoardWordHh(sHh);
				dto.setBoardWordMi(sMi);

				dto.setBoardFilePath(filePath);

				if (Main.USER != null) {
					dto.setUserId(Main.USER.getUserId());
				}

				int cnt = bs.insertBoard(dto);
				if (cnt > 0) {
					JOptionPane.showMessageDialog(null, "작성이 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
					dispose(); // 현재 프레임 닫기
					new BoardListFrame(); // 게시물 목록 창 열기
				} else {
					JOptionPane.showMessageDialog(null, "오류가 발생하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		// 버튼(cancelBtn) 클릭 이벤트
		cancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new BoardListFrame();
				dispose();
			}
		});

		setContentPane(contentPane);
		setVisible(true);
	}

	/**
	 * Create the frame.
	 * 
	 * @return
	 */
	public void BoardSearchFrame() {

		if (searchFrame != null) {
			searchFrame.dispose();
		}
		searchFrame = new JFrame();
		// 프레임 타이틀바
		searchFrame.setTitle("BoardSearchFrame");
		// 프레임 위치, 크기(픽셀)
		searchFrame.setBounds(1200, 100, 500, 850);
		// 배경 흰색
		searchFrame.setBackground(Color.white);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// 배경 흰색
		contentPane.setBackground(Color.white);

		JPanel pagePane = new JPanel();
		pagePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		pagePane.setLayout(new FlowLayout(FlowLayout.CENTER));
		// 배경 흰색
		pagePane.setBackground(Color.white);
		contentPane.add(pagePane, BorderLayout.NORTH);

		JButton prevButton = new JButton("<");
		pagePane.add(prevButton);

		prevButton.addMouseListener(new MouseAdapter() {

			@Override
			@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent e) {
				if (currentPage == 1) {
					return;
				} else {
					currentPage -= 1;
				}

				if ((result = rna.getAddress(serarch, currentPage)) != null) {
					String errorCode = (String) result.get("errorCode");
					String errorMessage = (String) result.get("errorMessage");

					if (errorCode.equals("0")) {
						totalPage = (int) result.get("totalPage");
						if (totalPage > 0) {
							addrs = (List<String>) result.get("list");
						} else {
							JOptionPane.showMessageDialog(null, "조회 결과가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, errorMessage, "알림", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				/* 조회 목록 갱신 */
				SearchListUpdate();
			}
		});

		pageLbl = new JLabel();
		pageLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		pagePane.add(pageLbl);

		JButton nextButton = new JButton(">");
		pagePane.add(nextButton);

		nextButton.addMouseListener(new MouseAdapter() {

			@Override
			@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent e) {
				if (currentPage == totalPage) {
					return;
				} else {
					currentPage += 1;
				}

				if ((result = rna.getAddress(serarch, currentPage)) != null) {
					String errorCode = (String) result.get("errorCode");
					String errorMessage = (String) result.get("errorMessage");

					if (errorCode.equals("0")) {
						totalPage = (int) result.get("totalPage");
						if (totalPage > 0) {
							addrs = (List<String>) result.get("list");
						} else {
							JOptionPane.showMessageDialog(null, "조회 결과가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, errorMessage, "알림", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				/* 조회 목록 갱신 */
				SearchListUpdate();
			}
		});

		listPane = new JPanel();
		listPane.setLayout(new GridLayout(10, 1));
		// 배경 흰색
		listPane.setBackground(Color.white);
		JScrollPane scrollPane = new JScrollPane(listPane);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		// 배경 흰색
		buttonPane.setBackground(Color.white);
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		// 닫기 버튼
		JButton cancelBtn = new JButton("닫기");
		buttonPane.add(cancelBtn);

		cancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchFrame.dispose();
			}
		});

		/* 조회 목록 갱신 */
		SearchListUpdate();

		searchFrame.setVisible(true);
		searchFrame.setContentPane(contentPane);
	}

	/* 조회 목록 갱신 */
	public void SearchListUpdate() {
		if (addrs != null && addrs.size() > 0) {
			// 목록 전체 삭제
			listPane.removeAll();
			for (String addr : addrs) {

				// 목록 추가
				JPanel optionPane = new JPanel();
				optionPane.setLayout(new BorderLayout());
				// 배경 흰색
				optionPane.setBackground(Color.white);
				listPane.add(optionPane);

				JLabel optionLbl = new JLabel(addr);
				optionPane.add(optionLbl, BorderLayout.WEST);

				JPanel buttonPane = new JPanel();
				buttonPane.setBorder(new EmptyBorder(15, 5, 15, 5));
				// 배경 흰색
				buttonPane.setBackground(Color.white);
				optionPane.add(buttonPane, BorderLayout.EAST);

				JButton addrBtn = new JButton("선택");
				buttonPane.add(addrBtn);

				addrBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						List<AddressDTO> list = nas.getGeocode(addr);
						if (list != null && list.size() > 0) {
							File file = nas.getStatic(list.get(0));
							if (file != null && file.length() > 0) {
								filePath = file.getPath();
								ImageIcon img = new ImageIcon(filePath);
								imageLbl.setIcon(img);
							}
						}
					}
				});
			}
			listPane.revalidate(); // UI 업데이트
			listPane.repaint(); // UI 갱신

			pageLbl.setText(Integer.toString(currentPage) + " / " + Integer.toString(totalPage));
			pageLbl.revalidate(); // UI 업데이트
			pageLbl.repaint(); // UI 갱신
		}
	}
}
