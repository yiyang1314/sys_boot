package cn.sz.gl.test;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;

import cn.sz.gl.dao.UsersDao;
import cn.sz.gl.dbc.MySqlSessionFactory;
import cn.sz.gl.pojo.Users;

public class Test03 {

	public static void main(String[] args) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MySqlSessionFactory.getMySqlSession();
			
			UsersDao dao=sqlSession.getMapper(UsersDao.class);
			dao.findAll().forEach(System.out::println);
			
//			System.out.println(dao.findById(1112));
			Users u=new Users();
//			u.setName("fds");
//			u.setPassword("35325");
//			System.out.println(dao.insert(u));
//			u=dao.findById(3562);
//			System.out.println(u);
//			u.setName("wangwuy");
//			u.setPassword("987543");
//			
//			System.out.println(dao.update(u));
//			u.setId(3572);
//			System.out.println(dao.delete(u));
			Map<String,Object> m=new HashMap<String,Object>();
//			m.put("column", "name");
//			m.put("keywords", "n");
//			System.out.println(dao.getCount(m));
//			m.put("name", "zhangsan");
//			m.put("password", "123456");
//			System.out.println(dao.login(m));
//			Map<String,Object> m1=new HashMap<String,Object>();
			
//			Map<String,Object> m=new HashMap<String,Object>();
//			m.put("column", "name");
//			m.put("keywords", "n");
//			m.put("start", 1);
//			m.put("end",8);
//			
//			System.out.println(dao.findPageAll(m).size());
//			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MySqlSessionFactory.closeSqlSession();
		}


	}

}
