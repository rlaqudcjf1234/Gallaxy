package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class myPageBegin extends JPanel {
	// 임시 =============================
	private JLabel infoLabel; // 정보를 표시할 JLabel 추가
	private UserData loggedInUser; // 사용자 정보를 위한 UserData 객체
// 임시 =====================================================

	public myPageBegin() {
		setBackground(new Color(111, 111, 111));
		setLayout(null);
		setSize(500, 850);

		loggedInUser = new UserData("홍길동", "hong@example.com"); // 사용자 정보 초기화 임시)))))

		// 하단의 버튼 설정 (뒤로가기 - btnBack // 회원정보 변경/ 로그아웃 - btnEdit )
		JButton btnBack = new JButton("뒤로가기");
		JButton btnEdit = new JButton("회원정보 변경/ 로그아웃");

		btnBack.setBackground(new Color(000, 000, 000));
		btnEdit.setBackground(new Color(000, 000, 000));

		btnBack.setSize(450, 35);
		btnBack.setLocation(
				// x 좌표: 프레임 너비의 절반에서 btnBack 버튼의 너비의 절반을 뺀 값
				((int) getSize().getWidth() / 2) - (btnBack.getWidth() / 2),
				// y 좌표: 프레임 높이에서 150을 뺀 값 (하단 위치)
				getSize().height - 100);

		btnBack.setFont(new Font("굴림", Font.BOLD, 14));
		btnEdit.setSize(450, 35);

		btnEdit.setLocation(((int) getSize().getWidth() / 2) - (btnEdit.getWidth() / 2), 0);

		btnEdit.setFont(new Font("굴림", Font.BOLD, 14));

		add(btnBack);
		add(btnEdit);

		// 정보 표시용 JLabel 설정
		infoLabel = new JLabel(loggedInUser.getInfo()); // 정보 가져오기 (임시 클래스 가져옴 )
		infoLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		// 배경색 설정
		infoLabel.setOpaque(true); // 배경색을 보이게 설정
		infoLabel.setBackground(new Color(255, 255, 255)); // 흰색 배경
		infoLabel.setForeground(new Color(0, 0, 0)); // 텍스트 색상

		Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // 검은색, 두께 2
		infoLabel.setBorder(border); // JLabel에 테두리 추가

		// 크기 설정
		int labelWidth = 450; // 넓이 300
		int labelHeight = 150; // 높이 30
		infoLabel.setSize(labelWidth, labelHeight); // JLabel 크기 설정

		// 위치 설정
		infoLabel.setBounds(((int) getSize().getWidth() / 2) - (labelWidth / 2), // 중앙 정렬
				btnEdit.getY() + btnEdit.getHeight() + 10, // btnEdit 아래에 위치
				labelWidth, // 설정한 넓이
				labelHeight // 설정한 높이
		);
		add(infoLabel); // JLabel 추가

		// 버튼 이벤트(뒤로가기 / 정보수정, 로그아웃)
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// backToMainPage 클래스 미구현으로 이동만 가능
				myPageBase.getInstance(new backToMainPage());
			}
		});

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// myPageEdit 클래스 미구현.
				myPageBase.getInstance(new myPageEdit());

			}
		});

	}
}
