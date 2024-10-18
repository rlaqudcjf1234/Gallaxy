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
	// backButton(�ڷΰ���) nextButton(�޷´�������ư) prevButton(�޷�������ư)
	// saveMemoButton(�Է�������ư)
	private JLabel monthLabel;
	private JTable calendarTable;
	private JScrollPane calendarScrollpane;
	private JTextArea healthMemorun;// �Ÿ� �Է¶�
	private JTextArea healthMemotime;// �Ÿ� �Է¶�
	private JPanel monthhealthMemo;// ������� �� �Ÿ� �ð� ��Į�θ�
	private JTextArea memoLabel;
	private DefaultTableModel calendarTableModel;
	private int realYear, realMonth, currentYear, currentMonth;
	private Map<String, String> memoData = new HashMap<>();

	public Calendarpp() {
		frame = new JFrame("�ｺ�ɾ�");
		frame.setSize(500, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		backButton = new JButton("�ڷΰ���");
		prevButton = new JButton("<");
		nextButton = new JButton(">");
		saveMemoButton = new JButton("����ϱ�");
		monthLabel = new JLabel("", JLabel.CENTER);
		//Ķ���� ��¥ 
		calendarTableModel = new DefaultTableModel(new Object[][] {}, new String[] { "��", "��", "ȭ", "��", "��", "��", "��" }) {
	      @Override
	      public boolean isCellEditable(int rowIndex, int columnIndex) {
	               return false;
	       }
		};

		calendarTable = new JTable(calendarTableModel);
		calendarScrollpane = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);
		calendarTable.setFillsViewportHeight(true);
		// �޸�ĭ �����
		healthMemorun = new JTextArea("�Ÿ��Է�: ");// �޸�ĭ �����Է�
		healthMemotime = new JTextArea("�ð��Է�: ");// �޸�ĭ �����Է�
		monthhealthMemo=new JPanel();
		memoLabel = new JTextArea("");// �޸� ������ ǥ��
		memoLabel.setLineWrap(true);// ������ ǥ��
		memoLabel.setWrapStyleWord(true);
		memoLabel.setEditable(false);
		//�����޸��ʱ�ȭ
		
		// ��ư �߰�
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

		// ��ư ũ�� �ֱ�
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
		monthhealthMemo.setBorder(BorderFactory.createTitledBorder("���� �޸�"));
		calendarScrollpane.setBounds(25, 70, 400, 50);
		calendarPanel.setBounds(0, 0, 500, 850);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				// ->������������ ���ư���(�ڵ�ֱ�)

			}
		});
		//
		// �޷� ���� ��ư�׼�
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
		// �޷� ������ ��ư�׼�
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
		// �޸� �����ִ� â
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
					memoLabel.setText("����� �����ϴ�");
				}

			}
		});
		// �޸� �����ϱ� ��ư
		saveMemoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int row = calendarTable.getSelectedRow();
				int col = calendarTable.getSelectedColumn();
				String date = calendarTableModel.getValueAt(row, col).toString();
				String memo = healthMemorun.getText();
				String memoTime = healthMemotime.getText();

				memoData.put(date,memo+memoTime );
				updateMonthHealthMemo();
				JOptionPane.showConfirmDialog(frame, "����Ǿ����ϴ�");

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

		calendarTableModel.setRowCount(0); // ���� ������ �ʱ�ȭ
		calendarTableModel.setColumnCount(7); // �� �� ���� (��, ��, ȭ, ��, ��, ��, ��)

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
		LocalDate firstDayOfMonth = LocalDate.of(year, month + 1, 1); // month�� 0���� �����ϹǷ� +1
		String monthText = firstDayOfMonth.format(formatter);
		monthLabel.setText(monthText); // ���� ���� ���̺� ����

		// �޷� �ʱ�ȭ ����
		calendarTable.setRowHeight(38);
		calendarTableModel.setRowCount(6); // �ִ� 6�� (6��)

		int startDay = firstDayOfMonth.getDayOfWeek().getValue(); // 1(������) ~ 7(�Ͽ���)
		int daysInMonth = firstDayOfMonth.lengthOfMonth(); // �ش� ���� �� ��

		// ��¥�� ���̺� �߰�
		for (int day = 1; day <= daysInMonth; day++) {
			int row = (day + startDay - 2) / 7; // �� ���
			int col = (day + startDay - 2) % 7; // �� ���
			calendarTableModel.setValueAt(Integer.toString(day), row, col); // ��¥ �߰�
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
