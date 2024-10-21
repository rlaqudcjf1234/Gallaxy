package service.impl;

import org.apache.ibatis.session.SqlSession;

import config.MySqlSessionFactory;
import dao.HealthDAO;
import dto.HealthDTO;
import service.HealthService;

public class HealthServiceImpl implements HealthService {

	HealthDAO healthDAO;
	
	public HealthServiceImpl() {
		// TODO Auto-generated constructor stub
		healthDAO = new HealthDAO();
	}

	@Override
	public int insertHealth(HealthDTO dto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		int cnt = 0;
		try {
			cnt = healthDAO.insertHealth(session, dto);
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
