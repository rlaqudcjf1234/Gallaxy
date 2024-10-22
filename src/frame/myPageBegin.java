package frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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
	private List<Post> posts; // 게시글 목록
	private JPanel postPanel; // 게시글을 표시할 패널

	public myPageBegin() {
		MP();
	}

	public void MP() {
		setTitle("마이페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 850); // 프레임 사이즈
		setLayout(null); // null 레이아웃 사용

		JPanel panel = new JPanel();
		panel.setLayout(null); // 패널도 null 레이아웃 사용
		panel.setBounds(0, 0, 500, 850); // 패널 크기 설정

		JButton btnBack = new JButton("뒤로가기");
		JButton btnEdit = new JButton("회원정보 변경/ 로그아웃");

		btnBack.setSize(450, 35);
		btnBack.setLocation(25, 700); // 하단 위치

		btnBack.setFont(new Font("굴림", Font.BOLD, 14));
		btnEdit.setSize(450, 35);
		btnEdit.setLocation(25, 650); // btnBack 위에 위치

		btnEdit.setFont(new Font("굴림", Font.BOLD, 14));

		// 정보 표시용 JLabel 설정
		String userId = Main.USER.getUserId();
		String userName = Main.USER.getUserName();
		String userNickName = Main.USER.getUserNickName();
		String userEmail = Main.USER.getUserEmail();
		
		infoLabel = new JLabel("<html>"
				+ "&emsp;&emsp;&emsp;&emsp; "+ userNickName + " (" + userId + ")<br>"
				+ "&emsp;&emsp;&emsp;&emsp; 이름 : "+ userName +"<br>" 
				+ "&emsp;&emsp;&emsp;&emsp; E-Mail : "+ " (" + userEmail + ")<br>"+ "</html>");
		infoLabel.setFont(new Font("돋움", Font.BOLD, 18));
		infoLabel.setOpaque(true); // 배경색을 보이게 설정
		infoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 테두리 추가

		// 크기 및 위치 설정
		infoLabel.setBounds(25, 50, 450, 150); // 중앙 정렬 및 크기 설정
		panel.add(infoLabel); // JLabel 추가


		// 게시글 목록 초기화
        posts = new ArrayList<>();
        loadPosts(); // 게시글 로드

        // 테이블의 칼럼명 설정
        String[] columnNames = { "제목","댓글 수" };

        // 데이터를 2D 배열로 변환
        Object[][] data = new Object[posts.size()][2];
        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            data[i][0] = post.getTitle();        // 제목
            data[i][1] = post.getCommentCount(); // 댓글 수
        }

        // 테이블 모델 생성
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // JTable 생성
        JTable postTable = new JTable(model);
        postTable.setBounds(25, 220, 450, 400); // 테이블 위치 및 크기 설정
        postTable.setRowHeight(45); // 행의 높이 설정
        postTable.getColumnModel().getColumn(0).setPreferredWidth(330); // 제목 칼럼 크기 설정
        postTable.getColumnModel().getColumn(1).setPreferredWidth(30); // 댓글 수 칼럼 크기 설정

        // 테이블 클릭 이벤트 추가
        postTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = postTable.getSelectedRow(); // 클릭된 행의 인덱스
                Post selectedPost = posts.get(row); // 해당 게시글 가져오기
                openPostDetail(selectedPost); // 상세 페이지 열기
            }

			private void openPostDetail(Post selectedPost) {
				// TODO Auto-generated method stub
				
			}
        });

        // 스크롤 패널을 추가하여 테이블 스크롤 가능하게 설정
        JScrollPane scrollPane = new JScrollPane(postTable);
        scrollPane.setBounds(25, 220, 450, 400); // 스크롤 패널 위치 및 크기 설정
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

		panel.add(btnBack);
		panel.add(btnEdit);
		add(panel); // 패널을 프레임에 추가
		setVisible(true); // 프레임을 보이도록 설정
	}

	private void loadPosts() {
		
		BoardDTO dto = new BoardDTO();
		dto.setPageSize(5);
		dto.setUserId(Main.USER.getUserId());
		
		List<BoardDTO> list = bs.selectBoardList(dto);
		
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++) {
				// 게시글 데이터 추가
				posts.add(new Post(i+1+"", list.get(i).getBoardTitle()));
				
				 // 게시글을 JLabel로 추가
	            JLabel postLabel = new JLabel((i+1) + ". " + list.get(i).getBoardTitle());
            postLabel.setBounds(10, i * 30, 430, 30);  // 각 게시글 위치 및 크기 설정
	            postPanel.add(postLabel);  // 게시글을 패널에 추가
			}	
		} else {
			posts.add(new Post("                             ---- 작성된 게시글이 없습니다 -----" ,""));
	         
			
		}
		 
	}

	public static void main(String[] args) {
		new myPageBegin(); // myPageBegin 클래스의 인스턴스를 생성하여 표시
	}
}

// Post 클래스
class Post {
	private String title;
	private String content;

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public Object getCommentCount() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
}
