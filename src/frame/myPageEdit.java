package frame;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class myPageEdit extends JPanel {
	
    private JFrame frame; // ���� ������
    private JPanel panel; // �г�


	public  myPageEdit() {
		
		 frame = new JFrame("ȸ������ ����");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setBounds(100,100,500,400);
	        frame.setLayout(null); // null ���̾ƿ� ���

	        panel = new JPanel();
	        panel.setLayout(null); // �гε� null ���̾ƿ� ���
	        panel.setBounds(0, 0, 500, 400); // �г� ũ�� ����

	        // ������Ʈ ����
	        JLabel passwordLabel = new JLabel("��й�ȣ");
	        JPasswordField passwordField = new JPasswordField();
	        JButton confirmButton = new JButton("Ȯ��");
	        JButton backToMyPageBegin = new JButton("�ڷΰ���");

	        // ��ġ �� ũ�� ����
	        passwordLabel.setBounds(110, 120, 200, 30); // (x, y, width, height)
	        passwordField.setBounds(170, 120, 200, 30);
	        confirmButton.setBounds(90, 170, 300, 30);
	        backToMyPageBegin.setBounds(90, 210, 300, 30);

	        // �гο� ������Ʈ �߰�
	        panel.add(passwordLabel);
	        panel.add(passwordField);
	        panel.add(confirmButton);
	        panel.add(backToMyPageBegin);
	        
	        //�����ӿ� �г� �߰�
	        frame.add(panel);
	        
	    	panel.setVisible(true);
		
		
		
		

		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String enteredPassword = new String(passwordField.getPassword());
				if (checkPassword(enteredPassword)) {
					openUpdateInfoWindow();
					frame.dispose(); // ���� â �ݱ�
				} else {
					JOptionPane.showMessageDialog(frame, "��й�ȣ�� Ʋ�Ƚ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		backToMyPageBegin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent panel) {
				myPageBase.getInstance(new myPageBegin());
				frame.dispose();
			}
		});

		frame.setVisible(true);
	}

	private boolean checkPassword(String password) {
		String correctPassword = "1234"; // ���� ��й�ȣ
		return password.equals(correctPassword);
	}

	private void openUpdateInfoWindow() {
	    JFrame updateFrame = new JFrame("ȸ������ ����");
	    updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    updateFrame.setSize(500, 800); // ������ ������
	    updateFrame.setLayout(null); // null ���̾ƿ� ���

	    JPanel panel = new JPanel();
	    panel.setLayout(null); // �гε� null ���̾ƿ� ���
	    panel.setBounds(0, 0, 500, 800); // �г� ũ�� ����

	    // ������Ʈ ����
	    JLabel nickNameLabel = new JLabel("�г��� :");
	    JTextField nickNameField = new JTextField();
	    JLabel passwordLabel = new JLabel("�� ��й�ȣ:");
	    JPasswordField newPasswordField = new JPasswordField();
	    JLabel confirmPasswordLabel = new JLabel("��й�ȣ ��Ȯ��:");
	    JPasswordField confirmPasswordField = new JPasswordField();
	    JLabel emailLabel = new JLabel("�̸��� :");
	    JTextField emailField = new JTextField();
	    JButton saveButton = new JButton("����");
	    JButton backToCheck = new JButton("�ڷΰ���");

	    // ��ġ �� ũ�� ����
	    nickNameLabel.setBounds(80, 300, 100, 30);
	    nickNameField.setBounds(175, 300, 200, 30);
	    emailLabel.setBounds(80, 350, 100, 30);
	    emailField.setBounds(175, 350, 200, 30);
	    passwordLabel.setBounds(80, 400, 100, 30);
	    newPasswordField.setBounds(175, 400, 200, 30);
	    confirmPasswordLabel.setBounds(80, 450, 150, 30);
	    confirmPasswordField.setBounds(175, 450, 200, 30);
	    saveButton.setBounds(80, 500, 300, 30);
	    backToCheck.setBounds(80, 540, 300, 30);

	    // �гο� ������Ʈ �߰�
	    panel.add(nickNameLabel);
	    panel.add(nickNameField);
	    panel.add(emailLabel);
	    panel.add(emailField);
	    panel.add(passwordLabel);
	    panel.add(newPasswordField);
	    panel.add(confirmPasswordLabel);
	    panel.add(confirmPasswordField);
	    panel.add(saveButton);
	    panel.add(backToCheck);

	    // �����ӿ� �г� �߰�
	    updateFrame.add(panel);

	    // ��ư �׼� ������ ����
	    saveButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String email = emailField.getText();
	            String nickName = nickNameField.getText();
	            String newPassword = new String(newPasswordField.getPassword());
	            String confirmPassword = new String(confirmPasswordField.getPassword());

	            // ��й�ȣ Ȯ��
	            if (!newPassword.equals(confirmPassword)) {
	                JOptionPane.showMessageDialog(updateFrame, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
	                return; // ��й�ȣ�� ��ġ���� ������ ���� ���� ���� ����
	            }

	            // �����ͺ��̽��� ���� ���� ���� �߰�
	            JOptionPane.showMessageDialog(updateFrame, "ȸ�������� �����Ǿ����ϴ�.");
	        }
	    });

	    // ���� ���� â���� ��й�ȣ Ȯ�� â���� ���ư���
	    backToCheck.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // ���� �������� ���ư��� ���� �߰�
	            updateFrame.dispose(); // ���� â �ݱ�
	        }
	    });

	    updateFrame.setVisible(true); // �������� ���̵��� ����

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				String name = nameField.getText();
				String email = emailField.getText();
				String nickName = nickNameField.getText();
				String newPassword = new String(newPasswordField.getPassword());
				String confirmPassword = new String(confirmPasswordField.getPassword());

				// ��й�ȣ Ȯ��
				if (!newPassword.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(updateFrame, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
					return; // ��й�ȣ�� ��ġ���� ������ ���� ���� ���� ����
				}

				// �����ͺ��̽��� ���� ���� ���� �߰�
				JOptionPane.showMessageDialog(updateFrame, "ȸ�������� �����Ǿ����ϴ�.");
			}
		});

		// ���� ���� â���� ��й�ȣ Ȯ�� â���� ���ư���
		backToCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent panel) {
				myPageBase.getInstance(new myPageEdit());
				updateFrame.dispose(); // ���� â �ݱ�
			}
		});

		updateFrame.setVisible(true);
	}
}