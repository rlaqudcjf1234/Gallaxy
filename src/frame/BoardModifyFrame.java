package frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.AddressDTO;
import dto.BoardDTO;
import service.BoardService;
import service.NaverApiService;
import service.RetrieveNewAdress;
import service.impl.BoardServiceImpl;
import service.impl.NaverApiServiceImpl;
import service.impl.RetrieveNewAdressImpl;

public class BoardModifyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7515798435662293739L;

	private BoardService bs = new BoardServiceImpl();
	private NaverApiService nas = new NaverApiServiceImpl();
	private RetrieveNewAdress rna = new RetrieveNewAdressImpl();

	private String serarch = "";
	private int currentPage = 1;
	private Map<String, Object> result;
	private int totalPage;
	private List<String> addrs;
	private String filePath;
	private BoardDTO dto;

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
					BoardModifyFrame frame = new BoardModifyFrame(31);
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
	public BoardModifyFrame(int boardId) {

		// 대상 조회
		dto = bs.selectBoard(boardId);
		if (dto == null) {
			JOptionPane.showMessageDialog(null, "대상을 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
			dispose();
			return;
		}

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
		contentPane.add(imagePane);

		imageLbl = new JLabel(new ImageIcon(dto.getBoardFilePath()));
		imagePane.add(imageLbl);

		JPanel titlePane = new JPanel();
		titlePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.X_AXIS));
		contentPane.add(titlePane);

		JLabel textLbl = new JLabel("제목");
		textLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		titlePane.add(textLbl);

		textFld = new JTextField(dto.getBoardTitle());
		textFld.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		titlePane.add(textFld);

		JPanel datePane = new JPanel();
		datePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		datePane.setLayout(new BoxLayout(datePane, BoxLayout.X_AXIS));
		contentPane.add(datePane);

		JLabel dateLbl = new JLabel("일자");
		dateLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		datePane.add(dateLbl);

		yyyyCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(yyyy));
		yyyyCbx.setSelectedIndex(Arrays.asList(yyyy).indexOf(dto.getBoardWordYyyy()));
		datePane.add(yyyyCbx);

		mmCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(mm));
		mmCbx.setSelectedIndex(Arrays.asList(mm).indexOf(dto.getBoardWordMm()));
		datePane.add(mmCbx);

		ddCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(dd));
		ddCbx.setSelectedIndex(Arrays.asList(dd).indexOf(dto.getBoardWordDd()));
		datePane.add(ddCbx);

		JPanel timePane = new JPanel();
		timePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		timePane.setLayout(new BoxLayout(timePane, BoxLayout.X_AXIS));
		contentPane.add(timePane);

		JLabel timeLbl = new JLabel("시간");
		timeLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		timePane.add(timeLbl);

		apmCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(apm));
		apmCbx.setSelectedIndex(Arrays.asList(apm).indexOf(dto.getBoardWordApm()));
		timePane.add(apmCbx);

		hhCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(hh));
		hhCbx.setSelectedIndex(Arrays.asList(hh).indexOf(dto.getBoardWordHh()));
		timePane.add(hhCbx);

		miCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(mi));
		miCbx.setSelectedIndex(Arrays.asList(mi).indexOf(dto.getBoardWordMi()));
		timePane.add(miCbx);

		JPanel detailPane = new JPanel();
		detailPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		detailPane.setLayout(new BoxLayout(detailPane, BoxLayout.X_AXIS));
		contentPane.add(detailPane);

		JLabel detailLbl = new JLabel("내용");
		detailLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		detailPane.add(detailLbl);

		detailFld = new JTextArea(dto.getBoardContent());
		detailFld.setColumns(35);
		detailFld.setRows(10);
		detailPane.add(detailFld);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		contentPane.add(buttonPane);

		// 작성 완료 버튼
		JButton writeBtn = new JButton("작성 완료");
		buttonPane.add(writeBtn);

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

				dto.setBoardTitle(sTitle);
				dto.setBoardContent(sContent);

				dto.setBoardWordYyyy(sYyyy);
				dto.setBoardWordMm(sMm);
				dto.setBoardWordDd(sDd);

				dto.setBoardWordApm(sApm);
				dto.setBoardWordHh(sHh);
				dto.setBoardWordMi(sMi);

				dto.setBoardFilePath(filePath);

				dto.setUserId("test");

				int cnt = bs.updateBoard(dto);
				if (cnt > 0) {
					JOptionPane.showMessageDialog(null, "작성이 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);

					List<BoardDTO> updatedBoardList = bs.selectBoardList(new BoardDTO());
					BoardListFrame.updateBoardList(updatedBoardList); // 게시물 목록 갱신

					dispose(); // 현재 프레임 닫기
					BoardListFrame.showBoardList(); // 게시물 목록 창 열기
				} else {
					JOptionPane.showMessageDialog(null, "오류가 발생하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		// 작성 취소 버튼
		JButton cancelBtn = new JButton("작성 취소");
		cancelBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buttonPane.add(cancelBtn);

		cancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		setContentPane(contentPane);
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
		searchFrame.setBounds(600, 100, 500, 850);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel pagePane = new JPanel();
		pagePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		pagePane.setLayout(new FlowLayout(FlowLayout.CENTER));
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
		JScrollPane scrollPane = new JScrollPane(listPane);
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
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
				listPane.add(optionPane);

				JLabel optionLbl = new JLabel(addr);
				optionPane.add(optionLbl, BorderLayout.WEST);

				JPanel buttonPane = new JPanel();
				buttonPane.setBorder(new EmptyBorder(15, 5, 15, 5));
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
