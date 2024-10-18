package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class myPageBegin extends JPanel {
	// �ӽ� =============================
	private JLabel infoLabel; // ������ ǥ���� JLabel �߰�
	private UserData loggedInUser; // ����� ������ ���� UserData ��ü
// �ӽ� =====================================================

	public myPageBegin() {
		setBackground(new Color(111, 111, 111));
		setLayout(null);
		setSize(500, 850);

		loggedInUser = new UserData("ȫ�浿", "hong@example.com"); // ����� ���� �ʱ�ȭ �ӽ�)))))

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

		add(btnBack);
		add(btnEdit);

		// ���� ǥ�ÿ� JLabel ����
		infoLabel = new JLabel(loggedInUser.getInfo()); // ���� �������� (�ӽ� Ŭ���� ������ )
		infoLabel.setFont(new Font("����", Font.PLAIN, 14));

		// ���� ����
		infoLabel.setOpaque(true); // ������ ���̰� ����
		infoLabel.setBackground(new Color(255, 255, 255)); // ��� ���
		infoLabel.setForeground(new Color(0, 0, 0)); // �ؽ�Ʈ ����

		Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // ������, �β� 2
		infoLabel.setBorder(border); // JLabel�� �׵θ� �߰�

		// ũ�� ����
		int labelWidth = 450; // ���� 300
		int labelHeight = 150; // ���� 30
		infoLabel.setSize(labelWidth, labelHeight); // JLabel ũ�� ����

		// ��ġ ����
		infoLabel.setBounds(((int) getSize().getWidth() / 2) - (labelWidth / 2), // �߾� ����
				btnEdit.getY() + btnEdit.getHeight() + 10, // btnEdit �Ʒ��� ��ġ
				labelWidth, // ������ ����
				labelHeight // ������ ����
		);
		add(infoLabel); // JLabel �߰�

		// ��ư �̺�Ʈ(�ڷΰ��� / ��������, �α׾ƿ�)
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// backToMainPage Ŭ���� �̱������� �̵��� ����
				myPageBase.getInstance(new backToMainPage());
			}
		});

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// myPageEdit Ŭ���� �̱���.
				myPageBase.getInstance(new myPageEdit());

			}
		});

	}
}
