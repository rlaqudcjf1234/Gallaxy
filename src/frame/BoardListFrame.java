package frame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BoardListFrame {

	// ------------------------------------------------------------------
	public static void addWrite(String title, String content) {
		// ���� ��ư ����
		JLabel writeLabel = new JLabel(title);
		writeLabel.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		writeLabel.setFont(new java.awt.Font("�������", java.awt.Font.PLAIN, 16));
		writeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ���� Ŭ�� �� ���� ǥ��
				JOptionPane.showMessageDialog(mainFrame, content, title, 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		postListPanel.add(writeLabel);
		postListPanel.revalidate(); // �г� ����
		postListPanel.repaint(); // ȭ�� �緻����
	}

	// ------------------------------------------------------------------

	private static JPanel postListPanel = new JPanel(); // �Խù� ��� �г�
	private static Frame mainFrame;

	public static void main(String[] args) {
		Frame mainFrame = new Frame("���� ����Ʈ �Խ���");
		mainFrame.setBounds(700, 100, 500, 850); // ��ġ�� ũ��
		mainFrame.setBackground(new Color(247, 244, 242)); // ��� ��
		mainFrame.setLayout(null); // ���� ���̾ƿ� ���

		// �ΰ��� ��ư ����
		Button btnWrite = new Button("�� �ۼ�");
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

		// �ʱ� ��ư ��ġ �� ũ�� ���� (���ذ�)
		btnWrite.setBounds(360, 170, 70, 30);
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
				new BoardWriteFrame(mainFrame); // WriteBoard ������ ����
				mainFrame.setVisible(false); // ���� ������ �����
			}
		});

//		// TextField�� TextArea �߰�
//		TextField textFieldTitle = new TextField("������ �Է��ϼ���"); // ���� �Է�
//		TextArea textAreaContent = new TextArea("������ �Է��ϼ���"); // ����
//
//		// �Է� ĭ ��ġ �� ũ�� ����
//		textFieldTitle.setBounds(50, 220, 400, 30); // ���� �Է�ĭ ��ġ�� ũ��
//		textAreaContent.setBounds(50, 270, 400, 200); // ���� �Է�ĭ ��ġ�� ũ��
//
//		// ���콺 Ŭ�� �� �⺻ �ؽ�Ʈ �����
//		textFieldTitle.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (textFieldTitle.getText().equals("������ �Է��ϼ���")) {
//					textFieldTitle.setText(""); // Ŭ�� �� �ؽ�Ʈ �ʱ�ȭ
//				}
//			}
//		});
//
//		textAreaContent.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (textAreaContent.getText().equals("������ �Է��ϼ���")) {
//					textAreaContent.setText(""); // Ŭ�� �� �ؽ�Ʈ �ʱ�ȭ
//				}
//			}
//		});
//
//        // �����ӿ� TextField�� TextArea �߰�
//        mainFrame.add(textFieldTitle);
//        mainFrame.add(textAreaContent);

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
}
