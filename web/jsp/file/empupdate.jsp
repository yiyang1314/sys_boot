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
		<script type="text/javascript">
		function searchDeptno(){
			var deptno=location.search.substring(location.search.lastIndexOf("=")+1);
			
			var mgr=document.getElementById("mgr");
			var empno=document.getElementById("empno")+"";
			if(deptno&&empno==""){
				document.getElementById("mgr").value=deptno;
				document.getElementById("mgr").readOnly=true;
				document.getElementById("mgr").title="部门号不能变更";
			}
		}
		
		
		
		
		</script>
	</head>
	<body onload="searchDeptno()">
		<fieldset>
			<legend>员工管理系统</legend>
			<h1>员工${emp.empno==null?'新增':'修改' }服务</h1>
			<p style="text-align:right;">----员工服务，----一路顺风</p>
			<form action="emp_save" method="post" style="width:550px;" name="myform" id="myform">
			<table border="1">
				<tr>
					<td colspan="2"><input name="empno" type="hidden" value="${emp.empno}" id="empno"/></td>
				</tr>	
				<tr>	
					<th>姓名</th>
					<td><input name="ename" type="text" value="${emp.ename}" /></td>
				</tr>	
				<tr>
					<th>职位</th>
					<td><input name="job" type="text" value="${emp.job}" /></td>
				</tr>	
				<tr>
					<th>上级</th>
					<td><input name="mgr" id="mgr" type="text" value="${emp.mgr}" /></td>
				</tr>	
				<tr>
					<th>入职日期</th>
					<td><input name="hiredate" type="text" value="${emp.hiredate}" /></td>
				</tr>	
				<tr>
					<th>工资</th>
					<td><input name="sal" type="text" value="${emp.sal}" /></td>
				</tr>	
				<tr>
				<th>奖金</th>
					<td><input name="comm" type="text" value="${emp.comm}" /></td>
				</tr>	
				<tr>
					<th>部门编号</th>
					<td><input name="deptno" type="text" value="${emp.deptno}" /></td>

				</tr>
				<tr align="center">
					
					<td colspan="2">
					<input name="btn" type="submit" value="${emp.empno==null?'新增':'修改' }" />
					<input name="btn5" type="reset" value="重置" />			
					</td>

				</tr>
			</table>
				</form>	
				<a href="emp_page">返回</a>
				<a href="jsp/file/wain.jsp">返回</a>
		</fieldset>

	
	</body>
</html>