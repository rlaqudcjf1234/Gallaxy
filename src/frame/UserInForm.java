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
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 시 프로그램 종료
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
		setTitle("메인 화면"); // 창 제목 설정
		setBounds(650, 0, 500, 750); // 창 위치 및 크기 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 시 프로그램 종료
		setLayout(new BorderLayout()); // 레이아웃 설정

		// 메인 패널 생성 및 설정
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(247, 244, 242)); // 배경색 설정
		mainPanel.setLayout(new FlowLayout()); // 플로우 레이아웃 설정

		// 환영 메시지 레이블
		JLabel welcomeLabel = new JLabel("환영합니다!"); // 폰트 설정 없이 기본 사용
		mainPanel.add(welcomeLabel); // 패널에 레이블 추가

		add(mainPanel, BorderLayout.CENTER); // 프레임에 메인 패널 추가

		// 로그인 패널 생성 및 설정
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null); // 절대 레이아웃 사용
		loginPanel.setBackground(new Color(247, 244, 242)); // 배경색 설정

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

		// 창이 보이도록 설정
		setVisible(true);

		// 회원가입 클릭 이벤트
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JFrame logRegisterForm = new UserRegForm(); // 회원가입 폼 열기
				logRegisterForm.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						setVisible(true);
					}
				});

				tfId.setText(""); // 입력 초기화
				tfPw.setText(""); // 입력 초기화
			}
		});

		// 로그인 클릭 이벤트
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = tfId.getText();
				String userPw = String.valueOf(tfPw.getPassword());

				if (userId.isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요.", "로그인폼", JOptionPane.WARNING_MESSAGE);
					return;

				}
				if (userPw.isEmpty()) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.", "로그인폼", JOptionPane.WARNING_MESSAGE);
					return;
				}

				UserDTO dto = us.selectUser(userId);

				if (dto == null) {
					JOptionPane.showMessageDialog(null, "존재하지 않는 Id입니다.");
					return;
				}
				if (!userPw.equals(dto.getUserPw())) {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					return;
				}
				
				Main.USER = dto;
				
				JOptionPane.showMessageDialog(null, dto.getUserNickName()+"님 로그인에 성공하셨습니다.", "로그인폼", JOptionPane.INFORMATION_MESSAGE);
				
				new MainBoard();
				dispose();
			}
		});
	}
}
