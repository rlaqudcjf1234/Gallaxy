package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.UserDTO;
import main.Main;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserInForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1917772537922383914L;

	UserService us = new UserServiceImpl();

	private JLabel lblId, lblPw;
	private JTextField tfId;
	private JPasswordField tfPw;
	private JButton btnLogin, btnRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInForm frame = new UserInForm();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� �� ���α׷� ����
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserInForm() {
		setTitle("���� ȭ��"); // â ���� ����
		setBounds(650, 0, 500, 750); // â ��ġ �� ũ�� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� �� ���α׷� ����
		setLayout(new BorderLayout()); // ���̾ƿ� ����

		// ���� �г� ���� �� ����
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(247, 244, 242)); // ���� ����
		mainPanel.setLayout(new FlowLayout()); // �÷ο� ���̾ƿ� ����

		// ȯ�� �޽��� ���̺�
		JLabel welcomeLabel = new JLabel("ȯ���մϴ�!"); // ��Ʈ ���� ���� �⺻ ���
		mainPanel.add(welcomeLabel); // �гο� ���̺� �߰�

		add(mainPanel, BorderLayout.CENTER); // �����ӿ� ���� �г� �߰�

		// �α��� �г� ���� �� ����
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null); // ���� ���̾ƿ� ���
		loginPanel.setBackground(new Color(247, 244, 242)); // ���� ����

		// ������Ʈ �ʱ�ȭ
		lblId = new JLabel("ID:");
		lblPw = new JLabel("Password:");
		tfId = new JTextField();
		tfPw = new JPasswordField();
		btnLogin = new JButton("�α���");
		btnRegister = new JButton("ȸ������");

		// ������Ʈ ��ġ �� ũ�� ����
		lblId.setBounds(130, 250, 140, 30);
		tfId.setBounds(130, 280, 200, 30);
		lblPw.setBounds(130, 310, 140, 30);
		tfPw.setBounds(130, 340, 200, 30);
		btnLogin.setBounds(130, 400, 90, 30);
		btnRegister.setBounds(240, 400, 90, 30);

		// �гο� ������Ʈ �߰�
		loginPanel.add(lblId);
		loginPanel.add(lblPw);
		loginPanel.add(tfId);
		loginPanel.add(tfPw);
		loginPanel.add(btnLogin);
		loginPanel.add(btnRegister);

		// �����ӿ� �г� �߰�
		add(loginPanel);

		// â�� ���̵��� ����
		setVisible(true);

		// ȸ������ Ŭ�� �̺�Ʈ
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JFrame logRegisterForm = new UserRegForm(); // ȸ������ �� ����
				logRegisterForm.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						setVisible(true);
					}
				});

				tfId.setText(""); // �Է� �ʱ�ȭ
				tfPw.setText(""); // �Է� �ʱ�ȭ
			}
		});

		// �α��� Ŭ�� �̺�Ʈ
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = tfId.getText();
				String userPw = String.valueOf(tfPw.getPassword());

				if (userId.isEmpty()) {
					JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���.", "�α�����", JOptionPane.WARNING_MESSAGE);
					return;

				}
				if (userPw.isEmpty()) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���.", "�α�����", JOptionPane.WARNING_MESSAGE);
					return;
				}

				UserDTO dto = us.selectUser(userId);

				if (dto == null) {
					JOptionPane.showMessageDialog(null, "�������� �ʴ� Id�Դϴ�.");
					return;
				}
				if (!userPw.equals(dto.getUserPw())) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					return;
				}
				
				Main.USER = dto;
				
				JOptionPane.showMessageDialog(null, dto.getUserNickName()+"�� �α��ο� �����ϼ̽��ϴ�.", "�α�����", JOptionPane.INFORMATION_MESSAGE);
				
				new MainBoard();
				dispose();
			}
		});
	}
}
