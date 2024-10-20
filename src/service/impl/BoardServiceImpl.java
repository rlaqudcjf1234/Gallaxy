package service.impl;

import java.util.List;

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

	@Override
	public int selectBoardCnt(BoardDTO dto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		int cnt = 0;
		try {
			cnt = boardDAO.selectBoardCnt(session, dto);
		} catch (Exception e) {
			// TODO: handle exception\
			e.printStackTrace();
		} finally {
			session.close();
		}

		return cnt;
	}

	@Override
	public List<BoardDTO> selectBoardList(BoardDTO dto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<BoardDTO> list = null;
		try {
			list = boardDAO.selectBoardList(session, dto);
		} catch (Exception e) {
			// TODO: handle exception\
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public BoardDTO selectBoard(int boardId) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		BoardDTO dto = null;
		try {
			dto = boardDAO.selectBoard(session, boardId);
		} catch (Exception e) {
			// TODO: handle exception\
			e.printStackTrace();
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public int updateBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		int cnt = 0;
		try {
			cnt = boardDAO.updateBoard(session, dto);
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
