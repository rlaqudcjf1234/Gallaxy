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

	// �Խù� ������ ǥ���� �󺧰� �ؽ�Ʈ ����
	private JLabel titleLabel;
	private JTextArea contentArea;
	private JLabel imageLabel;

	// �Խù� ���� Ŭ���� ����
	private BoardDTO board;

	public BoardDetailFrame(BoardDTO board) {
		this.board = board; // ���޹��� �Խù� ������ ����
		initialize();
	}

	private void initialize() {

		setTitle("�Խù� ���� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 850);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// �Խù� �̹����� �ִ� ��� ǥ��
		if (board.getBoardFilePath() != null && !board.getBoardFilePath().isEmpty()) {
			ImageIcon img = new ImageIcon(board.getBoardFilePath());
			imageLabel = new JLabel(img);
			imageLabel.setBounds(10, 50, 460, 300); // �̹��� ��ġ ����
			contentPane.add(imageLabel);
		}

		// ������ ǥ���� �ؽ�Ʈ �ʵ�
	    JTextField titleField = new JTextField(board.getBoardTitle());
	    titleField.setFont(new Font("���� ���", Font.BOLD, 24));
	    titleField.setBounds(80, 360, 350, 40); // ���� ��ġ ����
	    titleField.setEditable(false); // ���� ���� �Ұ�
	    contentPane.add(titleField);

	    // ���� �� �߰�
	    JLabel titleLabel = new JLabel("����: "); // ���� �տ� �� �߰�
	    titleLabel.setFont(new Font("���� ���", Font.PLAIN, 18));
	    titleLabel.setBounds(20, 370, 50, 20); // ���� �� ��ġ ����
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
		scrollPane.setBounds(40, 520, 400, 250);
		contentPane.add(scrollPane);

		JLabel authorLabel = new JLabel("�ۼ��� :   " + board.getUserId());
		authorLabel.setFont(new Font("���� ���", Font.PLAIN, 18));
		authorLabel.setBounds(20, 400, 460, 30);
		contentPane.add(authorLabel);
		
		JLabel dateLabel = new JLabel("��¥ : " + board.getBoardWordYyyy() + " " + board.getBoardWordMm() + " " + board.getBoardWordDd());
		dateLabel.setFont(new Font("���� ���", Font.PLAIN, 23));
		dateLabel.setBounds(30, 440, 460, 30);
		contentPane.add(dateLabel);

		JLabel timeLabel = new JLabel("�ð� : " + board.getBoardWordApm() +  " " + board.getBoardWordHh() +  " " + board.getBoardWordMi());
		timeLabel.setBounds(30, 480, 460, 30);
		timeLabel.setFont(new Font("���� ���", Font.PLAIN, 23));
		contentPane.add(timeLabel);

		// ��ư �г� �߰�
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));

		// �ݱ� ��ư
		JButton closeButton = new JButton("�ݱ�");
		closeButton.setFont(new Font("���� ���", Font.PLAIN, 13));
		closeButton.setBackground(Color.LIGHT_GRAY);
		closeButton.setBounds(400, 17, 60, 30);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // â �ݱ�
			}
		});

		contentPane.add(buttonPane);

		contentPane.add(closeButton);

		setContentPane(contentPane);
		setVisible(true);
	}

	// �Խù� ���� ������ ����ϴ� �޼��� (��: �Խù� ���� �� ȣ��)
	public static void showBoardDetail(BoardDTO board) {
		new BoardDetailFrame(board);
	}
}
