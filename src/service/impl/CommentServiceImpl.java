package service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import config.MySqlSessionFactory;
import dao.CommentDAO;
import dto.BoardDTO;
import dto.CommentDTO;
import service.CommentService;

public class CommentServiceImpl implements CommentService {

	CommentDAO commentDAO;
	
	public CommentServiceImpl() {
		// TODO Auto-generated constructor stub
		commentDAO = new CommentDAO();
	}

	@Override
	public int insertComment(CommentDTO dto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		int cnt = 0;
		try {
			cnt = commentDAO.insertComment(session, dto);
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
	public List<CommentDTO> selectCommentList(CommentDTO dto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<CommentDTO> list = null;
		try {
			list = commentDAO.selectCommentList(session, dto);
		} catch (Exception e) {
			// TODO: handle exception\
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}
	
}
