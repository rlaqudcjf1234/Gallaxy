package frame;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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
		// �г� ���� �� ���̾ƿ� ����
		JPanel mainBoard = new JPanel(null); // null ���̾ƿ� ���
		mainBoard.setBounds(0, 0, 500, 750); // �г� ��ġ�� ũ�� ����(���� ���α��� 850���� ����)

		// �̹��� ������ ����
		imgComm = new ImageIcon("resources/community.png");
		imgHC = new ImageIcon("resources/hc.png");
		imgMyP = new ImageIcon("resources/mypage.png");
		imgLogoA = new ImageIcon("resources/logo.png");

		// �̹��� ũ�� ���� (�ʿ� ��)
		Image imgLogo = imgLogoA.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		imgLogoA = new ImageIcon(imgLogo); // ������ �̹����� ������Ʈ

		// ��ư ���� �� �̹��� ������ �߰�
		btnComm = new JButton("Ŀ�´�Ƽ", imgComm);
		btnHC = new JButton("�ｺ �ɾ�", imgHC);
		btnMyP = new JButton("���� ������", imgMyP);

		// ��ư ��ġ �� ũ�� ����
		btnComm.setBounds(29, 412, 127, 127);
		btnHC.setBounds(185, 256, 127, 127);
		btnMyP.setBounds(341, 100, 127, 127);

		// �гο� ��ư �߰�
		mainBoard.add(btnComm);
		mainBoard.add(btnHC);
		mainBoard.add(btnMyP);

		// ��ư �̺�Ʈ -> Ŀ�´�Ƽ
		btnComm.addActionListener(e -> { //BoardListFrame �� �ٸ� ���Ͽ� ������ �־�ߵ�
			System.out.println("Ŀ�´�Ƽ ��ư Ŭ����!"); // ����׿� �޽���
			 new BoardListFrame().setVisible(true); // ���ο� â ����
			 
			dispose(); // ���� â �ݱ�
		});
		// ��ư �̺�Ʈ -> �ｺ �ɾ�
		btnHC.addActionListener(e -> {
			System.out.println("�ｺ �ɾ� ��ư Ŭ����!"); // ����׿� �޽���
			new BoardListFrame().setVisible(true); // ���Ŀ� BoardListFrame�� �ｺ�ɾ� ���Ϸ� ����
			
			dispose(); // ���� â �ݱ�
		});
		// ��ư �̺�Ʈ -> Ŀ�´�Ƽ
		btnMyP.addActionListener(e -> {
			System.out.println("���� ������ ��ư Ŭ����!"); // ����׿� �޽���
			new myPageBegin(); // ���Ŀ� BoardListFrame�� ���� ������ ���Ϸ� ����
			
			dispose(); // ���� â �ݱ�
		});
		// �г��� JFrame�� �߰�
		add(mainBoard);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MainBoard());
	}
}
