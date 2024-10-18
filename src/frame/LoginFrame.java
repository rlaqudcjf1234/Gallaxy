package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginFrame extends JFrame {
	public LoginFrame() {
		setTitle("���� ȭ��"); // â ���� ����
		setBounds(650, 0, 500, 750); // â ũ�� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� �� ���α׷� ����
		setLayout(new BorderLayout()); // ���̾ƿ� ����

		// ���� �г� ����
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(247, 244, 242)); // ���� ����
		mainPanel.setLayout(new FlowLayout()); // �÷ο� ���̾ƿ� ����

		// ���� ȭ�鿡 �߰��� ����
		JLabel welcomeLabel = new JLabel("ȯ���մϴ�!"); // ȯ�� �޽��� ���̺�
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // ��Ʈ ����

		mainPanel.add(welcomeLabel); // �гο� ���̺� �߰�

		add(mainPanel, BorderLayout.CENTER); // �����ӿ� �г� �߰�

		setVisible(true); // â ���̵��� ����
	}
}