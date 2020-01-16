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
	<form class="form-inline" action="${pageContext.request.contextPath }/goodsType/showType.do" method="post" id="search" >
		<div style="width: 80%; margin: 20px auto;">

			类型编号：<input type="text" name="gtno" class="form-control" value="${requestScope.gtno}"/> 
			类型名称：<input type="text" name="gtname" class="form-control" value="${requestScope.gtname}"/> 
				<input type="button" value="查询" class="btn btn-success" />
		</div>
		</form>
		<div style="text-align: right; width: 80%; margin: 20px auto">
			<a class="btn btn-info" href="${pageContext.request.contextPath}/goodsType/typeAdd.jsp" id="add">增加</a>
			. </div>

		<table class="table table-hover table-striped table-bordered table-conensed" style="width: 80%; margin: 0 auto;">
			<caption></caption>
			<thead>
				<tr>
					<th>产品类型编号</th>
					<th>产品类型名称</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${requestScope.goodsType }" var="type">
				<tr>
					<td>${type.gtno}</td>
					<td>${type.gtname}</td>
					<td>					
						<a class="btn btn-danger" href="${pageContext.request.contextPath }/goodsType/gtDel.do?gtid=${type.gtid}">删除</a>
						<a class="btn btn-warning"	href= "#" id= ${type.gtid }>修改</a>
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
						<form class="form-horizontal" action="${pageContext.request.contextPath}/goodsType/addType.do" method="post" id="form-info" name="add_form">
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">产品类型编号：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="typeCode">

								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">产品类型名称：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="typeName">

								</div>
							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="add_btn" form="add_form">保存</button>
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
						<form class="form-horizontal" action="${pageContext.request.contextPath}/goodsType/updType.do" method="post" id="form-upd" name="upd_form">
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">产品类型编号：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="typeCode">

								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">产品类型名称：</label>
								<div class="col-md-6 ">
									<input type="text" class="form-control" required="required" name="typeName">
								</div>
							</div>
							<div class="form-group">
								
									<input type="hidden" class="form-control" required="required" name="typeId">
								
							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="upd_btn" form="upd_form">保存</button>
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
				$("tbody .btn-warning").click(function() {
					var gtid=$(this).attr("id");
					
					var code=$(this).parents("tr").children("td:eq(0)").text();
					var name=$(this).parents("tr").children("td:eq(1)").text();
					$("#form-upd [name=typeId]").attr("value",gtid);
					$("#form-upd [name=typeCode]").attr("value",code);
					$("#form-upd [name=typeName]").attr("value",name);
					$('#myModal1').modal();
					return false;
				});
				$("#upd_btn").click(function() {
					$('#form-upd').submit();
					$('#myModal1').modal('hide');
				});	
				$("#search [type=button]").click(function () {					
					$("#search").submit();
				})
			})
		</script>
	</body>

</html>