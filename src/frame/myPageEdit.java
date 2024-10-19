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
	
	private String correctPassword = "yourCorrectPassword"; // 실제 비밀번호로 변경 필요
	
	public myPageEdit() {
		
		setBackground(new Color(111, 111, 111));
		setLayout(new GridBagLayout());
		setSize(500, 850);
		
		// 패널 생성
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 5, 10);

		// 버튼 생성
		JButton btnGoToMp = new JButton("뒤로가기"); // 마이페이지 메인 화면으로 돌아가기
		JButton btnConfirm = new JButton("수정하기"); // 변경된 정보로 수정
		JButton btnLogout = new JButton("로그아웃"); // 변경된 정보로 수정

		// 버튼 색상
		btnGoToMp.setBackground(Color.GRAY);
		btnConfirm.setBackground(Color.GRAY);
		btnLogout.setBackground(Color.GRAY);
		// 버튼 사이즈
		btnGoToMp.setSize(450, 35);
		btnConfirm.setSize(450, 35);
		btnLogout.setSize(450, 35);

		// 비밀번호 입력 및 재확인
		JLabel currentPasswordLabel = new JLabel("기존 비밀번호:");
		JPasswordField currentPassword = new JPasswordField(15);

		JLabel confirmPasswordLabel = new JLabel("비밀번호 재확인:");
		JPasswordField confirmPassword = new JPasswordField(15);

		//JButton submitButton = new JButton("확인");

		// 컴포넌트 배치
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
		gbc.anchor = GridBagConstraints.CENTER; // 중앙 정렬
		panel.add(btnGoToMp, gbc);

		gbc.gridy = 3;
		panel.add(btnConfirm, gbc);

		gbc.gridy = 4;
		panel.add(btnLogout, gbc);

		add(panel);

		// 버튼 액션 리스너
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent panel) {
                String inputCurrentPassword = new String(currentPassword.getPassword());
                String inputConfirmPassword = new String(confirmPassword.getPassword());

                // 비밀번호 확인 로직
                if (inputCurrentPassword.equals(correctPassword) && inputCurrentPassword.equals(inputConfirmPassword)) {
                    // 비밀번호가 일치할 경우
                    JOptionPane.showMessageDialog(null, "비밀번호가 확인되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
                    // 다음 단계로 넘어가는 로직을 여기에 추가
                } else {
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

		btnGoToMp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent panel) {
				 myPageBase.getInstance(new myPageBegin());
				// 마이페이지 메인 화면으로 돌아가기
				// 해당 로직 추가
			}
		});

		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent panel) {
				// 로그아웃 처리 로직 추가
				JOptionPane.showMessageDialog(null, "로그아웃되었습니다.", "로그아웃", JOptionPane.INFORMATION_MESSAGE);
				// 로그인 화면으로 이동하는 로직 추가
			}
		});
	}
	


	
}