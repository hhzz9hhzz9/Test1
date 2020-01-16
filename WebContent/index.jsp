<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet" type="text/css"/>
</head>
<body>

	<div class="container-fluid">
		<div class="row" style="background-color: ghostwhite">
			<div class="col-md-12"
				style="background-color: black; height: 100px;">
				<h1 align="center">
					<a href="${pageContext.request.contextPath }/index.jsp"
						style="text-decoration: none; color: white;">仓储管理系统</a>
				</h1>
			</div>
		</div>
		<div>
			${cookie.userName.value}已经登录&nbsp;<a
				href="${pageContext.request.contextPath }/exit.do">安全退出</a>
		</div>
	</div>

	<div class="row" style="height: 700px; margin-top: 50px">
		<div class="col-md-3">
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-toggle="collapse"
								data-parent="#accordion" href="#collapseOne"> 系统管理 </a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body">
							<ul class="list-group">
								<li class="list-group-item"><a href="${pageContext.request.contextPath }/user/showUser.do" target="goodsFrame">用户管理</a></li>
								<li class="list-group-item"><a href="${pageContext.request.contextPath }/user/updPwd.jsp" target="goodsFrame">用户密码修改</a></li>

								<li class="list-group-item"><a href="${pageContext.request.contextPath }/customer/showCus.do" target="goodsFrame">客户管理</a></li>
							</ul>
						</div>
					</div>
				</div>


				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-toggle="collapse"
								data-parent="#accordion" href="#collapseTwo"> 基础数据管理 </a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="list-group">
								<li class="list-group-item"><a
									href="${pageContext.request.contextPath }/goods/showGoods.do"
									target="goodsFrame">商品管理</a></li>
								<li class="list-group-item"><a
									href="${pageContext.request.contextPath }/goodsType/showType.do"
									target="goodsFrame">商品类型管理</a></li>

								<li class="list-group-item"><a
									href="${pageContext.request.contextPath }/supplier/showSupplier.do"
									target="goodsFrame">供应商管理</a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-toggle="collapse"
								data-parent="#accordion" href="#collapseThree"> 库存管理 </a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="list-group">
								<li class="list-group-item"><a href="#" target="goodsFrame">入库管理</a>
								</li>
								<li class="list-group-item"><a
									href="${pageContext.request.contextPath }/outStrock/oss.do"
									target="goodsFrame">出库管理</a></li>

							</ul>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="col-md-9" style="height: 700px">
			<iframe name="goodsFrame" width="100%" height="100%"></iframe>

		</div>
		

	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</body>
</html>