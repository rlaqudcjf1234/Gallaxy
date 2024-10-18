package dao;

import org.apache.ibatis.session.SqlSession;

import dto.BoardDTO;

public class BoardDAO {

	public int insertBoard(SqlSession session, BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return session.insert("board.insertBoard", dto);
	}

}
