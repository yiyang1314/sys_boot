package cn.sz.gl.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sz.gl.pojo.Emp;
import cn.sz.gl.pojo.Emplus;
import cn.sz.gl.service.EmpService;
import cn.sz.gl.service.impl.EmpServiceImpl;


public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String GET = "findAll";
	private static final String BYID = "findById";
	private static final String POST = "save";
	private static final String PUT = "updateById";
	private static final String IDS ="ids";
	private static final String DEL ="delete";
	private static final String FINDPAGE ="page";
	
	private EmpService restful=new EmpServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		System.out.println("已进入EmpServlet'doGet中.....");
		String serverpath=request.getServletPath();
		System.out.println("serverpath: "+serverpath);
		String path=serverpath.substring(serverpath.indexOf("_")+1);
		System.out.println("path: "+path);
		if (path.equals(GET)) {
			this.findAll(request, response);
		}else if(path.equals(BYID)) {
			this.findById(request, response);
		}else if (path.equals(FINDPAGE)) {
			this.findPageAll(request, response);
		}else if(path.equals(IDS)||path.equals(DEL)) {
			this.deleteEmp(request, response);
		}else if (path.equals(PUT)) {
			this.updateEmp(request, response);
		}else{
			
			//request.getSession().setAttribute(arg0, arg1);
			request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
		}
		
	}

	

	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Emp> list=new ArrayList<Emp>();
	    Emp Emp=new Emp();
		list=restful.findAll();
		System.out.println(list);
		if(list.size()==0){
			request.getSession().setAttribute("msg", "查询有误!");
			response.sendRedirect("error.jsp");
		}
		request.setAttribute("emp_list", list);
		request.setAttribute("enablePage", false);
		request.setAttribute("enableSearch", false);
		request.getRequestDispatcher("jsp/file/empluslist.jsp").forward(request, response);
	
	}
	
	
	public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer empno=Integer.parseInt(request.getParameter("empno"));
		System.out.println("empno:"+ empno);
		if(!(empno!=null&&empno.intValue()>0)){
			request.getSession().setAttribute("msg", "请求失败，请稍后重试!");
			response.sendRedirect("jsp/file/wain.jsp");
		}
		Emp emp = restful.findById(empno);
		if(emp ==null){
			request.getSession().setAttribute("msg", "用户不存在!");
			response.sendRedirect("jsp/file/wain.jsp");
		}else{
			request.setAttribute("emp", emp);
			System.out.println(emp);
			request.getRequestDispatcher("jsp/file/empinfo.jsp").forward(request, response);
		}
	}
	
	
	public void findPageAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cp=request.getParameter("currentPage");
		String ps=request.getParameter("pageSize");
		Integer pageSize=3;
		Integer currentPage=1;
		if(ps!=null){
			pageSize=Integer.parseInt(ps);
		}
		if(cp!=null){
			currentPage=Integer.parseInt(cp);
		}
		String column= request.getParameter("column");
		String keywords=request.getParameter("keywords");
		if(column==null||column.length()==0){
			column="ename";
		}
		if(keywords==null||keywords.length()==0){
			keywords="";
		}
		List<Emplus> list = restful.findEmplusPageAll(
				column,
				keywords,
				currentPage,
				pageSize);
		int count=restful.getCount(column,keywords);
		Map columns=new HashMap();
		columns.put("ename", "员工姓名");
		columns.put("job", "职位");
		request.setAttribute("columns", columns);
		request.setAttribute("keywords", keywords);
		
		request.setAttribute("enablePage", true);
		request.setAttribute("enableSearch", true);
		
		request.setAttribute("column", column);
		request.setAttribute("emp_list", list);
		
		request.setAttribute("count", count);
		request.setAttribute("pageCount", (count-1)/pageSize+1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.getRequestDispatcher("jsp/file/emplist.jsp")
					.forward(request, response);
	}
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("已进入EmpServlet'doPost中.....");
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		String path=request.getParameter("path");
		System.out.println("POST	       : "+path);
		Emp emp=new Emp();
		String e_empno		=request.getParameter("empno");
		String e_ename		=request.getParameter("ename");
		if(e_ename!=null&&e_ename.length()>0){
			emp.setEname(e_ename);
		}
		
		String e_job		=request.getParameter("job");
		if(e_job!=null&&e_job.length()>0){
			emp.setJob(e_job);
		}
		String e_mgr		=request.getParameter("mgr");
		if(e_mgr!=null&&e_mgr.length()>0){
			emp.setMgr(Integer.valueOf(e_mgr));
		}
		String e_hiredate	=request.getParameter("hiredate");
		SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd");
		if(e_hiredate!=null&&e_hiredate.length()>0){
			try {
				emp.setHiredate(sft.parse(e_hiredate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		String e_sal		=request.getParameter("sal");
		if(e_sal!=null&&e_sal.length()>0){
				emp.setSal(Double.valueOf(e_sal));
		}
		String e_comm		=request.getParameter("comm");
		if(e_comm!=null&&e_comm.length()>0){
			emp.setComm(Double.valueOf(e_comm));
		}
		String e_deptno		=request.getParameter("deptno");
		if(e_deptno!=null&&e_deptno.length()>0){
			emp.setDeptno(Integer.valueOf(e_deptno));
		}
		
		
		Map map=new HashMap();
		if(e_empno!=null&&e_empno.length()>0){
			Integer empno=Integer.valueOf(e_empno);
			emp.setEmpno(empno);
			map=restful.update(emp);
		}else{
			map=restful.save(emp);
		}
		
		request.setAttribute("resultmap", map);
		request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);

		
	}


	public void updateEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Emp emp=null;
		String id=request.getParameter("empno");
		System.out.println("已进入EmpServlet'doPut中.....");
		Integer empno=null;
		if(id!=null&&id.length()>0){
			empno=Integer.parseInt(id);
		}
		if(!(empno!=null&&empno.intValue()>0)){
			request.setAttribute("msg", "编号不能为空！");
			request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
		}
		emp =restful.findById(empno);
		if(emp ==null){
			request.setAttribute("msg", "用户不存在!");
			request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
		}else{
			request.setAttribute("emp", emp );
			request.getRequestDispatcher("jsp/file/empupdate.jsp").forward(request, response);
		}
		
	}


	public void deleteEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("已进入EmpServlet'doDelete中.....");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String serverpath=request.getServletPath();
		System.out.println("serverpath: "+serverpath);
		String path=serverpath.substring(serverpath.indexOf("_")+1);
		System.out.println("path: "+path);
		Map map=new HashMap();
		if (path.equals(IDS)) {
			String ids[]=request.getParameter("ids").split(",");
			map=restful.deleteIds(ids);
		}else {
			String id=request.getParameter("empno");
			map=restful.delete(id);
		}
		request.setAttribute("resultmap", map);
		request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
	}
}
