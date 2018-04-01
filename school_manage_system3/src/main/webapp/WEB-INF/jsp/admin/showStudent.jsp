<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath()); /*根路径  */
%>
<head>
	<title>学生信息显示</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="${APP_PATH }/js/jquery-3.2.1.min.js"></script>
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="${APP_PATH }/css/bootstrap.min.css">
	<!--  bootstrap.js-->
	<script src="${APP_PATH }/js/bootstrap.min.js"></script>

	<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="top.jsp"></jsp:include>
	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">学生名单管理</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="selectStudent" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入姓名" name="findByName">
									<span class="input-group-addon btn" id="sub">搜索</span>
								</div>
							</form>
							<button class="btn btn-default col-md-2" style="margin-top: 20px" onClick="location.href='addStudent'">
								添加用户信息
								<sapn class="glyphicon glyphicon-plus"/>
							</button>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
					                <th>学号</th>
				  					<th>姓名</th>
				  					<th>性别</th>
				  					<th>出生年份</th>
				  					<th>入学时间</th>
				  					<th>学院</th>
				  					<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${list}" var="item">
								<tr>
									<td>${item.userid}</td>
									<td>${item.username}</td>
									<td>${item.sex}</td>
									<td><fmt:formatDate value="${item.birthyear}" dateStyle="medium" /></td>
									<td><fmt:formatDate value="${item.grade}" dateStyle="medium" /></td>
									<td>${item.collegeName}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='editStudent?id=${item.userid}'">修改</button>
										<button class="btn btn-default btn-xs btn-danger btn-primary" id="delete" onClick="location.href='deleteStudent?id=${item.userid}'">删除</button>
										<!--弹出框-->
									</td>
								</tr>
							</c:forEach>
					        </tbody>
				    </table>
				    <div class="panel-footer">
				    	<c:if test="${flag == true }">
							<div class = "row">
								<div class = "col-md-6 col-md-offset-6">
									<ur class = "pagination">
										<li> <a href = "showStudent?currentPage=1">首页</a></li>
										<c:if test="${page.currentPage ==1}">
											<li class = "disabled"> <a href = "showStudent?currentPage=${page.currentPage - 1}">&laquo;</a></li>
										</c:if>
										<c:if test="${page.currentPage > 1}">
											<li> <a href = "showStudent?currentPage=${page.currentPage - 1}">&laquo;</a></li>
										</c:if>
										<c:forEach begin = "${page.start }" end = "${page.end }" step = "1" var = "i">
										
											<c:choose>
												<c:when test="${page.currentPage == i }">
													<li class = "active"> <a href = "showStudent?currentPage=${page.currentPage }"> ${page.currentPage }</a></li>
												</c:when>
												<c:otherwise>
													<li> <a href = "showStudent?currentPage=${i}">${i }</a></li>
												</c:otherwise>
											</c:choose>
										
										</c:forEach>
										<c:if test="${ page.currentPage < page.totalPage }">
												<li> <a href = "showStudent?currentPage=${page.currentPage + 1}">&raquo;</a></li>
										</c:if>
										<c:if test="${page.currentPage == page.totalPage}">
											<li class = "disabled"> <a href = "showStudent?currentPage=${page.currentPage + 1}">&raquo;</a></li>
										</c:if>
										<li> <a href = "showStudent?currentPage=${page.totalPage }">尾页</a></li>
									</ur>
								</div>
							</div>
						</c:if>
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
<script type="text/javascript">
		$("#nav li:nth-child(2)").addClass("active");

        function confirmd() {
            var msg = "您真的确定要删除吗？！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        };

        $("#sub").click(function () {
            $("#form1").submit();
        });

	</script>
</html>