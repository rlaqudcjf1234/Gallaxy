package frame;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainBoard extends JFrame {

	private ImageIcon imgComm, imgMyP, imgHC, imgLogoA;
	private JButton btnComm, btnMyP, btnHC;

	public MainBoard() {
		// ���� ȭ�� ����
		mainView();
		addImage(); // �̹��� �� ��ư �߰�
	}

	public void mainView() {
		setTitle("����ȭ��");
		setBounds(700, 100, 500, 750); // ��ġ�� ũ�� (���� 850)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����� ���α׷� ����
		getContentPane().setBackground(new Color(247, 244, 242)); // ��� ��
		setLayout(null); // ���� ���̾ƿ� ���

		// ���� �̺�Ʈ �߰�
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
		// JFrame ���̰� ����
	}

	private void addImage() {
		JPanel mainBoard = new JPanel(null);
		mainBoard.setBounds(0, 0, 500, 750); // ���� 850�� ���� ����
		mainBoard.setBackground(new Color(247, 244, 242)); // �г� ���

		// �̹��� ũ�� ���� �� ��ư�� ����
		imgComm = resizeImage(new ImageIcon("image/Community.png"), 80, 80);
		imgHC = resizeImage(new ImageIcon("image/HealthCare.png"), 80, 80);
		imgMyP = resizeImage(new ImageIcon("image/MyPage.png"), 80, 80);
		imgLogoA = resizeImage(new ImageIcon("image/Galaxy_Logo.png"), 200, 200);

		// �ΰ� JLabel�� �߰�
		JLabel lblLogo = new JLabel(imgLogoA);
		lblLogo.setBounds(150, 50, 200, 200); // �߾� ��ܿ� ��ġ

		// ��ư ���� �� ����
		btnComm = new JButton("Ŀ�´�Ƽ", imgComm);
		btnHC = new JButton("�ｺ �ɾ�", imgHC);
		btnMyP = new JButton("���� ������", imgMyP);

		// ��ư ��ġ ����
		btnComm.setBounds(29, 300, 127, 127);
		btnHC.setBounds(185, 300, 127, 127);
		btnMyP.setBounds(341, 300, 127, 127);

		// ��ư ���� �� �׵θ� ����
		btnComm.setBackground(new Color(247, 244, 242));
		btnHC.setBackground(new Color(247, 244, 242));
		btnMyP.setBackground(new Color(247, 244, 242));

		btnComm.setBorderPainted(false);
		btnHC.setBorderPainted(false);
		btnMyP.setBorderPainted(false);

		// �ؽ�Ʈ�� �̹��� ���� ����
		btnComm.setHorizontalTextPosition(JButton.CENTER);
		btnComm.setVerticalTextPosition(JButton.BOTTOM);

		btnHC.setHorizontalTextPosition(JButton.CENTER);
		btnHC.setVerticalTextPosition(JButton.BOTTOM);

		btnMyP.setHorizontalTextPosition(JButton.CENTER);
		btnMyP.setVerticalTextPosition(JButton.BOTTOM);
		
		// �гο� ��ư �߰�
		mainBoard.add(btnComm);
		mainBoard.add(btnHC);
		mainBoard.add(btnMyP);
		mainBoard.add(lblLogo);

		// ���� ����
		btnComm.setMargin(new java.awt.Insets(5, 5, 5, 5));
		btnHC.setMargin(new java.awt.Insets(5, 5, 5, 5));
		btnMyP.setMargin(new java.awt.Insets(5, 5, 5, 5));

		btnComm.setForeground(Color.BLACK);
		btnHC.setForeground(Color.BLACK);
		btnMyP.setForeground(Color.BLACK);

		// �̺�Ʈ ������ �߰� (������ ����)
		btnComm.addActionListener(e -> {
			System.out.println("Ŀ�´�Ƽ ��ư Ŭ����!");
			new BoardListFrame();
			dispose();
		});
		btnHC.addActionListener(e -> {
			System.out.println("�ｺ �ɾ� ��ư Ŭ����!");
			new Calendarpp();
			dispose();
		});
		btnMyP.addActionListener(e -> {
			System.out.println("���� ������ ��ư Ŭ����!");
			new myPageBegin();
			dispose();
		});

		// �г��� JFrame�� �߰�
		add(mainBoard);
	}

	// �̹��� ũ�� ���� �޼���
	private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
		Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MainBoard());
	}
}
