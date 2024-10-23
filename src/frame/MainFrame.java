package frame;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.UserDTO;
import main.Main;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2404755582124014856L;
	
	private ImageIcon imgComm, imgMyP, imgHC, imgLogoA;
	private JButton btnComm, btnMyP, btnHC, btnAd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDTO userDTO = new UserDTO();
					userDTO.setUserId("테스트");
					Main.USER = userDTO;
					
					MainFrame frame = new MainFrame();
					// X버튼 종료
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MainFrame() {
		// 메인 화면 생성
		mainView();
		addImage(); // 이미지 및 버튼 추가
	}

	public void mainView() {
		// 프레임 타이틀바
		setTitle("메인화면");
		// 프레임 위치, 크기(픽셀)
		setBounds(700, 100, 500, 850);
		// 종료시 프로그램 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(247, 244, 242)); // 배경 색
		setLayout(null); // 절대 레이아웃 사용
		setVisible(true);
		// JFrame 보이게 설정
	}

	private void addImage() {
		JPanel mainBoard = new JPanel(null);
		mainBoard.setBounds(0, 0, 500, 750); // 이후 850로 변경 가능
		mainBoard.setBackground(new Color(247, 244, 242)); // 패널 배경

		// 이미지 크기 조정 및 버튼에 설정
		imgComm = resizeImage(new ImageIcon("image/Community.png"), 80, 80);
		imgHC = resizeImage(new ImageIcon("image/HealthCare.png"), 80, 80);
		imgMyP = resizeImage(new ImageIcon("image/MyPage.png"), 80, 80);
		imgLogoA = resizeImage(new ImageIcon("image/Galaxy_Logo.png"), 200, 200);

		// 로고를 JLabel에 추가
		JLabel lblLogo = new JLabel(imgLogoA);
		lblLogo.setBounds(150, 50, 200, 200); // 중앙 상단에 위치

		// 버튼 생성 및 설정
		btnComm = new JButton("러닝 메이트", imgComm);
		btnHC = new JButton("헬스 케어", imgHC);
		btnMyP = new JButton("내 정보", imgMyP);

		// 버튼 위치 설정
		btnComm.setBounds(29, 300, 127, 127);
		btnHC.setBounds(185, 300, 127, 127);
		btnMyP.setBounds(341, 300, 127, 127);

		// 버튼 배경색 및 테두리 설정
		btnComm.setBackground(new Color(247, 244, 242));
		btnHC.setBackground(new Color(247, 244, 242));
		btnMyP.setBackground(new Color(247, 244, 242));

		btnComm.setBorderPainted(false);
		btnHC.setBorderPainted(false);
		btnMyP.setBorderPainted(false);

		// 텍스트와 이미지 정렬 설정
		btnComm.setHorizontalTextPosition(JButton.CENTER);
		btnComm.setVerticalTextPosition(JButton.BOTTOM);

		btnHC.setHorizontalTextPosition(JButton.CENTER);
		btnHC.setVerticalTextPosition(JButton.BOTTOM);

		btnMyP.setHorizontalTextPosition(JButton.CENTER);
		btnMyP.setVerticalTextPosition(JButton.BOTTOM);
		
		// 패널에 버튼 추가
		mainBoard.add(btnComm);
		mainBoard.add(btnHC);
		mainBoard.add(btnMyP);
		mainBoard.add(lblLogo);

		// 여백 설정
		btnComm.setMargin(new java.awt.Insets(5, 5, 5, 5));
		btnHC.setMargin(new java.awt.Insets(5, 5, 5, 5));
		btnMyP.setMargin(new java.awt.Insets(5, 5, 5, 5));

		btnComm.setForeground(Color.BLACK);
		btnHC.setForeground(Color.BLACK);
		btnMyP.setForeground(Color.BLACK);

		// 이벤트 리스너 추가 (기존과 동일)
		btnComm.addActionListener(e -> {
			System.out.println("커뮤니티 버튼 클릭됨!");
			BoardListFrame.search = null;
			new BoardListFrame();
			dispose();
		});
		btnHC.addActionListener(e -> {
			System.out.println("헬스 케어 버튼 클릭됨!");
			new CalendarFrame();
			dispose();
		});
		btnMyP.addActionListener(e -> {
			System.out.println("마이 페이지 버튼 클릭됨!");
			new MyInfoReadFrame();
			dispose();
		});
		

		ImageIcon logoIcon2 = resizeImage(new ImageIcon("SolDesk_Ad.png"), 500, 300); // 버튼 크기에 맞게 조정
		btnAd = new JButton();
		btnAd.setIcon(logoIcon2);
		btnAd.setBorderPainted(false); // 버튼 테두리 제거
		btnAd.setContentAreaFilled(false); // 버튼 배경 제거
		btnAd.setFocusPainted(false); // 포커스 효과 제거
		btnAd.setMargin(null);  // 마진 제거
		btnAd.setBounds(0, 585, 490, 130); // 버튼 위치 및 크기 설정
		// 클릭 이벤트 추가
		btnAd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String url = "https://soldesk.com/";

				try {
					// URI로 변환하고 기본 웹 브라우저에서 열기
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException | URISyntaxException ex) {
					ex.printStackTrace(); // 예외 처리
				}
			}
		});
		mainBoard.add(btnAd);

		// 패널을 JFrame에 추가
		add(mainBoard);
	}

	// 이미지 크기 조절 메서드
	private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
		Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}
}
