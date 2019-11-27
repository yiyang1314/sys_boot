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
		<script type="text/javascript">
			function update(id){
				if(confirm("您确定要修改"+id+"吗？")){
					location.href="emp_updateById?empno="+id;
				}
				   
			}
		</script>
				<style>
			body{
				margin:0px;
				padding:0px;
				background-color:green;
			}
			fieldset{
				width:650px;
				margin-top:120px;
				margin-left:150px;
				margin-bottom:50px;
				background-color:white;
				padding-right: 180px;
				padding-left: 80px;
				padding-bottom: 80px;
			}
			.green{
				background-color:white;
			}
			.red{
				background-color:white;
			}
			.red{
				background-color:white;
			}
		</style>
	</head>
	<body>
		<fieldset>
			<legend>顺风车搬家预约系统</legend>
			<h1>顺风车搬家预约系统----movebookinginfo.jsp</h1>
			<p style="text-align:right;">----顺丰搬家，----一路顺风</p>
			
			<table border="2">
				<tr>
					<th>员工编号</th>
					<td>${emp.empno}</td>
				</tr>
				<tr>
					<th>姓名</th>
					<td>${emp.ename}</td>
				</tr>
				<tr>
					<th>职位</th>
					<td>${emp.job}</td>
				</tr>
				<tr>
					<th>上级</th>
					<td>${emp.mgr}</td>
				</tr>
				<tr>
					<th>入职日期</th>
					<td>${emp.hiredate}</td>
				</tr>
				<tr>
					<th>工资</th>
					<td>${emp.sal}</td>
				</tr>
				<tr>
					<th>奖金</th>
					<td>${emp.comm}</td>
				</tr>
				<tr>
					<th>部门编号</th>
					<td>${emp.deptno}</td>
				</tr>
				<tr>
					
					<td colspan="2">
						<input type="button" value="修改" onclick="update('${emp.empno}')"/>
					</td>
				</tr>
		</table>
		<a href="dept_page">返回部门列表</a>
		<a href="emp_page">返回员工列表</a>
		</fieldset>
		
	</body>
</html>