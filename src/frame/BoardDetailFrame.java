package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.BoardDTO;
import dto.CommentDTO;
import dto.UserDTO;
import main.Main;
import service.BoardService;
import service.CommentService;
import service.impl.BoardServiceImpl;
import service.impl.CommentServiceImpl;

public class BoardDetailFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1730899176307489779L;

	private BoardService bs = new BoardServiceImpl();
	private CommentService cs = new CommentServiceImpl();

	// 게시물 정보를 표시할 라벨과 텍스트 영역
	private BoardDTO dto;

	private JTextArea contentArea;
	private JLabel imageLabel;
	private JTextField commentField;
	private JPanel commentPanel;

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
					
					BoardDetailFrame frame = new BoardDetailFrame(1);
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
	public BoardDetailFrame(int boardId) {

		// 게시물 내용 조회
		dto = bs.selectBoard(boardId);
		// 게시물이 없을 경우 list페이지로
		if (dto == null) {
			JOptionPane.showMessageDialog(null, "대상을 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
			new BoardListFrame();
			dispose();
			return;
		}
		// 프레임 타이틀바
		setTitle("러닝 메이트 보기");
		// 프레임 위치, 크기(픽셀)
		setBounds(700, 100, 500, 850);
		// 종료시 프로그램 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JPanel contentPane = new JPanel();
		// 배경 흰색
		contentPane.setBackground(Color.white);
		contentPane.setLayout(null);

		// 게시물 이미지가 있는 경우 표시
		if (dto.getBoardFilePath() != null && !dto.getBoardFilePath().isEmpty()) {
			ImageIcon img = new ImageIcon(dto.getBoardFilePath());
			imageLabel = new JLabel(img);
			imageLabel.setBounds(10, 20, 460, 250); // 이미지 위치 설정
			contentPane.add(imageLabel);
		}

		// 제목 라벨 추가
		JLabel titleLabel = new JLabel("제목:"); // 제목 앞에 라벨 추가
		titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		titleLabel.setBounds(30, 290, 50, 20); // 제목 라벨 위치 설정
		contentPane.add(titleLabel);

		// 제목을 표시할 텍스트 필드
		JTextField titleField = new JTextField(dto.getBoardTitle());
		titleField.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		titleField.setBounds(85, 280, 370, 40); // 제목 위치 설정
		titleField.setEditable(false); // 제목 수정 불가
		titleField.setBorder(null);
		// 배경 흰색
		titleField.setBackground(Color.white);
		contentPane.add(titleField);

		// 작성자 라벨
		JLabel authorLabel = new JLabel("작성자:");
		authorLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		authorLabel.setBounds(30, 320, 50, 20);
		contentPane.add(authorLabel);

		// 작성자를 표시할 텍스트 필드
		JTextField authorField = new JTextField(dto.getUserNickName());
		authorField.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		authorField.setBounds(85, 310, 370, 40); // 작성자 위치 설정
		authorField.setEditable(false); // 작성자 수정 불가
		authorField.setBorder(null);
		// 배경 흰색
		authorField.setBackground(Color.white);
		contentPane.add(authorField);

		// 날짜 라벨
		JLabel dateLabel = new JLabel("날짜:");
		dateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		dateLabel.setBounds(30, 350, 50, 20);
		contentPane.add(dateLabel);

		// 날짜를 표시할 텍스트 필드
		JTextField dateField = new JTextField(
				dto.getBoardWordYyyy() + " " + dto.getBoardWordMm() + " " + dto.getBoardWordDd());
		dateField.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		dateField.setBounds(85, 340, 200, 40); // 날짜 위치 설정
		dateField.setEditable(false); // 날짜 수정 불가
		dateField.setBorder(null);
		// 배경 흰색
		dateField.setBackground(Color.white);
		contentPane.add(dateField);

		// 시간 라벨
		JLabel timeLabel = new JLabel("시간:");
		timeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		timeLabel.setBounds(30, 380, 50, 20);
		contentPane.add(timeLabel);

		// 시간을 표시할 텍스트 필드
		JTextField timeField = new JTextField(
				dto.getBoardWordApm() + " " + dto.getBoardWordHh() + " " + dto.getBoardWordMi());
		timeField.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		timeField.setBounds(85, 370, 200, 40); // 시간 위치 설정
		timeField.setEditable(false); // 시간 수정 불가
		timeField.setBorder(null);
		// 배경 흰색
		timeField.setBackground(Color.white);
		contentPane.add(timeField);

		if (Main.USER != null && Main.USER.getUserId().equals(dto.getUserId())) {
			// 수정 버튼
			JButton modifyButton = new JButton("수정");
			modifyButton.setBounds(300, 380, 70, 30);
			contentPane.add(modifyButton);

			// 수정 버튼 클릭 이벤트 추가
			modifyButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					// BoardModifyFrame 열기
					new BoardModifyFrame(dto.getBoardId());
					dispose();
				}
			});
		}

		// 목록 버튼
		JButton returnButton = new JButton("목록");
		returnButton.setBounds(380, 380, 70, 30);
		contentPane.add(returnButton);

		// 목록 버튼 클릭 이벤트 추가
		returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// BoardListFrame 열기
				new BoardListFrame();
				dispose();
			}
		});
		
		// 게시물 내용을 표시하는 텍스트 영역
		contentArea = new JTextArea(dto.getBoardContent());
		contentArea.setWrapStyleWord(true);
		contentArea.setLineWrap(true);
		contentArea.setEditable(false); // 내용 수정 불가
		contentArea.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

		JScrollPane scrollPane = new JScrollPane(contentArea);
		scrollPane.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		scrollPane.setBounds(25, 420, 425, 200);
		contentPane.add(scrollPane);

		// 댓글 패널
		commentPanel = new JPanel();
		commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
		JScrollPane commentScrollPane = new JScrollPane(commentPanel);
		commentScrollPane.setBounds(25, 630, 425, 100);
		contentPane.add(commentScrollPane);

		// 댓글 입력 필드
		commentField = new JTextField();
		commentField.setBounds(25, 740, 350, 30);
		contentPane.add(commentField);

		// 글자수 제한
		commentField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				int max = 30;
				if (commentField.getText().length() > max + 1) {
					e.consume();
					String shortened = commentField.getText().substring(0, max);
					commentField.setText(shortened);
				} else if (commentField.getText().length() > max) {
					e.consume();
				}
			}
		});

		// 댓글 버튼
		JButton commentButton = new JButton("작성");
		commentButton.setBounds(380, 740, 70, 30);
		contentPane.add(commentButton);

		commentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String comment = commentField.getText();
				if (!comment.trim().isEmpty()) {
					/*
					 * comments.add(comment); // 댓글 저장
					 */
					CommentDTO commentDTO = new CommentDTO();
					commentDTO.setBoardId(dto.getBoardId());
					commentDTO.setCommentContent(comment);
					if (Main.USER != null) {
						dto.setUserId(Main.USER.getUserId());
					}

					int cnt = cs.insertComment(commentDTO);
					if (cnt == 0) {
						// 뎃글 등록 실패 알림 추가
						return;
					}
					updateCommentPanel(); // 댓글 목록 갱신
					commentField.setText(""); // 입력 필드 초기화
				} else {
					JOptionPane.showMessageDialog(null, "댓글을 입력하세요!");
				}
			}
		});

		// 버튼 패널 추가
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));

		updateCommentPanel();

		contentPane.add(buttonPane);

		setContentPane(contentPane);
		setVisible(true);
	}

	private void updateCommentPanel() {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setBoardId(dto.getBoardId());
		List<CommentDTO> list = cs.selectCommentList(commentDTO);
		if (list != null && list.size() > 0) {
			commentPanel.removeAll(); // 기존 댓글 초기화
			for (CommentDTO result : list) {
				JLabel commentLabel = new JLabel(result.getUserNickName() + ": " + result.getCommentContent());
				commentLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
				commentPanel.add(commentLabel); // 댓글 추가
			}
			commentPanel.revalidate(); // UI 업데이트
			commentPanel.repaint(); // UI 갱신
		}
	}
}
