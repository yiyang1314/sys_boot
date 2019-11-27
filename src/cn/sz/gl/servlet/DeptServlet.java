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

import cn.sz.gl.pojo.Dept;
import cn.sz.gl.pojo.DeptPlus;
import cn.sz.gl.pojo.Emplus;
import cn.sz.gl.service.DeptService;
import cn.sz.gl.service.impl.DeptServiceImpl;



public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String GET = "findAll";
	private static final String BYID = "findById";
	private static final String POST = "save";
	private static final String PUT = "updateById";
	private static final String IDS ="ids";
	private static final String DEL ="delete";
	private static final String FINDPAGE ="page";
	private static final String PLUS="findByIdPlus";
	private DeptService restful=new DeptServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		System.out.println("已进入DeptServlet'doGet中.....");
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
			this.deleteDept(request, response);
		}else if (path.equals(PUT)) {
			this.updateDept(request, response);
		}else if (path.equals(PLUS)) {
			this.findByIdlist(request, response);
		}else{
			
			//request.getSession().setAttribute(arg0, arg1);
			request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
		}
		
	}

	

	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Dept> list=new ArrayList<Dept>();
		list=restful.findAll();
		System.out.println(list);
		if(list.size()==0){
			request.getSession().setAttribute("msg", "查询有误!");
			response.sendRedirect("error.jsp");
		}
		request.setAttribute("dept_list", list);
		request.setAttribute("enablePage", false);
		request.setAttribute("enableSearch", false);
		request.getRequestDispatcher("jsp/file/deptpluslist.jsp").forward(request, response);
	
	}
	
	
	public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer deptno=Integer.parseInt(request.getParameter("deptno"));
		System.out.println("deptno:"+ deptno);
		if(!(deptno!=null&&deptno.intValue()>0)){
			request.getSession().setAttribute("msg", "请求失败，请稍后重试!");
			response.sendRedirect("jsp/file/wain.jsp");
		}
		Dept dept = restful.findById(deptno);
		if(dept ==null){
			request.getSession().setAttribute("msg", "用户不存在!");
			response.sendRedirect("jsp/file/wain.jsp");
		}else{
			request.setAttribute("dept", dept);
			System.out.println(dept);
			request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
		}
	}
	public void findByIdlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("deptno");

		Integer deptno=1;
		if(id!=null&&id.length()>0){
			deptno=Integer.valueOf(id);
		}
		DeptPlus deptplus = restful.findByIdlist(deptno);
		if(deptplus==null){
			request.getSession().setAttribute("msg", "用户不存在!");
			response.sendRedirect("jsp/file/wain.jsp");
		}else{
			request.setAttribute("deptplus", deptplus);
			System.out.println(deptplus);
			request.getRequestDispatcher("jsp/file/deptinfo.jsp")
			.forward(request, response);
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
			column="dname";
		}
		if(keywords==null||keywords.length()==0){
			keywords="";
		}
		List<Dept> list = restful.findPageAll(
				column,
				keywords,
				currentPage,
				pageSize);
		int count=restful.getCount(column,keywords);
		Map columns=new HashMap();
		columns.put("dname", "部门");
		request.setAttribute("columns", columns);
		request.setAttribute("keywords", keywords);
		
		request.setAttribute("enablePage", true);
		request.setAttribute("enableSearch", true);
		
		request.setAttribute("column", column);
		request.setAttribute("dept_list", list);
		
		request.setAttribute("count", count);
		request.setAttribute("pageCount", (count-1)/pageSize+1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.getRequestDispatcher("jsp/file/deptpluslist.jsp")
					.forward(request, response);
	}
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		Dept dept=new Dept();
		String 		id=request.getParameter("deptno");
		String dname		=request.getParameter("dname");
		String loc		=request.getParameter("loc");
		if(dname!=null&&dname.length()>0){
			dept.setDname(dname);
		}

		if(loc!=null&&loc.length()>0){
			dept.setLoc(loc);
		}
		Map map=new HashMap();
		if(id!=null&&id.length()>0){
			Integer deptno=Integer.valueOf(id);
			dept.setDeptno(deptno);
			map=restful.update(dept);
		}else{
			map=restful.save(dept);
		}
		request.setAttribute("resultmap", map);
		request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);

	}


	public void updateDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String 		id=request.getParameter("deptno");
		Integer deptno=0;
		if(id!=null&&id.length()>0){
			deptno=Integer.valueOf(id);
		}
		Dept dept=restful.findById(deptno);
		if(dept ==null){
			request.setAttribute("msg", "用户不存在!");
			request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
		}else{
			request.setAttribute("dept", dept );
			request.getRequestDispatcher("jsp/file/deptupdate.jsp").forward(request, response);
		}
		
	}


	public void deleteDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("已进入DeptServlet'doDelete中.....");
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
			String id=request.getParameter("Deptno");
			map=restful.delete(id);
		}
		request.setAttribute("resultmap", map);
		request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
	}
}
