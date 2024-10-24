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
import dto.UserDTO;
import main.Main;
import service.BoardService;
import service.impl.BoardServiceImpl;

public class BoardListFrame extends CommonFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2308679876217604867L;

	private BoardService bs = new BoardServiceImpl();

	private JPanel postListPanel; // 게시물 목록 패널
	private TextField searchField;
	
	public static String search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDTO userDTO = new UserDTO();
					userDTO.setUserId("테스트");
					Main.USER = userDTO;
					BoardListFrame frame = new BoardListFrame();
					// X버튼 종료
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public JLabel setSecondLabel() {
		// TODO Auto-generated method stub
		JLabel secondLbl = new JLabel("마이페이지");

		// 마이페이지 클릭 이벤트 추가
		secondLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MyInfoReadFrame();
				dispose();
			}
		});
		return secondLbl;
	}

	public BoardListFrame() {
		// 영역 넓이 시작 20 ~ 470
		setTitle("러닝 메이트");

		/*
		// 이미지 추가
		JLabel labelLogo = new JLabel(); // 이미지 표시를 위한 Label
		ImageIcon icon = new ImageIcon("gaesipan22.png");
		labelLogo.setIcon(icon); // JLabel에 이미지 설정
		labelLogo.setBounds(100, 120, 250, 40); // 이미지 위치 및 크기 설정 
		add(labelLogo);
		 */

		// 글 작성 버튼 생성
		JButton writeButton = new JButton("글 작성");
		writeButton.setBounds(390, 140, 80, 30);
		writeButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		writeButton.setBackground(Color.LIGHT_GRAY);
		add(writeButton);

		// 글 작성 버튼 클릭 이벤트 추가
		writeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// BoardWriteFrame 열기
				new BoardWriteFrame();
				dispose();
			}
		});

		// 게시물 목록 패널 설정
		postListPanel = new JPanel();
		postListPanel.setLayout(new BoxLayout(postListPanel, BoxLayout.Y_AXIS)); // 세로로 버튼 나열
		JScrollPane postScrollPane = new JScrollPane(postListPanel); // 스크롤 가능하게
		postScrollPane.setBounds(20, 180, 450, 400);
		add(postScrollPane);

		// 글 검색 필드
		searchField = new TextField(); // 제목 입력
		searchField.setBounds(20, 590, 360, 30);
		add(searchField);

		// 검색 버튼 생성
		JButton searchButton = new JButton("검색");
		searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		searchButton.setBackground(Color.LIGHT_GRAY);
		searchButton.setBounds(390, 590, 80, 30);
		add(searchButton);

		// 버튼(searchBtn) 클릭 이벤트
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String search = searchField.getText();
				// 검색 목록 변경
				updateBoardList(search);
			}
		});

		JButton adButton = new JButton();
		adButton.setIcon(new ImageIcon("SolDesk_Ad.png"));
		adButton.setBorderPainted(false); // 버튼 테두리 제거
		adButton.setContentAreaFilled(false); // 버튼 배경 제거
		adButton.setFocusPainted(false); // 포커스 효과 제거
		adButton.setBounds(20, 630, 450, 150); // 버튼 위치 및 크기 설정
		add(adButton);

		// 클릭 이벤트 추가
		adButton.addMouseListener(new MouseAdapter() {
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

		// 화면이 보임
		setVisible(true);
		// 게시물 목록 UI 업데이트
		updateBoardList(search);

	}

	// 게시물 목록을 갱신하는 메서드
	public void updateBoardList(String search) {
		// 기존 게시물 목록 초기화
		postListPanel.removeAll();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setPageSize(100);
		if (search != null && !search.equals("")) {
			boardDTO.setBoardTitle(search);
		}
		List<BoardDTO> list = bs.selectBoardList(boardDTO); // 게시물
		if (list != null && list.size() > 0) {
			for (BoardDTO board : list) {
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
						new BoardDetailFrame(board.getBoardId());
						dispose();
					}
				});

				postListPanel.add(postLabel); // 패널에 게시물 추가
			}
		} else {
			if (search != null && !search.equals("")) {
				JOptionPane.showMessageDialog(null, "검색결과가 존재하지 않습니다.");
			}
		}
		// UI 업데이트
		postListPanel.revalidate();
		// UI 갱신
		postListPanel.repaint();
		// 검색 갱신
		BoardListFrame.search = search;
		searchField.setText(search);
	}

}
