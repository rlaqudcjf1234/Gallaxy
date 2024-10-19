package service;

import dto.BoardDTO;

public interface BoardService {

	// 게시판 등록
	public int insertBoard(BoardDTO dto);
}
