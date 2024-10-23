package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import dto.BoardDTO;
import service.BoardService;
import service.impl.BoardServiceImpl;

public class BoardListFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2308679876217604867L;

	private BoardService bs = new BoardServiceImpl();

	private JPanel postListPanel = new JPanel(); // 게시물 목록 패널

	// 상세보기 영역
	private JFrame detailFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardListFrame frame = new BoardListFrame();
					// X버튼 종료
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BoardListFrame() {

		BoardDTO boardDTO = new BoardDTO();
		// 프로그램 시작 시 게시물 목록 불러오기
		boardDTO.setPageSize(100);
		List<BoardDTO> boardList = bs.selectBoardList(boardDTO); // 게시물 리스트 가져오기
		updateBoardList(boardList); // 게시물 목록 UI 업데이트

		setBounds(700, 100, 500, 850); // 위치와 크기
		getContentPane().setLayout(null); // 절대 레이아웃 사용
		getContentPane().setBackground(Color.WHITE);

		// 로고 버튼 생성
		JButton btnLogo = new JButton();

		ImageIcon logoIcon = new ImageIcon("LogoImage_130x130.png");
		btnLogo.setIcon(logoIcon);
		btnLogo.setBorderPainted(false); // 버튼 테두리 제거
		btnLogo.setContentAreaFilled(false); // 버튼 배경 제거
		btnLogo.setFocusPainted(false); // 포커스 효과 제거
		btnLogo.setBounds(20, 0, 130, 130); // 버튼 위치 및 크기 설정
		add(btnLogo);
		
		// 클릭 이벤트 추가
		btnLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "메인화면!");
				new MainBoard();
				dispose();
			}
		});

		// 로그아웃 텍스트 추가
		JLabel logoutLabel = new JLabel("로그아웃");
		logoutLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		logoutLabel.setBounds(260, 20, 100, 40); // 위치와 크기 조정
		logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 로그아웃 클릭 이벤트 추가
		logoutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UserInForm();
				dispose();
			}
		});

		add(logoutLabel); // 프레임에 마이페이지 텍스트 추가

		// 마이페이지 텍스트 추가
		JLabel myPageLabel = new JLabel("마이페이지");
		myPageLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		myPageLabel.setBounds(330, 20, 100, 40); // 위치와 크기 조정
		myPageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 마이페이지 클릭 이벤트 추가
		myPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new myPageBegin();
				dispose();
			}
		});

		add(myPageLabel); // 프레임에 마이페이지 텍스트 추가

		// 메인화면 텍스트 추가
		JLabel mainPageLabel = new JLabel("메인화면");
		mainPageLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		mainPageLabel.setBounds(415, 20, 100, 40); // 위치와 크기 조정
		mainPageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 메인화면 클릭 이벤트 추가
		mainPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainBoard();
				dispose();
			}
		});
		add(mainPageLabel); // 프레임에 메인화면 텍스트 추가

		// 이미지 추가
		JLabel labelLogo = new JLabel(); // 이미지 표시를 위한 Label
		ImageIcon icon = new ImageIcon("gaesipan22.png");
		labelLogo.setIcon(icon); // JLabel에 이미지 설정
		labelLogo.setBounds(100, 120, 250, 40); // 이미지 위치 및 크기 설정
		add(labelLogo);
		
		// 글 작성 버튼 생성
		JButton btnWrite = new JButton("글 작성");
		btnWrite.setBounds(370, 130, 80, 30);
		btnWrite.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		btnWrite.setBackground(Color.LIGHT_GRAY);
		add(btnWrite);

		// 글 작성 버튼 클릭 이벤트 추가
		btnWrite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// BoardWriteFrame 열기
				new BoardWriteFrame();
				dispose();
			}
		});
		// 글 검색 필드
		TextField contentSearch = new TextField("검색할 글 제목을 입력하세요"); // 제목 입력
		contentSearch.setBounds(50, 600, 300, 30);

		// 검색 버튼 생성
		JButton searchButton = new JButton("검색");
		searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		searchButton.setBackground(Color.LIGHT_GRAY);
		searchButton.setBounds(360, 600, 80, 30);

		// 프레임에 검색 버튼 추가
		add(searchButton);

		contentSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (contentSearch.getText().equals("검색할 글 제목을 입력하세요")) {
					contentSearch.setText(""); // 클릭 시 텍스트 초기화
				}
			}
		});

		// 버튼(searchBtn) 클릭 이벤트
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String content = contentSearch.getText();

				boardDTO.setBoardTitle(content);

				List<BoardDTO> list = bs.selectBoardList(boardDTO);

				if (list.equals(null) || list.isEmpty()) {
					// 검색 결과 없음
					JOptionPane.showMessageDialog(null, "검색결과가 존재하지 않습니다.");

				} else {
					// 검색 목록 변경
					updateBoardList(list);
				}

			}
		});

		add(contentSearch);

		JButton btnAd = new JButton();

		ImageIcon logoIcon2 = new ImageIcon("SolDesk_Ad.png");
		btnAd.setIcon(logoIcon2);
		btnAd.setBorderPainted(false); // 버튼 테두리 제거
		btnAd.setContentAreaFilled(false); // 버튼 배경 제거
		btnAd.setFocusPainted(false); // 포커스 효과 제거
		btnAd.setBounds(20, 605, 450, 240); // 버튼 위치 및 크기 설정
		// 클릭 이벤트 추가
		btnAd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String url = "https://soldesk.com/";

				try {
					// URI로 변환하고 기본 웹 브라우저에서 열기
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException | URISyntaxException ex) {
					ex.printStackTrace(); // 예외 처리
				}
			}
		});
		add(btnAd);

		// 게시물 목록 패널 설정
		postListPanel.setLayout(new BoxLayout(postListPanel, BoxLayout.Y_AXIS)); // 세로로 버튼 나열
		JScrollPane scrollPane = new JScrollPane(postListPanel); // 스크롤 가능하게
		scrollPane.setBounds(50, 170, 400, 400);
		add(scrollPane);

		// 화면이 보임
		setVisible(true);

		// 종료 -----------------------------------------
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			}
		});

	}
	
	// 게시물 목록을 갱신하는 메서드
	public void updateBoardList(List<BoardDTO> boardList) {
		postListPanel.removeAll(); // 기존 게시물 목록 초기화
		for (BoardDTO board : boardList) {
			JLabel postLabel = new JLabel(board.getBoardTitle()); // 게시물 제목으로 JLabel 생성
			postLabel.setHorizontalAlignment(SwingConstants.LEFT); // 텍스트 정렬
			postLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서
			postLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 글씨 크기 설정

			postLabel.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					postLabel.setText("<html><u>" + board.getBoardTitle() + "</u></html>"); // 밑줄 효과
				}

				@Override
				public void mouseExited(MouseEvent e) {
					postLabel.setText(board.getBoardTitle()); // 기본 상태로 되돌림
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// 내용 상세

					if (detailFrame != null) {
						detailFrame.dispose();
					}
					detailFrame = new BoardDetailFrame(board);
				}
			});

			postListPanel.add(postLabel); // 패널에 게시물 추가
		}
		postListPanel.revalidate(); // UI 업데이트
		postListPanel.repaint(); // UI 갱신
	}

}
