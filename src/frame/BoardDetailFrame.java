package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.BoardDTO;

public class BoardDetailFrame extends JFrame {

	private static final long serialVersionUID = 6032491971534575326L;

	// 게시물 정보를 표시할 라벨과 텍스트 영역
	private JLabel titleLabel;
	private JTextArea contentArea;
	private JLabel imageLabel;

	// 게시물 정보 클래스 변수
	private BoardDTO board;

	public BoardDetailFrame(BoardDTO board) {
		this.board = board; // 전달받은 게시물 정보를 저장
		initialize();
	}

	private void initialize() {

		setTitle("게시물 세부 정보");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 850);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// 게시물 이미지가 있는 경우 표시
		if (board.getBoardFilePath() != null && !board.getBoardFilePath().isEmpty()) {
			ImageIcon img = new ImageIcon(board.getBoardFilePath());
			imageLabel = new JLabel(img);
			imageLabel.setBounds(10, 50, 460, 300); // 이미지 위치 설정
			contentPane.add(imageLabel);
		}

		// 제목을 표시할 텍스트 필드
	    JTextField titleField = new JTextField(board.getBoardTitle());
	    titleField.setFont(new Font("맑은 고딕", Font.BOLD, 24));
	    titleField.setBounds(80, 360, 350, 40); // 제목 위치 설정
	    titleField.setEditable(false); // 제목 수정 불가
	    contentPane.add(titleField);

	    // 제목 라벨 추가
	    JLabel titleLabel = new JLabel("제목: "); // 제목 앞에 라벨 추가
	    titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
	    titleLabel.setBounds(20, 370, 50, 20); // 제목 라벨 위치 설정
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
		scrollPane.setBounds(40, 520, 400, 250);
		contentPane.add(scrollPane);

		JLabel authorLabel = new JLabel("작성자 :   " + board.getUserId());
		authorLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		authorLabel.setBounds(20, 400, 460, 30);
		contentPane.add(authorLabel);
		
		JLabel dateLabel = new JLabel("날짜 : " + board.getBoardWordYyyy() + " " + board.getBoardWordMm() + " " + board.getBoardWordDd());
		dateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 23));
		dateLabel.setBounds(30, 440, 460, 30);
		contentPane.add(dateLabel);

		JLabel timeLabel = new JLabel("시간 : " + board.getBoardWordApm() +  " " + board.getBoardWordHh() +  " " + board.getBoardWordMi());
		timeLabel.setBounds(30, 480, 460, 30);
		timeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 23));
		contentPane.add(timeLabel);

		// 버튼 패널 추가
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));

		// 닫기 버튼
		JButton closeButton = new JButton("닫기");
		closeButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		closeButton.setBackground(Color.LIGHT_GRAY);
		closeButton.setBounds(400, 17, 60, 30);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // 창 닫기
			}
		});

		contentPane.add(buttonPane);

		contentPane.add(closeButton);

		setContentPane(contentPane);
		setVisible(true);
	}

	// 게시물 세부 정보를 출력하는 메서드 (예: 게시물 선택 시 호출)
	public static void showBoardDetail(BoardDTO board) {
		new BoardDetailFrame(board);
	}
}
