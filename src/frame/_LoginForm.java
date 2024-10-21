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
        this.users=users;  // 유저 데이터 초기화
        showFrame();  // 프레임 보여주기 및 컴포넌트 초기화
        addListeners();  // 이벤트 리스너 추가 (컴포넌트 초기화 이후에 호출)
    }

    public void showFrame() {
        setTitle("메인 화면");  // 창 제목 설정
        setBounds(650, 0, 500, 750);  // 창 위치 및 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 종료 시 프로그램 종료
        setLayout(new BorderLayout());  // 레이아웃 설정

        // 메인 패널 생성 및 설정
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(247, 244, 242));  // 배경색 설정
        mainPanel.setLayout(new FlowLayout());  // 플로우 레이아웃 설정

        // 환영 메시지 레이블
        JLabel welcomeLabel = new JLabel("환영합니다!");  // 폰트 설정 없이 기본 사용
        mainPanel.add(welcomeLabel);  // 패널에 레이블 추가

        add(mainPanel, BorderLayout.CENTER);  // 프레임에 메인 패널 추가

        // 로그인 패널 생성 및 설정
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);  // 절대 레이아웃 사용
        loginPanel.setBackground(new Color(247, 244, 242));  // 배경색 설정

        // 컴포넌트 초기화
        lblId = new JLabel("ID:");
        lblPw = new JLabel("Password:");
        tfId = new JTextField();
        tfPw = new JPasswordField();
        btnLogin = new JButton("로그인");
        btnRegister = new JButton("회원가입");

        // 컴포넌트 위치 및 크기 설정
        lblId.setBounds(130, 250, 140, 30);
        tfId.setBounds(130, 280, 200, 30);
        lblPw.setBounds(130, 310, 140, 30);
        tfPw.setBounds(130, 340, 200, 30);
        btnLogin.setBounds(130, 400, 90, 30);
        btnRegister.setBounds(240, 400, 90, 30);

        // 패널에 컴포넌트 추가
        loginPanel.add(lblId);
        loginPanel.add(lblPw);
        loginPanel.add(tfId);
        loginPanel.add(tfPw);
        loginPanel.add(btnLogin);
        loginPanel.add(btnRegister);

        // 프레임에 패널 추가
        add(loginPanel);

        setVisible(true);  // 프레임 보이기
    }

    public void addListeners() {
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LogRegisterForm(users);  // 회원가입 폼 열기
                tfId.setText("");  // 입력 초기화
                tfPw.setText("");  // 입력 초기화
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfId.getText();
                String password = String.valueOf(tfPw.getPassword());

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginForm.this, "아이디를 입력하세요.", "로그인폼", JOptionPane.WARNING_MESSAGE);
                } else if (!users.contains(new User(id))) {
                    JOptionPane.showMessageDialog(LoginForm.this, "존재하지 않는 Id입니다.");
                } else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginForm.this, "비밀번호를 입력하세요.", "로그인폼", JOptionPane.WARNING_MESSAGE);
                } else if (!users.getUser(id).getPw().equals(password)) {
                    JOptionPane.showMessageDialog(LoginForm.this, "비밀번호가 일치하지 않습니다.");
                } else {
                    setVisible(false);
                    tfId.setText("");  // 입력 초기화
                    tfPw.setText("");  // 입력 초기화
                    //new MainBoard();  // 메인 보드 호출
                }
            }
        });
    }
   }