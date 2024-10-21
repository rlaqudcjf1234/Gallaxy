package frame;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import dto.BoardDTO;
import service.BoardService;
import service.impl.BoardServiceImpl;

public class BoardListFrame extends JFrame {

    private JPanel postListPanel; // �Խù� ��� �г�
    private BoardService boardService; // ���� ��ü

    public BoardListFrame() {
        boardService = new BoardServiceImpl(); // ���� �ʱ�ȭ
        initializeUI(); // UI �ʱ�ȭ
        loadBoardList(); // �Խù� ��� �ε�
    }

    // UI �ʱ�ȭ �޼���
    private void initializeUI() {
        setTitle("���� ����Ʈ �Խ���");
        setBounds(700, 100, 500, 850);
        setBackground(new Color(247, 244, 242));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �ΰ� ��ư ����
        JButton btnLogo = createImageButton("Running Mate.png", 30, 50, 110, 95, 
            e -> JOptionPane.showMessageDialog(this, "����ȭ��!"));
        add(btnLogo);

        // �� �ۼ� ��ư ����
        JButton btnWrite = new JButton("�� �ۼ�");
        btnWrite.setBounds(360, 170, 80, 30);
        btnWrite.setFont(new Font("���� ���", Font.PLAIN, 13));
        btnWrite.setBackground(Color.LIGHT_GRAY);
        btnWrite.addActionListener(e -> openWriteFrame());
        add(btnWrite);

        // �˻� �Է�â ����
        TextField contentSearch = new TextField("�˻��� �� ������ �Է��ϼ���");
        contentSearch.setBounds(50, 640, 300, 30);
        contentSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contentSearch.setText(""); // Ŭ�� �� �ؽ�Ʈ �ʱ�ȭ
            }
        });
        add(contentSearch);

        // �Խù� ��� ��ũ�� �г� ����
        postListPanel = new JPanel();
        postListPanel.setLayout(new BoxLayout(postListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(postListPanel);
        scrollPane.setBounds(50, 220, 400, 400);
        add(scrollPane);

        // �α׾ƿ�, ����������, ����ȭ�� �� �߰�
        /*add(createClickableLabel("�α׾ƿ�", 260, 50, e -> showMessage("�α׾ƿ� �մϴ�!")));
        add(createClickableLabel("����������", 330, 50, e -> showMessage("������������ �̵�!")));
        add(createClickableLabel("����ȭ��", 415, 50, e -> showMessage("����ȭ������ �̵�!")));
*/
        setVisible(true); // ȭ�� ǥ��
    }

    // �Խù� ����� �ε��ϴ� �޼���
    private void loadBoardList() {
        List<BoardDTO> boardList = boardService.selectBoardList(new BoardDTO());
        updateBoardList(boardList);
    }

    // �Խù� ����� �����ϴ� �޼���
    private void updateBoardList(List<BoardDTO> boardList) {
        postListPanel.removeAll();
        for (BoardDTO board : boardList) {
            JButton postButton = new JButton(board.getBoardTitle());
            postButton.setHorizontalAlignment(SwingConstants.LEFT);
            postButton.setBorderPainted(false);
            postButton.setFocusPainted(false);
            postButton.setBackground(Color.WHITE);
            postButton.addActionListener(e -> new BoardDetailFrame(board));
            postListPanel.add(postButton);
        }
        postListPanel.revalidate();
        postListPanel.repaint();
    }

    // �� �ۼ� ȭ���� ���� �޼���
    private void openWriteFrame() {
        new BoardWriteFrame().setVisible(true);
        dispose(); // ���� ������ �ݱ�
    }

    // �̹��� ��ư�� �����ϴ� ���� �޼���
    private JButton createImageButton(String path, int x, int y, int width, int height, ActionListener action) {
        JButton button = new JButton();
        try {
            button.setIcon(new ImageIcon(path));
        } catch (Exception e) {
            System.err.println("�̹��� �ε� ����: " + path);
        }
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBounds(x, y, width, height);
        button.addActionListener(action);
        return button;
    }

    // Ŭ�� ������ ���� �����ϴ� ���� �޼���
    private JLabel createClickableLabel(String text, int x, int y, MouseListener listener) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLUE);
        label.setBounds(x, y, 100, 40);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(listener);
        return label;
    }

    // �޽����� ǥ���ϴ� �޼���
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /*public static void main(String[] args) {
        new BoardListFrame();
    }*/
}
