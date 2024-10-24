package frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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

public class MyInfoModifyFrame extends JFrame {

	private UserService us = new UserServiceImpl();

	public MyInfoModifyFrame() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 800); // 프레임 사이즈
		setLocation(1200, 100);
		setLayout(null); // null 레이아웃 사용

		// 로고이미지

		JButton logo = new JButton();
		ImageIcon logoIcon = new ImageIcon("LogoImage_130x130.png");
		logo.setIcon(logoIcon);
		logo.setBorderPainted(false); // 버튼 테두리 제거
		logo.setContentAreaFilled(false); // 버튼 배경 제거
		logo.setFocusPainted(false); // 포커스 효과 제거
		logo.setBounds(155, 50, 190, 190);// 버튼 위치 및 크기 설정

		JPanel panel = new JPanel();
		panel.setLayout(null); // 패널도 null 레이아웃 사용
		panel.setBounds(0, 0, 500, 800); // 패널 크기 설정
		panel.setBackground(Color.WHITE);

		// 컴포넌트 생성
		JLabel nickNameLabel = new JLabel("닉네임 :");
		JTextField nickNameField = new JTextField(Main.USER.getUserNickName());
		JLabel passwordLabel = new JLabel("새 비밀번호:");
		JPasswordField newPasswordField = new JPasswordField();
		JLabel confirmPasswordLabel = new JLabel("비밀번호 재확인:");
		JPasswordField confirmPasswordField = new JPasswordField();
		JLabel emailLabel = new JLabel("이메일 :");
		JTextField emailField = new JTextField(Main.USER.getUserEmail());
		JButton saveButton = new JButton("저장");
		

		newPasswordField.setForeground(Color.GRAY);
		confirmPasswordLabel.setForeground(Color.GRAY);
		emailLabel.setForeground(Color.GRAY);
		nickNameLabel.setForeground(Color.GRAY);

		// 위치 및 크기 설정
		nickNameLabel.setBounds(80, 300, 100, 30);
		nickNameField.setBounds(175, 300, 200, 30);
		emailLabel.setBounds(80, 350, 100, 30);
		emailField.setBounds(175, 350, 200, 30);
		passwordLabel.setBounds(80, 400, 100, 30);
		newPasswordField.setBounds(175, 400, 200, 30);
		confirmPasswordLabel.setBounds(80, 450, 150, 30);
		confirmPasswordField.setBounds(175, 450, 200, 30);
		saveButton.setBounds(80, 500, 300, 30);
		//backToCheck.setBounds(80, 540, 300, 30);
		// 패널에 컴포넌트 추가
		panel.add(nickNameLabel);
		panel.add(nickNameField);
		panel.add(emailLabel);
		panel.add(emailField);
		panel.add(passwordLabel);
		panel.add(newPasswordField);
		panel.add(confirmPasswordLabel);
		panel.add(confirmPasswordField);
		panel.add(saveButton);
		panel.add(logo);

		// 프레임에 패널 추가
		add(panel);

		// 버튼 액션 리스너 설정
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDTO userDTO = new UserDTO();
				if (Main.USER != null) {
					userDTO.setUserId(Main.USER.getUserId());
				}

				String email = emailField.getText();
				userDTO.setUserEmail(email);
				String nickName = nickNameField.getText();
				userDTO.setUserNickName(nickName);
				String newPassword = new String(newPasswordField.getPassword());
				String confirmPassword = new String(confirmPasswordField.getPassword());

				// 이메일, 닉네임 업데이트

				// 비밀번호 확인
				if (!newPassword.equals("")) {
					if (!newPassword.equals(confirmPassword)) {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
						return; // 비밀번호가 일치하지 않으면 이후 로직 실행 중지
					}
					userDTO.setUserPw(newPassword);
				}

				int cnt = us.updateUser(userDTO);
				if (cnt > 0) {
					// 데이터베이스에 정보 저장 로직 추가
					Main.USER.setUserEmail(email);
					Main.USER.setUserNickName(nickName);
					if (newPassword != "") {
						Main.USER.setUserPw(newPassword);
					}
					JOptionPane.showMessageDialog(null, "회원정보가 수정되었습니다.");
				} else {
					// 데이터베이스에 정보 저장 실패
					JOptionPane.showMessageDialog(null, "회원정보 수정 실패!, 다시 시도해 주세요.", "오류",
							JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}

		});

		setVisible(true); // 프레임을 보이도록 설정
	}
}