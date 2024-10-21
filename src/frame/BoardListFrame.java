package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

	private static JPanel postListPanel = new JPanel(); // 게시물 목록 패널
	private Frame mainFrame;

	// 게시물 목록을 갱신하는 메서드
	public static void updateBoardList(List<BoardDTO> boardList) {
		postListPanel.removeAll(); // 기존 게시물 목록 초기화
		for (BoardDTO board : boardList) {
			JButton postButton = new JButton(board.getBoardTitle()); // 게시물 제목으로 버튼 생성
			postButton.setHorizontalAlignment(SwingConstants.LEFT); // 버튼 내 글자 정렬
			postButton.setBorderPainted(false); // 테두리 제거
			postButton.setFocusPainted(false); // 포커스 제거
			postButton.setBackground(Color.WHITE); // 배경 색

			postButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// 내용 상세
					new BoardDetailFrame(board);
				}
			});

			postListPanel.add(postButton); // 패널에 게시물 추가
		}
		postListPanel.revalidate(); // UI 업데이트
		postListPanel.repaint(); // UI 갱신
	}

	public static void showBoardList() {

		BoardService bs = new BoardServiceImpl();

		BoardDTO boardDTO = new BoardDTO();

		// 프로그램 시작 시 게시물 목록 불러오기
		List<BoardDTO> boardList = bs.selectBoardList(new BoardDTO()); // 게시물 리스트 가져오기
		updateBoardList(boardList); // 게시물 목록 UI 업데이트

		Frame mainFrame = new Frame("러닝 메이트 게시판");
		mainFrame.setBounds(700, 100, 500, 850); // 위치와 크기
		mainFrame.setBackground(new Color(247, 244, 242)); // 배경 색
		mainFrame.setLayout(null); // 절대 레이아웃 사용

		// 로고 버튼 생성
		JButton btnLogo = new JButton();

		ImageIcon logoIcon = new ImageIcon("Running Mate.png");
		btnLogo.setIcon(logoIcon);
		btnLogo.setBorderPainted(false); // 버튼 테두리 제거
		btnLogo.setContentAreaFilled(false); // 버튼 배경 제거
		btnLogo.setFocusPainted(false); // 포커스 효과 제거
		btnLogo.setBounds(30, 50, 110, 95); // 버튼 위치 및 크기 설정
		// 클릭 이벤트 추가
		btnLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "메인화면!");
			}
		});
		// 글 작성 버튼 생성
		JButton btnWrite = new JButton("글 작성");

		// 초기 버튼 위치 및 크기 설정 (기준값)
		btnWrite.setBounds(360, 170, 80, 30);
		btnWrite.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		btnWrite.setBackground(Color.LIGHT_GRAY);

		// 프레임에 버튼 추가
		mainFrame.add(btnWrite);
		mainFrame.add(btnLogo);

		// 이미지 추가
		JLabel labelLogo = new JLabel(); // 이미지 표시를 위한 Label
		ImageIcon icon = new ImageIcon("gaesipan11.png");
		labelLogo.setIcon(icon); // JLabel에 이미지 설정
		labelLogo.setBounds(120, 130, 230, 80); // 이미지 위치 및 크기 설정

		// 프레임에 Label 추가
		mainFrame.add(labelLogo);

		// 글 작성 버튼 클릭 이벤트 추가
		btnWrite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// BoardWriteFrame 열기
				BoardWriteFrame writeFrame = new BoardWriteFrame();
				writeFrame.setVisible(true);
				mainFrame.dispose();
			}
		});

		// 글 검색 필드  
		TextField contentSearch = new TextField("검색할 글 제목을 입력하세요"); // 제목 입력
		contentSearch.setBounds(50, 640, 300, 30);

		// 검색 버튼 생성
		JButton searchButton = new JButton("검색");
		searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		searchButton.setBackground(Color.LIGHT_GRAY);
		searchButton.setBounds(360, 640, 80, 30);

		// 프레임에 검색 버튼 추가
		mainFrame.add(searchButton);

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
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {

				String content = contentSearch.getText();
				if (content.equals("") || content.equals("검색할 글 제목을 입력하세요")) {
					// 검색어 없음 경고
					JOptionPane.showMessageDialog(mainFrame, "검색어를 입력하지 않았습니다.");
					
					return; 
				}

				boardDTO.setBoardTitle(content);

				List<BoardDTO> list = bs.selectBoardList(boardDTO);
				if (list.equals(null) || list.isEmpty()) {
					// 검색 결과 없음
					JOptionPane.showMessageDialog(mainFrame, "검색결과가 존재하지 않습니다.");
					
				} else {
					// 검색 목록 변경
					JOptionPane.showMessageDialog(mainFrame, "테스트");
					
				}

			}
		});

		mainFrame.add(contentSearch);

		// 게시물 목록 패널 설정
		postListPanel.setLayout(new BoxLayout(postListPanel, BoxLayout.Y_AXIS)); // 세로로 버튼 나열
		JScrollPane scrollPane = new JScrollPane(postListPanel); // 스크롤 가능하게
		scrollPane.setBounds(50, 220, 400, 400);
		mainFrame.add(scrollPane);

		// 로그아웃 텍스트 추가
		JLabel logoutLabel = new JLabel("로그아웃");
		logoutLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		logoutLabel.setBounds(260, 50, 100, 40); // 위치와 크기 조정
		logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 로그아웃 클릭 이벤트 추가
		logoutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "로그아웃 합니다!");
			}
		});

		mainFrame.add(logoutLabel); // 프레임에 마이페이지 텍스트 추가

		// 로그아웃 텍스트 추가
		JLabel myPageLabel = new JLabel("마이페이지");
		myPageLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		myPageLabel.setBounds(330, 50, 100, 40); // 위치와 크기 조정
		myPageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 로그아웃 클릭 이벤트 추가
		myPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "마이페이지로 이동!");
			}
		});

		mainFrame.add(myPageLabel); // 프레임에 마이페이지 텍스트 추가

		// 메인화면 텍스트 추가
		JLabel mainPageLabel = new JLabel("메인화면");
		mainPageLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		mainPageLabel.setBounds(415, 50, 100, 40); // 위치와 크기 조정
		mainPageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 로그아웃 클릭 이벤트 추가
		mainPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "메인화면으로 이동!");
			}
		});

		mainFrame.add(mainPageLabel); // 프레임에 메인화면 텍스트 추가

		// 화면이 보임
		mainFrame.setVisible(true);

		// 종료 -----------------------------------------
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			}
		});

	}

//	public static void main(String[] args) {
//		showBoardList();
//	}

}
