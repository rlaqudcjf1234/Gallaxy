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
import javax.swing.JButton;
import javax.swing.JComponent;
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

		inputPanel.setBounds(25, 570, 220, 120);
		saveMemoButton.setBounds(27, 690, 216, 27);// ����ϱ� ��ư
		calendarScrollpane.setBounds(25, 70, 440, 400);// �޷���ü
		prevButton.setBounds(25, 30, 50, 30);// �� ������ư
		nextButton.setBounds(415, 30, 50, 30);// �� ������ư
		monthLabel.setBounds(150, 30, 200, 30);// ��� �����ִ� �ǳ�
		backButton.setBounds(90, 750, 300, 45);// �ڷΰ���
		memoLabel.setBounds(25, 480, 440, 80);// �޷¿� �ԷµȰ� �����ִ¸޸�
		monthhealthMemo.setBounds(250, 570, 200, 150);// �������޸�
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
				}
			}

			public void showMemo(String dd) {
				int yyyy = currentYear;
				int mm = currentMonth;

				HealthDTO dto = new HealthDTO();
				dto.setUserId("test");
				dto.setHealthYyyy(yyyy);
				dto.setHealthMm(mm);
				dto.setHealthDd(Integer.parseInt(dd));

				HealthDTO result = hs.selectHealth(dto);
				if (result == null) {
					memoLabel.setText("����� �����ϴ�");
				} else {
					memoLabel.setText(String.format("�Ÿ�: %d km, �ð�: %d ��, ��� �ӵ�: %.2f km/h, Į�θ�: %.2f kcal",
							result.getHealthDistance(), result.getHealthTime(), result.getHealthSpeed(),
							result.getHealthCalories()));
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
					//dto.setUserId(Main.USER.getUserId());
					dto.setUserId("test");
					dto.setHealthYyyy(yyyy);
					dto.setHealthMm(mm);
					dto.setHealthDd(dd);
					dto.setHealthDistance((int) distance);
					dto.setHealthTime((int) time);
					dto.setHealthSpeed(speed);
					dto.setHealthCalories(calories);

					if (hs.insertHealth(dto) > 0) {
						JOptionPane.showMessageDialog(frame, "����� �Ϸ��߽��ϴ�.", "��ϿϷ�", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frame, "����� �����߽��ϴ�.", "��Ͽ���", JOptionPane.ERROR_MESSAGE);
					}

					// ������� ���ϱ�
					updateMonthHealthMemo();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(), "�Է¿���", JOptionPane.ERROR_MESSAGE);
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

		// ������� ���ϱ�
		updateMonthHealthMemo();
	}// class calendar

	private void updateMonthHealthMemo() {

		int yyyy = currentYear;
		int mm = currentMonth;

		HealthDTO dto = new HealthDTO();
		dto.setUserId("test");
		dto.setHealthYyyy(yyyy);
		dto.setHealthMm(mm);

		HealthDTO result = hs.selectHealthSt(dto);

		if (result != null) {
			monthhealthMemo.removeAll();
			monthhealthMemo.setLayout(new BoxLayout(monthhealthMemo, BoxLayout.Y_AXIS));
			// �Ÿ�
			JLabel distanceLabel = new JLabel(String.format("�ѰŸ�: %d km", result.getHealthDistance()));
			distanceLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(distanceLabel);
			// �ð�
			JLabel timeLabel = new JLabel(String.format("�ѽð�: %d ��", result.getHealthTime()));
			timeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(timeLabel);
			// Į�θ�
			JLabel caloriesLabel = new JLabel(String.format("��Į�θ�: %.2f Kcal", result.getHealthCalories()));
			caloriesLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(caloriesLabel);

			monthhealthMemo.revalidate();
			monthhealthMemo.repaint();
		} else {
			monthhealthMemo.removeAll();
			monthhealthMemo.setLayout(new BoxLayout(monthhealthMemo, BoxLayout.Y_AXIS));
			// �Ÿ�
			JLabel distanceLabel = new JLabel(String.format("�ѰŸ�: %d km", 0));
			distanceLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(distanceLabel);
			// �ð�
			JLabel timeLabel = new JLabel(String.format("�ѽð�: %d ��", 0));
			timeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(timeLabel);
			// Į�θ�
			JLabel caloriesLabel = new JLabel(String.format("��Į�θ�: %.2f Kcal", 0.0));
			caloriesLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			monthhealthMemo.add(caloriesLabel);

			monthhealthMemo.revalidate();
			monthhealthMemo.repaint();
		}

	}

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

		// ������� ���ϱ�
		updateMonthHealthMemo();
	}// updateCalendar

	public static void main(String[] args) {
		/*
		 * SwingUtilities.invokeLater(new Runnable() { public void run() {
		 * 
		 * } });
		 */new Calendarpp();
	}

}
