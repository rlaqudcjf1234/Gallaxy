package frame;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class myPageBase extends JFrame {

	//private static final Component JPane = null;

	private static myPageBase instance;
	
    public static myPageBase getInstance(myPageEdit editPage) {
        // editPage�� ����Ͽ� ȭ�� ��ȯ ���� ����
        return instance;
    }

	private myPageBase(JPanel panel) {
		// �ý��� ����(ȭ�� ũ�⸦ ��� ���� Toolkit)
		Toolkit tk = Toolkit.getDefaultToolkit();// �ػ�

		// �⺻ JFrame ����
		setTitle("����������");
		setLayout(null);
		setBounds(((int) tk.getScreenSize().getWidth()) / 2 - 300, ((int) tk.getScreenSize().getHeight()) / 2 - 400,
				500, 850);
		//add(e);
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x��ư
		setVisible(true);
	}// ������

	// �̱��� ����� ����Ϸ��� �Ѵ�
	public static void getInstance(JPanel panel) {
		// static���� ���������Ƿ� �ش� �޼��尡 �����ں��ٵ� ���� ȣ��ȴ�
		if (instance == null) {
		instance = new myPageBase(panel);// �����ڸ� ���� �⺻ ������ ����
		} else {
		instance.getContentPane().removeAll();
		instance.add(panel);
		instance.revalidate(); // ���̾ƿ� �����ڿ��� ���̾ƿ������� �ٽ� ����ϵ��� ����
		instance.repaint(); // ���̾ƿ��� ���� �׸���
		}// getInstance() if
		instance.setVisible(true);
	


}
}