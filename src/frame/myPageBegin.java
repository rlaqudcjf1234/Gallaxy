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
import javax.swing.border.Border;

public class myPageBegin extends JPanel {
	private JLabel infoLabel; // ������ ǥ���� JLabel �߰�
	private List<Post> posts; // �Խñ� ���
	private JPanel postPanel; // �Խñ��� ǥ���� �г�

	public myPageBegin() {
		setBackground(new Color(111, 111, 111));
		setLayout(null);
		setSize(500, 850);

		// �ϴ��� ��ư ���� (�ڷΰ��� - btnBack // ȸ������ ����/ �α׾ƿ� - btnEdit )
		JButton btnBack = new JButton("�ڷΰ���");
		JButton btnEdit = new JButton("ȸ������ ����/ �α׾ƿ�");

		btnBack.setBackground(new Color(000, 000, 000));
		btnEdit.setBackground(new Color(000, 000, 000));

		btnBack.setSize(450, 35);
		btnBack.setLocation(
				// x ��ǥ: ������ �ʺ��� ���ݿ��� btnBack ��ư�� �ʺ��� ������ �� ��
				((int) getSize().getWidth() / 2) - (btnBack.getWidth() / 2),
				// y ��ǥ: ������ ���̿��� 150�� �� �� (�ϴ� ��ġ)
				getSize().height - 100);

		btnBack.setFont(new Font("����", Font.BOLD, 14));
		btnEdit.setSize(450, 35);

		btnEdit.setLocation(((int) getSize().getWidth() / 2) - (btnEdit.getWidth() / 2), 0);

		btnEdit.setFont(new Font("����", Font.BOLD, 14));

		// ���� ǥ�ÿ� JLabel ����
		infoLabel = new JLabel("����� ����"); // �ʱ�ȭ �߰�
		// infoLabel = new JLabel(loggedInUser.getInfo()); // ���� �������� (�ӽ� Ŭ���� ������ )
		infoLabel.setFont(new Font("����", Font.PLAIN, 14));

		// ���� ����
		infoLabel.setOpaque(true); // ������ ���̰� ����
		infoLabel.setBackground(new Color(255, 255, 255)); // ��� ���
		infoLabel.setForeground(new Color(0, 0, 0)); // �ؽ�Ʈ ����

		Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // ������, �β� 2
		infoLabel.setBorder(border); // JLabel�� �׵θ� �߰�

		// ũ�� ����
		int labelWidth = 450;
		int labelHeight = 150;
		infoLabel.setSize(labelWidth, labelHeight); // JLabel ũ�� ����

		// ��ġ ����
		infoLabel.setBounds(((int) getSize().getWidth() / 2) - (labelWidth / 2), // �߾� ����
				btnEdit.getY() + btnEdit.getHeight() + 10, // btnEdit �Ʒ��� ��ġ
				labelWidth, // ������ ����
				labelHeight // ������ ����
		);
		add(infoLabel); // JLabel �߰�
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
		scrollPane.setBounds(25, 170, 450, 500); // ��ġ�� ũ�� ����
		add(scrollPane);

		// ��ư �̺�Ʈ ���� (����)

		// ��ư �̺�Ʈ(�ڷΰ��� / ��������, �α׾ƿ�)
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent panel) {
				// backToMainPage Ŭ���� �̱������� �̵��� ����
				myPageBase.getInstance(new myPageBegin());

			}
		});

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent panel) {
				System.out.println("btnEdit Ŭ��Ŭ��");
				myPageBase.getInstance(new myPageEdit());

			}
		});
		add(btnBack);
		add(btnEdit);

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
		// myPageBegin Ŭ������ �ν��Ͻ��� �����ϰ�, �̸� ȭ�鿡 ǥ���մϴ�.
		JFrame frame = new JFrame("����������");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 850);

		myPageBegin panel = new myPageBegin(); // myPageBegin �г� ����
		frame.add(panel); // �г��� �����ӿ� �߰�

		frame.setVisible(true); // �������� ���̵��� ����
	}
}

//Post Ŭ����
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
