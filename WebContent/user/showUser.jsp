<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	</head>

	<body>
		<div style="text-align: right; width: 80%; margin: 20px auto">
			<a class="btn btn-info" href="${pageContext.request.contextPath}/user/userAdd.jsp" id="add">增加</a>
			. </div>

		<table class="table table-hover table-striped table-bordered table-conensed" style="width: 80%; margin: 0 auto;">
			<caption></caption>
			<thead>
				<tr>
					<th>用户名</th>
					<th>用户角色</th>
					<th>用户状态</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${requestScope.user }" var="u">
					<tr>
						<td>${u.userName }</td>
						<td>${u.userRole }</td>
						<td>${u.userStatus }</td>
						<td>

							<c:if test="${u.userStatus=='启用'}">
								<a class="btn btn-danger" href="${pageContext.request.contextPath}/user/caozuo.do?uid=${u.userId}&status=1">停用</a>

							</c:if>
							<c:if test="${u.userStatus=='停用'}">
								<a class="btn btn-success" href="${pageContext.request.contextPath}/user/caozuo.do?uid=${u.userId}&status=0">启用</a>
							</c:if>
							<a class="btn btn-primary" href='a' id=${u.userId }>修改</a>
							<a class="btn btn-warning" href="${pageContext.request.contextPath}/user/caozuo.do?uid=${u.userId}&status=-1">重置密码</a>

						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">新增</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="${pageContext.request.contextPath}/user/userAdd.do" method="post" id="form-info" name="user_add_form">
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">用户名：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="userName">

								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">用户角色：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="userRole">

								</div>
							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="add_btn" form="user_add_form">保存</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="myModal1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">修改</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="${pageContext.request.contextPath}/user/userUpd.do" method="post" id="form-upd" name="user_upd_form">
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">用户名：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="userName">

								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">用户角色：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="userRole">
								</div>
							</div>
							<div class="form-group">
								
									<input type="hidden" class="form-control" required="required" name="userId">
								
							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="upd_btn" form="user_upd_form">保存</button>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$("#add").click(function() {
				$('#myModal').modal();
				return false;
			});

			$("#add_btn").click(function() {
				$('#form-info').submit();
				$('#myModal').modal('hide');
			});
			$(function() {
				$("tbody .btn-primary").click(function() {
					var uid=$(this).attr("id");
					
					var name=$(this).parents("tr").children("td:eq(0)").text();
					var role=$(this).parents("tr").children("td:eq(1)").text();
					$("#form-upd [name=userId]").attr("value",uid);
					$("#form-upd [name=userName]").attr("value",name);
					$("#form-upd [name=userRole]").attr("value",role);
					$('#myModal1').modal();
					return false;
				});
				$("#upd_btn").click(function() {
					$('#form-upd').submit();
					$('#myModal1').modal('hide');
				});				
			})
		</script>
	</body>

</html>