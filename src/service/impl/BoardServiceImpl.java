package service.impl;

import org.apache.ibatis.session.SqlSession;

import config.MySqlSessionFactory;
import dao.BoardDAO;
import dto.BoardDTO;
import service.BoardService;

public class BoardServiceImpl implements BoardService {

	BoardDAO boardDAO;

	public BoardServiceImpl() {
		// TODO Auto-generated constructor stub
		boardDAO = new BoardDAO();
	}

	@Override
	public int insertBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		int cnt = 0;
		try {
			cnt = boardDAO.insertBoard(session, dto);
		} catch (Exception e) {
			// TODO: handle exception\
			e.printStackTrace();
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}

		return cnt;
	}

}
