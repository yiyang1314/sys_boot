package cn.service.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.sz.gl.pojo.Users;
import cn.sz.gl.service.UsersService;
import cn.sz.gl.service.impl.UsersServiceImpl;

public class UsersServiceImplTest {

	private UsersService service=null;
	
	@Before
	public void init() {
		service=new UsersServiceImpl();
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
		System.out.println(service.findPageAll("name", "a", 2, 3).size());

	}

	@Test
	public void testSave() {
		Users u=new Users();
		u.setName("fastjson");
		u.setPassword("35325");
		System.out.println(service.save(u));
	}

	@Test
	public void testUpdate() {
		Users u = service.findById(3562);
		System.out.println(u);
		u.setName("wanbgggy");
		System.out.println(service.update(u));
	}

	@Test
	public void testDelete() {
		Users u=new Users();
		u.setId(3562);
		u.setName("fastjson");
		u.setPassword("35325");
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
	public void testLogin() {
		System.out.println(service.login("zhangsan", "123456"));
	}

}
