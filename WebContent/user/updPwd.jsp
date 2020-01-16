<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet" type="text/css"/>
	<body>
		<form class="form-horizontal" role="form" style="width: 550px; margin: 10px auto;" action="${pageContext.request.contextPath }/updPwd.do" method="post">
			<div class="form-group">
				<label for="goodsCode" class="col-sm-3 control-label">原密码:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="oldPwd">
				</div>
				<div class="clo-sm-3" id="showPwd"></div>
			</div>

			<div class="form-group">
				<label for="goodsPrice" class="col-sm-3 control-label">新密码:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="newPwd">
				</div>
			</div>

			<div class="form-group">
				<label for="goodsPrice" class="col-sm-3 control-label">再次确认:</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="newPwd1">
				</div>
			</div>

			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label"></label>
				<div class="col-sm-6">
					<input type="submit" class="btn btn-info col-sm-12" value="保存" />
				</div>
			</div>
		</form>

		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			var flag = false;
			$(function() {
				$("[name=oldPwd]").change(function() {
					$.ajax({
						cache: false,
						type: "post",
						url: "../checkPwd.do",
						async: true,
						data: {
							"oldPwd":
							$("[name=oldPwd]").val()
						},
						error: function() {
							alert("Connection error");
						},
						success: function(data) {
							if(data == "true") {

								tag = true;
								$("#showPwd").html("");
							} else {
								tag = false;
								$("#showPwd").html("原密码不正确");
							}
						}
					});
				})
				$(":submit").click(function() {
					if(tag == false) {

						alert("原密码不正确");
						return false;
					}

					if($("[name=newPwd]").val() != $("[name=newPwd1]").val()) {

						alert("密码和确认密码不一致");
						return false;
					}
				});
			})
		</script>
		
	</body>

</html>