package service.impl;

import org.apache.ibatis.session.SqlSession;

import config.MySqlSessionFactory;
import dao.ExampleDAO;
import service.ExampleService;

public class ExampleServiceImpl implements ExampleService {

	ExampleDAO dao;

	public ExampleServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = new ExampleDAO();
	}
	
	@Override
	public boolean getInit() {
		
		SqlSession session = MySqlSessionFactory.getSqlSession();
		boolean b = false;
		
		try {
			b = dao.selectInit(session);
		}finally {
			session.close();
		}
		return b;
	}

}
