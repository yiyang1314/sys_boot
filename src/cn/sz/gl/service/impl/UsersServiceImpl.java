package cn.sz.gl.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.sz.gl.dao.UsersDao;

import cn.sz.gl.dbc.MySqlSessionFactory;
import cn.sz.gl.pojo.Users;
import cn.sz.gl.service.UsersService;

public class UsersServiceImpl implements UsersService {
	private static SqlSession sqlsession
				=MySqlSessionFactory.getMySqlSession();
	private UsersDao dao = null;
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<Users> findAll(){
		try {
			dao=sqlsession.getMapper(UsersDao.class);
			return dao.findAll();
		} catch (Exception e) {
			System.out.println("findAll'查询列表失败！");
			
		}
		return Collections.EMPTY_LIST;
	}
	
	/**
	 * 根据主键查对象
	 * @param Usersno
	 * @return
	 */
	public Users findById(Integer usersid){
		
	
		try {
			dao=sqlsession.getMapper(UsersDao.class);
			return dao.findById(usersid);
		} catch (Exception e) {
			System.out.println("findById'查询列表失败！");
		}
		return null;
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
	public List<Users> findPageAll(String column,String keywords,Integer currentPage,Integer pageSize){
		
		try {
			dao=sqlsession.getMapper(UsersDao.class);
			String col="name";
			String key="n";
			if(column!=null){
				col=column;
			}
			if(keywords!=null){
				key=keywords;
			}
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("column", col);
			m.put("keywords",key);
			m.put("start", (currentPage-1)*pageSize);
			m.put("end",currentPage*pageSize);
			List<Users> list =dao.findPageAll(m);

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

	public Map save(Users Users){
		Map map=new HashMap();
		try {
			dao=sqlsession.getMapper(UsersDao.class);
			if (dao.insert(Users)) {
				map.put("status", 200);
				map.put("msg", "添加成功!");
				sqlsession.commit();
				return map;
			}
		}catch (Exception e) {
			sqlsession.rollback();
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
	public Map update(Users Users){
		Map map=new HashMap();
		try {
			dao=sqlsession.getMapper(UsersDao.class);
			if(dao.update(Users)){
				map.put("status", 200);
				map.put("msg", "修改成功!");
				sqlsession.commit();
				return map;
			}
		
		}catch (Exception e) {
			sqlsession.rollback();
			System.out.println("update'更新列表失败！");
		}
		map.put("status", 404);
		map.put("msg", "修改失败!");
		return map;
	}
	
	/**
	 * 删除
	 * @param Usersno
	 * @return
	 */

	public Map delete(String id){
		Map map=new HashMap();
		try {
			dao=sqlsession.getMapper(UsersDao.class);
			if(id==null){
				map.put("status", 302);
				map.put("msg", "用户不存在！");
				return map;
			}
			Users vo=new Users();
			if(id!=null&&id.length()>0){
				vo.setId(Integer.valueOf(id));
			}
			
			if(dao.delete(vo)){
				map.put("status", 200);
				map.put("msg", "删除成功！");
				sqlsession.commit();
				return map;
			}
		} catch (Exception e) {
			sqlsession.rollback();
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
			dao=sqlsession.getMapper(UsersDao.class);
			Users vo=new Users();
			List<Integer> list=new ArrayList<Integer>();
			for(String id:ids){
				if(id!=null&&id.length()>0){
					list.add(Integer.valueOf(id));
				}
			}
			n=dao.delBatchIds(list);
			if(n>0){
				map.put("status", 200);
				map.put("msg", "已批量删除"+n+ "条记录！");
				sqlsession.commit();
				return map;
			}
		} catch (Exception e) {
			sqlsession.rollback();
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
			dao=sqlsession.getMapper(UsersDao.class);
			if(column==null||column==""){
				column="name";
			}
			Map<String,String> m=new HashMap<String,String>();
			m.put("column", column);
			m.put("keywords", keywords);
			return dao.getCount(m);
		} catch (Exception e) {
				System.out.println("deleteIds'删除列表失败！");
		}
		return 0;
	}

	@Override
	public Users login(String username, String password) {
		try {
			dao=sqlsession.getMapper(UsersDao.class);
			Map<String, String> m=new HashMap();
			m.put("name", username);
			m.put("password",password);
			return dao.login(m);
		} catch (Exception e) {
			
		}finally{
			
		}
		return null;
	}

	
}
