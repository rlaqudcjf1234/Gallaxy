package frame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class myPageEdit extends JPanel{
	
	private String correctPassword = "yourCorrectPassword"; // ���� ��й�ȣ�� ���� �ʿ�
	
	public myPageEdit() {
		
		setBackground(new Color(111, 111, 111));
		setLayout(new GridBagLayout());
		setSize(500, 850);
		
		// �г� ����
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 5, 10);

		// ��ư ����
		JButton btnGoToMp = new JButton("�ڷΰ���"); // ���������� ���� ȭ������ ���ư���
		JButton btnConfirm = new JButton("�����ϱ�"); // ����� ������ ����
		JButton btnLogout = new JButton("�α׾ƿ�"); // ����� ������ ����

		// ��ư ����
		btnGoToMp.setBackground(Color.GRAY);
		btnConfirm.setBackground(Color.GRAY);
		btnLogout.setBackground(Color.GRAY);
		// ��ư ������
		btnGoToMp.setSize(450, 35);
		btnConfirm.setSize(450, 35);
		btnLogout.setSize(450, 35);

		// ��й�ȣ �Է� �� ��Ȯ��
		JLabel currentPasswordLabel = new JLabel("���� ��й�ȣ:");
		JPasswordField currentPassword = new JPasswordField(15);

		JLabel confirmPasswordLabel = new JLabel("��й�ȣ ��Ȯ��:");
		JPasswordField confirmPassword = new JPasswordField(15);

		//JButton submitButton = new JButton("Ȯ��");

		// ������Ʈ ��ġ
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(currentPasswordLabel, gbc);
		gbc.gridx = 1;
		panel.add(currentPassword, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(confirmPasswordLabel, gbc);
		gbc.gridx = 1;
		panel.add(confirmPassword, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER; // �߾� ����
		panel.add(btnGoToMp, gbc);

		gbc.gridy = 3;
		panel.add(btnConfirm, gbc);

		gbc.gridy = 4;
		panel.add(btnLogout, gbc);

		add(panel);

		// ��ư �׼� ������
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent panel) {
                String inputCurrentPassword = new String(currentPassword.getPassword());
                String inputConfirmPassword = new String(confirmPassword.getPassword());

                // ��й�ȣ Ȯ�� ����
                if (inputCurrentPassword.equals(correctPassword) && inputCurrentPassword.equals(inputConfirmPassword)) {
                    // ��й�ȣ�� ��ġ�� ���
                    JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ�εǾ����ϴ�!", "����", JOptionPane.INFORMATION_MESSAGE);
                    // ���� �ܰ�� �Ѿ�� ������ ���⿡ �߰�
                } else {
                    JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

		btnGoToMp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent panel) {
				 myPageBase.getInstance(new myPageBegin());
				// ���������� ���� ȭ������ ���ư���
				// �ش� ���� �߰�
			}
		});

		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent panel) {
				// �α׾ƿ� ó�� ���� �߰�
				JOptionPane.showMessageDialog(null, "�α׾ƿ��Ǿ����ϴ�.", "�α׾ƿ�", JOptionPane.INFORMATION_MESSAGE);
				// �α��� ȭ������ �̵��ϴ� ���� �߰�
			}
		});
	}
	


	
}