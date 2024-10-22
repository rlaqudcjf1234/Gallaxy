package frame;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import service.CommentService;
import service.UserService;
import service.impl.CommentServiceImpl;
import service.impl.UserServiceImpl;

public class BoardDetailFrame extends JFrame {

	private final long serialVersionUID = 6032491971534575326L;
	
	CommentService cs = new CommentServiceImpl();

	// 게시물 정보를 표시할 라벨과 텍스트 영역
	private JLabel titleLabel;
	private JTextArea contentArea;
	private JLabel imageLabel;

	// 게시물 정보 클래스 변수
	private BoardDTO board;

	private JTextField commentField;
	private JPanel commentPanel;
	private ArrayList<String> comments; // 댓글 목록

	public BoardDetailFrame(BoardDTO board) {
		this.board = board; // 전달받은 게시물 정보를 저장
		this.comments = new ArrayList<>();
		DetailFrame();
	}

	private void DetailFrame() {

		setTitle("게시물 세부 정보");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 850);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// 게시물 이미지가 있는 경우 표시
		if (board.getBoardFilePath() != null && !board.getBoardFilePath().isEmpty()) {
			ImageIcon img = new ImageIcon(board.getBoardFilePath());
			imageLabel = new JLabel(img);
			imageLabel.setBounds(10, 50, 460, 250); // 이미지 위치 설정
			contentPane.add(imageLabel);
		}

		// 제목을 표시할 텍스트 필드
		JTextField titleField = new JTextField(board.getBoardTitle());
		titleField.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		titleField.setBounds(100, 310, 350, 40); // 제목 위치 설정
		titleField.setEditable(false); // 제목 수정 불가
		titleField.setBorder(null);
		contentPane.add(titleField);

		// 제목 라벨 추가
		JLabel titleLabel = new JLabel("제목: "); // 제목 앞에 라벨 추가
		titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		titleLabel.setBounds(40, 320, 50, 20); // 제목 라벨 위치 설정
		contentPane.add(titleLabel);

		// 게시물 내용을 표시하는 텍스트 영역
		contentArea = new JTextArea(board.getBoardContent());
		contentArea.setWrapStyleWord(true);
		contentArea.setLineWrap(true);
		contentArea.setEditable(false); // 내용 수정 불가
		contentArea.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

		JScrollPane scrollPane = new JScrollPane(contentArea);
		scrollPane.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		scrollPane.setBounds(40, 430, 400, 200);
		contentPane.add(scrollPane);

		// 댓글 패널
		commentPanel = new JPanel();
		commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
		JScrollPane commentScrollPane = new JScrollPane(commentPanel);
		commentScrollPane.setBounds(40, 650, 400, 100);
		contentPane.add(commentScrollPane);

		// 댓글 입력 필드
		commentField = new JTextField();
		commentField.setBounds(40, 755, 305, 30);
		contentPane.add(commentField);

		// 댓글 버튼
		JButton commentButton = new JButton("작성");
		commentButton.setBounds(360, 755, 80, 30);
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
					dto.setUserId("test");

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
		contentPane.add(commentButton);

		JLabel authorLabel = new JLabel(board.getUserId());
		authorLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		authorLabel.setBounds(0, 350, 460, 30);
		authorLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		contentPane.add(authorLabel);

		JLabel dateLabel = new JLabel("날짜 : " + board.getBoardWordYyyy() + " " + board.getBoardWordMm() + " "
				+ board.getBoardWordDd() + "  /  시간 : " + board.getBoardWordApm() + " " + board.getBoardWordHh() + " "
				+ board.getBoardWordMi());

		dateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		dateLabel.setBounds(50, 385, 460, 30);
		contentPane.add(dateLabel);

		//		JLabel timeLabel = new JLabel(
		//				"시간 : " + board.getBoardWordApm() + " " + board.getBoardWordHh() + " " + board.getBoardWordMi());
		//		timeLabel.setBounds(30, 430, 460, 30);
		//		timeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		//		contentPane.add(timeLabel);

		// 버튼 패널 추가
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));

		// 닫기 버튼
		JButton closeButton = new JButton("닫기");
		closeButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		closeButton.setBackground(Color.LIGHT_GRAY);
		closeButton.setBounds(400, 15, 60, 30);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // 창 닫기
			}
		});
		
		updateCommentPanel();

		contentPane.add(buttonPane);

		contentPane.add(closeButton);

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
				JLabel commentLabel = new JLabel(result.getCommentContent());
				commentLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
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
