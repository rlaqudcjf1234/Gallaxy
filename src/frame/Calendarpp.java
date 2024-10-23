package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import dto.HealthDTO;
import dto.UserDTO;
import main.Main;
import service.HealthService;
import service.impl.HealthServiceImpl;

import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calendarpp {

	HealthService hs = new HealthServiceImpl();

	private CalendarMonthly calendarmonthly;
	private JFrame frame;
	private JPanel calendarPanel;
	private JButton backButton, nextButton, saveMemoButton, prevButton;
	// backButton(뒤로가기) nextButton(달력다음월버튼) prevButton(달력전월버튼)
	// saveMemoButton(입력정보버튼)
	private JLabel monthLabel;
	private JTable calendarTable;
	private JScrollPane calendarScrollpane;
	private JTextField healthMemorun;// 거리 입력란
	private JTextField healthMemotime;// 거리 입력란
	private JPanel monthhealthMemo;// 월별평균 뛴 거리 시간 총칼로리
	private JTextArea memoLabel;
	private DefaultTableModel calendarTableModel;
	private int realYear, realMonth, currentYear, currentMonth;
	private Map<String, String> memoData = new HashMap<>();
	private JComponent inputPanel;
	private JLabel dateInfoLabel;
	private JLabel imageLabel;

	public Calendarpp() {

		calendarmonthly = new CalendarMonthly();
		frame = new JFrame("헬스케어");
		frame.setSize(500, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		backButton = new JButton("뒤로가기");
		prevButton = new JButton("<");
		nextButton = new JButton(">");
		saveMemoButton = new JButton("기록하기");
		monthLabel = new JLabel("", JLabel.CENTER);
		dateInfoLabel = new JLabel("선택한 날짜");
		ImageIcon icon = new ImageIcon("src/img/솔데스크.png");
		imageLabel = new JLabel(icon);
		// 캘린더 일주일

		calendarTableModel = new DefaultTableModel(new Object[6][7],
				new String[] { "월", "화", "수", "목", "금", "토", "일" }) { // 헤더와 함께 데이터 초기화

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false; // 모든 셀을 수정할 수 없도록 설정
			}
		};

		calendarTable = new JTable(calendarTableModel);
		calendarScrollpane = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);
		calendarPanel.setLayout(null);
		calendarTable.setFillsViewportHeight(true);
		calendarPanel.setBackground(Color.WHITE);
		// 메모칸 만들기
		healthMemorun = new JTextField(10); // 거리 입력란 초기화
		healthMemotime = new JTextField(10); // 시간 입력란 초기화

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.setBackground(Color.WHITE);

		monthhealthMemo = new JPanel();
		memoLabel = new JTextArea("");// 메모 내용을 표시
		memoLabel.setLineWrap(true);// 여러줄 표시
		memoLabel.setWrapStyleWord(true);
		memoLabel.setEditable(false);
		inputPanel.revalidate();
		inputPanel.repaint();
		// 버튼 추가
		inputPanel.add(new JLabel("거리 입력(km)"));
		inputPanel.add(healthMemorun);
		inputPanel.add(new JLabel("시간 입력(분)"));
		inputPanel.add(healthMemotime);
		inputPanel.add(Box.createVerticalGlue());

		Dimension inputSize = new Dimension(200, 20);

		healthMemorun.setPreferredSize(inputSize);
		healthMemotime.setPreferredSize(inputSize);

		calendarPanel.add(prevButton);
		calendarPanel.add(nextButton);
		calendarPanel.add(monthLabel);
		calendarPanel.add(backButton);
		calendarPanel.add(calendarScrollpane);
		calendarPanel.add(memoLabel);
		calendarPanel.add(monthhealthMemo);
		calendarPanel.add(inputPanel);
		calendarPanel.add(saveMemoButton);
		calendarPanel.add(dateInfoLabel);
		calendarPanel.add(imageLabel);

		// 버튼 비활성화 초기화

		saveMemoButton.setEnabled(false);
		DocumentListener inputListener = new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				checkInput();
			}

			public void removeUpdate(DocumentEvent e) {
				checkInput();
			}

			public void changedUpdate(DocumentEvent e) {
				checkInput();
			}

			private void checkInput() {
				String distance = healthMemorun.getText();
				String time = healthMemotime.getText();
				saveMemoButton.setEnabled(!distance.isEmpty() && !time.isEmpty());
			}
		};

		healthMemorun.getDocument().addDocumentListener(inputListener);
		healthMemotime.getDocument().addDocumentListener(inputListener);

		// 버튼 판넬 크기 넣기
		inputPanel.setBounds(25, 620, 220, 120);// 입력정보 박스 200 오름
		inputPanel.setBorder(new TitledBorder(null, "입력 정보", 4, 0, new Font("맑은고딕", Font.BOLD, 15)));
		// imageLabel.setBounds(20, 630 ,435 ,100);//솔데스크 이미지
		dateInfoLabel.setBounds(25, 585, 350, 30);// 날짜선택하면 보여주기
		dateInfoLabel.setFont(new Font("맑은고딕", Font.BOLD, 15));
		saveMemoButton.setBounds(27, 740, 216, 27);// 기록하기 버튼
		calendarScrollpane.setBounds(25, 220, 440, 251);// 달력전체
		prevButton.setBounds(25, 175, 50, 30);// 월 이전버튼
		nextButton.setBounds(415, 175, 50, 30);// 월 다음버튼
		monthLabel.setBounds(145, 170, 200, 30);// 년월 보여주는 판넬
		monthLabel.setFont(new Font("맑은고딕", Font.BOLD, 28));
		// backButton.setBounds(90, 750, 300, 45);// 뒤로가기
		memoLabel.setBounds(28, 510, 440, 80);// 달력에 입력된거 보여주는메모
		memoLabel.setFont(new Font("맑은고딕", Font.BOLD, 13));
		monthhealthMemo.setBounds(250, 620, 200, 150);// 월간통계메모
		monthhealthMemo.setLayout(new BoxLayout(monthhealthMemo, BoxLayout.Y_AXIS));
		monthhealthMemo.setBorder(new TitledBorder(null, "월간 통계", 4, 0, new Font("맑은고딕", Font.BOLD, 15)));
		calendarPanel.setBounds(0, 0, 500, 850);// 전체 크기
		monthhealthMemo.setBackground(Color.WHITE);
		// 로고 버튼 생성
		JLabel mainPageLabel = new JLabel("메인화면");
		mainPageLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		mainPageLabel.setBounds(410, 25, 100, 40); // 위치와 크기 조정
		mainPageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 로그아웃 클릭 이벤트 추가
		mainPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainBoard();
				frame.dispose();
			}
		});

		calendarPanel.add(mainPageLabel); // 프레임에 메인화면 텍스트 추가
		JButton btnLogo = new JButton();

		ImageIcon logoIcon = new ImageIcon("LogoImage_130x130.png");
		btnLogo.setIcon(logoIcon);
		btnLogo.setBorderPainted(false); // 버튼 테두리 제거
		btnLogo.setContentAreaFilled(false); // 버튼 배경 제거
		btnLogo.setFocusPainted(false); // 포커스 효과 제거
		btnLogo.setBounds(20, 10, 130, 130); // 버튼 위치 및 크기 설정
		calendarPanel.add(btnLogo);

		// 클릭 이벤트 추가
		btnLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainBoard();
				frame.dispose();
			}
		});
		JLabel logoutLabel = new JLabel("로그아웃");
		logoutLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		logoutLabel.setBounds(250, 25, 80, 40); // 위치와 크기 조정
		logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 로그아웃 클릭 이벤트 추가
		logoutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(frame, "로그아웃 합니다!");
				new UserInForm();
				frame.dispose();
			}
		});

		calendarPanel.add(logoutLabel); // 프레임에 마이페이지 텍스트 추가

		// 로그아웃 텍스트 추가
		JLabel myPageLabel = new JLabel("마이페이지");
		myPageLabel.setForeground(Color.BLUE); // 텍스트 색상 변경
		myPageLabel.setBounds(325, 25, 100, 40); // 위치와 크기 조정
		myPageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 손 모양 커서

		// 로그아웃 클릭 이벤트 추가
		myPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new myPageBegin();
				frame.dispose();
			}
		});

		calendarPanel.add(myPageLabel); // 프레임에 마이페이지 텍스트 추가

		JLabel labelLogo = new JLabel(); // 이미지 표시를 위한 Label
		ImageIcon icon2 = new ImageIcon("gaesipan23.png");
		labelLogo.setIcon(icon2); // JLabel에 이미지 설정
		labelLogo.setBounds(100, 110, 300, 50); // 이미지 위치 및 크기 설정

		// 프레임에 Label 추가
		calendarPanel.add(labelLogo);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				new MainBoard();

			}
		});
		//
		// 달력 전월 버튼액션
		prevButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				if (currentMonth == 0) {
					currentMonth = 11;
					currentYear -= 1;
				} else {
					currentMonth -= 1;
				}
				updateCalendar(currentMonth, currentYear);

			}

		});
		// 달력 다음월 버튼액션
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (currentMonth == 11) {
					currentMonth = 0;
					currentYear += 1;
				} else {
					currentMonth += 1;
				}
				updateCalendar(currentMonth, currentYear);
			}
		});
		// 메모 보여주는 창
		calendarTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				int row = calendarTable.getSelectedRow();
				int col = calendarTable.getSelectedColumn();
				String date = (String) calendarTableModel.getValueAt(row, col);
				if (date == null || date.isEmpty()) {
					memoLabel.setText("유효한 날짜를 선택하세요");
				} else {
					showMemo(date);
					updateDateInfo(row, col);
				}
			}

			private void updateDateInfo(int row, int col) {
				int day = Integer.parseInt((String) calendarTableModel.getValueAt(row, col));
				LocalDate selectedDate = LocalDate.of(currentYear, currentMonth + 1, day);

				dateInfoLabel.setText(String.format("선택한 날짜: %d년, %d월, %d일", selectedDate.getYear(),
						selectedDate.getMonthValue(), selectedDate.getDayOfMonth()));
				
				System.out.println(dateInfoLabel.getText());
				dateInfoLabel.revalidate();
				dateInfoLabel.repaint();
				dateInfoLabel.setVisible(true);
			}

			public void showMemo(String dd) {
				// * String.format("거리: %.2f km, 시간: %.2f 분, 평균 속도: %.2f km/h, 칼로리: %.2f kcal",

				int yyyy = currentYear;
				int mm = currentMonth;

				HealthDTO dto = new HealthDTO();
				if (Main.USER != null) {
					dto.setUserId(Main.USER.getUserId());
				}
				dto.setHealthYyyy(yyyy);
				dto.setHealthMm(mm);
				dto.setHealthDd(Integer.parseInt(dd));

				HealthDTO result = hs.selectHealth(dto);
				if (result == null) {
					memoLabel.setText("기록이 없습니다.");
				} else {
					memoLabel.setText(String.format("거리: %d km, 시간: %d 분, 평균 속도: %.2f km/h, 칼로리: %.2f kcal",
							result.getHealthDistance(), result.getHealthTime(), result.getHealthSpeed(),
							result.getHealthCalories()));
				}

			}
		});
		// 메모 저장하기 버튼

		saveMemoButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				int row = calendarTable.getSelectedRow();
				int col = calendarTable.getSelectedColumn();
				if (row < 0 || col < 0) {
					JOptionPane.showMessageDialog(frame, "날짜를 선택하세요.");
					return;
				}
				String selectedDate = (String) calendarTableModel.getValueAt(row, col);
				System.out.println("selected" + selectedDate);
				if (selectedDate == null || selectedDate.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "유효한 날짜를 선택하세요");
					return;
				}
				String distanceText = healthMemorun.getText();
				String timeText = healthMemotime.getText();
				// 입력값을 숫자로 변환
				try {
					int yyyy = currentYear;
					int mm = currentMonth;
					int dd = Integer.parseInt(selectedDate);
					double distance = Double.parseDouble(distanceText);// km
					double time = Double.parseDouble(timeText);// 분
					// 평균속도 및 칼로리 계산
					double speed = (distance / (time / 60));
					double calories = time * 7.33;

					HealthDTO dto = new HealthDTO();
					if (Main.USER != null) {
						dto.setUserId(Main.USER.getUserId());
					}
					dto.setHealthYyyy(yyyy);
					dto.setHealthMm(mm);
					dto.setHealthDd(dd);
					dto.setHealthDistance((int) distance);
					dto.setHealthTime((int) time);
					dto.setHealthSpeed(speed);
					dto.setHealthCalories(calories);

					if (hs.insertHealth(dto) > 0) {
						JOptionPane.showMessageDialog(frame, "기록되었습니다.", "기록완료", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frame, "기록이 입력되지 않았습니다.", "기록오류", JOptionPane.ERROR_MESSAGE);
					}

					updateMonthHealthMemo();

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(), "입력오류", JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		realYear = Calendar.getInstance().get(Calendar.YEAR);
		realMonth = Calendar.getInstance().get(Calendar.MONTH);
		currentYear = realYear;
		currentMonth = realMonth;

		updateCalendar(currentMonth, currentYear);

		frame.add(calendarPanel);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);

		updateMonthHealthMemo();
	}// class calendar

	private void updateMonthHealthMemo() {

		int yyyy = currentYear;
		int mm = currentMonth;

		HealthDTO dto = new HealthDTO();
		if (Main.USER != null) {
			dto.setUserId(Main.USER.getUserId());
		}
		dto.setHealthYyyy(yyyy);
		dto.setHealthMm(mm);

		HealthDTO result = hs.selectHealthSt(dto);

		if (result != null) {
			monthhealthMemo.removeAll();
			monthhealthMemo.setLayout(new BoxLayout(monthhealthMemo, BoxLayout.Y_AXIS));
			// 거리
			JLabel distanceLabel = new JLabel(String.format("총거리: %d km", result.getHealthDistance()));
			distanceLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(distanceLabel);
			distanceLabel.setFont(new Font("맑은고딕", Font.BOLD, 15));
			// 시간
			JLabel timeLabel = new JLabel(String.format("총시간: %d 분", result.getHealthTime()));
			timeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(timeLabel);
			timeLabel.setFont(new Font("맑은고딕", Font.BOLD, 14));
			// 칼로리
			JLabel caloriesLabel = new JLabel(String.format("총칼로리: %.2f Kcal", result.getHealthCalories()));
			caloriesLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(caloriesLabel);
			caloriesLabel.setFont(new Font("맑은고딕", Font.BOLD, 14));
			monthhealthMemo.revalidate();
			monthhealthMemo.repaint();
		} else {
			monthhealthMemo.removeAll();
			monthhealthMemo.setLayout(new BoxLayout(monthhealthMemo, BoxLayout.Y_AXIS));
			// 거리
			JLabel distanceLabel = new JLabel(String.format("총거리: %d km", 0));
			distanceLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(distanceLabel);
			// 시간
			JLabel timeLabel = new JLabel(String.format("총시간: %d 분", 0));
			timeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(timeLabel);
			// 칼로리
			JLabel caloriesLabel = new JLabel(String.format("총칼로리: %.2f Kcal", 0.0));
			caloriesLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(caloriesLabel);

			monthhealthMemo.revalidate();
			monthhealthMemo.repaint();
		}

	}

	public void updateCalendar(int month, int year) {
		calendarTableModel.setRowCount(0); // 기존 데이터 초기화

		// 월별 헤더 추가

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
		LocalDate firstDayOfMonth = LocalDate.of(year, month + 1, 1); // month는 0부터 시작하므로 +1
		String monthText = firstDayOfMonth.format(formatter);
		monthLabel.setText(monthText); // 월과 연도 레이블 설정

		// 달력 초기화 설정
		calendarTable.setRowHeight(38);
		int startDay = firstDayOfMonth.getDayOfWeek().getValue(); // 1(월요일) ~ 7(일요일)
		int daysInMonth = firstDayOfMonth.lengthOfMonth(); // 해당 월의 일 수

		for (int i = 0; i < 6; i++) {
			calendarTableModel.addRow(new Object[7]);
		}

		// 날짜를 테이블에 추가
		for (int day = 1; day <= daysInMonth; day++) {
			int row = (day + startDay - 2) / 7; // 행 계산
			int col = (day + startDay - 2) % 7; // 열 계산
			calendarTableModel.setValueAt(Integer.toString(day), row, col); // +1을 통해 아래로 내리기
		}

		// UI 업데이트
		calendarTable.revalidate();
		calendarTable.repaint();

		updateMonthHealthMemo();
	}// updateCalendar

	public static void main(String[] args) {
		/*
		 * SwingUtilities.invokeLater(new Runnable() { public void run() {
		 * 
		 * } });
		 */
		 UserDTO userDTO = new UserDTO();
		 userDTO.setUserId("1111");
		 
		 Main.USER = userDTO;
		 new Calendarpp();
		 
	}

}
