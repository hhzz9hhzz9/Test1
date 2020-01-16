
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

	<form class="form-horizontal" role="form"
		style="width: 550px; margin: 10px auto;"
		action="${pageContext.request.contextPath }/goods/addGoods.do"
		method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="goodsCode" class="col-sm-3 control-label">商品编号:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" placeholder="请输入商品编号"
					name="goodsCode" value="${requestScope.Goods.gno }">
			</div>
		</div>
		<div class="form-group">
			<label for="goodsName" class="col-sm-3 control-label">商品名称:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" placeholder="请输入商品名称"
					name="goodsName" value="${requestScope.Goods.gname }">
			</div>
		</div>
		<div class="form-group">
			<label for="goodsName" class="col-sm-3 control-label">商品类型:</label>
			<div class="col-sm-9">
				<select class="form-control" name="typeId">

					<c:forEach items="${requestScope.GoodsType }" var="goodsType">
						<option value="${goodsType.gtid}" <c:if test="${goodsType.gtid==requestScope.Goods.gtid}"> selected="selected"</c:if>>${goodsType.gtname}</option>
					</c:forEach>

				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="goodsName" class="col-sm-3 control-label">产商:</label>
			<div class="col-sm-9">
				<select class="form-control" name="supplierId">
					
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
			<label for="goodsImg" class="col-sm-3 control-label">商品图片:</label>
			<div class="col-sm-9">
					<input type="file"  name="goodsImg">
			</div>
 		</div>
		<div class="form-group">
			
			<div class="col-sm-9">
				<input type="hidden" class="form-control" name="goodsId"
					value="${requestScope.Goods.gid}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputEmail3" class="col-sm-3 control-label"></label>
			<div class="col-sm-9">
				<input type="submit" class="btn btn-info col-sm-12" value="保存" />
			</div>
		</div>
	</form>


	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</body>
</html>