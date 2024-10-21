package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class myPageBegin extends JPanel {
	private JLabel infoLabel; // 정보를 표시할 JLabel 추가
	private List<Post> posts; // 게시글 목록
	private JPanel postPanel; // 게시글을 표시할 패널

	public myPageBegin() {
		setBackground(new Color(111, 111, 111));
		setLayout(null);
		setSize(500, 850);

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

		// 정보 표시용 JLabel 설정
		infoLabel = new JLabel("사용자 정보"); // 초기화 추가
		// infoLabel = new JLabel(loggedInUser.getInfo()); // 정보 가져오기 (임시 클래스 가져옴 )
		infoLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		// 배경색 설정
		infoLabel.setOpaque(true); // 배경색을 보이게 설정
		infoLabel.setBackground(new Color(255, 255, 255)); // 흰색 배경
		infoLabel.setForeground(new Color(0, 0, 0)); // 텍스트 색상

		Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // 검은색, 두께 2
		infoLabel.setBorder(border); // JLabel에 테두리 추가

		// 크기 설정
		int labelWidth = 450;
		int labelHeight = 150;
		infoLabel.setSize(labelWidth, labelHeight); // JLabel 크기 설정

		// 위치 설정
		infoLabel.setBounds(((int) getSize().getWidth() / 2) - (labelWidth / 2), // 중앙 정렬
				btnEdit.getY() + btnEdit.getHeight() + 10, // btnEdit 아래에 위치
				labelWidth, // 설정한 넓이
				labelHeight // 설정한 높이
		);
		add(infoLabel); // JLabel 추가
		// ===========================게시글 전시 =============================
		// 게시글 목록 초기화
		posts = new ArrayList<>();
		loadPosts(); // 게시글 로드

		// 게시글 표시 패널 설정
		postPanel = new JPanel();
		postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
		postPanel.setBackground(Color.WHITE);

		// 게시글 추가
		for (Post post : posts) {
			JLabel postLabel = new JLabel(
					"<html><strong>" + post.getTitle() + "</strong><br>" + post.getContent() + "</html>");
			postLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			postPanel.add(postLabel);
		}

		JScrollPane scrollPane = new JScrollPane(postPanel);
		scrollPane.setBounds(25, 170, 450, 500); // 위치와 크기 설정
		add(scrollPane);

		// 버튼 이벤트 설정 (생략)

		// 버튼 이벤트(뒤로가기 / 정보수정, 로그아웃)
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent panel) {
				// backToMainPage 클래스 미구현으로 이동만 가능
				myPageBase.getInstance(new myPageBegin());

			}
		});

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent panel) {
				System.out.println("btnEdit 클릭클릭");
				myPageBase.getInstance(new myPageEdit());

			}
		});
		add(btnBack);
		add(btnEdit);

	}

	private void loadPosts() {
        // 게시글 데이터 추가
        posts.add(new Post("첫 번째 게시글", "이것은 첫 번째 게시글의 내용입니다."));
        posts.add(new Post("두 번째 게시글", "이것은 두 번째 게시글의 내용입니다."));
        posts.add(new Post("세 번째 게시글", "이것은 세 번째 게시글의 내용입니다."));
        posts.add(new Post("네 번째 게시글", "이것은 네 번째 게시글의 내용입니다."));
        posts.add(new Post("다섯 번째 게시글", "이것은 다섯 번째 게시글의 내용입니다."));
	}
	public static void main(String[] args) {
		// myPageBegin 클래스의 인스턴스를 생성하고, 이를 화면에 표시합니다.
		JFrame frame = new JFrame("마이페이지");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 850);

		myPageBegin panel = new myPageBegin(); // myPageBegin 패널 생성
		frame.add(panel); // 패널을 프레임에 추가

		frame.setVisible(true); // 프레임을 보이도록 설정
	}
}

//Post 클래스
class Post {
	private String title;
	private String content;

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
}
