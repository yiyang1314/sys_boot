package cn.sz.gl.dao;

import java.util.List;
import java.util.Map;

import cn.sz.gl.pojo.Dept;
import cn.sz.gl.pojo.DeptPlus;
public interface DeptDao {

	/**
	 * 查询全部
	 * @return
	 */
	public List<Dept> findAll();
	
	/**
	 * 根据主键查对象
	 * @param Dept
	 * @return
	 */
	public Dept findById(Integer id);
	
	/**
	 * 模糊查询+分页
	 * @param column : 根据哪一列来模糊查询
	 * @param kw : 查询的关键字
	 * @param cp : 起始的行数
	 * @param ps : 每页最多显示多少行数据
	 * @return
	 */
	public List<Dept> findPageAll(Map<String,Object> m);
	
	/**
	 * 用来统计满足条件的数据总共有多少行
	 * @param column
	 * @param kw
	 * @return
	 */
	public int getCount(Map<String,String> mm);
	
	/**
	 * 增加
	 * @param vo
	 * @return
	 */
	public boolean insert(Dept dept);
	
	/**
	 * 修改
	 * @param h : 对象里面，至少，必须有主键在
	 * @return
	 */
	public boolean update(Dept dept);
	
	/**
	 * 删除
	 * @param Dept
	 * @return
	 */
	public boolean delete(Dept dept);
	/**
	 * 根据部门编号查看部门所有员工
	 * @param deptno Integer
	 * @return DeptPlus 部门和员工的组合
	 */
	public DeptPlus findByIdlist(Integer deptno);
}
