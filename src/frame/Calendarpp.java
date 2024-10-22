package frame;

import java.awt.Dimension;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import dto.HealthDTO;

import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calendarpp {
	private CalendarMonthly calendarmonthly;
	private JFrame frame;
	private JPanel calendarPanel;
	private JButton backButton, nextButton, saveMemoButton, prevButton;
	// backButton(�ڷΰ���) nextButton(�޷´�������ư) prevButton(�޷�������ư)
	// saveMemoButton(�Է�������ư)
	private JLabel monthLabel;
	private JTable calendarTable;
	private JScrollPane calendarScrollpane;
	private JTextField healthMemorun;// �Ÿ� �Է¶�
	private JTextField healthMemotime;// �Ÿ� �Է¶�
	private JPanel monthhealthMemo;// ������� �� �Ÿ� �ð� ��Į�θ�
	private JTextArea memoLabel;
	private DefaultTableModel calendarTableModel;
	private int realYear, realMonth, currentYear, currentMonth;
	private Map<String, String> memoData = new HashMap<>();
	private JComponent inputPanel;
	private JLabel dateInfoLabel;
	private JLabel imageLabel;
	
	public Calendarpp() {

		calendarmonthly = new CalendarMonthly();
		frame = new JFrame("�ｺ�ɾ�");
		frame.setSize(500, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		backButton = new JButton("�ڷΰ���");
		prevButton = new JButton("<");
		nextButton = new JButton(">");
		saveMemoButton = new JButton("����ϱ�");
		monthLabel = new JLabel("", JLabel.CENTER);
		dateInfoLabel = new JLabel("������ ��¥");
		ImageIcon icon=new ImageIcon("src/img/�ֵ���ũ.png");
		imageLabel=new JLabel(icon);
		// Ķ���� ������

		calendarTableModel = new DefaultTableModel(new Object[6][7],
				new String[] { "��", "ȭ", "��", "��", "��", "��", "��" }) { // ����� �Բ� ������ �ʱ�ȭ

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false; // ��� ���� ������ �� ������ ����
			}
		};

		calendarTable = new JTable(calendarTableModel);
		calendarScrollpane = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);
		calendarPanel.setLayout(null);
		calendarTable.setFillsViewportHeight(true);
		

		// �޸�ĭ �����
		healthMemorun = new JTextField(10); // �Ÿ� �Է¶� �ʱ�ȭ
		healthMemotime = new JTextField(10); // �ð� �Է¶� �ʱ�ȭ

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.setBorder(BorderFactory.createTitledBorder("�Է� ����"));

		monthhealthMemo = new JPanel();
		memoLabel = new JTextArea("");// �޸� ������ ǥ��
		memoLabel.setLineWrap(true);// ������ ǥ��
		memoLabel.setWrapStyleWord(true);
		memoLabel.setEditable(false);
		inputPanel.revalidate();
		inputPanel.repaint();
		// ��ư �߰�
		inputPanel.add(new JLabel("�Ÿ� �Է�(km)"));
		inputPanel.add(healthMemorun);
		inputPanel.add(new JLabel("�ð� �Է�(��)"));
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
		// ��ư ��Ȱ��ȭ �ʱ�ȭ

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

		// ��ư �ǳ� ũ�� �ֱ�
		inputPanel.setBounds(25, 470, 220, 120);//�Է����� �ڽ�
		imageLabel.setBounds(20, 630 ,435 ,100);//�ֵ���ũ �̹���
		dateInfoLabel.setBounds(30, 440, 350, 30);//��¥�����ϸ� �����ֱ�
		saveMemoButton.setBounds(27, 590, 216, 27);// ����ϱ� ��ư
		calendarScrollpane.setBounds(25, 70, 440, 253);// �޷���ü
		prevButton.setBounds(25, 30, 50, 30);// �� ������ư
		nextButton.setBounds(415, 30, 50, 30);// �� ������ư
		monthLabel.setBounds(150, 30, 200, 30);// ��� �����ִ� �ǳ�
		backButton.setBounds(90, 750, 300, 45);// �ڷΰ���
		memoLabel.setBounds(25, 330, 440, 80);// �޷¿� �ԷµȰ� �����ִ¸޸�
		monthhealthMemo.setBounds(250, 470, 200, 150);// �������޸�
		monthhealthMemo.setLayout(new BoxLayout(monthhealthMemo, BoxLayout.Y_AXIS));
		monthhealthMemo.setBorder(BorderFactory.createTitledBorder("���� ���"));
		calendarPanel.setBounds(0, 0, 500, 850);// ��ü ũ��
		
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				new MainBoard();

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
				String date = (String) calendarTableModel.getValueAt(row, col);
				if (date == null || date.isEmpty()) {
					memoLabel.setText("��ȿ�� ��¥�� �����ϼ���");
				} else {
					showMemo(date);
					updateDateInfo(row, col);
				}
			}

			private void updateDateInfo(int row, int col) {
				int day = Integer.parseInt((String) calendarTableModel.getValueAt(row, col));
				LocalDate selectedDate = LocalDate.of(currentYear, currentMonth + 1, day);

				dateInfoLabel.setText(String.format("������ ��¥: %d��, %d��, %d��", selectedDate.getYear(),
						selectedDate.getMonthValue(), selectedDate.getDayOfMonth()));
				System.out.println(dateInfoLabel.getText());
				dateInfoLabel.revalidate();
				dateInfoLabel.repaint();
				dateInfoLabel.setVisible(true);
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
				if (row < 0 || col < 0) {
					JOptionPane.showMessageDialog(frame, "��¥�� �����ϼ���.");
					return;
				}
				String selectedDate = (String) calendarTableModel.getValueAt(row, col);
				System.out.println("selected" + selectedDate);
				if (selectedDate == null || selectedDate.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "��ȿ�� ��¥�� �����ϼ���");
					return;
				}
				String distanceText = healthMemorun.getText();
				String timeText = healthMemotime.getText();
				// �Է°��� ���ڷ� ��ȯ
				try {
					int yyyy = currentYear;
					int mm = currentMonth;
					int dd = Integer.parseInt(selectedDate);
					double distance = Double.parseDouble(distanceText);// km
					double time = Double.parseDouble(timeText);// ��
					// ��ռӵ� �� Į�θ� ���
					double speed = (distance / (time / 60));
					double calories = time * 7.33;

					HealthDTO dto = new HealthDTO();
					dto.setHealthYyyy(yyyy);
					dto.setHealthMm(mm);
					dto.setHealthDd(dd);
					dto.setHealthDistance((int) distance);
					dto.setHealthTime((int) time);
					dto.setHealthSpeed(speed);
					dto.setHealthCalories(calories);

					updateMonthHealthMemo();

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(), "�Է¿���", JOptionPane.ERROR_MESSAGE);
				}

			}

			private void updateMonthHealthMemo() {
				/*
				 * // ���� ��� ������Ʈ calendarmonthly.removeEntry(selectedDate);
				 * calendarmonthly.addEntry(selectedDate, distance, time); String result =
				 * String.format("�Ÿ�: %.2f km, �ð�: %.2f ��, ��� �ӵ�: %.2f km/h, Į�θ�: %.2f kcal",
				 * distance, time, averageSpeed, calories); JOptionPane.showMessageDialog(frame,
				 * "����Ǿ����ϴ�."); memoData.put(selectedDate, result);
				 */
				monthhealthMemo.removeAll();
				monthhealthMemo.setLayout(new BoxLayout(monthhealthMemo, BoxLayout.Y_AXIS));
				// �Ÿ�
				JLabel distanceLabel = new JLabel(String.format("�ѰŸ�: %.2f km", calendarmonthly.getTotalDistance()));
				distanceLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
				monthhealthMemo.add(distanceLabel);
				// �ð�
				JLabel timeLabel = new JLabel(String.format("�ѽð�: %.2f ��", calendarmonthly.getTotalTime()));
				timeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
				monthhealthMemo.add(timeLabel);
				// Į�θ�
				JLabel caloriesLabel = new JLabel(String.format("��Į�θ�: %.2f Kcal", calendarmonthly.getTotalCalories()));
				caloriesLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
				monthhealthMemo.add(caloriesLabel);

				monthhealthMemo.revalidate();
				monthhealthMemo.repaint();

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

		// ���� ��� �߰�

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
		LocalDate firstDayOfMonth = LocalDate.of(year, month + 1, 1); // month�� 0���� �����ϹǷ� +1
		String monthText = firstDayOfMonth.format(formatter);
		monthLabel.setText(monthText); // ���� ���� ���̺� ����

		// �޷� �ʱ�ȭ ����
		calendarTable.setRowHeight(38);
		int startDay = firstDayOfMonth.getDayOfWeek().getValue(); // 1(������) ~ 7(�Ͽ���)
		int daysInMonth = firstDayOfMonth.lengthOfMonth(); // �ش� ���� �� ��

		for (int i = 0; i < 6; i++) {
			calendarTableModel.addRow(new Object[7]);
		}

		// ��¥�� ���̺� �߰�
		for (int day = 1; day <= daysInMonth; day++) {
			int row = (day + startDay - 2) / 7; // �� ���
			int col = (day + startDay - 2) % 7; // �� ���
			calendarTableModel.setValueAt(Integer.toString(day), row, col); // +1�� ���� �Ʒ��� ������
		}

		// UI ������Ʈ
		calendarTable.revalidate();
		calendarTable.repaint();

	}// updateCalendar

	public static void main(String[] args) {
		/*
		 * SwingUtilities.invokeLater(new Runnable() { public void run() {
		 * 
		 * } });
		 */new Calendarpp();
	}

}
