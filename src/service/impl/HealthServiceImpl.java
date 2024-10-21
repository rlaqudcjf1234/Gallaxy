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
			if(healthDAO.selectHealth(session, dto) == null) {
				cnt = healthDAO.insertHealth(session, dto);
			} else {
				cnt = healthDAO.updateHealth(session, dto);
			}
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
	public HealthDTO selectHealthSt(HealthDTO dto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		HealthDTO result = null;
		try {
			result = healthDAO.selectHealthSt(session, dto);
		} catch (Exception e) {
			// TODO: handle exception\
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public HealthDTO selectHealth(HealthDTO dto) {
		// TODO Auto-generated method stub
		SqlSession session = MySqlSessionFactory.getSqlSession();
		HealthDTO result = null;
		try {
			result = healthDAO.selectHealth(session, dto);
		} catch (Exception e) {
			// TODO: handle exception\
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}
	
}
