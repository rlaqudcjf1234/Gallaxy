package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class CommonFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4707745251137398916L;

	private JPanel contentPanel;

	private JButton logoButton;
	private JLabel firstLabel;
	private JLabel secondLabel;
	private JLabel thirdLabel;

	/**
	 * Create the frame.
	 */
	public CommonFrame() {
		// 위치와 크기
		setBounds(700, 100, 500, 850);
		// 종료시 프로그램 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료시 프로그램 종료
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		// 배경색 흰색
		contentPanel.setBackground(Color.WHITE);

		// 로고 버튼 생성
		logoButton = new JButton();
		logoButton.setIcon(new ImageIcon("LogoImage_130x130.png"));
		logoButton.setBorderPainted(false); // 버튼 테두리 제거
		logoButton.setContentAreaFilled(false); // 버튼 배경 제거
		logoButton.setFocusPainted(false); // 포커스 효과 제거
		logoButton.setBounds(20, 10, 130, 130); // 버튼 위치 및 크기 설정
		contentPanel.add(logoButton);

		// 클릭 이벤트 추가
		logoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "메인화면!");
				new MainFrame();
				dispose();
			}
		});

		// 로그아웃 텍스트 추가
		firstLabel = new JLabel("로그아웃");
		firstLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		firstLabel.setBounds(265, 30, 60, 40); // 위치와 크기 조정
		firstLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서
		contentPanel.add(firstLabel); // 프레임에 내 정보 텍스트 추가

		// 로그아웃 클릭 이벤트 추가
		firstLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UserInForm();
				dispose();
			}
		});

		// 내 정보 텍스트 추가
		secondLabel = setSecondLabel();
		secondLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		secondLabel.setBounds(335, 30, 75, 40); // 위치와 크기 조정
		secondLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서
		contentPanel.add(secondLabel); // 프레임에 내 정보 텍스트 추가

		// 메인화면 텍스트 추가
		thirdLabel = new JLabel("메인화면");
		thirdLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		thirdLabel.setBounds(420, 30, 60, 40); // 위치와 크기 조정
		thirdLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서
		contentPanel.add(thirdLabel); // 프레임에 메인화면 텍스트 추가

		// 메인화면 클릭 이벤트 추가
		thirdLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainFrame();
				dispose();
			}
		});

		setContentPane(contentPanel);
		// 화면이 보임
		setVisible(true);
	}

	public abstract JLabel setSecondLabel();

}
