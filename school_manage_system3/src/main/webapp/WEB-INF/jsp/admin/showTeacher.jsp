<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath()); /*根路径  */
%>
<head>
	<title>教师信息显示</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入jequery -->
	<script src="${APP_PATH }/js/jquery-3.2.1.min.js"></script>
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="${APP_PATH }/css/bootstrap.min.css">
	<!--  bootstrap.js-->
	<script src="${APP_PATH }/js/bootstrap.min.js"></script>

	<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

</head>
<body>
	<!-- 顶栏 -->

	<!-- 中间主体 --><jsp:include page="top.jsp"></jsp:include>
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">教师名单管理</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="selectTeacher" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入姓名" name="findByName">
									<span class="input-group-addon btn" onclick="document.getElementById('form1').submit" id="sub">搜索</span>
								</div>
							</form>
							<button class="btn btn-default col-md-2" style="margin-top: 20px" onClick="location.href='addTeacher'">
								添加教师信息
								<sapn class="glyphicon glyphicon-plus"/>
							</button>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>教师编号</th>
									<th>姓名</th>
									<th>性别</th>
									<th>出生年份</th>
									<th>学历</th>
									<th>职称</th>
									<th>入职年份</th>
									<th>学院</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${teacherList}" var="item">
								<tr>
									<td>${item.userid}</td>
									<td>${item.username}</td>
									<td>${item.sex}</td>
									<td><fmt:formatDate value="${item.birthyear}" dateStyle="medium" /></td>
									<td>${item.degree}</td>
									<td>${item.title}</td>
									<td><fmt:formatDate value="${item.grade}" dateStyle="medium" /></td>
									<td>${item.collegeName}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='editTeacher?id=${item.userid}'">修改</button>
										<button class="btn btn-default btn-xs btn-danger btn-primary" onClick="location.href='deleteTeacher?id=${item.userid}'">删除</button>
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
										<li> <a href = "showCourse?currentPage=1">首页</a></li>
										<c:if test="${page.currentPage ==1}">
											<li class = "disabled"> <a href = "showCourse?currentPage=${page.currentPage - 1}">&laquo;</a></li>
										</c:if>
										<c:if test="${page.currentPage > 1}">
											<li> <a href = "showCourse?currentPage=${page.currentPage - 1}">&laquo;</a></li>
										</c:if>
										<c:forEach begin = "${page.start }" end = "${page.end }" step = "1" var = "i">
										
											<c:choose>
												<c:when test="${page.currentPage == i }">
													<li class = "active"> <a href = "showCourse?currentPage=${page.currentPage }"> ${page.currentPage }</a></li>
												</c:when>
												<c:otherwise>
													<li> <a href = "showCourse?currentPage=${i}">${i }</a></li>
												</c:otherwise>
											</c:choose>
										
										</c:forEach>
										<c:if test="${ page.currentPage < page.totalPage }">
												<li> <a href = "showCourse?currentPage=${page.currentPage + 1}">&raquo;</a></li>
										</c:if>
										<c:if test="${page.currentPage == page.totalPage}">
											<li class = "disabled"> <a href = "showCourse?currentPage=${page.currentPage + 1}">&raquo;</a></li>
										</c:if>
										<li> <a href = "showCourse?currentPage=${page.totalPage }">尾页</a></li>
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
		$("#nav li:nth-child(3)").addClass("active")


        function confirmd() {
            var msg = "您真的确定要删除吗？！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }

        $("#sub").click(function () {
            $("#form1").submit();
        });
	</script>
</html>