package dao;

import org.apache.ibatis.session.SqlSession;

import dto.AddressDTO;

public class AddressDAO {
	
	public int setAddress(SqlSession session, AddressDTO dto) {
		
		
		return session.insert("board.insertAddress", dto);
	}

}
