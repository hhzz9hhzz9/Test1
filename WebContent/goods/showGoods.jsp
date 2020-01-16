
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet" type="text/css"/>


</head>
<body>
	<form class="form-inline"
		action="${pageContext.request.contextPath }/goods/showGoods.do" method="post"
		name="form1">
		<div style="width: 80%; margin: 20px auto;">

			编号：<input type="text" name="gno" value="${requestScope.result.gno }"
				class="form-control" /> 产品：<input type="text" name="gname"
				value="${requestScope.result.gname }" class="form-control" /> 类型：<input
				type="text" name="gtname" value="${requestScope.result.gtname }"
				class="form-control" /> 产商：<input type="text" name="sname"
				value="${requestScope.result.sname }" class="form-control" /> <input
				type="submit" value="查询" class="btn btn-success" />
		</div>
		<div style="text-align: right; width: 80%; margin: 20px auto">
			<a class="btn btn-info"
				href="${pageContext.request.contextPath }/goods/updGoods.do">增加</a>
		</div>


		<table
			class="table table-hover table-striped table-bordered table-conensed"
			style="width: 80%; margin: 0 auto;">
			<caption></caption>
			<thead>
				<tr>
					<th>商品编号</th>
					<th>商品名称</th>
					<th>商品类型</th>
					<th>供应商</th>
					
					<th>价格/元</th>
					<th>商品库存</th>
					<th>图片</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${requestScope.pageBean.data }" var="g">
					<tr>
						<td>${g.gno }</td>
						<td>${g.gname }</td>
						<td>${g.goodsType.gtname }</td>
						<td>${g.supplier.sname }</td>
						
						<td>${g.price}</td>
						<td>${g.gcount}</td>
					
						
						<td>${g.img}</td>
						<td><a class="btn btn-danger"
							href="  ${pageContext.request.contextPath }/goods/delGoods.do?gid=${g.gid }">删除</a>
							<a class="btn btn-warning"
							href="${pageContext.request.contextPath }/goods/updGoods.do?gid=${g.gid }">修改</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="11" style="align-content: center;">
						<ul class="pagination">
							<li><a href="javascript:go(1)">&laquo;</a></li>
							<li><a
								href="javascript:go(${requestScope.pageBean.currentPage-1 })">&lt;</a></li>
							<c:forEach begin="1" end="${requestScope.pageBean.pages}" var="i">
								<c:choose>
									<c:when test="${requestScope.pageBean.currentPage==i }">
										<li class="active"><a href="javascript:go(${i})">${i}</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:go(${i})">${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<li><a
								href="javascript:go(${requestScope.pageBean.currentPage+1 })">&gt;</a></li>
							<li><a href="javascript:go(${requestScope.pageBean.pages})">&raquo;</a></li>
							<span>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;共${requestScope.pageBean.pages }页，共${requestScope.pageBean.totalRows }条
							&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
							当前页是第${requestScope.pageBean.currentPage }页</span>
						</ul>
					</td>

				</tr>
			</tbody>
		</table>
		<input type="hidden" name="cutPage" value="1" />
	</form>
	
		<div class="modal fade" id="myModal1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">修改</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="${pageContext.request.contextPath}/goods/updgoods.do" method="post" id="form-upd" name="upd_form">
							<div class="form-group">
			<label for="goodsCode" class="col-sm-3 control-label">商品编号:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" placeholder="请输入商品编号"
					name="gno" value="${requestScope.Goods.gno}">
			</div>
		</div>
		<div class="form-group">
			<label for="goodsName" class="col-sm-3 control-label">商品名称:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" placeholder="请输入商品名称"
					name="gname" value="${requestScope.Goods.gname}">
			</div>
		</div>
		<div class="form-group">
			<label for="goodsName" class="col-sm-3 control-label">商品类型:</label>
			<div class="col-sm-9">
				<select class="form-control" name="gtid">

					<c:forEach items="${requestScope.GoodsType }" var="goodsType">
						<option value="${goodsType.gtid}" <c:if test="${goodsType.gtid==requestScope.Goods.gtid}"> selected="selected"</c:if>>${goodsType.gtname}</option>
					</c:forEach>

				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="goodsName" class="col-sm-3 control-label">产商:</label>
			<div class="col-sm-9">
				<select class="form-control" name="sid">
					
					<c:forEach items="${requestScope.Supplier }" var="supplier">
					<option value="${supplier.sid}" <c:if test="${supplier.sid==requestScope.Goods.sid}"> selected="selected"</c:if>>
						${supplier.sname}
					</option>
					</c:forEach>
				
				</select>
			</div>
		</div>

		<div class="form-group">
			<label for="goodsPrice" class="col-sm-3 control-label">价格:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" placeholder="请输入价格"
					name="price" value="${requestScope.Goods.price}">
			</div>
		</div>
		

		<div class="form-group">
			<label for="goodsType" class="col-sm-3 control-label">图片:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" placeholder="请输入备注"
					name="info" value="${requestScope.Goods.img}">
			</div>
		</div>

							<div class="form-group">
								
									<input type="hidden" class="form-control" required="required" name="supplierId">
								
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
		
		function go(param) {
			form1.cutPage.value = param;
			form1.submit();
		}
			
		</script>
		</body>
</html>