package service;

import java.util.List;

import dto.BoardDTO;

public interface BoardService {

	// �Խ��� ���
	public int insertBoard(BoardDTO dto);

	// �Խ��� ��ü �Ǽ� ��ȸ
	public int selectBoardCnt(BoardDTO dto);

	// �Խ��� ��� ��ȸ
	public List<BoardDTO> selectBoardList(BoardDTO dto);
	
	// �Խ��� ��� ��ȸ
	public BoardDTO selectBoard(int boardId);
	
	// �Խ��� ��� ����
	public int updateBoard(BoardDTO dto);
}
