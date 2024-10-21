package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class myPageBegin extends JFrame {
	private JLabel infoLabel; // ������ ǥ���� JLabel �߰�
	private List<Post> posts; // �Խñ� ���
	private JPanel postPanel; // �Խñ��� ǥ���� �г�

	public myPageBegin() {
		MP();
	}

	public void MP() {
		setTitle("����������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 850); // ������ ������
		setLayout(null); // null ���̾ƿ� ���

		JPanel panel = new JPanel();
		panel.setLayout(null); // �гε� null ���̾ƿ� ���
		panel.setBounds(0, 0, 500, 850); // �г� ũ�� ����
		panel.setBackground(new Color(111, 111, 111));

		// �ϴ��� ��ư ���� (�ڷΰ��� - btnBack // ȸ������ ����/ �α׾ƿ� - btnEdit )
		JButton btnBack = new JButton("�ڷΰ���");
		JButton btnEdit = new JButton("ȸ������ ����/ �α׾ƿ�");

		btnBack.setBackground(Color.BLACK);
		btnEdit.setBackground(Color.BLACK);

		btnBack.setSize(450, 35);
		btnBack.setLocation(25, 700); // �ϴ� ��ġ

		btnBack.setFont(new Font("����", Font.BOLD, 14));
		btnEdit.setSize(450, 35);
		btnEdit.setLocation(25, 650); // btnBack ���� ��ġ

		btnEdit.setFont(new Font("����", Font.BOLD, 14));

		// ���� ǥ�ÿ� JLabel ����
		infoLabel = new JLabel("����� ����"); // �ʱ�ȭ �߰�
		infoLabel.setFont(new Font("����", Font.PLAIN, 14));
		infoLabel.setOpaque(true); // ������ ���̰� ����
		infoLabel.setBackground(Color.WHITE); // ��� ���
		infoLabel.setForeground(Color.BLACK); // �ؽ�Ʈ ����
		infoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // �׵θ� �߰�

		// ũ�� �� ��ġ ����
		infoLabel.setBounds(25, 50, 450, 150); // �߾� ���� �� ũ�� ����
		panel.add(infoLabel); // JLabel �߰�

		// ===========================�Խñ� ���� =============================

		// �Խñ� ��� �ʱ�ȭ
		posts = new ArrayList<>();
		loadPosts(); // �Խñ� �ε�

		// �Խñ� ǥ�� �г� ����
		postPanel = new JPanel();
		postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
		postPanel.setBackground(Color.WHITE);

		// �Խñ� �߰�
		for (Post post : posts) {
			JLabel postLabel = new JLabel(
					"<html><strong>" + post.getTitle() + "</strong><br>" + post.getContent() + "</html>");
			postLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			postPanel.add(postLabel);
		}

		JScrollPane scrollPane = new JScrollPane(postPanel);
		scrollPane.setBounds(25, 220, 450, 400); // ��ġ�� ũ�� ����
		panel.add(scrollPane);

		// ��ư �̺�Ʈ ����
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainBoard(); // MainBoard �ν��Ͻ� ����
				dispose(); // ���� â �ݱ�
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new myPageEdit();
				// myPageBase.getInstance(new myPageEdit());
			}
		});

		panel.add(btnBack);
		panel.add(btnEdit);
		add(panel); // �г��� �����ӿ� �߰�
		setVisible(true); // �������� ���̵��� ����
	}

	private void loadPosts() {
		// �Խñ� ������ �߰�
		posts.add(new Post("ù ��° �Խñ�", "�̰��� ù ��° �Խñ��� �����Դϴ�."));
		posts.add(new Post("�� ��° �Խñ�", "�̰��� �� ��° �Խñ��� �����Դϴ�."));
		posts.add(new Post("�� ��° �Խñ�", "�̰��� �� ��° �Խñ��� �����Դϴ�."));
		posts.add(new Post("�� ��° �Խñ�", "�̰��� �� ��° �Խñ��� �����Դϴ�."));
		posts.add(new Post("�ټ� ��° �Խñ�", "�̰��� �ټ� ��° �Խñ��� �����Դϴ�."));
	}

	public static void main(String[] args) {
		new myPageBegin(); // myPageBegin Ŭ������ �ν��Ͻ��� �����Ͽ� ǥ��
	}
}

// Post Ŭ����
class Post {
	private String title;
	private String content;

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
}
