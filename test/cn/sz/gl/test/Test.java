package cn.sz.gl.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.sz.gl.pojo.Emp;

public class Test {

	public static void main(String[] args) {
		
		String resouce = "/mybatis_config.xml";
		
		InputStream is = Test.class.getResourceAsStream(resouce);
		SqlSessionFactoryBuilder builder = null;
		SqlSessionFactory factory = null;
		SqlSession sqlSession = null;
		
		//�����Ŀ�ģ���Ϊ�˴�����������
		builder = new SqlSessionFactoryBuilder();
		//�������ӹ���
		factory = builder.build(is);
		//���ݹ������������ݿ�����
		sqlSession = factory.openSession();
		//Connection conn = sqlSession.getConnection();
		
		
		
		List<Emp> emplist = sqlSession.selectList("cn.sz.gl.pojo.Emp.findAll");
		//���������ɾ���Ĳ�������ʱ��Ҫ�ύ���߻ع�����
		//sqlSession.commit();
		//sqlSession.rollback();
		//����
		for(int i=0;i<emplist.size();i++) {
			Emp emp = emplist.get(i);
			System.out.println(emp.getEmpno()+"---"+emp.getEname()+"---"+emp.getHiredate()+"-----"+emp.getSal());
		}
		
		
		sqlSession.close();
		
		
	}
}
