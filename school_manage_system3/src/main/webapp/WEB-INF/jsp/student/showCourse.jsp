<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath()); /*根路径  */
%>
<head>
	<title>课程信息显示</title>

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
	<jsp:include page="top.jsp"></jsp:include>
	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">课程列表</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="selectCourse" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入课程名" name="findByName">
									<span class="input-group-addon btn" onclick="document.getElementById('form1').submit" id="sub">搜索</span>
								</div>
							</form>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>课程号</th>
									<th>课程名称</th>
									<th>授课老师编号</th>
									<th>上课时间</th>
									<th>上课地点</th>
									<th>周数</th>
									<th>课程类型</th>
									<th>学分</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${list}" var="item">
								<tr>
									<td>${item.courseid}</td>
									<td>${item.coursename}</td>
									<td>${item.teacherid}</td>
									<td>${item.coursetime}</td>
									<td>${item.classroom}</td>
									<td>${item.courseweek}</td>
									<td>${item.coursetype}</td>
									<td>${item.score}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='stuSelectedCourse?id=${item.courseid}'">选课</button>
										<!--弹出框-->
									</td>
								</tr>
							</c:forEach>
					        </tbody>
				    </table>
				   <div class = "panel-footer">
				    <div class = "row">
				    	<div class = "col-md-8 col-md-offset-4">
					    	<ul class = "pagination">
						    	<li> <a href = "showCourse?currentPage=1">首页</a> </li>
						    	<!-- 当前页是1 即无上一页 -->
						    	<c:if test="${page.currentPage == 1 }">
						    		<li class="disabled"><a href = "showCourse?currentPage=${page.currentPage-1 }">&laquo;</a> </li>
						    		<c:forEach begin = "${page.start }" end = "${page.end }" step = "1" var = "i">
						    			<c:if test="${i == page.currentPage }">
						    	       <li class="active"><a href = "showCourse?currentPage=${i }">${i }</a> </li>
						    			</c:if>
						    			<c:if test="${i != page.currentPage }">
						    		   <li>	<a href = "showCourse?currentPage=${i }">${i }</a></li>
						    			</c:if>
						    		</c:forEach>
						    		<li><a href = "showCourse?currentPage=${page.currentPage+1 }">&raquo;</a></li>
						    	</c:if>
						    	<!-- 当前页是中间页 -->
						    	<c:if test="${page.currentPage > 1 && page.currentPage < page.totalPage }">
						    		<li><a href = "showCourse?currentPage=${page.currentPage-1 }">&laquo;</a> </li>
						    		<c:forEach begin = "${page.start }" end = "${page.end }" step = "1" var = "i">
						    			<c:if test="${page.currentPage == i }">
						    			<li class="active"><a href = "showCourse?currentPage=${i }">${i }</a> </li>
						    			</c:if>
						    			<c:if test="${i != page.currentPage }">
						    			<li><a href = "showCourse?currentPage=${i }">${i }</a></li>
						    			</c:if>
						    		</c:forEach>
						    		<li><a href = "showCourse?currentPage=${page.currentPage+1 }">&raquo;</a></li>
						    	</c:if>
						    	<c:if test="${page.currentPage == page.totalPage }">
						    		<li><a href = "showCourse?currentPage=${page.currentPage-1 }">&laquo;</a></li>
						    		<c:forEach begin = "${page.start }" end = "${page.end }" step = "1" var = "i">
						    			<c:if test="${page.currentPage == i }">
						    		<li class="active"><a href = "showCourse?currentPage=${i }">${i }</a> </li>
						    			</c:if>
						    			<c:if test="${i != page.currentPage }">
						    		<li><a href = "showCourse?currentPage=${i }">${i }</a></li>
						    			</c:if>
						    		</c:forEach>
						    		<li class="disabled"><a href = "showCourse?currentPage=${page.currentPage+1 }">&raquo;</a></li>
						    	</c:if>
						    <li> <a href = "showCourse?currentPage=${page.totalPage }">尾页</a> </li>
					    	</ul>
					    </div>
			    	</div>
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
		<%--设置菜单中--%>
		$("#nav li:nth-child(1)").addClass("active")
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