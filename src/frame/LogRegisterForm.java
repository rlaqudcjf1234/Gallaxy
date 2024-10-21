package frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LogRegisterForm extends JFrame {
	private LogUserDataSet users;

	private JLabel lblId, lblPw, lblEmail, lblRe, lblName, lblNickName;
	private JRadioButton rbtnMale, rbtnFemale;
	private JTextField tfId, tfEmail, tfName, tfNickName;
	private JPasswordField tfPw, tfRe;
	private JButton btnSignup, btnCancel;

	public LogRegisterForm(LogUserDataSet users) {
		this.users = users;
		setupUI();
	}

	private void setupUI() {
		setTitle("회원가입 페이지"); // 창 제목 설정
		setBounds(650, 0, 500, 750); // 창 크기 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 시 프로그램 종료
		setBackground(new Color(247, 244, 242));
		setLayout(null); // 절대 레이아웃 사용

		// 회원가입 폼 패널 생성
		JPanel signupPanel = new JPanel();
		signupPanel.setLayout(null); // 절대 레이아웃 사용
		signupPanel.setBounds(0, 0, 500, 750); // 위치 및 크기 설정
		signupPanel.setBackground(new Color(247, 244, 242)); // 배경색 설정

		// 레이블 및 텍스트 필드

		lblId = new JLabel("ID:");
		lblPw = new JLabel("Password:");
		lblRe = new JLabel("Retry:");
		lblName = new JLabel("Name:");
		lblEmail = new JLabel("Email:");
		lblNickName = new JLabel("NickName:");

		lblId.setBounds(130, 260, 80, 25); // 위치 및 크기 설정
		lblPw.setBounds(130, 300, 80, 25); // 위치 및 크기 설정
		lblRe.setBounds(130, 340, 80, 25);
		lblName.setBounds(130, 380, 80, 25); // 위치 및 크기 설정
		lblEmail.setBounds(130, 420, 80, 25); // 위치 및 크기 설정
		lblNickName.setBounds(130, 460, 80, 25);

		tfId = new JTextField();
		tfPw = new JPasswordField();
		tfRe = new JPasswordField();
		tfName = new JTextField();
		tfEmail = new JTextField();
		tfNickName = new JTextField();

		tfId.setBounds(220, 260, 140, 25); // 위치 및 크기 설정
		tfPw.setBounds(220, 300, 140, 25); // 위치 및 크기 설정
		tfRe.setBounds(220, 340, 140, 25);
		tfName.setBounds(220, 380, 140, 25); // 위치 및 크기 설정
		tfEmail.setBounds(220, 420, 140, 25); // 위치 및 크기 설정
		tfNickName.setBounds(220, 460, 140, 25);

		rbtnMale = new JRadioButton("Male", true); // 남성 선택 기본값
		rbtnFemale = new JRadioButton("Female");

		ButtonGroup group = new ButtonGroup();
		group.add(rbtnMale);
		group.add(rbtnFemale);

		rbtnMale.setBounds(130, 500, 100, 25); // 남성 버튼
		rbtnFemale.setBounds(260, 500, 100, 25); // 여성 버튼

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

		btnSignup.setBounds(130, 550, 99, 30); // 위치 및 크기 설정
		btnCancel.setBounds(260, 550, 99, 30); // 위치 및 크기 설정

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
				new LoginForm(users);
				dispose();// 창 닫기
			}
		});

		// 회원가입 버튼 클릭 이벤트 처리
		btnSignup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 입력 값 검증
				if (Blank()) {
					JOptionPane.showMessageDialog(LogRegisterForm.this, "모든 정보를 입력해주세요.");
					// 모두 입력했을 때
				} else {
					// Id 중복일 때
					if (users.isIdOverlap(tfId.getText())) {
						JOptionPane.showMessageDialog(LogRegisterForm.this, "이미 존재하는 Id입니다.");
						tfId.requestFocus();

						// Pw와 Re가 일치하지 않았을 때
					} else if (!String.valueOf(tfPw.getPassword()).equals(String.valueOf(tfRe.getPassword()))) {
						JOptionPane.showMessageDialog(LogRegisterForm.this, "Password와 Retry가 일치하지 않습니다.");
						tfPw.requestFocus();
					} else {
						users.addUsers(new User(tfId.getText(), String.valueOf(tfPw.getPassword()), tfName.getText(),
								tfNickName.getText(), getGender(), tfEmail.getText()));
						JOptionPane.showMessageDialog(LogRegisterForm.this, "회원가입을 완료했습니다!");
						new LoginForm(users);
						dispose();
					}
				}
				/*
				 * if (tfId.getText().isEmpty() || tfPw.getPassword().length == 0 ||
				 * tfRe.getPassword().length == 0 || tfName.getText().isEmpty() ||
				 * tfEmail.getText().isEmpty() || tfNickName.getText().isEmpty()) {
				 * JOptionPane.showMessageDialog(null, "모든 필드를 채워주세요.", "입력 오류",
				 * JOptionPane.ERROR_MESSAGE); } else if
				 * (!String.valueOf(tfPw.getPassword()).equals(String.valueOf(tfRe.getPassword()
				 * ))) { JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "입력 오류",
				 * JOptionPane.ERROR_MESSAGE); } else { // 성공적인 회원가입 처리 String gender =
				 * rbtnMale.isSelected() ? "남성" : "여성"; JOptionPane.showMessageDialog(null,
				 * "회원가입 성공!\n" + "ID: " + tfId.getText() + "\n" + "이름: " + tfName.getText() +
				 * "\n" + "이메일: " + tfEmail.getText() + "\n" + "별명: " + tfNickName.getText() +
				 * "\n" + "성별: " + gender, "회원가입 완료", JOptionPane.INFORMATION_MESSAGE); // 입력 필드
				 * 초기화 resetFields();
				 * 
				 * // 새 화면으로 이동 new LoginForm(); // MainScreen 인스턴스 생성 dispose(); // 현재 회원가입 창
				 * 닫기 }
				 */
			}
		});

		// 종료 이벤트
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
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

	// 입력 필드 초기화 메서드
	/*
	 * private void resetFields() { tfId.setText(""); tfPw.setText("");
	 * tfRe.setText(""); tfName.setText(""); tfEmail.setText("");
	 * tfNickName.setText(""); rbtnMale.setSelected(true); // 기본값으로 남성 선택 }
	 */
}
/*
 * (null, "회원가입 성공!\n" + "ID: " + tfId.getText() + "\n" + "이름: " +
 * tfName.getText() + "\n" + "이메일: " + tfEmail.getText() + "\n" + "별명: " +
 * tfNickName.getText() + "\n" + "성별: " + getGender(), "회원가입 완료",
 * JOptionPane.INFORMATION_MESSAGE);
 */