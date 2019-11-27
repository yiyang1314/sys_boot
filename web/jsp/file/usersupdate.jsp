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
		
		function checkmsg(){
			var forms=document.myform;
			var name=forms.name.value;
			var password=forms.password.value+"";
			var pwd=forms.pawd.value+"";
			if(password>16&&password.length<6){
				alert("密码太长或者太短！");
				return ;
			}
			if(pwd!=password){
				alert("密码不一致！");
				forms.password.focus();
				return;
			}
		}
		
		
		</script>
	</head>
	<body>
		<fieldset>
			<legend>员工管理系统</legend>
			<h1>员工${user.id==null?'用户':'修改' }服务</h1>
			<p style="text-align:right;">----员工服务，----一路顺风</p>
			<form action="users_save" method="post" style="width:550px;" name="myform" id="myform">
			<table border="1">
				<tr>
					<td colspan="2"><input name="id" type="hidden" value="${user.id}" /></td>
				</tr>	
				<tr>	
					<th>姓名</th>
					<td><input name="name" type="text" value="${user.name}" /></td>
				</tr>	
				<tr>
					<th>密码</th>
					<td><input name="password" type="password" value="${user.password}" /></td>
				</tr>
			
				<tr>
					<th>确认密码</th>
					<td><input name="pwd" type="password" value="" /></td>
				</tr>

				<tr align="center">
					
					<td colspan="2">
					<input name="btn" type="submit" value="${user.id==null?'注册':'修改' }" onclick="checkmsg()"/>
					<input name="btn5" type="reset" value="重置" />			
					</td>

				</tr>
			</table>
				</form>	
				<a href="jsp/file/wain.jsp">返回</a>
		</fieldset>

	
	</body>
</html>