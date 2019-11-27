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
				background-color:green;
			}
			fieldset{
				width:720px;
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
				margin-left:80px;
			}
			form{
					margin-bottom: 5px;
			}
		</style>
		<script type="text/javascript" src="jsp/js/deletelist.js"></script>
		<script type="text/javascript">
			function update(id){
				if(confirm("您确定要修改"+id+"吗？")){
					location.href="users_updateById?id="+id;
				}
				   
			}
			function del(id){
				if(confirm("您确定要删除"+id+"吗？")){
					location.href="users_delete?id="+id;
				}
				   
			}
			var flags=false;
			function ckeckMove(){
				if(!flags){
					flags=!flags;
					selectAll();
				}else{
					unselectAll();
				}
			}

		</script>
	</head>
	<body>
		<fieldset>
			<legend>员工管理系统</legend>
			<h1>查看用户列表</h1>
			<p style="text-align:right;">----员工服务，----一路顺风</p>	
		<form name="fom" id="fom" method="post">
		<table border="2">
			<tr>
				<th><input type="checkbox" onclick="ckeckMove()"> 选择</th>
				<th>员工编号</th>
				<th>用户名</th>
				<th>密码</th>
				<th>操作</th>
			</tr>
			<c:forEach var="u" items="${users_list}">
				<tr>
				<td><input type="checkbox" name="delid" value="${u.id}"/></td>
				<td>${u.id}</td>   
				<td><a href="users_findById?id=${u.id}">${u.name}</a></td>    
				<td>${u.password}</td>  
				<td>
					<input type="button" value="修改" onclick="update('${u.id}')"/>
					<input type="button" value="删除" onclick="del('${u.id}')"/>
				</td>
				</tr>
			</c:forEach>
		</table>
		</form>
			<c:if test="${count>0}">
			<jsp:include page="../page/page.jsp">
				<jsp:param value="users_page" name="action"/>
				<jsp:param value="${enablePage}" name="enablePage"/>
				<jsp:param value="${column}" name="column"/>
				<jsp:param value="${enableSearch }" name="enableSearch"/>
				<jsp:param value="${count}" name="count"/>
			</jsp:include>
			</c:if> 
		<p style="padding-left:50px;">
		<a href="jsp/file/usersupdate.jsp">添加</a>
		<a href="jsp/file/wain.jsp">返回</a>
		<input type="button" value="批量删除" title="已启用"  onclick="delids('users',this)" />
		</p>
	</fieldset>
	</body>
</html>