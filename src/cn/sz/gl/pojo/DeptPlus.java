package cn.sz.gl.pojo;

import java.io.Serializable;
import java.util.List;

public class DeptPlus implements Serializable {
	private Integer deptno;
	private String  dname;
	private String  loc;
	private List<Emp> emplist;
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public List<Emp> getEmplist() {
		return emplist;
	}
	public void setEmplist(List<Emp> emplist) {
		this.emplist = emplist;
	}
	@Override
	public String toString() {
		return "DeptPlus [deptno=" + deptno + ", dname=" + dname +", emplistsize=" + getEmplist().size()+ "]";
	}
	
	
}
