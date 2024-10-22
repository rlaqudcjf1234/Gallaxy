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

	// �Խù� ������ ǥ���� �󺧰� �ؽ�Ʈ ����
	private JLabel titleLabel;
	private JTextArea contentArea;
	private JLabel imageLabel;

	// �Խù� ���� Ŭ���� ����
	private BoardDTO board;

	private JTextField commentField;
	private JPanel commentPanel;
	private ArrayList<String> comments; // ��� ���

	public BoardDetailFrame(BoardDTO board) {
		this.board = board; // ���޹��� �Խù� ������ ����
		this.comments = new ArrayList<>();
		DetailFrame();
	}

	private void DetailFrame() {

		setTitle("�Խù� ���� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 850);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// �Խù� �̹����� �ִ� ��� ǥ��
		if (board.getBoardFilePath() != null && !board.getBoardFilePath().isEmpty()) {
			ImageIcon img = new ImageIcon(board.getBoardFilePath());
			imageLabel = new JLabel(img);
			imageLabel.setBounds(10, 50, 460, 250); // �̹��� ��ġ ����
			contentPane.add(imageLabel);
		}

		// ������ ǥ���� �ؽ�Ʈ �ʵ�
		JTextField titleField = new JTextField(board.getBoardTitle());
		titleField.setFont(new Font("���� ���", Font.BOLD, 23));
		titleField.setBounds(100, 310, 350, 40); // ���� ��ġ ����
		titleField.setEditable(false); // ���� ���� �Ұ�
		titleField.setBorder(null);
		contentPane.add(titleField);

		// ���� �� �߰�
		JLabel titleLabel = new JLabel("����: "); // ���� �տ� �� �߰�
		titleLabel.setFont(new Font("���� ���", Font.PLAIN, 16));
		titleLabel.setBounds(40, 320, 50, 20); // ���� �� ��ġ ����
		contentPane.add(titleLabel);

		// �Խù� ������ ǥ���ϴ� �ؽ�Ʈ ����
		contentArea = new JTextArea(board.getBoardContent());
		contentArea.setWrapStyleWord(true);
		contentArea.setLineWrap(true);
		contentArea.setEditable(false); // ���� ���� �Ұ�
		contentArea.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentArea.setFont(new Font("���� ���", Font.PLAIN, 16));

		JScrollPane scrollPane = new JScrollPane(contentArea);
		scrollPane.setFont(new Font("���� ���", Font.PLAIN, 16));
		scrollPane.setBounds(40, 430, 400, 200);
		contentPane.add(scrollPane);

		// ��� �г�
		commentPanel = new JPanel();
		commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
		JScrollPane commentScrollPane = new JScrollPane(commentPanel);
		commentScrollPane.setBounds(40, 650, 400, 100);
		contentPane.add(commentScrollPane);

		// ��� �Է� �ʵ�
		commentField = new JTextField();
		commentField.setBounds(40, 755, 305, 30);
		contentPane.add(commentField);

		// ��� ��ư
		JButton commentButton = new JButton("�ۼ�");
		commentButton.setBounds(360, 755, 80, 30);
		commentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String comment = commentField.getText();
				if (!comment.trim().isEmpty()) {
					/*
					comments.add(comment); // ��� ����
					*/
					CommentDTO dto = new CommentDTO();
					dto.setBoardId(board.getBoardId());
					dto.setCommentContent(comment);
					dto.setUserId("test");

					int cnt = cs.insertComment(dto);
					if (cnt == 0) {
						// ���� ��� ���� �˸� �߰�
						return;
					}
					updateCommentPanel(); // ��� ��� ����
					commentField.setText(""); // �Է� �ʵ� �ʱ�ȭ
				} else {
					JOptionPane.showMessageDialog(null, "����� �Է��ϼ���!");
				}
			}
		});
		contentPane.add(commentButton);

		JLabel authorLabel = new JLabel(board.getUserId());
		authorLabel.setFont(new Font("���� ���", Font.PLAIN, 14));
		authorLabel.setBounds(0, 350, 460, 30);
		authorLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		contentPane.add(authorLabel);

		JLabel dateLabel = new JLabel("��¥ : " + board.getBoardWordYyyy() + " " + board.getBoardWordMm() + " "
				+ board.getBoardWordDd() + "  /  �ð� : " + board.getBoardWordApm() + " " + board.getBoardWordHh() + " "
				+ board.getBoardWordMi());

		dateLabel.setFont(new Font("���� ���", Font.PLAIN, 16));
		dateLabel.setBounds(50, 385, 460, 30);
		contentPane.add(dateLabel);

		//		JLabel timeLabel = new JLabel(
		//				"�ð� : " + board.getBoardWordApm() + " " + board.getBoardWordHh() + " " + board.getBoardWordMi());
		//		timeLabel.setBounds(30, 430, 460, 30);
		//		timeLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
		//		contentPane.add(timeLabel);

		// ��ư �г� �߰�
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));

		// �ݱ� ��ư
		JButton closeButton = new JButton("�ݱ�");
		closeButton.setFont(new Font("���� ���", Font.PLAIN, 13));
		closeButton.setBackground(Color.LIGHT_GRAY);
		closeButton.setBounds(400, 15, 60, 30);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // â �ݱ�
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
			commentPanel.removeAll(); // ���� ��� �ʱ�ȭ
			for (CommentDTO result : list) {
				JLabel commentLabel = new JLabel(result.getCommentContent());
				commentLabel.setFont(new Font("���� ���", Font.PLAIN, 14));
				commentPanel.add(commentLabel); // ��� �߰�
			}
			commentPanel.revalidate(); // UI ������Ʈ
			commentPanel.repaint(); // UI ����
		}
	}

	// �Խù� ���� ������ ����ϴ� �޼��� (��: �Խù� ���� �� ȣ��)
	public void showBoardDetail(BoardDTO board) {
		new BoardDetailFrame(board);
	}
}
