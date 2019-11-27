package cn.sz.gl.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sz.gl.pojo.Emp;
import cn.sz.gl.pojo.Emplus;




public interface EmpService {

	
	/**
	 * 查询全部
	 * @return
	 */

	public List<Emp> findAll();
	
	/**
	 * 根据主键查对象
	 * @param Empno
	 * @return
	 */
	public Emp findById(Integer Empid);
	
	/**
	 * 模糊查询+分页
	 * @param column : 根据哪一列来模糊查询
	 * @param kw : 查询的关键字
	 * @param cp : 起始的行数
	 * @param ps : 每页最多显示多少行数据
	 * @return
	 */
	public List<Emp> findPageAll(String column,String keywords,Integer currentPage,Integer pageSize);
	
	
	/**
	 * 增加
	 * @param vo
	 * @return
	 */

	public Map save(Emp entity);
	
	/**
	 * 修改
	 * @param vo : 对象里面，至少，必须有主键在
	 * @return
	 */
	public Map update(Emp entity);
	
	/**
	 * 删除
	 * @param Empno
	 * @return
	 */

	public Map delete(String id);

	/**deleteIds
	 * 根据ids 批量删除
	 * @param request
	 * @param response
	 */
	public Map deleteIds(String[] ids);
	
	/**
	 * 用来统计满足条件的数据总共有多少行
	 * @param column
	 * @param kw
	 * @return
	 */
	public int getCount(String column,String keywords);
	/**
	 * 模糊查询+分页
	 * @param column : 根据哪一列来模糊查询
	 * @param kw : 查询的关键字
	 * @param cp : 起始的行数
	 * @param ps : 每页最多显示多少行数据
	 * @return
	 */
	public List<Emplus> findEmplusPageAll(String column, String keywords, int currentPage, int pageSize);
}
