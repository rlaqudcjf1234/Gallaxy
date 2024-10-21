package frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import dto.AddressDTO;
import dto.BoardDTO;
import service.BoardService;
import service.NaverApiService;
import service.RetrieveNewAdress;
import service.impl.BoardServiceImpl;
import service.impl.NaverApiServiceImpl;
import service.impl.RetrieveNewAdressImpl;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class BoardWriteFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6032491971534575326L;

	private BoardService bs = new BoardServiceImpl();
	private NaverApiService nas = new NaverApiServiceImpl();
	private RetrieveNewAdress rna = new RetrieveNewAdressImpl();

	private Map<String, Object> result;
	private String serarch = "";
	private int currentPage = 1;
	private int totalPage;
	private List<String> addrs;

	private String textInit = "������ �Է��ϼ���";
	private String detailInit = "������ �Է��ϼ���";

	private String yyyy[] = { "2024��", "2025��" };
	private String mm[] = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��" };
	private String dd[] = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��", "13��", "14��",
			"15��", "16��", "17��", "18��", "19��", "20��", "21��", "22��", "23��", "24��", "25��", "26��", "27��", "28��", "29��",
			"30��", "31��" };
	private String apm[] = { "����", "����" };
	private String hh[] = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��" };
	private String mi[] = { "00��", "30��" };

	/* BoardWriteFrame */
	private JTextField addressFld;
	private JLabel imageLbl;
	private JTextField textFld;
	private JTextArea detailFld;
	private JComboBox<String> yyyyCbx;
	private JComboBox<String> mmCbx;
	private JComboBox<String> ddCbx;
	private JComboBox<String> apmCbx;
	private JComboBox<String> hhCbx;
	private JComboBox<String> miCbx;
	private String filePath = "temp/����Ư���� ���α� ���� 13-3 ���μ�����.jpg";

	/* BoardSFrame */
	private JFrame searchFrame;
	private JLabel pageLbl;
	private JPanel listPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardWriteFrame frame = new BoardWriteFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BoardWriteFrame() {
		// ������ Ÿ��Ʋ��
		setTitle("BoardWriteFrame");
		// X��ư ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ������ ��ġ, ũ��(�ȼ�)
		setBounds(100, 100, 500, 850);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel searchPane = new JPanel();
		searchPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(searchPane);

		JLabel addressLbl = new JLabel("�ּ�");
		searchPane.add(addressLbl);

		addressFld = new JTextField(30);
		searchPane.add(addressFld);

		JButton searchBtn = new JButton("��ȸ");
		searchPane.add(searchBtn);

		// ��ư(searchBtn) Ŭ�� �̺�Ʈ
		searchBtn.addActionListener(new ActionListener() {

			@Override
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				serarch = addressFld.getText();
				if (serarch.length() < 2) {
					JOptionPane.showMessageDialog(null, "�˻���� �α��� �̻� �ԷµǾ�� �մϴ�.", "�˸�",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if ((result = rna.getAddress(serarch, currentPage = 1)) != null) {
					String errorCode = (String) result.get("errorCode");
					String errorMessage = (String) result.get("errorMessage");

					if (errorCode.equals("0")) {
						totalPage = (int) result.get("totalPage");
						if (totalPage > 0) {
							addrs = (List<String>) result.get("list");
						} else {
							JOptionPane.showMessageDialog(null, "��ȸ ����� �����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, errorMessage, "�˸�", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				BoardSearchFrame();
			}
		});

		JPanel imagePane = new JPanel();
		imagePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		imagePane.setLayout(new BoxLayout(imagePane, BoxLayout.X_AXIS));
		contentPane.add(imagePane);

		imageLbl = new JLabel(new ImageIcon(filePath));
		imagePane.add(imageLbl);

		JPanel titlePane = new JPanel();
		titlePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.X_AXIS));
		contentPane.add(titlePane);

		JLabel textLbl = new JLabel("����");
		textLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		titlePane.add(textLbl);

		textFld = new JTextField(textInit);
		textFld.setFont(new Font("���� ���", Font.PLAIN, 13));
		titlePane.add(textFld);

		// Ŭ�� �̺�Ʈ �⺻ �ؽ�Ʈ(textFld) ����
		textFld.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFld.getText().equals(textInit)) {
					textFld.setText(""); // Ŭ�� �� �ؽ�Ʈ �ʱ�ȭ
				}
			}
		});

		JPanel datePane = new JPanel();
		datePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		datePane.setLayout(new BoxLayout(datePane, BoxLayout.X_AXIS));
		contentPane.add(datePane);

		JLabel dateLbl = new JLabel("����");
		dateLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		datePane.add(dateLbl);

		Calendar cal = Calendar.getInstance();

		yyyyCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(yyyy));
		datePane.add(yyyyCbx);

		mmCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(mm));
		mmCbx.setSelectedIndex(cal.get(Calendar.MONTH));
		datePane.add(mmCbx);

		ddCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(dd));
		ddCbx.setSelectedIndex(cal.get(Calendar.DAY_OF_MONTH) - 1);
		datePane.add(ddCbx);

		JPanel timePane = new JPanel();
		timePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		timePane.setLayout(new BoxLayout(timePane, BoxLayout.X_AXIS));
		contentPane.add(timePane);

		JLabel timeLbl = new JLabel("�ð�");
		timeLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		timePane.add(timeLbl);

		apmCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(apm));
		apmCbx.setSelectedIndex(cal.get(Calendar.AM_PM));
		timePane.add(apmCbx);

		hhCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(hh));
		hhCbx.setSelectedIndex(cal.get(Calendar.HOUR) - 1);
		timePane.add(hhCbx);

		miCbx = new JComboBox<String>(new DefaultComboBoxModel<String>(mi));
		timePane.add(miCbx);

		JPanel detailPane = new JPanel();
		detailPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		detailPane.setLayout(new BoxLayout(detailPane, BoxLayout.X_AXIS));
		contentPane.add(detailPane);

		JLabel detailLbl = new JLabel("����");
		detailLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		detailPane.add(detailLbl);

		detailFld = new JTextArea(detailInit);
		detailFld.setColumns(35);
		detailFld.setRows(10);
		detailPane.add(detailFld);

		// Ŭ�� �̺�Ʈ �⺻ �ؽ�Ʈ(contentFld) ����
		detailFld.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (detailFld.getText().equals(detailInit)) {
					detailFld.setText(""); // Ŭ�� �� �ؽ�Ʈ �ʱ�ȭ
				}
			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		contentPane.add(buttonPane);

		// �ۼ� �Ϸ� ��ư
		JButton writeBtn = new JButton("�ۼ� �Ϸ�");
		buttonPane.add(writeBtn);

		// ��ư(writeBtn) Ŭ�� �̺�Ʈ
		writeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// �ۼ��� ���� ó��
				String sTitle = textFld.getText();
				String sContent = detailFld.getText();
				String sYyyy = yyyy[yyyyCbx.getSelectedIndex()];
				String sMm = mm[mmCbx.getSelectedIndex()];
				String sDd = dd[ddCbx.getSelectedIndex()];
				String sApm = apm[apmCbx.getSelectedIndex()];
				String sHh = hh[hhCbx.getSelectedIndex()];
				String sMi = mi[miCbx.getSelectedIndex()];

				if (sTitle.isEmpty() || sTitle.equals(textInit)) {
					JOptionPane.showMessageDialog(null, textInit, "�˸�", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (sContent.isEmpty() || sContent.equals(detailInit)) {
					JOptionPane.showMessageDialog(null, detailInit, "�˸�", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				BoardDTO dto = new BoardDTO();
				dto.setBoardTitle(sTitle);
				dto.setBoardContent(sContent);

				dto.setBoardWordYyyy(sYyyy);
				dto.setBoardWordMm(sMm);
				dto.setBoardWordDd(sDd);

				dto.setBoardWordApm(sApm);
				dto.setBoardWordHh(sHh);
				dto.setBoardWordMi(sMi);

				dto.setBoardFilePath(filePath);
				
				dto.setUserId("test");

				int cnt = bs.insertBoard(dto);
				if (cnt > 0) {
					JOptionPane.showMessageDialog(null, "�ۼ��� �Ϸ�Ǿ����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);

					List<BoardDTO> updatedBoardList = bs.selectBoardList(new BoardDTO());
					BoardListFrame.updateBoardList(updatedBoardList); // �Խù� ��� ����

					dispose(); // ���� ������ �ݱ�
					BoardListFrame.showBoardList(); // �Խù� ��� â ����
				} else {
					JOptionPane.showMessageDialog(null, "������ �߻��Ͽ����ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		// �ۼ� ��� ��ư
		JButton cancelBtn = new JButton("�ۼ� ���");
		cancelBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buttonPane.add(cancelBtn);

		cancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		setContentPane(contentPane);
	}

	/**
	 * Create the frame.
	 * 
	 * @return
	 */
	public void BoardSearchFrame() {

		if (searchFrame != null) {
			searchFrame.dispose();
		}
		searchFrame = new JFrame();
		// ������ Ÿ��Ʋ��
		searchFrame.setTitle("BoardSearchFrame");
		// ������ ��ġ, ũ��(�ȼ�)
		searchFrame.setBounds(600, 100, 500, 850);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel pagePane = new JPanel();
		pagePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		pagePane.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(pagePane, BorderLayout.NORTH);

		JButton prevButton = new JButton("<");
		pagePane.add(prevButton);

		prevButton.addMouseListener(new MouseAdapter() {

			@Override
			@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent e) {
				if (currentPage == 1) {
					return;
				} else {
					currentPage -= 1;
				}

				if ((result = rna.getAddress(serarch, currentPage)) != null) {
					String errorCode = (String) result.get("errorCode");
					String errorMessage = (String) result.get("errorMessage");

					if (errorCode.equals("0")) {
						totalPage = (int) result.get("totalPage");
						if (totalPage > 0) {
							addrs = (List<String>) result.get("list");
						} else {
							JOptionPane.showMessageDialog(null, "��ȸ ����� �����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, errorMessage, "�˸�", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				/* ��ȸ ��� ���� */
				SearchListUpdate();
			}
		});

		pageLbl = new JLabel();
		pageLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
		pagePane.add(pageLbl);

		JButton nextButton = new JButton(">");
		pagePane.add(nextButton);

		nextButton.addMouseListener(new MouseAdapter() {

			@Override
			@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent e) {
				if (currentPage == totalPage) {
					return;
				} else {
					currentPage += 1;
				}

				if ((result = rna.getAddress(serarch, currentPage)) != null) {
					String errorCode = (String) result.get("errorCode");
					String errorMessage = (String) result.get("errorMessage");

					if (errorCode.equals("0")) {
						totalPage = (int) result.get("totalPage");
						if (totalPage > 0) {
							addrs = (List<String>) result.get("list");
						} else {
							JOptionPane.showMessageDialog(null, "��ȸ ����� �����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, errorMessage, "�˸�", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				/* ��ȸ ��� ���� */
				SearchListUpdate();
			}
		});

		listPane = new JPanel();
		listPane.setLayout(new GridLayout(10, 1));
		JScrollPane scrollPane = new JScrollPane(listPane);
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		// �ݱ� ��ư
		JButton cancelBtn = new JButton("�ݱ�");
		buttonPane.add(cancelBtn);

		cancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchFrame.dispose();
			}
		});

		/* ��ȸ ��� ���� */
		SearchListUpdate();

		searchFrame.setVisible(true);
		searchFrame.setContentPane(contentPane);
	}

	/* ��ȸ ��� ���� */
	public void SearchListUpdate() {
		if (addrs != null && addrs.size() > 0) {
			// ��� ��ü ����
			listPane.removeAll();
			for (String addr : addrs) {

				// ��� �߰�
				JPanel optionPane = new JPanel();
				optionPane.setLayout(new BorderLayout());
				listPane.add(optionPane);

				JLabel optionLbl = new JLabel(addr);
				optionPane.add(optionLbl, BorderLayout.WEST);

				JPanel buttonPane = new JPanel();
				buttonPane.setBorder(new EmptyBorder(15, 5, 15, 5));
				optionPane.add(buttonPane, BorderLayout.EAST);

				JButton addrBtn = new JButton("����");
				buttonPane.add(addrBtn);

				addrBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						List<AddressDTO> list = nas.getGeocode(addr);
						if (list != null && list.size() > 0) {
							File file = nas.getStatic(list.get(0));
							if (file != null && file.length() > 0) {
								filePath = file.getPath();
								ImageIcon img = new ImageIcon(filePath);
								imageLbl.setIcon(img);
							}
						}
					}
				});
			}
			listPane.revalidate(); // UI ������Ʈ
			listPane.repaint(); // UI ����

			pageLbl.setText(Integer.toString(currentPage) + " / " + Integer.toString(totalPage));
			pageLbl.revalidate(); // UI ������Ʈ
			pageLbl.repaint(); // UI ����
		}
	}
}
