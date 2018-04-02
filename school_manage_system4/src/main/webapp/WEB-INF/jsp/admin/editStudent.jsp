<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>editStudent</title>
    	<!-- 引入jequery -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<!--  bootstrap.js-->
	<script src="js/bootstrap.min.js"></script>
    
  </head>
  
  <body>
  
  	<!-- 头部 -->
    <jsp:include page="top.jsp"></jsp:include>
    
    <!-- 主体 -->
    <div class = "container">
    	<div class = "row">
    		<jsp:include page="menu.jsp"></jsp:include>
    		<div class = "col-md-10">
    			<div class = "panel panel-default">
    				<div class = "panel-heading">
    					<div class = "row"> 
    						<h1 style="text-align:center">修改学生信息</h1>
    					</div>
    				<div class = "panel-body">
	    				<form action = "admin/editStudent" class = "form-horizontal" role = "form" method = "post">
	    					<div class="form-group">
	    						<label for="userid" class = "col-sm-2 control-label">学号</label>
		    					<div class = "col-sm-10">
		    						<input type = "text" class = "form-control" id="userid" name="userid" value = "${student.userid }"/>
		    					</div>
	    					</div>
	    					<div class="form-group">
	    						<label for="name" class="col-sm-2 control-label">姓名</label>
	    						<div class="col-sm-10">
	    							<input type="text" class="form-control" id="name" name="username" value ="${student.username }">
	    						</div>
	    					</div>
	    					<div class="form-group">
	    						<label  class="col-sm-2 control-label">性别</label>
	    						<div class="col-sm-10">
	    							<label class="checkbox-inline">
	    								<input type="radio" name="sex" value="男" checked>男</input>
	    							</label>
	    							<label class="checkbox-inline">
	    								<input type="radio" name="sex" value="男" >女
	    							</label>
	    						</div>
	    					</div>
	    					<div class="form-group">
	    						<label for="inputPassword3" class="col-sm-2 control-label">出生年月</label>
	    						<div class="col-sm-4">
	    							<input type="date" class="form-control" name="birthyear" value="<fmt:formatDate value="${student.birthyear}" dateStyle="medium" pattern="yyyy-MM-dd" />" name="birthyear" />
	    						</div>
	    					</div>
	    					<div class="form-group">
	    						<label for="inputPassword3" class="col-sm-2 control-label">入学时间</label>
	    						<div class="col-sm-4">
	    							<input type="date" class="form-control" name="grade" value="<fmt:formatDate value="${student.grade}" dateStyle="medium" pattern="yyyy-MM-dd" />" />
	    						</div>
	    					</div>
	    					<div class="form-group">
	    						<label for="inputPassword3" class="col-sm-2 control-label">所属院系</label>
	    						<div class="col-sm-10">
	    							<select class="form-control" name = "collegeid">
	    								<c:forEach items="${collegelist }" var = "college">
	    									<option value = "${college.collegeid }"> ${college.collegename }</option>
	    								</c:forEach>
	    							</select>
	    						</div>
	    					</div>
	    					<div class="form-group" style="text-align:center">
	    						<button class="btn btn-default" type = "submit">提交</button>
	    						<button class="btn btn-default" type = "reset">重置</button>
	    					</div>
	    				</form>
	    			</div>
    			</div>
    		</div>
    	</div>
    </div>
    <div class="container" id="footer">
	<div class="row">
		<div class="col-md-12"></div>
	</div>
	</div>
  </body>
</html>
