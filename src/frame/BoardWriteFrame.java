package frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.AddressDTO;
import service.NaverApiService;
import service.RetrieveNewAdress;
import service.impl.NaverApiServiceImpl;
import service.impl.RetrieveNewAdressImpl;

public class BoardWriteFrame extends JFrame {

	RetrieveNewAdress rna = new RetrieveNewAdressImpl();
	NaverApiService nas = new NaverApiServiceImpl();
	
	String filePath = "temp/서울특별시 종로구 삼일대로17길 42-4.jpg";

	/**
	 * 
	 */
	private static final long serialVersionUID = -9174227746682050944L;

	public BoardWriteFrame() {
		//프레임 타이틀바
		setTitle("BoardWriteFrame");
		//프레임 크기(픽셀)
		setSize(500, 850);
		//X버튼 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임의 X 클릭 시 종료.

		// JFrame 안쪽 영역.
		Container container = getContentPane();

		// JFrame 안쪽 영역 상단에 들어갈 주소입력
		JPanel search = initSearch();
		container.add(BorderLayout.NORTH, search); // 상단 pan 세팅
		
		JPanel input = initInput();
		container.add(BorderLayout.CENTER, input); // 중단 pan 세팅

		setVisible(true);

	}

	private JPanel initInput() {
		// TODO Auto-generated method stub
		JPanel pan = new JPanel();
		
		
		
		return pan;
	}

	public JPanel initSearch() {
		JPanel pan = new JPanel();
		JLabel addressLbl = new JLabel("주소입력"); // 주소 조회 label
		pan.add(addressLbl);

		JTextField address = new JTextField(30); // 주소 조회 input
		pan.add(address);

		JButton btn = new JButton("클릭"); // 주소 조회 실행
		pan.add(btn);
		
		ImageIcon img = new ImageIcon(filePath);
		JLabel imageLbl = new JLabel(img);
		pan.add(imageLbl);

		// pan에 생성한 버튼(btn) 클릭 시 처리하는 이벤트 핸들러.
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String serarch = address.getText();
				List<String> addrs = rna.getAddress(serarch);

				if (addrs != null && addrs.size() > 0) {
					List<AddressDTO> list = nas.getGeocode(addrs.get(0));
					if (list != null && list.size() > 0) {
						File file = nas.getStatic(list.get(0));
						if (file != null && file.length() > 0) {
							filePath = file.getPath();
							ImageIcon img = new ImageIcon(filePath);
							imageLbl.setIcon(img);
						}
					}
				}
			}
		});

		return pan;
	}

	public static void main(String[] args) {
		new BoardWriteFrame();
	}

}
