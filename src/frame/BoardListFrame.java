package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 5543592341811617934L;
	
	private static JPanel postListPanel = new JPanel(); // �Խù� ��� �г�

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new BoardListFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BoardListFrame() {
		// TODO Auto-generated constructor stub
		showBoardList();
	}

	// �Խù� ����� �����ϴ� �޼���
	public static void updateBoardList(List<BoardDTO> boardList) {
		postListPanel.removeAll(); // ���� �Խù� ��� �ʱ�ȭ
		for (BoardDTO board : boardList) {
			JLabel postLabel = new JLabel(board.getBoardTitle()); // �Խù� �������� JLabel ����
			postLabel.setHorizontalAlignment(SwingConstants.LEFT); // �ؽ�Ʈ ����
			postLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // �� ��� Ŀ��
			postLabel.setFont(new Font("���� ���", Font.PLAIN, 14)); // �۾� ũ�� ����

			postLabel.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					postLabel.setText("<html><u>" + board.getBoardTitle() + "</u></html>"); // ���� ȿ��
				}

				@Override
				public void mouseExited(MouseEvent e) {
					postLabel.setText(board.getBoardTitle()); // �⺻ ���·� �ǵ���
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// ���� ��
					new BoardDetailFrame(board);
				}
			});

			postListPanel.add(postLabel); // �гο� �Խù� �߰�
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
		mainFrame.setBackground(Color.white);
		mainFrame.setLayout(null); // ���� ���̾ƿ� ���

		// �ΰ� ��ư ����
		JButton btnLogo = new JButton();

		ImageIcon logoIcon = new ImageIcon("LogoImage_130x130.png");
		btnLogo.setIcon(logoIcon);
		btnLogo.setBorderPainted(false); // ��ư �׵θ� ����
		btnLogo.setContentAreaFilled(false); // ��ư ��� ����
		btnLogo.setFocusPainted(false); // ��Ŀ�� ȿ�� ����
		btnLogo.setBounds(20, 30, 130, 130); // ��ư ��ġ �� ũ�� ����
		// Ŭ�� �̺�Ʈ �߰�
		btnLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "����ȭ��!");
				new MainBoard();
				mainFrame.dispose();
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
		ImageIcon icon = new ImageIcon("gaesipan22.png");
		labelLogo.setIcon(icon); // JLabel�� �̹��� ����
		labelLogo.setBounds(100, 160, 250, 40); // �̹��� ��ġ �� ũ�� ����

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
		contentSearch.setBounds(50, 630, 300, 30);

		// �˻� ��ư ����
		JButton searchButton = new JButton("�˻�");
		searchButton.setFont(new Font("���� ���", Font.PLAIN, 13));
		searchButton.setBackground(Color.LIGHT_GRAY);
		searchButton.setBounds(360, 630, 80, 30);

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
			public void actionPerformed(ActionEvent e) {

				String content = contentSearch.getText();

				boardDTO.setBoardTitle(content);

				List<BoardDTO> list = bs.selectBoardList(boardDTO);
				
				if (list.equals(null) || list.isEmpty()) {
					// �˻� ��� ����
					JOptionPane.showMessageDialog(mainFrame, "�˻������ �������� �ʽ��ϴ�.");

				} else {
					// �˻� ��� ����
					updateBoardList(list);
		        }

			}
		});

		mainFrame.add(contentSearch);

		JButton btnAd = new JButton();

		ImageIcon logoIcon2 = new ImageIcon("SolDesk_Ad.png");
		btnAd.setIcon(logoIcon2);
		btnAd.setBorderPainted(false); // ��ư �׵θ� ����
		btnAd.setContentAreaFilled(false); // ��ư ��� ����
		btnAd.setFocusPainted(false); // ��Ŀ�� ȿ�� ����
		btnAd.setBounds(20, 635, 450, 240); // ��ư ��ġ �� ũ�� ����
		// Ŭ�� �̺�Ʈ �߰�
		btnAd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String url = "https://soldesk.com/";

				try {
					// URI�� ��ȯ�ϰ� �⺻ �� ���������� ����
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException | URISyntaxException ex) {
					ex.printStackTrace(); // ���� ó��
				}
			}
		});
		mainFrame.add(btnAd);

		// �Խù� ��� �г� ����
		postListPanel.setLayout(new BoxLayout(postListPanel, BoxLayout.Y_AXIS)); // ���η� ��ư ����
		JScrollPane scrollPane = new JScrollPane(postListPanel); // ��ũ�� �����ϰ�
		scrollPane.setBounds(50, 210, 400, 400);
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
				new UserInForm();
				mainFrame.dispose();
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
				new myPageBegin();
				mainFrame.dispose();
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
				new MainBoard();
				mainFrame.dispose();
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

}
