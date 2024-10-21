package dao;

import org.apache.ibatis.session.SqlSession;

import dto.HealthDTO;

public class HealthDAO {

	public HealthDTO selectHealth(SqlSession session, HealthDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("health.selectHealth", dto);
	}

	public int insertHealth(SqlSession session, HealthDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("health.insertHealth", dto);
	}

	public int updateHealth(SqlSession session, HealthDTO dto) {
		// TODO Auto-generated method stub
		return session.update("health.updateHealth", dto);
	}

	public HealthDTO selectHealthSt(SqlSession session, HealthDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne("health.selectHealthSt", dto);
	}

}
