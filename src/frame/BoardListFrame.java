package frame;

import java.awt.Color;
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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
		JLabel secondLbl = new JLabel("내 정보 확인");

		// 내 정보 클릭 이벤트 추가
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
//		JScrollPane postScrollPane = new JScrollPane(postListPanel); // 스크롤 가능하게
		postListPanel.setBounds(20, 180, 450, 400);
		add(postListPanel);

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

		// 새로운 라벨 추가
		JLabel TitleLabel = new JLabel("러닝메이트");
		TitleLabel.setFont(new Font("돋움체", Font.BOLD, 35));
		TitleLabel.setBounds(150, 115, 250, 40); // 라벨 위치 및 크기 설정
		add(TitleLabel);

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

			Object[][] boardArray = new Object[list.size()][3];

			for (int i = 0; i < list.size(); i++) {
				BoardDTO post = list.get(i);
				boardArray[i][0] = i + 1; // 순번
				boardArray[i][1] = post.getBoardTitle(); // 게시물 제목
				boardArray[i][2] = post.getUserNickName(); // 작성자 이름
			}

			// JTable 생성
			JTable boardTable = new JTable(
					new DefaultTableModel(boardArray, new String[] { "순번", "제목", "작성자"}));
			boardTable.setRowHeight(45); // 행 높이 설정

			// 칼럼 너비 설정
			boardTable.getColumnModel().getColumn(0).setPreferredWidth(30); // 순번
			boardTable.getColumnModel().getColumn(1).setPreferredWidth(300); // 제목
			boardTable.getColumnModel().getColumn(2).setPreferredWidth(70); // 작성자
			

			// 중앙 정렬 렌더러 설정 (순번과 댓글 수 칼럼에만 적용)
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			boardTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // 순번 칼럼
			boardTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // 작성자 칼럼

			// 테이블 클릭 이벤트 추가 (클릭 시 상세 페이지로 이동)
			boardTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int row = boardTable.getSelectedRow(); // 클릭된 행의 인덱스
					BoardDTO selectedBoard = list.get(row); // 해당 게시물 정보 가져오기
					new BoardDetailFrame(selectedBoard.getBoardId(), 'b'); // 게시물 상세 페이지로 이동
					dispose(); // 현재 창 닫기
				}
			});
			// 테이블을 스크롤 가능하게 설정
			JScrollPane scrollPane = new JScrollPane(boardTable);
			scrollPane.setBounds(20, 180, 350, 400); // 스크롤 패널 위치 및 크기 설정
			postListPanel.add(scrollPane); // 스크롤 패널 추가
		} else {
			// 검색 결과가 없을 경우 메시지 표시
			JOptionPane.showMessageDialog(null, "검색결과가 존재하지 않습니다.");
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
