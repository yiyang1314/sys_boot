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
				width:850px;
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
		<script type="text/javascript" src="jsp/js/deletelist.js"></script>
		<script type="text/javascript">
			
			function find(id){
				if(confirm("您确定要修改"+id+"吗？")){
					location.href="emp_findById?empno="+id;
				}
				   
			}
			
			function update(id){
				if(confirm("您确定要修改"+id+"吗？")){
					location.href="emp_updateById?empno="+id;
				}
				   
			}
			
			function del(id){
				if(confirm("您确定要删除"+id+"吗？")){
					location.href="emp_delete?empno="+id;
				}
				   
			}
			var flags=false;
			function ckeckMove(){
				alert(1);
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
			<h1>员工管理查询系统</h1>
			<p style="text-align:right;">----员工管理查询，----一路顺风</p>
			<form name="fom" id="fom" method="post">
			<table border="2" >
			<tr>
				<th><input type="checkbox" onclick="ckeckMove()"> 选择</th>
				<th>员工编号</th>
				<th>姓名</th>
				<th>职位</th>
				<th>上级</th>
				<th>入职日期</th>
				<th>工资</th>
				<th>奖金</th>
				<th>部门编号</th>
				<th>操作</th>
			</tr>
			<c:forEach var="emp" items="${emp_list}">
				<tr>
					<td><input type="checkbox" name="delid" value="${emp.empno}"/></td>
					<td>${emp.empno}</td>
					<td><a href="emp_findById?empno=${emp.empno}">${emp.ename}</a></td>
					<td>${emp.job}</td>
					<td>${emp.mgrname}</td>
					<td>
						<fmt:formatDate value="${emp.hiredate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						<fmt:formatNumber value="${emp.sal}" pattern="￥"></fmt:formatNumber>
					</td>
					<td>${emp.comm}</td>
					<td><a href="dept_findByIdPlus?deptno=${emp.dept['deptno']}">${emp.dept['dname']}</a></td>
					<td>
						<input type="button" value="修改" onclick="update('${emp.empno}')"/>
						<input type="button" value="删除" onclick="del('${emp.empno}')"/>
					</td>
				</tr>
			</c:forEach>
			</table>
			</form>
			<c:if test="${count>0}">
			<jsp:include page="../page/page.jsp">
				<jsp:param value="emp_page" name="action"/>
				<jsp:param value="${enablePage}" name="enablePage"/>
				<jsp:param value="${column}" name="column"/>
				<jsp:param value="${enableSearch }" name="enableSearch"/>
				<jsp:param value="${count}" name="count"/>
			</jsp:include>
			</c:if> 
		<a href="jsp/file/empupdate.jsp">添加</a>
		<a href="jsp/file/wain.jsp">返回</a>
		<input type="button" value="批量删除" title="已启用"  onclick="delids('emp',this)" />
		<c:if test="${enablePage==false }">
			<a href="emp_page">分页</a>
		</c:if>
		<c:if test="${enablePage==true }">
			<a href="emp_findAll">查看所有信息</a>
		</c:if>
		
		</fieldset>

	
	</body>
</html>