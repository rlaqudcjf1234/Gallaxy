package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.BoardDTO;
import main.Main;
import service.BoardService;
import service.impl.BoardServiceImpl;

public class myPageBegin extends JFrame {

	BoardService bs = new BoardServiceImpl();

	private JLabel infoLabel; // 정보를 표시할 JLabel 추가
	private List<BoardDTO> posts; // 게시글 목록
	private JPanel postPanel; // 게시글을 표시할 패널

	public myPageBegin() {
		MP();
	}

	public void MP() {
		setTitle("마이페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 850); // 프레임 사이즈
		setLayout(null); // null 레이아웃 사용
		setBackground(new Color(247, 244, 242));
		

		JPanel panel = new JPanel();
		panel.setLayout(null); // 패널도 null 레이아웃 사용
		panel.setBounds(0, 0, 500, 850); // 패널 크기 설정
		panel.setBackground(new Color(247, 244, 242));

		JButton btnBack = new JButton("뒤로가기");
		JButton btnEdit = new JButton("회원정보 변경");
		JButton btnLogout = new JButton("로그아웃");
		
		JButton btnLogo = new JButton();

		ImageIcon logoIcon = new ImageIcon("LogoImage_130x130.png");
		btnLogo.setIcon(logoIcon);
		btnLogo.setBorderPainted(false); // 버튼 테두리 제거
		btnLogo.setContentAreaFilled(false); // 버튼 배경 제거
		btnLogo.setFocusPainted(false); // 포커스 효과 제거
		btnLogo.setBounds(15, 15, 130, 130);// 버튼 위치 및 크기 설정

		btnBack.setSize(90, 25);
		btnBack.setLocation(390, 10); // 하단 위치
		btnBack.setFont(new Font("맑은고딕", Font.BOLD, 11));
		btnBack.setContentAreaFilled(false); // 배경 제거
		btnBack.setBorderPainted(false); // 테두리 제거
		btnBack.setForeground(Color.BLUE);
		
		btnEdit.setSize(110, 25);
		btnEdit.setLocation(310, 10); // btnBack 위에 위치
		btnEdit.setFont(new Font("맑은고딕", Font.BOLD, 11));
		btnEdit.setContentAreaFilled(false); // 배경 제거
		btnEdit.setBorderPainted(false); // 테두리 제거
		btnEdit.setForeground(Color.BLUE);
		
		btnLogout.setSize(110, 25);
		btnLogout.setLocation(230, 10); // btnBack 위에 위치
		btnLogout.setFont(new Font("맑은고딕", Font.BOLD, 11));
		btnLogout.setContentAreaFilled(false); // 배경 제거
		btnLogout.setBorderPainted(false); // 테두리 제거
		btnLogout.setForeground(Color.BLUE);
		
		

		// 정보 표시용 JLabel 설정
		String userId = Main.USER.getUserId();
		String userName = Main.USER.getUserName();
		String userNickName = Main.USER.getUserNickName();
		String userEmail = Main.USER.getUserEmail();

		infoLabel = new JLabel("<html>" + "&emsp;&emsp;&emsp;&emsp; " + userNickName + " (" + userId + ")<br>"
				+ "&emsp;&emsp;&emsp;&emsp; 이름 : " + userName + "<br>" + "&emsp;&emsp;&emsp;&emsp; E-Mail : " + " ("
				+ userEmail + ")<br>" + "</html>");
		infoLabel.setFont(new Font("돋움", Font.BOLD, 18));
		infoLabel.setOpaque(true); // 배경색을 보이게 설정
		infoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 테두리 추가

		// 크기 및 위치 설정
		infoLabel.setBounds(19, 195, 450, 150); // 중앙 정렬 및 크기 설정
		panel.add(infoLabel); // JLabel 추가

		// 게시글 목록 초기화
		posts = new ArrayList<>();
		loadPosts(); // 게시글 로드
		

		// 테이블의 칼럼명 설정
		String[] columnNames = { "순번", "제목", "댓글" };

		// 데이터를 2D 배열로 변환
		Object[][] data = new Object[posts.size()][3];
		for (int i = 0; i < posts.size(); i++) {
			BoardDTO post = posts.get(i);
			data[i][0] = i + 1;
			data[i][1] = post.getBoardTitle();
			data[i][2] = post.getCommentCnt();
		}

		// 테이블 모델 생성
		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		// JTable 생성
		JTable postTable = new JTable(model);
		postTable.setBounds(18, 650, 450, 400); // 테이블 위치 및 크기 설정
		postTable.setRowHeight(45); // 행의 높이 설정
		postTable.getColumnModel().getColumn(0).setPreferredWidth(35); // 순번 칼럼 크기 설정
		postTable.getColumnModel().getColumn(1).setPreferredWidth(385); // 제목 칼럼 크기 설정
		postTable.getColumnModel().getColumn(2).setPreferredWidth(30); // 댓글 수 칼럼 크기 설정

		// 테이블 클릭 이벤트 추가
		postTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = postTable.getSelectedRow(); // 클릭된 행의 인덱스
				BoardDTO post = posts.get(row); // 해당 게시글 가져오기
				new BoardDetailFrame(post);
			}
		});

		// 스크롤 패널을 추가하여 테이블 스크롤 가능하게 설정
		JScrollPane scrollPane = new JScrollPane(postTable);
		scrollPane.setBounds(19, 350, 450, 400); // 스크롤 패널 위치 및 크기 설정
		panel.add(scrollPane); // 패널에 스크롤 패널 추가

		// 버튼 이벤트 설정
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainBoard(); // MainBoard 인스턴스 생성
				dispose(); // 현재 창 닫기
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new myPageEdit();
				// myPageBase.getInstance(new myPageEdit());
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserInForm(); 
				dispose();
			}
		});
		
		panel.add(btnLogo);
		panel.add(btnBack);
		panel.add(btnEdit);
		panel.add(btnLogout);
		add(panel); // 패널을 프레임에 추가
		setVisible(true); // 프레임을 보이도록 설정
	}

	private void loadPosts() {
		

		BoardDTO dto = new BoardDTO();
		dto.setPageSize(10);
		dto.setUserId(Main.USER.getUserId()); // 임시

		List<BoardDTO> list = bs.selectBoardList(dto);

		if (list != null && list.size() > 0) {
			posts = list;
			for (int i = 0; i < 10; i++) {

			}
		} else {
			JLabel emptyPost = new JLabel("작성된 게시글이 없습니다.");
			emptyPost.setForeground(Color.BLACK); // 글자 색상 설정
			emptyPost.setFont(new Font("맑은고딕", Font.BOLD, 18));
			emptyPost.setBounds(140, 400, 450, 50); // 위치 및 크기 설정
			// 패널에 JLabel 추가 (예를 들어 panel은 JPanel입니다)
			add(emptyPost); // panel은 이 메소드에서 사용 중인 JPanel 객체입니다.

		}
		

		
	}
}