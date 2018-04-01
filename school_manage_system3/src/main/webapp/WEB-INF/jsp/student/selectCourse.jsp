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
					    	<h1 class="col-md-5">已选课程</h1>


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
							<c:forEach  items="${selectedCourseList}" var="item">
								<%--输出还没修完的课程--%>
								<c:if test="${!item.over}">
									<tr>
										<td>${item.couseCustom.courseid}</td>
										<td>${item.couseCustom.coursename}</td>
										<td>${item.couseCustom.teacherid}</td>
										<td>${item.couseCustom.coursetime}</td>
										<td>${item.couseCustom.classroom}</td>
										<td>${item.couseCustom.courseweek}</td>
										<td>${item.couseCustom.coursetype}</td>
										<td>${item.couseCustom.score}</td>
										<td>
											<button class="btn btn-default btn-xs btn-info" onClick="location.href='outCourse?id=${item.courseid}'">退课</button>
											<!--弹出框-->
										</td>
									</tr>
								</c:if>
							</c:forEach>
					        </tbody>
				    </table>
				    <div class="panel-footer">
						
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
		$("#nav li:nth-child(2)").addClass("active")

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