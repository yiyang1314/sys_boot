<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()+"/";
%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<html>
	<head>
		<base href="<%=basepath%>" />
		<style>
			body{
				margin:0px;
				padding:0px;
				background-color:blue;
			}
			fieldset{
				width:550px;
				margin-top:120px;
				margin-left:150px;
				margin-bottom:50px;
				background-color:green;
				padding-right: 180px;
				padding-left: 80px;
				padding-bottom: 80px;
			}
			table{
				border-collapse: collapse;
				width:450px;
			}
			th{width:150px;text-align: left;}

		</style>
	</head>
	<body>
		<fieldset>
			<legend>员工管理系统</legend>
			<h1>部门${dept.deptno==null?'新增':'修改' }服务</h1>
			<p style="text-align:right;">----部门服务，----一路顺风</p>
			<form action="dept_save" method="post" style="width:550px;" name="myform" id="myform">
			<table border="1">
				<tr>
					<td colspan="2"><input name="deptno" type="hidden" value="${dept.deptno}" /></td>
				</tr>	
				<tr>	
					<th>部门名称</th>
					<td><input name="dname" type="text" value="${dept.dname}" /></td>
				</tr>	
				<tr>
					<th>地址</th>
					<td><input name="loc" type="text" value="${dept.loc}" /></td>
				</tr>	
				
				<tr align="center">
					
					<td colspan="2">
					<input name="btn" type="submit" value="${dept.deptno==null?'新增':'修改' }" />
					<input name="btn5" type="reset" value="重置" />			
					</td>

				</tr>
			</table>
				</form>	
				<a href="dept_page">返回部门列表</a>
				<a href="emp_page">返回员工列表</a>
				<a href="jsp/file/wain.jsp">返回</a>
		</fieldset>

	
	</body>
</html>