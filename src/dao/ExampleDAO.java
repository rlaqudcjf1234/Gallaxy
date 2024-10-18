package dao;

import org.apache.ibatis.session.SqlSession;

public class ExampleDAO {

	public boolean selectInit(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectOne("example.selectInit");
	}

}
