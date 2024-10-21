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
import javax.swing.SwingUtilities;

public class myPageBegin extends JFrame {
	private JLabel infoLabel; // 정보를 표시할 JLabel 추가
	private List<Post> posts; // 게시글 목록
	private JPanel postPanel; // 게시글을 표시할 패널

	public myPageBegin() {
		MP();
	}

	public void MP() {
		setTitle("마이페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 850); // 프레임 사이즈
		setLayout(null); // null 레이아웃 사용

		JPanel panel = new JPanel();
		panel.setLayout(null); // 패널도 null 레이아웃 사용
		panel.setBounds(0, 0, 500, 850); // 패널 크기 설정
		panel.setBackground(new Color(111, 111, 111));

		// 하단의 버튼 설정 (뒤로가기 - btnBack // 회원정보 변경/ 로그아웃 - btnEdit )
		JButton btnBack = new JButton("뒤로가기");
		JButton btnEdit = new JButton("회원정보 변경/ 로그아웃");

		btnBack.setBackground(Color.BLACK);
		btnEdit.setBackground(Color.BLACK);

		btnBack.setSize(450, 35);
		btnBack.setLocation(25, 700); // 하단 위치

		btnBack.setFont(new Font("굴림", Font.BOLD, 14));
		btnEdit.setSize(450, 35);
		btnEdit.setLocation(25, 650); // btnBack 위에 위치

		btnEdit.setFont(new Font("굴림", Font.BOLD, 14));

		// 정보 표시용 JLabel 설정
		infoLabel = new JLabel("사용자 정보"); // 초기화 추가
		infoLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		infoLabel.setOpaque(true); // 배경색을 보이게 설정
		infoLabel.setBackground(Color.WHITE); // 흰색 배경
		infoLabel.setForeground(Color.BLACK); // 텍스트 색상
		infoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 테두리 추가

		// 크기 및 위치 설정
		infoLabel.setBounds(25, 50, 450, 150); // 중앙 정렬 및 크기 설정
		panel.add(infoLabel); // JLabel 추가

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
		scrollPane.setBounds(25, 220, 450, 400); // 위치와 크기 설정
		panel.add(scrollPane);

		// 버튼 이벤트 설정
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainBoard(); // MainBoard 인스턴스 생성
				dispose(); // 현재 창 닫기
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new myPageEdit();
				// myPageBase.getInstance(new myPageEdit());
			}
		});

		panel.add(btnBack);
		panel.add(btnEdit);
		add(panel); // 패널을 프레임에 추가
		setVisible(true); // 프레임을 보이도록 설정
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
		new myPageBegin(); // myPageBegin 클래스의 인스턴스를 생성하여 표시
	}
}

// Post 클래스
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
