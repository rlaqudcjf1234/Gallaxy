package frame;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import dto.BoardDTO;
import service.BoardService;
import service.impl.BoardServiceImpl;

public class BoardListFrame extends JFrame {

    private JPanel postListPanel; // 게시물 목록 패널
    private BoardService boardService; // 서비스 객체

    public BoardListFrame() {
        boardService = new BoardServiceImpl(); // 서비스 초기화
        initializeUI(); // UI 초기화
        loadBoardList(); // 게시물 목록 로드
    }

    // UI 초기화 메서드
    private void initializeUI() {
        setTitle("러닝 메이트 게시판");
        setBounds(700, 100, 500, 850);
        setBackground(new Color(247, 244, 242));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 로고 버튼 생성
        JButton btnLogo = createImageButton("Running Mate.png", 30, 50, 110, 95, 
            e -> JOptionPane.showMessageDialog(this, "메인화면!"));
        add(btnLogo);

        // 글 작성 버튼 생성
        JButton btnWrite = new JButton("글 작성");
        btnWrite.setBounds(360, 170, 80, 30);
        btnWrite.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        btnWrite.setBackground(Color.LIGHT_GRAY);
        btnWrite.addActionListener(e -> openWriteFrame());
        add(btnWrite);

        // 검색 입력창 생성
        TextField contentSearch = new TextField("검색할 글 제목을 입력하세요");
        contentSearch.setBounds(50, 640, 300, 30);
        contentSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contentSearch.setText(""); // 클릭 시 텍스트 초기화
            }
        });
        add(contentSearch);

        // 게시물 목록 스크롤 패널 생성
        postListPanel = new JPanel();
        postListPanel.setLayout(new BoxLayout(postListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(postListPanel);
        scrollPane.setBounds(50, 220, 400, 400);
        add(scrollPane);

        // 로그아웃, 마이페이지, 메인화면 라벨 추가
        /*add(createClickableLabel("로그아웃", 260, 50, e -> showMessage("로그아웃 합니다!")));
        add(createClickableLabel("마이페이지", 330, 50, e -> showMessage("마이페이지로 이동!")));
        add(createClickableLabel("메인화면", 415, 50, e -> showMessage("메인화면으로 이동!")));
*/
        setVisible(true); // 화면 표시
    }

    // 게시물 목록을 로드하는 메서드
    private void loadBoardList() {
        List<BoardDTO> boardList = boardService.selectBoardList(new BoardDTO());
        updateBoardList(boardList);
    }

    // 게시물 목록을 갱신하는 메서드
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

    // 글 작성 화면을 여는 메서드
    private void openWriteFrame() {
        new BoardWriteFrame().setVisible(true);
        dispose(); // 현재 프레임 닫기
    }

    // 이미지 버튼을 생성하는 헬퍼 메서드
    private JButton createImageButton(String path, int x, int y, int width, int height, ActionListener action) {
        JButton button = new JButton();
        try {
            button.setIcon(new ImageIcon(path));
        } catch (Exception e) {
            System.err.println("이미지 로드 실패: " + path);
        }
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBounds(x, y, width, height);
        button.addActionListener(action);
        return button;
    }

    // 클릭 가능한 라벨을 생성하는 헬퍼 메서드
    private JLabel createClickableLabel(String text, int x, int y, MouseListener listener) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLUE);
        label.setBounds(x, y, 100, 40);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(listener);
        return label;
    }

    // 메시지를 표시하는 메서드
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /*public static void main(String[] args) {
        new BoardListFrame();
    }*/
}
