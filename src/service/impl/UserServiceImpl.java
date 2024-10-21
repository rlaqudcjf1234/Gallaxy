package service.impl;

import org.apache.ibatis.session.SqlSession;

import config.MySqlSessionFactory;
import dao.UserDAO;
import dto.UserDTO;
import service.UserService;

public class UserServiceImpl implements UserService {

	UserDAO userDao;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
		userDao = new UserDAO();
	}

	@Override
	public UserDTO selectUser(String userId) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		UserDTO dto = null;
		try {
			dto = userDao.selectUser(session, userId);
		} catch (Exception e) {
			// TODO: handle exception\
			e.printStackTrace();
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		return dto;
	}

	@Override
	public int insertUser(UserDTO dto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		int cnt = 0;
		try {
			cnt = userDao.insertUser(session, dto);
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
