package frame;

import java.awt.Color;
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
import main.Main;
import service.CommentService;
import service.impl.CommentServiceImpl;

public class BoardDetailFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1730899176307489779L;

	CommentService cs = new CommentServiceImpl();

	// 게시물 정보를 표시할 라벨과 텍스트 영역
	private JTextArea contentArea;
	private JLabel imageLabel;

	// 게시물 정보 클래스 변수
	private BoardDTO board;

	private JTextField commentField;
	private JPanel commentPanel;

	public BoardDetailFrame(BoardDTO board) {
		this.board = board; // 전달받은 게시물 정보를 저장
		DetailFrame();
	}

	private void DetailFrame() {

		setTitle("게시물 세부 정보");
		setBounds(1200, 100, 500, 850);

		JPanel contentPane = new JPanel();
		// 배경 흰색
		contentPane.setBackground(Color.white);
		contentPane.setLayout(null);

		// 게시물 이미지가 있는 경우 표시
		if (board.getBoardFilePath() != null && !board.getBoardFilePath().isEmpty()) {
			ImageIcon img = new ImageIcon(board.getBoardFilePath());
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
		JTextField titleField = new JTextField(board.getBoardTitle());
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
		JTextField authorField = new JTextField(board.getUserNickName());
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
				board.getBoardWordYyyy() + " " + board.getBoardWordMm() + " " + board.getBoardWordDd());
		dateField.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		dateField.setBounds(85, 340, 250, 40); // 날짜 위치 설정
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
				board.getBoardWordApm() + " " + board.getBoardWordHh() + " " + board.getBoardWordMi());
		timeField.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		timeField.setBounds(85, 370, 250, 40); // 시간 위치 설정
		timeField.setEditable(false); // 시간 수정 불가
		timeField.setBorder(null);
		// 배경 흰색
		timeField.setBackground(Color.white);
		contentPane.add(timeField);

		if (Main.USER != null && Main.USER.getUserId().equals(board.getUserId())) {
			// 수정 버튼
			JButton modifyButton = new JButton("수정");
			modifyButton.setBounds(380, 380, 70, 30);
			contentPane.add(modifyButton);
			
			// 글 작성 버튼 클릭 이벤트 추가
			modifyButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					// BoardWriteFrame 열기
					new BoardModifyFrame(board.getBoardId());
					dispose();
				}
			});
		}
		// 게시물 내용을 표시하는 텍스트 영역
		contentArea = new JTextArea(board.getBoardContent());
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
					comments.add(comment); // 댓글 저장
					*/
					CommentDTO dto = new CommentDTO();
					dto.setBoardId(board.getBoardId());
					dto.setCommentContent(comment);
					if (Main.USER != null) {
						dto.setUserId(Main.USER.getUserId());
					}

					int cnt = cs.insertComment(dto);
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
		CommentDTO dto = new CommentDTO();
		dto.setBoardId(board.getBoardId());
		List<CommentDTO> list = cs.selectCommentList(dto);
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

	// 게시물 세부 정보를 출력하는 메서드 (예: 게시물 선택 시 호출)
	public void showBoardDetail(BoardDTO board) {
		new BoardDetailFrame(board);
	}
}
