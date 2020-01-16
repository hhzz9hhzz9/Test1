<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <div style="text-align: right; width: 80%; margin: 20px auto">
			<a class="btn btn-info" href="${pageContext.request.contextPath}/customerr/cusAdd.jsp" id="add">增加</a>
			. </div>

		<table class="table table-hover table-striped table-bordered table-conensed" style="width: 80%; margin: 0 auto;">
			<caption></caption>
			<thead>
				<tr>
					<th>客户名</th>
					<th>客户电话</th>
					<th>客户地址</th>
					<th>客户状态</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${requestScope.customer }" var="c">
					<tr>
						<td>${c.cname }</td>
						<td>${c.ctel }</td>
						<td>${c.caddr }</td>
						<td>${c.cstatus }</td>
						<td>

							<c:if test="${c.cstatus=='启用'}">
								<a class="btn btn-danger" href="${pageContext.request.contextPath}/customer/caozuo.do?cid=${c.cid}&status=1">停用</a>

							</c:if>
							<c:if test="${c.cstatus=='停用'}">
								<a class="btn btn-success" href="${pageContext.request.contextPath}/customer/caozuo.do?cid=${c.cid}&status=0">启用</a>
							</c:if>
							<a class="btn btn-primary" href='a' id=${c.cid }>修改</a>

						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		<div class="modal fade" id="cusModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">新增</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="${pageContext.request.contextPath}/customer/cusAdd.do" method="post" id="add-info" name="cus_add_form">
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">客户账号：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="cusName">

								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">客户电话：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="cusTel">

								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">客户地址：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="cusAddr">

								</div>
							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="add_btn" form="cus_add_form">保存</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="cusModal1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">修改</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="${pageContext.request.contextPath}/customer/cusUpd.do" method="post" id="cus-upd" name="cus_upd_form">
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">客户账号：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="cusName">

								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">客户电话：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="cusTel">

								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">客户地址：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="cusAddr">

								</div>
							</div>
							<div class="form-group">
								
									<input type="hidden" class="form-control" required="required" name="cusId">
								
							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="upd_btn" form="cus_upd_form">保存</button>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$("#add").click(function() {
				$('#cusModal').modal();
				return false;
			});

			$("#add_btn").click(function() {
				$('#add-info').submit();
				$('#myModal').modal('hide');
			});
			$(function() {
				$("tbody .btn-primary").click(function() {
					var cid=$(this).attr("id");
					alert(cid);
					var name=$(this).parents("tr").children("td:eq(0)").text();
					var tel=$(this).parents("tr").children("td:eq(1)").text();
					var addr=$(this).parents("tr").children("td:eq(2)").text();
					$("#cus-upd [name=cusId]").attr("value",cid);
					$("#cus-upd [name=cusName]").attr("value",name);
					$("#cus-upd [name=cusTel]").attr("value",tel);
					$("#cus-upd [name=cusAddr]").attr("value",addr);
					$('#cusModal1').modal();
					return false;
				});
				$("#upd_btn").click(function() {
					$('#cus-upd').submit();
					$('#cusModal1').modal('hide');
				});				
			})
		</script>
	</body>
</html>