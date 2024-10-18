package config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {
	static SqlSessionFactory sqlSessionFactory = null;
	static {// �ʱ�ȭ
		// jdbc.properties���� ���
		String resource = "MybatisConfig.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			System.out.println("�ʱ�ȭ ����");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}// end static

	// SqlSession ��ȯ
	public static SqlSession getSqlSession() {
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
}
