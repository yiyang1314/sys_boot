package cn.sz.gl.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.sz.gl.dao.DeptDao;

import cn.sz.gl.dbc.MySqlSessionFactory;
import cn.sz.gl.pojo.Dept;
import cn.sz.gl.pojo.DeptPlus;
import cn.sz.gl.service.DeptService;



public class DeptServiceImpl implements DeptService {
	private static  SqlSession sqlsession
			=MySqlSessionFactory.getMySqlSession();
	private DeptDao dao = sqlsession.getMapper(DeptDao.class);
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<Dept> findAll(){
		
		List<Dept> list=null;
		try {
			 list =dao.findAll();
		} catch (Exception e) {
			System.out.println("findAll'查询列表失败！");
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
	
	/**
	 * 根据主键查对象
	 * @param Deptno
	 * @return
	 */
	public Dept findById(Integer Deptid){
		
		Dept Dept=null;
		try {
			Dept=dao.findById(Deptid);
		} catch (Exception e) {
			System.out.println("findById'查询列表失败！");
		}
		return Dept;
	}
	
	/**
	 * 模糊查询+分页
	 * @param column : 根据哪一列来模糊查询
	 * @param kw : 查询的关键字
	 * @param cp : 起始的行数
	 * @param ps : 每页最多显示多少行数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Dept> findPageAll(String column,String keywords,Integer currentPage,Integer pageSize){
		
		try {
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("column", column);
			m.put("keywords",keywords);
			m.put("start", (currentPage-1)*pageSize);
			m.put("end",currentPage*pageSize);
			List<Dept> list =dao.findPageAll(m);
			return list;
		} catch (Exception e) {
			System.out.println("findPageAll'查询分页列表失败！");
		}
		return Collections.EMPTY_LIST;
	}
	
	
	/**
	 * 增加
	 * @param vo
	 * @return
	 */

	public Map save(Dept dept){
		Map map=new HashMap();
		try {
			if (dao.insert(dept)) {
				map.put("status", 200);
				map.put("msg", "添加成功!");
				return map;
			}
		}catch (Exception e) {
			System.out.println("save'注册失败！");
		}
		
		map.put("status", 404);
		map.put("msg", "添加失败!");
		return map;
	}
	
	/**
	 * 修改
	 * @param vo : 对象里面，至少，必须有主键在
	 * @return
	 */
	public Map update(Dept Dept){
		Map map=new HashMap();
		try {
			if(dao.update(Dept)){
				map.put("status", 200);
				map.put("msg", "修改成功!");
				return map;
			}
		
		}catch (Exception e) {
			System.out.println("update'更新列表失败！");
		}
		map.put("status", 404);
		map.put("msg", "修改失败!");
		return map;
	}
	
	/**
	 * 删除
	 * @param Deptno
	 * @return
	 */

	public Map delete(String id){
		Map map=new HashMap();
		try {
			if(id==null&id.length()>0){
				map.put("status", 302);
				map.put("msg", "房源不存在！");
				return map;
			}
			Dept vo=new Dept();
			vo.setDeptno(Integer.valueOf(id));
			if(dao.delete(vo)){
				map.put("status", 200);
				map.put("msg", "删除成功！");
				return map;
			}
		} catch (Exception e) {
			System.out.println("delete'删除失败！");
		}
		map.put("status", 404);
		map.put("msg", "删除失败！");
		return map;

		
	}

	/**deleteIds
	 * 根据ids 批量删除
	 * @param request
	 * @param response
	 */
	public Map deleteIds(String[] ids){
		Map map=new HashMap();
		int n=0;
		try {
			
			for(String id:ids){
				System.out.println(id);
				//dao.del(Integer.parseInteger(id));
				n++;
			}
			if(n>0){
				map.put("status", 200);
				map.put("msg", "已批量删除"+n+ "条记录！");
				return map;
			}
		} catch (Exception e) {
			System.out.println("deleteIds'删除列表失败！");
		}
		map.put("status", 404);
		map.put("msg", "批量删除失败！");
		return map;
	}
	
	/**
	 * 用来统计满足条件的数据总共有多少行
	 * @param column
	 * @param kw
	 * @return
	 */
	public int getCount(String column,String keywords){
		try{
			String col="dname";
			String key="n";
			if(column!=null){
				col=column;
			}
			if(keywords!=null){
				key=keywords;
			}
			Map<String,String> m=new HashMap<String,String>();
			m.put("column", column);
			m.put("keywords", key);
			return dao.getCount(m);
		} catch (Exception e) {
				System.out.println("deleteIds'删除列表失败！");
		}
		return 0;
	}

	@Override
	public DeptPlus findByIdlist(Integer deptno) {
		try{
			return dao.findByIdlist(deptno);
		} catch (Exception e) {
				System.out.println("deleteIds'删除列表失败！");
		}
		return null;
	}
}
