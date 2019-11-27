package cn.service.test;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import cn.sz.gl.dao.DeptDao;
import cn.sz.gl.dbc.MySqlSessionFactory;
import cn.sz.gl.pojo.DeptPlus;
import cn.sz.gl.service.DeptService;

import cn.sz.gl.service.impl.DeptServiceImpl;


public class DeptServiceImplTest extends DeptServiceImpl {

	private DeptService service=null;
	
	@Before
	public void init() {
		service=new DeptServiceImpl();
	}		
	
	@Test
	public void testFindAll() {
		service.findAll().forEach(System.out::println);
	}

	@Test
	public void testFindById() {
		System.out.println(service.findById(2222));
	}

	@Test
	public void testFindPageAll() {
		System.out.println(service.findPageAll("dname", "", 1, 3).size());

	}

	@Test
	public void testSave() {
		DeptPlus u=new DeptPlus();
//		System.out.println(service.save(u));
	}

	@Test
	public void testUpdate() {
//		DeptPlus u = service.findById(3562);
//		System.out.println(u);
//		u.setName("wanbgggy");
//		System.out.println(service.update(u));
	}

	@Test
	public void testDelete() {
		DeptPlus u=new DeptPlus();
		System.out.println(service.delete("3562"));
	}

	@Test
	public void testDeleteIds() {
		String []ids={"2323","3582","c582","23","293","23","23"};
		System.out.println(service.deleteIds(ids));
	}

	@Test
	public void testGetCount() {
		System.out.println(service.getCount("name", "a"));
	}




	@Test
	public void testFindByIdlist() {
		
		SqlSession sqlsession
				=MySqlSessionFactory.getMySqlSession();
		
		DeptDao dao = sqlsession.getMapper(DeptDao.class);
		
		System.out.println(dao.findByIdlist(10));
		MySqlSessionFactory.closeSqlSession();
	}

}
