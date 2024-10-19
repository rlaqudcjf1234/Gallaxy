package service;

import java.util.List;

import dto.BoardDTO;

public interface BoardService {

	// 게시판 등록
	public int insertBoard(BoardDTO dto);

	// 게시판 전체 건수 조회
	public int selectBoardCnt(BoardDTO dto);

	// 게시판 목록 조회
	public List<BoardDTO> selectBoardList(BoardDTO dto);

}
