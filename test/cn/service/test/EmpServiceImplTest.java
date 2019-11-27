package cn.service.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cn.sz.gl.pojo.Emp;
import cn.sz.gl.service.EmpService;
import cn.sz.gl.service.impl.EmpServiceImpl;

public class EmpServiceImplTest {

	private EmpService service=null;
	
	@Before
	public void init() {
		service=new EmpServiceImpl();
	}		
	
	@Test
	public void testFindAll() {
		
		service.findAll().forEach(System.out::println);
	}

	@Test
	public void testFindById() {
		System.out.println(service.findById(1011));
	}

	@Test
	public void testFindPageAll() {
		System.out.println(service.findPageAll("name", "a", 2, 3).size());

	}

	@Test
	public void testSave() {
		Emp u=new Emp();
		u.setDeptno(10);
		u.setJob("java");
		u.setSal(3555d);
		u.setMgr(1011);
		System.out.println(service.save(u));
	}

	@Test
	public void testUpdate() {
		Emp u = service.findById(3562);
		System.out.println(u);
		System.out.println(service.update(u));
	}

	@Test
	public void testDelete() {
		Emp u=new Emp();
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



}
