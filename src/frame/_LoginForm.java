package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame {

    private LogUserDataSet users;
    private JLabel lblId, lblPw;
    private JTextField tfId;
    private JPasswordField tfPw;
    private JButton btnLogin, btnRegister;

    public LoginForm(LogUserDataSet users) {
        this.users=users;  // ���� ������ �ʱ�ȭ
        showFrame();  // ������ �����ֱ� �� ������Ʈ �ʱ�ȭ
        addListeners();  // �̺�Ʈ ������ �߰� (������Ʈ �ʱ�ȭ ���Ŀ� ȣ��)
    }

    public void showFrame() {
        setTitle("���� ȭ��");  // â ���� ����
        setBounds(650, 0, 500, 750);  // â ��ġ �� ũ�� ����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // ���� �� ���α׷� ����
        setLayout(new BorderLayout());  // ���̾ƿ� ����

        // ���� �г� ���� �� ����
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(247, 244, 242));  // ���� ����
        mainPanel.setLayout(new FlowLayout());  // �÷ο� ���̾ƿ� ����

        // ȯ�� �޽��� ���̺�
        JLabel welcomeLabel = new JLabel("ȯ���մϴ�!");  // ��Ʈ ���� ���� �⺻ ���
        mainPanel.add(welcomeLabel);  // �гο� ���̺� �߰�

        add(mainPanel, BorderLayout.CENTER);  // �����ӿ� ���� �г� �߰�

        // �α��� �г� ���� �� ����
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);  // ���� ���̾ƿ� ���
        loginPanel.setBackground(new Color(247, 244, 242));  // ���� ����

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

        setVisible(true);  // ������ ���̱�
    }

    public void addListeners() {
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LogRegisterForm(users);  // ȸ������ �� ����
                tfId.setText("");  // �Է� �ʱ�ȭ
                tfPw.setText("");  // �Է� �ʱ�ȭ
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfId.getText();
                String password = String.valueOf(tfPw.getPassword());

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginForm.this, "���̵� �Է��ϼ���.", "�α�����", JOptionPane.WARNING_MESSAGE);
                } else if (!users.contains(new User(id))) {
                    JOptionPane.showMessageDialog(LoginForm.this, "�������� �ʴ� Id�Դϴ�.");
                } else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginForm.this, "��й�ȣ�� �Է��ϼ���.", "�α�����", JOptionPane.WARNING_MESSAGE);
                } else if (!users.getUser(id).getPw().equals(password)) {
                    JOptionPane.showMessageDialog(LoginForm.this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                } else {
                    setVisible(false);
                    tfId.setText("");  // �Է� �ʱ�ȭ
                    tfPw.setText("");  // �Է� �ʱ�ȭ
                    //new MainBoard();  // ���� ���� ȣ��
                }
            }
        });
    }
   }