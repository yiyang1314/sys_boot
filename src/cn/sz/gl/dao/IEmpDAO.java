package cn.sz.gl.dao;

import java.util.List;

import cn.sz.gl.pojo.Emp;
import cn.sz.gl.pojo.Emplus;

public interface IEmpDAO {

	public List<Emp> findAll();
	
	public Emp findById(Integer empno);
	
	public boolean insert(Emp vo);
	
	public boolean update(Emp vo);
	
	public boolean delete(Emp vo);
	
	public List<Emp> findPageAll(String column,String kw,int cp,int ps);
	
	public int getCount(String column,String kw);
	
	public List<Emplus> findEmplusPageAll(String column,String kw,int cp,int ps);

	public String findByMgrName(Integer mgr);
	
	
	public int delBatchIds(List<Integer> list);
}
