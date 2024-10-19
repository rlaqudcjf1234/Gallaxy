package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.BoardDTO;

public class BoardDAO {

	public int insertBoard(SqlSession session, BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return session.insert("board.insertBoard", dto);
	}
	
	public int selectBoardCnt(SqlSession session, BoardDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("board.selectBoardCnt", dto);
	}

	public List<BoardDTO> selectBoardList(SqlSession session, BoardDTO dto) {
		// TODO Auto-generated method stub
		return session.selectList("board.selectBoardList", dto);
	}

	
}
