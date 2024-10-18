package frame;

import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class myPageBase extends JFrame {

	private static final Component JPane = null;

	private static myPageBase instance;

	private myPageBase(JPanel e) {
		// �ý��� ����(ȭ�� ũ�⸦ ��� ���� Toolkit)
		Toolkit tk = Toolkit.getDefaultToolkit();// �ػ�

		// �⺻ JFrame ����
		setTitle("����������");
		setLayout(null);
		setBounds(((int) tk.getScreenSize().getWidth()) / 2 - 300, ((int) tk.getScreenSize().getHeight()) / 2 - 400,
				500, 850);
		add(e);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x��ư
	}// ������

	// �̱��� ����� ����Ϸ��� �Ѵ�
	public static void getInstance(JPanel e) {
		// static���� ���������Ƿ� �ش� �޼��尡 �����ں��ٵ� ���� ȣ��ȴ�

		instance = new myPageBase(e);// �����ڸ� ���� �⺻ ������ ����

		instance.getContentPane().removeAll();
		instance.getContentPane().add(e);

		instance.revalidate(); // ���̾ƿ� �����ڿ��� ���̾ƿ������� �ٽ� ����ϵ��� ����
		instance.repaint(); // ���̾ƿ��� ���� �׸���
	}// getInstance()

}
