package frame;


import java.awt.Color;
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
	
    private JFrame frame; // 메인 프레임
    private JPanel panel; // 패널


	public  myPageEdit () {
		
		 frame = new JFrame("회원정보 수정");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setBounds(100,100,500,400);
	        frame.setLayout(null); // null 레이아웃 사용
	        
	        panel = new JPanel();
	        panel.setLayout(null); // 패널도 null 레이아웃 사용
	        panel.setBounds(0, 0, 500, 400); // 패널 크기 설정
	        panel.setBackground(new Color(247, 244, 242));
	        
	        // 컴포넌트 생성
	        JLabel passwordLabel = new JLabel("비밀번호");
	        JPasswordField passwordField = new JPasswordField();
	        JButton confirmButton = new JButton("확인");
	        JButton backToMyPageBegin = new JButton("뒤로가기");
	        

	        // 위치 및 크기 설정
	        passwordLabel.setBounds(110, 120, 200, 30); // (x, y, width, height)
	        passwordField.setBounds(170, 120, 200, 30);
	        confirmButton.setBounds(90, 170, 300, 30);
	        backToMyPageBegin.setBounds(90, 210, 300, 30);
	        

	        // 패널에 컴포넌트 추가
	        panel.add(passwordLabel);
	        panel.add(passwordField);
	        panel.add(confirmButton);
	        panel.add(backToMyPageBegin);
	        
	        
	        //프레임에 패널 추가
	        frame.add(panel);
	        
	    	panel.setVisible(true);
		
		
		
		

		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String enteredPassword = new String(passwordField.getPassword());
				if (checkPassword(enteredPassword)) {
					openUpdateInfoWindow();
					frame.dispose(); // 현재 창 닫기
				} else {
					JOptionPane.showMessageDialog(frame, "비밀번호가 틀렸습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		backToMyPageBegin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent panel) {
				frame.dispose();
			}
		});
		
//		 logoutButton.addActionListener(new ActionListener() {
//	            @Override
//	            public void actionPerformed(ActionEvent e) {
//	                logoutUser();
//	                new UserInForm(); 
//	                frame.dispose(); 
//	            }
//	        });

		frame.setVisible(true);
	}

	private void logoutUser() {
        System.out.println("사용자가 로그아웃했습니다.");
    }
	
	private boolean checkPassword(String password) {
		String correctPassword = "1234"; // 예시 비밀번호
		return password.equals(correctPassword);
	}

	private void openUpdateInfoWindow() {
	    JFrame updateFrame = new JFrame("회원정보 수정");
	    updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    updateFrame.setSize(500, 800); // 프레임 사이즈
	    updateFrame.setLayout(null); // null 레이아웃 사용

	    JPanel panel = new JPanel();
	    panel.setLayout(null); // 패널도 null 레이아웃 사용
	    panel.setBounds(0, 0, 500, 800); // 패널 크기 설정

	    // 컴포넌트 생성
	    JLabel nickNameLabel = new JLabel("닉네임 :");
	    JTextField nickNameField = new JTextField();
	    JLabel passwordLabel = new JLabel("새 비밀번호:");
	    JPasswordField newPasswordField = new JPasswordField();
	    JLabel confirmPasswordLabel = new JLabel("비밀번호 재확인:");
	    JPasswordField confirmPasswordField = new JPasswordField();
	    JLabel emailLabel = new JLabel("이메일 :");
	    JTextField emailField = new JTextField();
	    JButton saveButton = new JButton("저장");
	    JButton backToCheck = new JButton("뒤로가기");

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
	    backToCheck.setBounds(80, 540, 300, 30);

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
	    panel.add(backToCheck);

	    // 프레임에 패널 추가
	    updateFrame.add(panel);

	    // 버튼 액션 리스너 설정
	    saveButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String email = emailField.getText();
	            String nickName = nickNameField.getText();
	            String newPassword = new String(newPasswordField.getPassword());
	            String confirmPassword = new String(confirmPasswordField.getPassword());

	            // 비밀번호 확인
	            if (!newPassword.equals(confirmPassword)) {
	                JOptionPane.showMessageDialog(updateFrame, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
	                return; // 비밀번호가 일치하지 않으면 이후 로직 실행 중지
	            }

	            // 데이터베이스에 정보 저장 로직 추가
	            JOptionPane.showMessageDialog(updateFrame, "회원정보가 수정되었습니다.");
	        }
	    });

	    // 정보 수정 창에서 비밀번호 확인 창으로 돌아가기
	    backToCheck.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // 이전 페이지로 돌아가는 로직 추가
	            updateFrame.dispose(); // 현재 창 닫기
	        }
	    });

	    updateFrame.setVisible(true); // 프레임을 보이도록 설정

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				String name = nameField.getText();
				String email = emailField.getText();
				String nickName = nickNameField.getText();
				String newPassword = new String(newPasswordField.getPassword());
				String confirmPassword = new String(confirmPasswordField.getPassword());

				// 비밀번호 확인
				if (!newPassword.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(updateFrame, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
					return; // 비밀번호가 일치하지 않으면 이후 로직 실행 중지
				}

				// 데이터베이스에 정보 저장 로직 추가
				JOptionPane.showMessageDialog(updateFrame, "회원정보가 수정되었습니다.");
			}
		});

//		// 정보 수정 창에서 비밀번호 확인 창으로 돌아가기
//		backToCheck.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent panel) {
//				new myPageEdit();
//				updateFrame.dispose(); // 현재 창 닫기
//			}
//		});

		updateFrame.setVisible(true);
	}
}