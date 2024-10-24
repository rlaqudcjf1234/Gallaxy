package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dto.UserDTO;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserRegForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5592827020966786913L;
	
	UserService us = new UserServiceImpl();

	private JLabel lblId, lblPw, lblEmail, lblRe, lblName, lblNickName;
	private JRadioButton rbtnMale, rbtnFemale;
	private JTextField tfId, tfEmail, tfName, tfNickName;
	private JPasswordField tfPw, tfRe;
	private JButton btnSignup, btnCancel;
	private ImageIcon imgLogoA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegForm frame = new UserRegForm();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 시 프로그램 종료
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserRegForm() {
		setTitle("회원가입"); // 창 제목 설정
		setBounds(700, 100, 500, 850); // 창 크기 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 시 프로그램 종료
		setBackground(new Color(247, 244, 242));
		setLayout(null); // 절대 레이아웃 사용

		// 회원가입 폼 패널 생성
		JPanel signupPanel = new JPanel();
		signupPanel.setLayout(null); // 절대 레이아웃 사용
		signupPanel.setBounds(0, 0, 500, 850); // 위치 및 크기 설정
		signupPanel.setBackground(new Color(247, 244, 242)); // 배경색 설정

		imgLogoA = resizeImage(new ImageIcon("image/Galaxy_Logo.png"), 200, 200);
		
		// 레이블 및 텍스트 필드

		lblId = new JLabel("ID:");
		lblPw = new JLabel("Password:");
		lblRe = new JLabel("Retry:");
		lblName = new JLabel("Name:");
		lblEmail = new JLabel("Email:");
		lblNickName = new JLabel("NickName:");
		JLabel lblLogo = new JLabel(imgLogoA);
		
		lblLogo.setBounds(150, 50, 200, 200); // 중앙 상단에 위치
		lblId.setBounds(130, 280, 80, 25); // 위치 및 크기 설정
		lblPw.setBounds(130, 320, 80, 25); // 위치 및 크기 설정
		lblRe.setBounds(130, 360, 80, 25);
		lblName.setBounds(130, 400, 80, 25); // 위치 및 크기 설정
		lblEmail.setBounds(130, 440, 80, 25); // 위치 및 크기 설정
		lblNickName.setBounds(130, 480, 80, 25);

		tfId = new JTextField();
		tfPw = new JPasswordField();
		tfRe = new JPasswordField();
		tfName = new JTextField();
		tfEmail = new JTextField();
		tfNickName = new JTextField();

		tfId.setBounds(220, 280, 140, 25); // 위치 및 크기 설정
		tfPw.setBounds(220, 320, 140, 25); // 위치 및 크기 설정
		tfRe.setBounds(220, 360, 140, 25);
		tfName.setBounds(220, 400, 140, 25); // 위치 및 크기 설정
		tfEmail.setBounds(220, 440, 140, 25); // 위치 및 크기 설정
		tfNickName.setBounds(220, 480, 140, 25);

		rbtnMale = new JRadioButton("Male", true); // 남성 선택 기본값
		rbtnFemale = new JRadioButton("Female");

		ButtonGroup group = new ButtonGroup();
		group.add(rbtnMale);
		group.add(rbtnFemale);

		rbtnMale.setBounds(130, 520, 100, 25); // 남성 버튼
		rbtnFemale.setBounds(260, 520, 100, 25); // 여성 버튼

		signupPanel.add(lblLogo);
		signupPanel.add(lblId);
		signupPanel.add(lblPw);
		signupPanel.add(tfId);
		signupPanel.add(tfPw);
		signupPanel.add(lblRe);
		signupPanel.add(tfRe);
		signupPanel.add(lblName);
		signupPanel.add(tfName);
		signupPanel.add(lblEmail);
		signupPanel.add(tfEmail);
		signupPanel.add(lblNickName);
		signupPanel.add(tfNickName);
		signupPanel.add(rbtnMale); // 남성 버튼 추가
		signupPanel.add(rbtnFemale); // 여성 버튼 추가

		// 버튼 추가
		btnSignup = new JButton("회원가입");
		btnCancel = new JButton("취소");

		btnSignup.setBounds(130, 570, 99, 30); // 위치 및 크기 설정
		btnCancel.setBounds(260, 570, 99, 30); // 위치 및 크기 설정

		rbtnMale.setOpaque(false);  // 배경 투명 설정
		rbtnFemale.setOpaque(false);
		
		signupPanel.add(btnSignup);
		signupPanel.add(btnCancel);
		
		// 메인 프레임에 패널 추가
		add(signupPanel);

		// 창이 보이도록 설정
		setVisible(true);

		// 취소 이벤트
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();// 창 닫기
			}
		});

		// 회원가입 버튼 클릭 이벤트 처리
		btnSignup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 입력 값 검증
				if (Blank()) {
					JOptionPane.showMessageDialog(UserRegForm.this, "모든 정보를 입력해주세요.");
					// 모두 입력했을 때
				} else {
					// Id 중복일 때
					UserDTO dto = new UserDTO();
					String userId = tfId.getText();
					
					dto.setUserId(userId);
					
					dto.setUserPw(String.valueOf(tfPw.getPassword()));
					
					dto.setUserName(tfName.getText());

					dto.setUserNickName(tfNickName.getText());

					dto.setUserGender(getGender());
					
					dto.setUserEmail(tfEmail.getText());
					
					if(us.selectUser(userId) != null) {
						JOptionPane.showMessageDialog(UserRegForm.this, "이미 존재하는 Id입니다.");
						tfId.requestFocus();
						return;
					}
					if (!String.valueOf(tfPw.getPassword()).equals(String.valueOf(tfRe.getPassword()))) {
						JOptionPane.showMessageDialog(UserRegForm.this, "Password와 Retry가 일치하지 않습니다.");
						tfPw.requestFocus();
						return;
					}
					if(us.insertUser(dto) > 0) {
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "회원가입을 실패하였습니다.");
					}
				}
			}
		});
	}

	public boolean Blank() {
		boolean result = false;
		if (tfId.getText().isEmpty()) {
			tfId.requestFocus();
			return true;
		}
		if (String.valueOf(tfPw.getPassword()).isEmpty()) {
			tfPw.requestFocus();
			return true;
		}
		if (String.valueOf(tfRe.getPassword()).isEmpty()) {
			tfRe.requestFocus();
			return true;
		}
		if (tfName.getText().isEmpty()) {
			tfName.requestFocus();
			return true;
		}
		if (tfNickName.getText().isEmpty()) {
			tfNickName.requestFocus();
			return true;
		}
		if (tfEmail.getText().isEmpty()) {
			tfEmail.requestFocus();
		}
		return result;
	}

	public String getGender() {
		if (rbtnMale.isSelected()) {
			return rbtnMale.getText();
		}
		return rbtnFemale.getText();
	}
	private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
		Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}
}