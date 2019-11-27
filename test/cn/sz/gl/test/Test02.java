package cn.sz.gl.test;

import java.util.List;

import cn.sz.gl.dao.IEmpDAO;
import cn.sz.gl.dao.impl.EmpDAOImpl;
import cn.sz.gl.pojo.Emp;

public class Test02 {

	public static void main(String[] args) {
		IEmpDAO empdao = new EmpDAOImpl();
		List<Emp> emplist = empdao.findAll();
		for (int i = 0; i < emplist.size(); i++) {
			Emp emp = emplist.get(i);
			System.out.println(emp.getEmpno()+"----"+emp.getEname()+"----"+emp.getSal());
		}
		
		/*Emp vo = new Emp();
		vo.setEname("lisi");
		vo.setMgr(7369);
		
		boolean flag = empdao.doCreate(vo);
		System.out.println("���Ӳ������flag:"+flag);*/
		
		/*//�޸�
		Emp vo = new Emp();
		vo.setEmpno(1002);
		vo.setEname("zhangsan");
		
		boolean flag = empdao.doUpdate(vo);
		System.out.println("�޸Ĳ��������"+flag);*/
		
		/*String column = "ename";
		String kw = "";
		int cp = 1;
		int ps = 5;
		List<Emp> emplist = empdao.findSplit(column, kw, cp, ps);
		for (int i = 0; i < emplist.size(); i++) {
			Emp emp = emplist.get(i);
			System.out.println(emp.getEmpno()+"----"+emp.getEname()+"----"+emp.getSal());
		}*/
		System.out.println("����������������������"+empdao.getCount("ename", ""));
	}
}
