package dao;

import org.apache.ibatis.session.SqlSession;

import dto.UserDTO;

public class UserDAO {

	public UserDTO selectUser(SqlSession session, String userId) {
		// TODO Auto-generated method stub
		return session.selectOne("user.selectUser", userId);
	}

	public int insertUser(SqlSession session, UserDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("user.insertUser", dto);
	}

	public int updateUser(SqlSession session, UserDTO dto) {
		return session.update("user.updateUser", dto);
	}

}
