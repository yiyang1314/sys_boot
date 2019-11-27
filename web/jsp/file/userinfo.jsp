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
					location.href="users_updateById?id="+id;
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
			table{
				border-collapse: collapse;
				width:350px;
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
			<legend>员工管理系统</legend>
			<h1>查看用户信息服务</h1>
			<p style="text-align:right;">----员工服务，----一路顺风</p>
			
			<table border="2">
			<tr>
				<th>主键</th>
				<td>${u.id}</td>
			</tr>
			<tr>
				<th>用户名</th>
				<td>${u.name}</td>
			</tr>
			<tr>
				<th>密码</th>
				<td>${u.password}</td>
			</tr>

			<tr align="center">
					<td colspan="2">
						<input type="button" value="去修改" onclick="update('${u.id}')"/>
					</td>
				</tr>
		</table>
			<a href="users_page">返回</a>
		</fieldset>
		
	</body>
</html>