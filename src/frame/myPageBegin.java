package frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.BoardDTO;
import main.Main;
import service.BoardService;
import service.impl.BoardServiceImpl;

public class myPageBegin extends JFrame {
	
	BoardService bs = new BoardServiceImpl();
	
	private JLabel infoLabel; // ������ ǥ���� JLabel �߰�
	private List<Post> posts; // �Խñ� ���
	private JPanel postPanel; // �Խñ��� ǥ���� �г�

	public myPageBegin() {
		MP();
	}

	public void MP() {
		setTitle("����������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 850); // ������ ������
		setLayout(null); // null ���̾ƿ� ���

		JPanel panel = new JPanel();
		panel.setLayout(null); // �гε� null ���̾ƿ� ���
		panel.setBounds(0, 0, 500, 850); // �г� ũ�� ����

		JButton btnBack = new JButton("�ڷΰ���");
		JButton btnEdit = new JButton("ȸ������ ����/ �α׾ƿ�");

		btnBack.setSize(450, 35);
		btnBack.setLocation(25, 700); // �ϴ� ��ġ

		btnBack.setFont(new Font("����", Font.BOLD, 14));
		btnEdit.setSize(450, 35);
		btnEdit.setLocation(25, 650); // btnBack ���� ��ġ

		btnEdit.setFont(new Font("����", Font.BOLD, 14));

		// ���� ǥ�ÿ� JLabel ����
		String userId = Main.USER.getUserId();
		String userName = Main.USER.getUserName();
		String userNickName = Main.USER.getUserNickName();
		String userEmail = Main.USER.getUserEmail();
		
		infoLabel = new JLabel(userId); // �ʱ�ȭ �߰�
		infoLabel.setFont(new Font("����", Font.PLAIN, 14));
		infoLabel.setOpaque(true); // ������ ���̰� ����
		infoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // �׵θ� �߰�

		// ũ�� �� ��ġ ����
		infoLabel.setBounds(25, 50, 450, 150); // �߾� ���� �� ũ�� ����
		panel.add(infoLabel); // JLabel �߰�

		// ===========================�Խñ� ���� =============================

		// �Խñ� ��� �ʱ�ȭ
        posts = new ArrayList<>();
        loadPosts(); // �Խñ� �ε�

        // ���̺��� Į���� ����
        String[] columnNames = { "����","��� ��" };

        // �����͸� 2D �迭�� ��ȯ
        Object[][] data = new Object[posts.size()][2];
        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            data[i][0] = post.getTitle();        // ����
            data[i][1] = post.getCommentCount(); // ��� ��
        }

        // ���̺� �� ����
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // JTable ����
        JTable postTable = new JTable(model);
        postTable.setBounds(25, 220, 450, 400); // ���̺� ��ġ �� ũ�� ����
        postTable.setRowHeight(30); // ���� ���� ����
        postTable.getColumnModel().getColumn(0).setPreferredWidth(300); // ���� Į�� ũ�� ����
        //postTable.getColumnModel().getColumn(1).setPreferredWidth(60); // �ۼ��� Į�� ũ�� ����
        postTable.getColumnModel().getColumn(1).setPreferredWidth(60); // ��� �� Į�� ũ�� ����

        // ���̺� Ŭ�� �̺�Ʈ �߰�
        postTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = postTable.getSelectedRow(); // Ŭ���� ���� �ε���
                Post selectedPost = posts.get(row); // �ش� �Խñ� ��������
                openPostDetail(selectedPost); // �� ������ ����
            }

			private void openPostDetail(Post selectedPost) {
				// TODO Auto-generated method stub
				
			}
        });

        // ��ũ�� �г��� �߰��Ͽ� ���̺� ��ũ�� �����ϰ� ����
        JScrollPane scrollPane = new JScrollPane(postTable);
        scrollPane.setBounds(25, 220, 450, 400); // ��ũ�� �г� ��ġ �� ũ�� ����
        panel.add(scrollPane); // �гο� ��ũ�� �г� �߰�

		// ��ư �̺�Ʈ ����
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainBoard(); // MainBoard �ν��Ͻ� ����
				dispose(); // ���� â �ݱ�
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new myPageEdit();
				// myPageBase.getInstance(new myPageEdit());
			}
		});

		panel.add(btnBack);
		panel.add(btnEdit);
		add(panel); // �г��� �����ӿ� �߰�
		setVisible(true); // �������� ���̵��� ����
	}

	private void loadPosts() {
		
		BoardDTO dto = new BoardDTO();
		dto.setPageSize(5);
		dto.setUserId(Main.USER.getUserId());
		
		List<BoardDTO> list = bs.selectBoardList(dto);
		
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++) {
				// �Խñ� ������ �߰�
				posts.add(new Post(i+1+"", list.get(i).getBoardTitle()));
				
				 // �Խñ��� JLabel�� �߰�
	            JLabel postLabel = new JLabel((i+1) + ". " + list.get(i).getBoardTitle());
            postLabel.setBounds(10, i * 30, 430, 30);  // �� �Խñ� ��ġ �� ũ�� ����
	            postPanel.add(postLabel);  // �Խñ��� �гο� �߰�
			}
		

		} else {
		
	        
			
		}
		 
	}

	public static void main(String[] args) {
		new myPageBegin(); // myPageBegin Ŭ������ �ν��Ͻ��� �����Ͽ� ǥ��
	}
}

// Post Ŭ����
class Post {
	private String title;
	private String content;

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public Object getCommentCount() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
}
