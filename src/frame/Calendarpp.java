package frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calendarpp {
	private JFrame frame;
	private JPanel calendarPanel;
	private JButton backButton, nextButton, saveMemoButton, prevButton;
	// backButton(뒤로가기) nextButton(달력다음월버튼) prevButton(달력전월버튼)
	// saveMemoButton(입력정보버튼)
	private JLabel monthLabel;
	private JTable calendarTable;
	private JScrollPane calendarScrollpane;
	private JTextArea healthMemorun;// 거리 입력란
	private JTextArea healthMemotime;// 거리 입력란
	private JPanel monthhealthMemo;// 월별평균 뛴 거리 시간 총칼로리
	private JTextArea memoLabel;
	private DefaultTableModel calendarTableModel;
	private int realYear, realMonth, currentYear, currentMonth;
	private Map<String, String> memoData = new HashMap<>();

	public Calendarpp() {
		frame = new JFrame("헬스케어");
		frame.setSize(500, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		backButton = new JButton("뒤로가기");
		prevButton = new JButton("<");
		nextButton = new JButton(">");
		saveMemoButton = new JButton("기록하기");
		monthLabel = new JLabel("", JLabel.CENTER);
		//캘린더 날짜 
		calendarTableModel = new DefaultTableModel(new Object[][] {}, new String[] { "일", "월", "화", "수", "목", "금", "토" }) {
	      @Override
	      public boolean isCellEditable(int rowIndex, int columnIndex) {
	               return false;
	       }
		};

		calendarTable = new JTable(calendarTableModel);
		calendarScrollpane = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);
		calendarTable.setFillsViewportHeight(true);
		// 메모칸 만들기
		healthMemorun = new JTextArea("거리입력: ");// 메모칸 넓이입력
		healthMemotime = new JTextArea("시간입력: ");// 메모칸 넓이입력
		monthhealthMemo=new JPanel();
		memoLabel = new JTextArea("");// 메모 내용을 표시
		memoLabel.setLineWrap(true);// 여러줄 표시
		memoLabel.setWrapStyleWord(true);
		memoLabel.setEditable(false);
		//월별메모초기화
		
		// 버튼 추가
		calendarPanel.add(prevButton);
		calendarPanel.add(nextButton);
		calendarPanel.add(monthLabel);
		calendarPanel.add(backButton);
		calendarPanel.add(calendarScrollpane);
		calendarPanel.add(healthMemorun);
		calendarPanel.add(healthMemotime);
		calendarPanel.add(saveMemoButton);
		calendarPanel.add(memoLabel);
		calendarPanel.add(calendarTable);
		calendarPanel.add(monthhealthMemo);

		// 버튼 크기 넣기
		calendarTable.setBounds(25, 70, 440, 400);
		prevButton.setBounds(25, 30, 50, 30);
		nextButton.setBounds(415, 30, 50, 30);
		monthLabel.setBounds(150, 30, 200, 30);
		backButton.setBounds(90, 750, 300, 45);
		healthMemorun.setBounds(25, 580, 200, 30);
		healthMemotime.setBounds(25, 620, 200, 30);
		saveMemoButton.setBounds(25, 660, 200, 30);
		memoLabel.setBounds(25, 480, 440, 80);
		monthhealthMemo.setBounds(250,570,200,150);
		monthhealthMemo.setLayout(new BoxLayout(monthhealthMemo,BoxLayout.Y_AXIS));
		monthhealthMemo.setBorder(BorderFactory.createTitledBorder("월별 메모"));
		calendarScrollpane.setBounds(25, 70, 400, 50);
		calendarPanel.setBounds(0, 0, 500, 850);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				// ->메인페이지로 돌아가기(코드넣기)

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
				String date = calendarTableModel.getValueAt(row, col).toString();
				if (!date.equals("")) {
					showMemo(date);
				}
			}

			public void showMemo(String date) {
				String memo = memoData.get(date);
				if (memo != null && !memo.isEmpty()) {
					memoLabel.setText(memo);
				} else {
					memoLabel.setText("기록이 없습니다");
				}

			}
		});
		// 메모 저장하기 버튼
		saveMemoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int row = calendarTable.getSelectedRow();
				int col = calendarTable.getSelectedColumn();
				String date = calendarTableModel.getValueAt(row, col).toString();
				String memo = healthMemorun.getText();
				String memoTime = healthMemotime.getText();

				memoData.put(date,memo+memoTime );
				updateMonthHealthMemo();
				JOptionPane.showConfirmDialog(frame, "저장되었습니다");

			}
			private void updateMonthHealthMemo() {
				monthhealthMemo.removeAll();
				for(Map.Entry<String, String> entry:memoData.entrySet()) {
					JLabel memoLabel=new JLabel(entry.getKey() +" : "+entry.getValue());
					monthhealthMemo.add(memoLabel);
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

	}// class calendar

	public void updateCalendar(int month, int year) {

		calendarTableModel.setRowCount(0); // 기존 데이터 초기화
		calendarTableModel.setColumnCount(7); // 열 수 설정 (일, 월, 화, 수, 목, 금, 토)

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
		LocalDate firstDayOfMonth = LocalDate.of(year, month + 1, 1); // month는 0부터 시작하므로 +1
		String monthText = firstDayOfMonth.format(formatter);
		monthLabel.setText(monthText); // 월과 연도 레이블 설정

		// 달력 초기화 설정
		calendarTable.setRowHeight(38);
		calendarTableModel.setRowCount(6); // 최대 6주 (6행)

		int startDay = firstDayOfMonth.getDayOfWeek().getValue(); // 1(월요일) ~ 7(일요일)
		int daysInMonth = firstDayOfMonth.lengthOfMonth(); // 해당 월의 일 수

		// 날짜를 테이블에 추가
		for (int day = 1; day <= daysInMonth; day++) {
			int row = (day + startDay - 2) / 7; // 행 계산
			int col = (day + startDay - 2) % 7; // 열 계산
			calendarTableModel.setValueAt(Integer.toString(day), row, col); // 날짜 추가
		}
	}// updateCalendar

	public static void main(String[] args) {
		/*
		 * SwingUtilities.invokeLater(new Runnable() { public void run() {
		 * 
		 * } });
		 */new Calendarpp();
	}

}
