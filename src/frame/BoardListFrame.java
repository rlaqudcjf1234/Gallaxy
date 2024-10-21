package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import dto.BoardDTO;
import service.BoardService;
import service.impl.BoardServiceImpl;

public class BoardListFrame extends JFrame {

	private static JPanel postListPanel = new JPanel(); // �Խù� ��� �г�
	private Frame mainFrame;

	// �Խù� ����� �����ϴ� �޼���
	public static void updateBoardList(List<BoardDTO> boardList) {
		postListPanel.removeAll(); // ���� �Խù� ��� �ʱ�ȭ
		for (BoardDTO board : boardList) {
			JButton postButton = new JButton(board.getBoardTitle()); // �Խù� �������� ��ư ����
			postButton.setHorizontalAlignment(SwingConstants.LEFT); // ��ư �� ���� ����
			postButton.setBorderPainted(false); // �׵θ� ����
			postButton.setFocusPainted(false); // ��Ŀ�� ����
			postButton.setBackground(Color.WHITE); // ��� ��

			postButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// ���� ��
					new BoardDetailFrame(board);
				}
			});

			postListPanel.add(postButton); // �гο� �Խù� �߰�
		}
		postListPanel.revalidate(); // UI ������Ʈ
		postListPanel.repaint(); // UI ����
	}

	public static void showBoardList() {

		BoardService bs = new BoardServiceImpl();

		BoardDTO boardDTO = new BoardDTO();

		// ���α׷� ���� �� �Խù� ��� �ҷ�����
		List<BoardDTO> boardList = bs.selectBoardList(new BoardDTO()); // �Խù� ����Ʈ ��������
		updateBoardList(boardList); // �Խù� ��� UI ������Ʈ

		Frame mainFrame = new Frame("���� ����Ʈ �Խ���");
		mainFrame.setBounds(700, 100, 500, 850); // ��ġ�� ũ��
		mainFrame.setBackground(new Color(247, 244, 242)); // ��� ��
		mainFrame.setLayout(null); // ���� ���̾ƿ� ���

		// �ΰ� ��ư ����
		JButton btnLogo = new JButton();

		ImageIcon logoIcon = new ImageIcon("Running Mate.png");
		btnLogo.setIcon(logoIcon);
		btnLogo.setBorderPainted(false); // ��ư �׵θ� ����
		btnLogo.setContentAreaFilled(false); // ��ư ��� ����
		btnLogo.setFocusPainted(false); // ��Ŀ�� ȿ�� ����
		btnLogo.setBounds(30, 50, 110, 95); // ��ư ��ġ �� ũ�� ����
		// Ŭ�� �̺�Ʈ �߰�
		btnLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "����ȭ��!");
			}
		});
		// �� �ۼ� ��ư ����
		JButton btnWrite = new JButton("�� �ۼ�");

		// �ʱ� ��ư ��ġ �� ũ�� ���� (���ذ�)
		btnWrite.setBounds(360, 170, 80, 30);
		btnWrite.setFont(new Font("���� ���", Font.PLAIN, 13));
		btnWrite.setBackground(Color.LIGHT_GRAY);

		// �����ӿ� ��ư �߰�
		mainFrame.add(btnWrite);
		mainFrame.add(btnLogo);

		// �̹��� �߰�
		JLabel labelLogo = new JLabel(); // �̹��� ǥ�ø� ���� Label
		ImageIcon icon = new ImageIcon("gaesipan11.png");
		labelLogo.setIcon(icon); // JLabel�� �̹��� ����
		labelLogo.setBounds(120, 130, 230, 80); // �̹��� ��ġ �� ũ�� ����

		// �����ӿ� Label �߰�
		mainFrame.add(labelLogo);

		// �� �ۼ� ��ư Ŭ�� �̺�Ʈ �߰�
		btnWrite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// BoardWriteFrame ����
				BoardWriteFrame writeFrame = new BoardWriteFrame();
				writeFrame.setVisible(true);
				mainFrame.dispose();
			}
		});

		// �� �˻� �ʵ�  
		TextField contentSearch = new TextField("�˻��� �� ������ �Է��ϼ���"); // ���� �Է�
		contentSearch.setBounds(50, 640, 300, 30);

		// �˻� ��ư ����
		JButton searchButton = new JButton("�˻�");
		searchButton.setFont(new Font("���� ���", Font.PLAIN, 13));
		searchButton.setBackground(Color.LIGHT_GRAY);
		searchButton.setBounds(360, 640, 80, 30);

		// �����ӿ� �˻� ��ư �߰�
		mainFrame.add(searchButton);

		contentSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (contentSearch.getText().equals("�˻��� �� ������ �Է��ϼ���")) {
					contentSearch.setText(""); // Ŭ�� �� �ؽ�Ʈ �ʱ�ȭ
				}
			}
		});

		// ��ư(searchBtn) Ŭ�� �̺�Ʈ
		searchButton.addActionListener(new ActionListener() {

			@Override
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {

				String content = contentSearch.getText();
				if (content.equals("") || content.equals("�˻��� �� ������ �Է��ϼ���")) {
					// �˻��� ���� ���
					JOptionPane.showMessageDialog(mainFrame, "�˻�� �Է����� �ʾҽ��ϴ�.");
					
					return; 
				}

				boardDTO.setBoardTitle(content);

				List<BoardDTO> list = bs.selectBoardList(boardDTO);
				if (list.equals(null) || list.isEmpty()) {
					// �˻� ��� ����
					JOptionPane.showMessageDialog(mainFrame, "�˻������ �������� �ʽ��ϴ�.");
					
				} else {
					// �˻� ��� ����
					JOptionPane.showMessageDialog(mainFrame, "�׽�Ʈ");
					
				}

			}
		});

		mainFrame.add(contentSearch);

		// �Խù� ��� �г� ����
		postListPanel.setLayout(new BoxLayout(postListPanel, BoxLayout.Y_AXIS)); // ���η� ��ư ����
		JScrollPane scrollPane = new JScrollPane(postListPanel); // ��ũ�� �����ϰ�
		scrollPane.setBounds(50, 220, 400, 400);
		mainFrame.add(scrollPane);

		// �α׾ƿ� �ؽ�Ʈ �߰�
		JLabel logoutLabel = new JLabel("�α׾ƿ�");
		logoutLabel.setForeground(Color.BLUE); // �ؽ�Ʈ ���� ����
		logoutLabel.setBounds(260, 50, 100, 40); // ��ġ�� ũ�� ����
		logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // �� ��� Ŀ��

		// �α׾ƿ� Ŭ�� �̺�Ʈ �߰�
		logoutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "�α׾ƿ� �մϴ�!");
			}
		});

		mainFrame.add(logoutLabel); // �����ӿ� ���������� �ؽ�Ʈ �߰�

		// �α׾ƿ� �ؽ�Ʈ �߰�
		JLabel myPageLabel = new JLabel("����������");
		myPageLabel.setForeground(Color.BLUE); // �ؽ�Ʈ ���� ����
		myPageLabel.setBounds(330, 50, 100, 40); // ��ġ�� ũ�� ����
		myPageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // �� ��� Ŀ��

		// �α׾ƿ� Ŭ�� �̺�Ʈ �߰�
		myPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "������������ �̵�!");
			}
		});

		mainFrame.add(myPageLabel); // �����ӿ� ���������� �ؽ�Ʈ �߰�

		// ����ȭ�� �ؽ�Ʈ �߰�
		JLabel mainPageLabel = new JLabel("����ȭ��");
		mainPageLabel.setForeground(Color.BLUE); // �ؽ�Ʈ ���� ����
		mainPageLabel.setBounds(415, 50, 100, 40); // ��ġ�� ũ�� ����
		mainPageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // �� ��� Ŀ��

		// �α׾ƿ� Ŭ�� �̺�Ʈ �߰�
		mainPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "����ȭ������ �̵�!");
			}
		});

		mainFrame.add(mainPageLabel); // �����ӿ� ����ȭ�� �ؽ�Ʈ �߰�

		// ȭ���� ����
		mainFrame.setVisible(true);

		// ���� -----------------------------------------
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			}
		});

	}

//	public static void main(String[] args) {
//		showBoardList();
//	}

}
