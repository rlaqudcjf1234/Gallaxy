package dao;

import org.apache.ibatis.session.SqlSession;

import dto.HealthDTO;

public class HealthDAO {

	public int insertHealth(SqlSession session, HealthDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("health.insertHealth", dto);
	}

}
