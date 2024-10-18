package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

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
import javax.swing.Icon;
import javax.swing.SwingConstants;
import javax.swing.DropMode;

public class BoardWriteFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6032491971534575326L;

	BoardService bs = new BoardServiceImpl();
	NaverApiService nas = new NaverApiServiceImpl();
	RetrieveNewAdress rna = new RetrieveNewAdressImpl();

	String filePath = "temp/서울특별시 종로구 종로 13-3 구두수선대.jpg";
	String textInit = "제목을 입력하세요";
	String contentInit = "내용을 입력하세요";

	private JPanel contentPane;
	private JLabel addressLbl;
	private JTextField addressFld;
	private JButton searchBtn;
	private JLabel imageLbl;
	private JTextField textFld;
	private JTextArea contentFld;

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
		// 프레임 타이틀바
		setTitle("BoardWriteFrame");
		// X버튼 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 프레임 위치, 크기(픽셀)
		setBounds(100, 100, 500, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		addressLbl = new JLabel("주소입력");
		contentPane.add(addressLbl);

		addressFld = new JTextField(30);
		contentPane.add(addressFld);

		searchBtn = new JButton("조회");
		contentPane.add(searchBtn);

		// 버튼(searchBtn) 클릭 이벤트
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String serarch = addressFld.getText();
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

		imageLbl = new JLabel(new ImageIcon(filePath));
		contentPane.add(imageLbl);
		/*
		 * textLbl = new JLabel("제목"); contentPane.add(textLbl);
		 */
		textFld = new JTextField(textInit);
		textFld.setColumns(35);
		contentPane.add(textFld);

		// 클릭 이벤트 기본 텍스트(textFld) 삭제
		textFld.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFld.getText().equals(textInit)) {
					textFld.setText(""); // 클릭 시 텍스트 초기화
				}
			}
		});

		contentFld = new JTextArea(contentInit);
		contentFld.setColumns(35);
		contentFld.setRows(10);
		contentPane.add(contentFld);

		// 클릭 이벤트 기본 텍스트(contentFld) 삭제
		contentFld.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (contentFld.getText().equals(contentInit)) {
					contentFld.setText(""); // 클릭 시 텍스트 초기화
				}
			}
		});

		// 작성 완료 버튼
		JButton writeBtn = new JButton("작성 완료");
		writeBtn.setBounds(250, 100, 100, 30);
		contentPane.add(writeBtn);

		// 버튼(writeBtn) 클릭 이벤트
		writeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 작성된 내용 처리
				String title = textFld.getText();
				String content = contentFld.getText();

				if (title.isEmpty() || title.equals(textInit)) {
					JOptionPane.showMessageDialog(null, textInit, "알림", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (content.isEmpty() || content.equals(contentInit)) {
					JOptionPane.showMessageDialog(null, contentInit, "알림", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				BoardDTO dto = new BoardDTO();
				dto.setBoardTitle(title);
				dto.setBoardContent(content);
				dto.setBoardFilePath(filePath);

				int cnt = bs.insertBoard(dto);
				if (cnt > 0) {
					JOptionPane.showMessageDialog(null, "작성이 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "오류가 발생하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		setContentPane(contentPane);
	}
}
