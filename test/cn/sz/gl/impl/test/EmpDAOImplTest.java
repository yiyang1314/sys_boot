package cn.sz.gl.impl.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import cn.sz.gl.dao.IEmpDAO;
import cn.sz.gl.dao.impl.EmpDAOImpl;
import cn.sz.gl.pojo.Emp;
import cn.sz.gl.pojo.Emplus;


public class EmpDAOImplTest{
	
	private IEmpDAO dao=null;
	
	@Before
	public void init() {
		dao=new EmpDAOImpl();
	}	
	@Test
	public void testFindAll() {
		
		dao.findAll().forEach(System.out::println);
	}

	@Test
	public void testFindById() {
		System.out.println(dao.findById(1019));
	}

	@Test
	public void testFindPageAll() {
		System.out.println(dao.findPageAll("ename", "n", 1, 7).size());

	}

	@Test
	public void testSave() {
		Emp u=new Emp();
		u.setComm(333d);
		u.setDeptno(10);
		u.setEname("annan");
		u.setJob("python");
		u.setMgr(1019);
		System.out.println(dao.insert(u));
	}

	@Test
	public void testUpdate() {
		Emp u = dao.findById(1027);
		u.setEname("wuyifan");
		System.out.println(u);
		System.out.println(dao.update(u));
	}

	@Test
	public void testDelete() {
	

		Emp u = dao.findById(1027);
		u.setEname("wuyifan");
		System.out.println(dao.delete(u));
	}

	@Test
	public void testDeleteIds() {
		String []ids={"2323","3582","1582","23","293","23","23"};
		//System.out.println(dao.deleteIds(ids));
	}

	@Test
	public void testGetCount() {
		System.out.println(dao.getCount("ename", "n"));
	}

	@Test
	public void testfindEmplusPageAll(){
		dao.findEmplusPageAll("job", "py", 1, 17).forEach(System.out::println);
	}
	@Test
	public void testfindByMgrName(){
		System.out.println(dao.findByMgrName(1019));
	}
	
	@Test
	public void testdelBatchIds(){
		Integer []ids={1085,1091,1089};
		System.out.println(dao.delBatchIds(Arrays.asList(ids)));
	}

}
