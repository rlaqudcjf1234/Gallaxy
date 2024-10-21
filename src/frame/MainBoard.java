package frame;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainBoard extends JFrame {
	
	private ImageIcon imgComm, imgMyP, imgHC, imgLogoA;
	private JButton btnComm, btnMyP, btnHC;

	public MainBoard() {
		// 메인 화면 생성
		mainView();
		addImage(); // 이미지 및 버튼 추가
	}

	public void mainView() {
		setTitle("메인화면");
		setBounds(700, 100, 500, 750); // 위치와 크기 (이후 850)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료시 프로그램 종료
		getContentPane().setBackground(new Color(247, 244, 242)); // 배경 색
		setLayout(null); // 절대 레이아웃 사용

		// 종료 이벤트 추가
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
		// JFrame 보이게 설정
	}

	private void addImage() {
		// 패널 생성 및 레이아웃 설정
		JPanel mainBoard = new JPanel(null); // null 레이아웃 사용
		mainBoard.setBounds(0, 0, 500, 750); // 패널 위치와 크기 설정(이후 세로길이 850으로 수정)

		// 이미지 아이콘 생성
		imgComm = new ImageIcon("resources/community.png");
		imgHC = new ImageIcon("resources/hc.png");
		imgMyP = new ImageIcon("resources/mypage.png");
		imgLogoA = new ImageIcon("resources/logo.png");

		// 이미지 크기 조정 (필요 시)
		Image imgLogo = imgLogoA.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		imgLogoA = new ImageIcon(imgLogo); // 조정된 이미지로 업데이트

		// 버튼 생성 및 이미지 아이콘 추가
		btnComm = new JButton("커뮤니티", imgComm);
		btnHC = new JButton("헬스 케어", imgHC);
		btnMyP = new JButton("마이 페이지", imgMyP);

		// 버튼 위치 및 크기 설정
		btnComm.setBounds(29, 412, 127, 127);
		btnHC.setBounds(185, 256, 127, 127);
		btnMyP.setBounds(341, 100, 127, 127);

		// 패널에 버튼 추가
		mainBoard.add(btnComm);
		mainBoard.add(btnHC);
		mainBoard.add(btnMyP);

		// 버튼 이벤트 -> 커뮤니티
		btnComm.addActionListener(e -> { //BoardListFrame 및 다른 파일에 생성자 있어야됨
			System.out.println("커뮤니티 버튼 클릭됨!"); // 디버그용 메시지
			 new BoardListFrame().setVisible(true); // 새로운 창 열기
			 
			dispose(); // 현재 창 닫기
		});
		// 버튼 이벤트 -> 헬스 케어
		btnHC.addActionListener(e -> {
			System.out.println("헬스 케어 버튼 클릭됨!"); // 디버그용 메시지
			new BoardListFrame().setVisible(true); // 이후에 BoardListFrame을 헬스케어 파일로 변경
			
			dispose(); // 현재 창 닫기
		});
		// 버튼 이벤트 -> 커뮤니티
		btnMyP.addActionListener(e -> {
			System.out.println("마이 페이지 버튼 클릭됨!"); // 디버그용 메시지
			new myPageBegin(); // 이후에 BoardListFrame을 마이 페이지 파일로 변경
			
			dispose(); // 현재 창 닫기
		});
		// 패널을 JFrame에 추가
		add(mainBoard);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MainBoard());
	}
}
