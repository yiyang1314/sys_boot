package cn.sz.gl.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.sz.gl.dao.IEmpDAO;
import cn.sz.gl.dbc.MySqlSessionFactory;
import cn.sz.gl.pojo.Emp;
import cn.sz.gl.pojo.Emplus;

public class EmpDAOImpl implements IEmpDAO {

	@Override
	public List<Emp> findAll() {
		SqlSession sqlSession = null;
		try {
			sqlSession = MySqlSessionFactory.getMySqlSession();
			return sqlSession.selectList("cn.sz.gl.pojo.Emp.findAll");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MySqlSessionFactory.closeSqlSession();
		}
		return null;
	}

	@Override
	public Emp findById(Integer empno) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MySqlSessionFactory.getMySqlSession();
			return sqlSession.selectOne("cn.sz.gl.pojo.Emp.findById", empno);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MySqlSessionFactory.closeSqlSession();
		}
		return null;
	}

	@Override
	public boolean insert(Emp vo) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MySqlSessionFactory.getMySqlSession();
			int res = sqlSession.insert("cn.sz.gl.pojo.Emp.insert", vo);
			sqlSession.commit();
			return res>0;
			
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			MySqlSessionFactory.closeSqlSession();
		}
		return false;
	}

	@Override
	public boolean update(Emp vo) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MySqlSessionFactory.getMySqlSession();
			int res = sqlSession.update("cn.sz.gl.pojo.Emp.update", vo);
			sqlSession.commit();
			return res>0;
			
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			MySqlSessionFactory.closeSqlSession();
		}
		return false;
	}

	@Override
	public boolean delete(Emp vo) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MySqlSessionFactory.getMySqlSession();
			int res = sqlSession.update("cn.sz.gl.pojo.Emp.delete", vo);
			sqlSession.commit();
			return res>0;
			
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			MySqlSessionFactory.closeSqlSession();
		}
		return false;
	}

	@Override
	public List<Emp> findPageAll(String column, String kw, int cp, int ps) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MySqlSessionFactory.getMySqlSession();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("column", column);
			map.put("kw", kw);
			map.put("end", cp*ps);
			map.put("start", (cp-1)*ps);
			return sqlSession.selectList("cn.sz.gl.pojo.Emp.findPageAll", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MySqlSessionFactory.closeSqlSession();
		}
		return null;
	}

	@Override
	public int getCount(String column, String kw) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MySqlSessionFactory.getMySqlSession();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("column", column);
			map.put("kw", kw);
			return sqlSession.selectOne("cn.sz.gl.pojo.Emp.getCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MySqlSessionFactory.closeSqlSession();
		}
		return 0;
	}

	@Override
	public List<Emplus> findEmplusPageAll(String column, String kw, int cp, int ps) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MySqlSessionFactory.getMySqlSession();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("column", column);
			map.put("kw", kw);
			map.put("end", cp*ps);
			map.put("start", (cp-1)*ps);
			return sqlSession.selectList("cn.sz.gl.pojo.Emp.findEmplusPageAll", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MySqlSessionFactory.closeSqlSession();
		}
		return null;
	}

	@Override
	public String findByMgrName(Integer mgr) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MySqlSessionFactory.getMySqlSession();

			return sqlSession.selectOne("cn.sz.gl.pojo.Emp.findByMgrName", mgr);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MySqlSessionFactory.closeSqlSession();
		}
		return null;
	}

	@Override
	public int delBatchIds(List<Integer> list) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MySqlSessionFactory.getMySqlSession();
			int c=sqlSession.delete("cn.sz.gl.pojo.Emp.delBatchIds", list);
			sqlSession.commit();
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MySqlSessionFactory.closeSqlSession();
		}
		return 0;
	}

	
}
