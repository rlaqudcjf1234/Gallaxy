package frame;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class myPageBase extends JFrame {

	//private static final Component JPane = null;

	private static myPageBase instance;
	
    public static myPageBase getInstance(myPageEdit editPage) {
        // editPage를 사용하여 화면 전환 로직 구현
        return instance;
    }

	private myPageBase(JPanel panel) {
		// 시스템 정보(화면 크기를 얻기 위한 Toolkit)
		Toolkit tk = Toolkit.getDefaultToolkit();// 해상도

		// 기본 JFrame 구조
		setTitle("마이페이지");
		setLayout(null);
		setBounds(((int) tk.getScreenSize().getWidth()) / 2 - 300, ((int) tk.getScreenSize().getHeight()) / 2 - 400,
				500, 850);
		//add(e);
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼
		setVisible(true);
	}// 생성자

	// 싱글톤 기법을 사용하려고 한다
	public static void getInstance(JPanel panel) {
		// static으로 선언했으므로 해당 메서드가 생성자보다도 먼저 호출된다
		if (instance == null) {
		instance = new myPageBase(panel);// 생성자를 통해 기본 프레임 정의
		} else {
		instance.getContentPane().removeAll();
		instance.add(panel);
		instance.revalidate(); // 레이아웃 관리자에게 레이아웃정보를 다시 계산하도록 지시
		instance.repaint(); // 레이아웃을 새로 그린다
		}// getInstance() if
		instance.setVisible(true);
	


}
}