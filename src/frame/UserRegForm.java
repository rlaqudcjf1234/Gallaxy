package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dto.UserDTO;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserRegForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5592827020966786913L;
	
	UserService us = new UserServiceImpl();

	private JLabel lblId, lblPw, lblEmail, lblRe, lblName, lblNickName;
	private JRadioButton rbtnMale, rbtnFemale;
	private JTextField tfId, tfEmail, tfName, tfNickName;
	private JPasswordField tfPw, tfRe;
	private JButton btnSignup, btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegForm frame = new UserRegForm();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� �� ���α׷� ����
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserRegForm() {
		setTitle("ȸ������ ������"); // â ���� ����
		setBounds(650, 0, 500, 750); // â ũ�� ����
		setBackground(new Color(247, 244, 242));
		setLayout(null); // ���� ���̾ƿ� ���

		// ȸ������ �� �г� ����
		JPanel signupPanel = new JPanel();
		signupPanel.setLayout(null); // ���� ���̾ƿ� ���
		signupPanel.setBounds(0, 0, 500, 750); // ��ġ �� ũ�� ����
		signupPanel.setBackground(new Color(247, 244, 242)); // ���� ����

		// ���̺� �� �ؽ�Ʈ �ʵ�

		lblId = new JLabel("ID:");
		lblPw = new JLabel("Password:");
		lblRe = new JLabel("Retry:");
		lblName = new JLabel("Name:");
		lblEmail = new JLabel("Email:");
		lblNickName = new JLabel("NickName:");

		lblId.setBounds(130, 260, 80, 25); // ��ġ �� ũ�� ����
		lblPw.setBounds(130, 300, 80, 25); // ��ġ �� ũ�� ����
		lblRe.setBounds(130, 340, 80, 25);
		lblName.setBounds(130, 380, 80, 25); // ��ġ �� ũ�� ����
		lblEmail.setBounds(130, 420, 80, 25); // ��ġ �� ũ�� ����
		lblNickName.setBounds(130, 460, 80, 25);

		tfId = new JTextField();
		tfPw = new JPasswordField();
		tfRe = new JPasswordField();
		tfName = new JTextField();
		tfEmail = new JTextField();
		tfNickName = new JTextField();

		tfId.setBounds(220, 260, 140, 25); // ��ġ �� ũ�� ����
		tfPw.setBounds(220, 300, 140, 25); // ��ġ �� ũ�� ����
		tfRe.setBounds(220, 340, 140, 25);
		tfName.setBounds(220, 380, 140, 25); // ��ġ �� ũ�� ����
		tfEmail.setBounds(220, 420, 140, 25); // ��ġ �� ũ�� ����
		tfNickName.setBounds(220, 460, 140, 25);

		rbtnMale = new JRadioButton("Male", true); // ���� ���� �⺻��
		rbtnFemale = new JRadioButton("Female");

		ButtonGroup group = new ButtonGroup();
		group.add(rbtnMale);
		group.add(rbtnFemale);

		rbtnMale.setBounds(130, 500, 100, 25); // ���� ��ư
		rbtnFemale.setBounds(260, 500, 100, 25); // ���� ��ư

		signupPanel.add(lblId);
		signupPanel.add(lblPw);
		signupPanel.add(tfId);
		signupPanel.add(tfPw);
		signupPanel.add(lblRe);
		signupPanel.add(tfRe);
		signupPanel.add(lblName);
		signupPanel.add(tfName);
		signupPanel.add(lblEmail);
		signupPanel.add(tfEmail);
		signupPanel.add(lblNickName);
		signupPanel.add(tfNickName);
		signupPanel.add(rbtnMale); // ���� ��ư �߰�
		signupPanel.add(rbtnFemale); // ���� ��ư �߰�

		// ��ư �߰�
		btnSignup = new JButton("ȸ������");
		btnCancel = new JButton("���");

		btnSignup.setBounds(130, 550, 99, 30); // ��ġ �� ũ�� ����
		btnCancel.setBounds(260, 550, 99, 30); // ��ġ �� ũ�� ����

		signupPanel.add(btnSignup);
		signupPanel.add(btnCancel);

		// ���� �����ӿ� �г� �߰�
		add(signupPanel);

		// â�� ���̵��� ����
		setVisible(true);

		// ��� �̺�Ʈ
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();// â �ݱ�
			}
		});

		// ȸ������ ��ư Ŭ�� �̺�Ʈ ó��
		btnSignup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �Է� �� ����
				if (Blank()) {
					JOptionPane.showMessageDialog(UserRegForm.this, "��� ������ �Է����ּ���.");
					// ��� �Է����� ��
				} else {
					// Id �ߺ��� ��
					UserDTO dto = new UserDTO();
					String userId = tfId.getText();
					
					dto.setUserId(userId);
					
					dto.setUserPw(String.valueOf(tfPw.getPassword()));
					
					dto.setUserName(tfName.getText());

					dto.setUserNickName(tfNickName.getText());

					dto.setUserGender(getGender());
					
					dto.setUserEmail(tfEmail.getText());
					
					if(us.selectUser(userId) != null) {
						JOptionPane.showMessageDialog(UserRegForm.this, "�̹� �����ϴ� Id�Դϴ�.");
						tfId.requestFocus();
						return;
					}
					if (!String.valueOf(tfPw.getPassword()).equals(String.valueOf(tfRe.getPassword()))) {
						JOptionPane.showMessageDialog(UserRegForm.this, "Password�� Retry�� ��ġ���� �ʽ��ϴ�.");
						tfPw.requestFocus();
						return;
					}
					if(us.insertUser(dto) > 0) {
						JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "ȸ�������� �����Ͽ����ϴ�.");
					}
					
					/*
						users.addUsers(new User(tfId.getText(), String.valueOf(tfPw.getPassword()), tfName.getText(),
								tfNickName.getText(), getGender(), tfEmail.getText()));
						JOptionPane.showMessageDialog(UserRegForm.this, "ȸ�������� �Ϸ��߽��ϴ�!");
						new UserInForm();
						dispose();
					*/
				}
			}
		});
	}

	public boolean Blank() {
		boolean result = false;
		if (tfId.getText().isEmpty()) {
			tfId.requestFocus();
			return true;
		}
		if (String.valueOf(tfPw.getPassword()).isEmpty()) {
			tfPw.requestFocus();
			return true;
		}
		if (String.valueOf(tfRe.getPassword()).isEmpty()) {
			tfRe.requestFocus();
			return true;
		}
		if (tfName.getText().isEmpty()) {
			tfName.requestFocus();
			return true;
		}
		if (tfNickName.getText().isEmpty()) {
			tfNickName.requestFocus();
			return true;
		}
		if (tfEmail.getText().isEmpty()) {
			tfEmail.requestFocus();
		}
		return result;
	}

	public String getGender() {
		if (rbtnMale.isSelected()) {
			return rbtnMale.getText();
		}
		return rbtnFemale.getText();
	}
}