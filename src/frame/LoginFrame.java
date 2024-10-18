package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginFrame extends JFrame {
	public LoginFrame() {
		setTitle("메인 화면"); // 창 제목 설정
		setBounds(650, 0, 500, 750); // 창 크기 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 시 프로그램 종료
		setLayout(new BorderLayout()); // 레이아웃 설정

		// 메인 패널 생성
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(247, 244, 242)); // 배경색 설정
		mainPanel.setLayout(new FlowLayout()); // 플로우 레이아웃 설정

		// 메인 화면에 추가할 내용
		JLabel welcomeLabel = new JLabel("환영합니다!"); // 환영 메시지 레이블
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // 폰트 설정

		mainPanel.add(welcomeLabel); // 패널에 레이블 추가

		add(mainPanel, BorderLayout.CENTER); // 프레임에 패널 추가

		setVisible(true); // 창 보이도록 설정
	}
}