<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>

	<div class="container-fluid">
		<div class="row"
			style="background-color: black; color: white; height: 100px">
			<div class="col-md-12">
				<h1 align="center">仓储管理系统</h1>
			</div>

		</div>
		<div class="row" style="height: 700px">
			<div class="col-md-12">
				<form class="form-horizontal" action="user.do" method="post"
					style="width: 400px;margin: 200px auto;">
					<div class="form-group">
						<label class="col-md-4 col-sm-4 control-label">用户名：</label>
						<div class="col-md-6 ">
							<input type="text" class="form-control" required="required"
								name="userName" id="userName" onblur="sendData()">

						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 col-sm-4 control-label">密码：</label>
						<div class="col-md-6 ">
							<input type="password" class="form-control" required="required"
								name="userPwd">

						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 col-sm-4 control-label">验证码：</label>
						<div class="col-md-3 ">
							<input type="text" class="form-control" required="required"
								name="checkCode">

						</div>
						<div class="col-md-5 ">
						<a><img src="checkCode.do" onclick="this.src='checkCode.do?t='+Math.random()"/></a>
						
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-4 control-label"></label>
						<div class="col-md-6">

							<input type="submit" class="btn btn-info" value="登录" /> <input
								type="reset" class="btn btn-info" value="重置" />
						</div>
					</div>
				</form>
			</div>

		</div>
		<div class="row" style="background-color: #eee; height: 100px">
			<div class="col-md-12" style="text-align: center; line-height: 100px">

				&copy;版权信息</div>

		</div>

	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript"> 

			if (window != top) 

			top.location.href = location.href; 

</script>
	
</body>
</html>