package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.BoardDTO;
import dto.CommentDTO;
import dto.UserDTO;
import main.Main;
import service.BoardService;
import service.CommentService;
import service.impl.BoardServiceImpl;
import service.impl.CommentServiceImpl;

public class MyInfoReadFrame extends CommonFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2435662714493322055L;

	private BoardService bs = new BoardServiceImpl();
	private CommentService cs = new CommentServiceImpl();

	private JLabel infoLabel; // 정보를 표시할 JLabel 추가

	private List<BoardDTO> boardList;
	private List<CommentDTO> commentList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDTO userDTO = new UserDTO();
					userDTO.setUserId("hjs");
					Main.USER = userDTO;
					MyInfoReadFrame frame = new MyInfoReadFrame();
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
		JLabel secondLbl = new JLabel("내 정보 수정");

		// 내 정보 클릭 이벤트 추가
		secondLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame = new JFrame("내 정보 수정");
				frame.setBounds(100, 100, 500, 400);
				frame.setLocation(1200, 100);
				frame.setLayout(null); // null 레이아웃 사용
				frame.setBackground(Color.WHITE);

				JPanel panel = new JPanel();
				panel.setLayout(null); // 패널도 null 레이아웃 사용
				panel.setBounds(0, 0, 500, 400); // 패널 크기 설정
				panel.setBackground(Color.WHITE);

				// 컴포넌트 생성
				JLabel passwordLabel = new JLabel("비밀번호");
				JPasswordField passwordField = new JPasswordField();
				JButton confirmButton = new JButton("확인");
				
				// 위치 및 크기 설정
				passwordLabel.setBounds(110, 120, 200, 30); // (x, y, width, height)
				passwordField.setBounds(170, 120, 200, 30);
				confirmButton.setBounds(90, 170, 300, 30);
				
				// 패널에 컴포넌트 추가
				panel.add(passwordLabel);
				panel.add(passwordField);
				panel.add(confirmButton);
				
				// 프레임에 패널 추가
				frame.add(panel);

				panel.setVisible(true);

				confirmButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String enteredPassword = new String(passwordField.getPassword());
						if (checkPassword(enteredPassword)) {
							frame.dispose(); // 현재 창 닫기
							JFrame myPageEdit = new MyInfoModifyFrame();
							myPageEdit.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosed(WindowEvent e) {
									// TODO Auto-generated method stub
									if (Main.USER != null) {
										String userId = Main.USER.getUserId();
										String userName = Main.USER.getUserName();
										String userNickName = Main.USER.getUserNickName();
										String userEmail = Main.USER.getUserEmail();

										String html = "<html>" + "&emsp;&emsp;&emsp;&emsp; " + userNickName + " ("
												+ userId + ")<br>" + "&emsp;&emsp;&emsp;&emsp; 이름 : " + userName
												+ "<br>" + "&emsp;&emsp;&emsp;&emsp; E-Mail : " + " (" + userEmail
												+ ")<br>" + "</html>";
										infoLabel.setText(html);
									}

								}
							});
						} else {
							JOptionPane.showMessageDialog(frame, "비밀번호가 틀렸습니다.", "오류", JOptionPane.ERROR_MESSAGE);
						}
					}
				});

			

				frame.setVisible(true);
				// new myPageEdit();
				// myPageBase.getInstance(new myPageEdit());
			}
		});
		return secondLbl;
	}

	public MyInfoReadFrame() {
		// 영역 넓이 시작 20 ~ 470
		setTitle("내 정보");

		// 정보 표시용 JLabel 설정
		String userId = Main.USER.getUserId();
		String userName = Main.USER.getUserName();
		String userNickName = Main.USER.getUserNickName();
		String userEmail = Main.USER.getUserEmail();

		infoLabel = new JLabel("<html>" + "&emsp;&emsp;&emsp;&emsp; " + userNickName + " (" + userId + ")<br>"
				+ "&emsp;&emsp;&emsp;&emsp; 이름 : " + userName + "<br>" + "&emsp;&emsp;&emsp;&emsp; E-Mail : " + " ("
				+ userEmail + ")<br>" + "</html>");
		infoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		infoLabel.setOpaque(true); // 배경색을 보이게 설정
		infoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 테두리 추가

		// 크기 및 위치 설정
		infoLabel.setBounds(20, 150, 450, 150); // 중앙 정렬 및 크기 설정
		add(infoLabel); // JLabel 추가
		
		  JLabel subTitle = new JLabel("내 정보");
			JLabel myWriting = new JLabel("-내가 쓴 게시글");
			JLabel comments = new JLabel("-내가 쓴 댓글");
			
			subTitle.setFont(new Font("돋움체", Font.BOLD, 38));
			myWriting.setFont(new Font("돋움체", Font.BOLD, 16));
			comments.setFont(new Font("돋움체", Font.BOLD, 16));
			
			subTitle.setBounds(181, 76, 200, 100);
			myWriting.setBounds(20, 276, 200, 100);
			comments.setBounds(20, 516, 200, 100);
			
			 add(subTitle);
		     add(myWriting);
		     add(comments);
		     
		     // JFrame 표시
		     setVisible(true);

		

		// 게시글 불러오기
		Object[][] boardArray = getBoardList(); 

		// 게시글 목록 초기화
		if (boardArray == null) {
			JLabel emptyPost = new JLabel("작성된 게시글이 없습니다.");
			emptyPost.setForeground(Color.BLACK); // 글자 색상 설정
			emptyPost.setFont(new Font("맑은고딕", Font.BOLD, 18));
			emptyPost.setBounds(140, 425, 270, 50); // 위치 및 크기 설정
			// 패널에 JLabel 추가 (예를 들어 panel은 JPanel입니다)
			add(emptyPost,  BorderLayout.CENTER); // panel은 이 메소드에서 사용 중인 JPanel 객체입니다.
		}

		// 가운데 정렬 렌더러 설정
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER); // 가운데 정렬 설정

		// JTable 생성
		JTable boardTable = new JTable(new DefaultTableModel(boardArray, new String[]{ "순번", "제목", "댓글" }));
		boardTable.setRowHeight(45); // 행의 높이 설정
		boardTable.getColumnModel().getColumn(0).setPreferredWidth(35); // 순번 칼럼 크기 설정
		boardTable.getColumnModel().getColumn(1).setPreferredWidth(385); // 제목 칼럼 크기 설정
		boardTable.getColumnModel().getColumn(2).setPreferredWidth(30); // 댓글 수 칼럼 크기 설정
		// 0번째(순번)와 2번째(댓글 수) 칼럼에 렌더러 적용
		boardTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // 0번째 칼럼 (순번)
		boardTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // 2번째 칼럼 (댓글 수)

		// 테이블 클릭 이벤트 추가
		boardTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = boardTable.getSelectedRow(); // 클릭된 행의 인덱스
				BoardDTO dto = boardList.get(row); // 해당 게시글 가져오기
				new BoardDetailFrame(dto.getBoardId(), 'm');
				dispose();
			}
		});

		// 스크롤 패널을 추가하여 테이블 스크롤 가능하게 설정
		JScrollPane boardScrollPane = new JScrollPane(boardTable);
		boardScrollPane.setBounds(20, 340, 450, 200); // 스크롤 패널 위치 및 크기 설정
		add(boardScrollPane); // 패널에 스크롤 패널 추가
		
		// 댓글 불러오기
		Object[][] commentArray = getCommentList(); 

		// 댓글 목록 초기화
		if (commentArray == null) {
			JLabel emptyPost = new JLabel("작성된 댓글이 없습니다.");
			emptyPost.setForeground(Color.BLACK); // 글자 색상 설정
			emptyPost.setFont(new Font("맑은고딕", Font.BOLD, 18));
			emptyPost.setBounds(145, 660, 220, 50); // 위치 및 크기 설정
			// 패널에 JLabel 추가 (예를 들어 panel은 JPanel입니다)
			add(emptyPost,  BorderLayout.CENTER); // panel은 이 메소드에서 사용 중인 JPanel 객체입니다.
		}

		// JTable 생성
		JTable commentTable = new JTable(new DefaultTableModel(commentArray, new String[]{ "순번", "댓글" }));
		commentTable.setRowHeight(45); // 행의 높이 설정
		commentTable.getColumnModel().getColumn(0).setPreferredWidth(35); // 순번 칼럼 크기 설정
		commentTable.getColumnModel().getColumn(1).setPreferredWidth(385); // 제목 칼럼 크기 설정
		// 0번째(순번)와 2번째(댓글 수) 칼럼에 렌더러 적용
		commentTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // 0번째 칼럼 (순번)

		// 테이블 클릭 이벤트 추가
		commentTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = commentTable.getSelectedRow(); // 클릭된 행의 인덱스
				CommentDTO dto = commentList.get(row); // 해당 게시글 가져오기
				new BoardDetailFrame(dto.getBoardId(), 'm');
				dispose();
			}
		});

		// 스크롤 패널을 추가하여 테이블 스크롤 가능하게 설정
		JScrollPane commentScrollPane = new JScrollPane(commentTable);
		commentScrollPane.setBounds(20, 580, 450, 200); // 스크롤 패널 위치 및 크기 설정
		add(commentScrollPane); // 패널에 스크롤 패널 추가
	}

	private boolean checkPassword(String password) {
		String correctPassword = Main.USER.getUserPw();
		return password.equals(correctPassword);
	}

	private Object[][] getBoardList() {
		Object[][] boardArray = null;

		try {
			BoardDTO dto = new BoardDTO();
			dto.setPageSize(5);
			dto.setUserId(Main.USER.getUserId());

			boardList = bs.selectBoardList(dto);

			if (boardList != null && boardList.size() > 0) {
				boardArray = new Object[boardList.size()][3];
				for (int i = 0; i < 10; i++) {
					BoardDTO post = boardList.get(i);
					boardArray[i][0] = i + 1;
					boardArray[i][1] = post.getBoardTitle();
					boardArray[i][2] = post.getCommentCnt();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}

		return boardArray;
	}

	private Object[][] getCommentList() {
		Object[][] commentArray = null;

		try {
			CommentDTO dto = new CommentDTO();
			dto.setPageSize(5);
			dto.setCommentOrder(false);
			dto.setUserId(Main.USER.getUserId());

			commentList = cs.selectCommentList(dto);

			if (commentList != null && commentList.size() > 0) {
				commentArray = new Object[commentList.size()][3];
				for (int i = 0; i < 10; i++) {
					CommentDTO post = commentList.get(i);
					commentArray[i][0] = i + 1;
					commentArray[i][1] = post.getCommentContent();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}

		return commentArray;
	}
}