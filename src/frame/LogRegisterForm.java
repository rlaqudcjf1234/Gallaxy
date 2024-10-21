package frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LogRegisterForm extends JFrame {
	private LogUserDataSet users;

	private JLabel lblId, lblPw, lblEmail, lblRe, lblName, lblNickName;
	private JRadioButton rbtnMale, rbtnFemale;
	private JTextField tfId, tfEmail, tfName, tfNickName;
	private JPasswordField tfPw, tfRe;
	private JButton btnSignup, btnCancel;

	public LogRegisterForm(LogUserDataSet users) {
		this.users = users;
		setupUI();
	}

	private void setupUI() {
		setTitle("ȸ������ ������"); // â ���� ����
		setBounds(650, 0, 500, 750); // â ũ�� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� �� ���α׷� ����
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
				new LoginForm(users);
				dispose();// â �ݱ�
			}
		});

		// ȸ������ ��ư Ŭ�� �̺�Ʈ ó��
		btnSignup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �Է� �� ����
				if (Blank()) {
					JOptionPane.showMessageDialog(LogRegisterForm.this, "��� ������ �Է����ּ���.");
					// ��� �Է����� ��
				} else {
					// Id �ߺ��� ��
					if (users.isIdOverlap(tfId.getText())) {
						JOptionPane.showMessageDialog(LogRegisterForm.this, "�̹� �����ϴ� Id�Դϴ�.");
						tfId.requestFocus();

						// Pw�� Re�� ��ġ���� �ʾ��� ��
					} else if (!String.valueOf(tfPw.getPassword()).equals(String.valueOf(tfRe.getPassword()))) {
						JOptionPane.showMessageDialog(LogRegisterForm.this, "Password�� Retry�� ��ġ���� �ʽ��ϴ�.");
						tfPw.requestFocus();
					} else {
						users.addUsers(new User(tfId.getText(), String.valueOf(tfPw.getPassword()), tfName.getText(),
								tfNickName.getText(), getGender(), tfEmail.getText()));
						JOptionPane.showMessageDialog(LogRegisterForm.this, "ȸ�������� �Ϸ��߽��ϴ�!");
						new LoginForm(users);
						dispose();
					}
				}
				/*
				 * if (tfId.getText().isEmpty() || tfPw.getPassword().length == 0 ||
				 * tfRe.getPassword().length == 0 || tfName.getText().isEmpty() ||
				 * tfEmail.getText().isEmpty() || tfNickName.getText().isEmpty()) {
				 * JOptionPane.showMessageDialog(null, "��� �ʵ带 ä���ּ���.", "�Է� ����",
				 * JOptionPane.ERROR_MESSAGE); } else if
				 * (!String.valueOf(tfPw.getPassword()).equals(String.valueOf(tfRe.getPassword()
				 * ))) { JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "�Է� ����",
				 * JOptionPane.ERROR_MESSAGE); } else { // �������� ȸ������ ó�� String gender =
				 * rbtnMale.isSelected() ? "����" : "����"; JOptionPane.showMessageDialog(null,
				 * "ȸ������ ����!\n" + "ID: " + tfId.getText() + "\n" + "�̸�: " + tfName.getText() +
				 * "\n" + "�̸���: " + tfEmail.getText() + "\n" + "����: " + tfNickName.getText() +
				 * "\n" + "����: " + gender, "ȸ������ �Ϸ�", JOptionPane.INFORMATION_MESSAGE); // �Է� �ʵ�
				 * �ʱ�ȭ resetFields();
				 * 
				 * // �� ȭ������ �̵� new LoginForm(); // MainScreen �ν��Ͻ� ���� dispose(); // ���� ȸ������ â
				 * �ݱ� }
				 */
			}
		});

		// ���� �̺�Ʈ
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
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

	// �Է� �ʵ� �ʱ�ȭ �޼���
	/*
	 * private void resetFields() { tfId.setText(""); tfPw.setText("");
	 * tfRe.setText(""); tfName.setText(""); tfEmail.setText("");
	 * tfNickName.setText(""); rbtnMale.setSelected(true); // �⺻������ ���� ���� }
	 */
}
/*
 * (null, "ȸ������ ����!\n" + "ID: " + tfId.getText() + "\n" + "�̸�: " +
 * tfName.getText() + "\n" + "�̸���: " + tfEmail.getText() + "\n" + "����: " +
 * tfNickName.getText() + "\n" + "����: " + getGender(), "ȸ������ �Ϸ�",
 * JOptionPane.INFORMATION_MESSAGE);
 */