package cn.sz.gl.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sz.gl.dao.IEmpDAO;
import cn.sz.gl.dao.impl.EmpDAOImpl;
import cn.sz.gl.pojo.Emp;
import cn.sz.gl.pojo.Emplus;
import cn.sz.gl.service.EmpService;

public class EmpServiceImpl implements EmpService {
	private IEmpDAO dao = new EmpDAOImpl();
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<Emp> findAll(){
		
		List<Emp> list=new ArrayList<Emp>();;
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
	 * @param Empno
	 * @return
	 */
	public Emp findById(Integer Empid){
		
		Emp emp=null;
		try {
			emp=dao.findById(Empid);
		} catch (Exception e) {
			System.out.println("findById'查询列表失败！");
		}
		return emp;
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
	public List<Emp> findPageAll(String column,String keywords,Integer currentPage,Integer pageSize){
		
		try {

			List<Emp> list =dao.findPageAll(
					column, 
					keywords, 
					currentPage, 
					pageSize);

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

	public Map save(Emp emp){
		Map map=new HashMap();
		try {
			if (dao.insert(emp)) {
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
	public Map update(Emp Emp){
		Map map=new HashMap();
		try {
			if(dao.update(Emp)){
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
	 * @param Empno
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
			Emp vo=new Emp();
			vo.setEmpno(Integer.valueOf(id));
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
			
			List<Integer> list=new ArrayList<Integer>();
			for(String id:ids){
				list.add(Integer.valueOf(id));
			}
			n=dao.delBatchIds(list);
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
			String col="title";
			String key="";
			if(column!=null){
				col=column;
			}
			if(keywords!=null){
				key=keywords;
			}
			
			return dao.getCount(column, key);
		} catch (Exception e) {
				System.out.println("deleteIds'删除列表失败！");
		}
		return 0;
	}

	@Override
	public List<Emplus> findEmplusPageAll(String column, String keywords, int currentPage, int pageSize) {

		try {

			List<Emplus> list =dao.findEmplusPageAll(
					column, 
					keywords, 
					currentPage, 
					pageSize);

			return list;
		} catch (Exception e) {
			System.out.println("findPageAll'查询分页列表失败！");
		}
		return Collections.EMPTY_LIST;
	}
}
