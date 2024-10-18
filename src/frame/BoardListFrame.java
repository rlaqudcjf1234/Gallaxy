package frame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BoardListFrame {

	// ------------------------------------------------------------------
	public static void addWrite(String title, String content) {
		// 제목 버튼 생성
		JLabel writeLabel = new JLabel(title);
		writeLabel.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		writeLabel.setFont(new java.awt.Font("나눔고딕", java.awt.Font.PLAIN, 16));
		writeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 제목 클릭 시 내용 표시
				JOptionPane.showMessageDialog(mainFrame, content, title, 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		postListPanel.add(writeLabel);
		postListPanel.revalidate(); // 패널 갱신
		postListPanel.repaint(); // 화면 재렌더링
	}

	// ------------------------------------------------------------------

	private static JPanel postListPanel = new JPanel(); // 게시물 목록 패널
	private static Frame mainFrame;

	public static void main(String[] args) {
		Frame mainFrame = new Frame("러닝 메이트 게시판");
		mainFrame.setBounds(700, 100, 500, 850); // 위치와 크기
		mainFrame.setBackground(new Color(247, 244, 242)); // 배경 색
		mainFrame.setLayout(null); // 절대 레이아웃 사용

		// 두개의 버튼 생성
		Button btnWrite = new Button("글 작성");
		JButton btnLogo = new JButton();

		ImageIcon logoIcon = new ImageIcon("Running Mate.png");
		btnLogo.setIcon(logoIcon);
		btnLogo.setBorderPainted(false); // 버튼 테두리 제거
		btnLogo.setContentAreaFilled(false); // 버튼 배경 제거
		btnLogo.setFocusPainted(false); // 포커스 효과 제거
		btnLogo.setBounds(30, 50, 110, 95); // 버튼 위치 및 크기 설정
		// 클릭 이벤트 추가
		btnLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "메인화면!");
			}
		});

		// 초기 버튼 위치 및 크기 설정 (기준값)
		btnWrite.setBounds(360, 170, 70, 30);
		btnWrite.setBackground(Color.LIGHT_GRAY);

		// 프레임에 버튼 추가
		mainFrame.add(btnWrite);
		mainFrame.add(btnLogo);

		// 이미지 추가
		JLabel labelLogo = new JLabel(); // 이미지 표시를 위한 Label
		ImageIcon icon = new ImageIcon("gaesipan11.png");
		labelLogo.setIcon(icon); // JLabel에 이미지 설정
		labelLogo.setBounds(120, 130, 230, 80); // 이미지 위치 및 크기 설정

		// 프레임에 Label 추가
		mainFrame.add(labelLogo);

		// 글 작성 버튼 클릭 이벤트 추가
		btnWrite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new BoardWriteFrame(mainFrame); // WriteBoard 프레임 열기
				mainFrame.setVisible(false); // 메인 프레임 숨기기
			}
		});

//		// TextField와 TextArea 추가
//		TextField textFieldTitle = new TextField("제목을 입력하세요"); // 제목 입력
//		TextArea textAreaContent = new TextArea("내용을 입력하세요"); // 내용
//
//		// 입력 칸 위치 및 크기 설정
//		textFieldTitle.setBounds(50, 220, 400, 30); // 제목 입력칸 위치와 크기
//		textAreaContent.setBounds(50, 270, 400, 200); // 내용 입력칸 위치와 크기
//
//		// 마우스 클릭 시 기본 텍스트 지우기
//		textFieldTitle.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (textFieldTitle.getText().equals("제목을 입력하세요")) {
//					textFieldTitle.setText(""); // 클릭 시 텍스트 초기화
//				}
//			}
//		});
//
//		textAreaContent.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (textAreaContent.getText().equals("내용을 입력하세요")) {
//					textAreaContent.setText(""); // 클릭 시 텍스트 초기화
//				}
//			}
//		});
//
//        // 프레임에 TextField와 TextArea 추가
//        mainFrame.add(textFieldTitle);
//        mainFrame.add(textAreaContent);

		// 게시물 목록 패널 설정
		postListPanel.setLayout(new BoxLayout(postListPanel, BoxLayout.Y_AXIS)); // 세로로 버튼 나열
		JScrollPane scrollPane = new JScrollPane(postListPanel); // 스크롤 가능하게
		scrollPane.setBounds(50, 220, 400, 400);
		mainFrame.add(scrollPane);

		// 로그아웃 텍스트 추가
		JLabel logoutLabel = new JLabel("로그아웃");
		logoutLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		logoutLabel.setBounds(260, 50, 100, 40); // 위치와 크기 조정
		logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 로그아웃 클릭 이벤트 추가
		logoutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "로그아웃 합니다!");
			}
		});

		mainFrame.add(logoutLabel); // 프레임에 마이페이지 텍스트 추가

		// 로그아웃 텍스트 추가
		JLabel myPageLabel = new JLabel("마이페이지");
		myPageLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		myPageLabel.setBounds(330, 50, 100, 40); // 위치와 크기 조정
		myPageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 로그아웃 클릭 이벤트 추가
		myPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "마이페이지로 이동!");
			}
		});

		mainFrame.add(myPageLabel); // 프레임에 마이페이지 텍스트 추가

		// 메인화면 텍스트 추가
		JLabel mainPageLabel = new JLabel("메인화면");
		mainPageLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		mainPageLabel.setBounds(415, 50, 100, 40); // 위치와 크기 조정
		mainPageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 로그아웃 클릭 이벤트 추가
		mainPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "메인화면으로 이동!");
			}
		});

		mainFrame.add(mainPageLabel); // 프레임에 메인화면 텍스트 추가

		// 화면이 보임
		mainFrame.setVisible(true);

		// 종료 -----------------------------------------
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
