<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加管理员</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/plugins/bootstrap/css/bootstrap.css" />
<script type="text/javascript">
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-6">
				<div class="view">
					<h3><font face="楷体">添加管理员</font></h3>
				</div>
				<form role="form" action="${pageContext.request.contextPath}/admin/updateAdminAction" method="post" class="form-horizontal">
				<table class="table table-striped">
					<tr>
						<td>用户名：</td>
						<td>
							<div class="form-group">
							<input class="form-control" id="username" name="adminBean.username" type="text"
							id="cellphone" placeholder="用户名" value="${bean.username}"/>
							</div>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td>
							<div class="form-group">
							<input type="password" name="adminBean.password"
							class="form-control" id="password" placeholder="密码" value="${bean.password}">
							</div>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>重复密码：</td>
						<td>
							<div class="form-group">
							<input type="password" name="adminBean.password2"
							class="form-control" placeholder="重复密码" value="${bean.password}">
							</div>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>手机号：</td>
						<td>
							<div class="form-group">
							<input class="form-control" id="cellphone" name="adminBean.cellphone" type="text"
							id="cellphone" placeholder="手机号" value="${bean.cellphone }" />
							</div>
						</td>
						<td></td>
					</tr>	
					<tr>
						<td></td>
						<td>
							<input type="submit"
								class="btn btn-primary btn-sm" value="注册"/>
						</td>
						<td></td>
					</tr>
				</table>
				<c:if test="${bean!=null }">
						<input type="hidden" name="adminBean.id" value="${bean.id}"/>
				</c:if>
				</form>
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.js"
		type="text/javascript" charset="utf-8"></script>
	<script
		src="${pageContext.request.contextPath}/static/plugins/bootstrap/js/bootstrap.js"
		type="text/javascript" charset="utf-8"></script>
	<script
		src="${pageContext.request.contextPath}/static/plugins/jquery-validation/dist/jquery.validate.js"
		type="text/javascript" charset="utf-8"></script>

	<script type="text/javascript">
	
	$(function() {
		// 联系电话(手机)验证   
		jQuery.validator
				.addMethod(
						"isTel",
						function(value, element) {
							var length = value.length;
							var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
							return this.optional(element)
									|| (length == 11 && mobile.test(value));
						}, "请正确填写您的手机号");
		
		
		var MyValidator = function() {
			var handleSubmit = function() {
				$('.form-horizontal').validate(
						{
							errorElement : 'span',
							errorClass : 'help-block',
							focusInvalid : false,
							rules : {
								'adminBean.username' : {
									required : true,
									remote: {
										url: "${pageContext.request.contextPath}/admin/checkUsernameAdminAction",
										type: "post",
										data: {"username": function(){return $("#username").val();}}
									}
								},
								'adminBean.password' : {
									required : true
								},
								'adminBean.password2' : {
									required : true,
									equalTo: "#password"
								},
								'adminBean.cellphone' : {
									required : true,
									isTel : true
								}
							},
							messages : {
								'adminBean.username' : {
									required : "请输入用户名",
									remote : "用户名已存在"
								},
								'adminBean.password' : {
									required : "请输入密码"
								},
								'adminBean.password2' : {
									required : "请输入重复密码",
									equalTo: "两次输入的密码不一致"
								},
								'adminBean.cellphone' : {
									required : "请输入手机号码"
								}
							},

							highlight : function(element) {
								$(element).closest('.form-group').addClass(
										'has-error');
							},

							success : function(label) {
								label.closest('.form-group').removeClass(
										'has-error');
								label.remove();
							},

							errorPlacement : function(error, element) {
								element.parent('div').append(error);
							},

							submitHandler : function(form) {
								form.submit();
							}
						});

				$('.form-horizontal input').keypress(function(e) {
					if (e.which == 13) {
						if ($('.form-horizontal').validate().form()) {
							$('.form-horizontal').submit();
						}
						return false;
					}
				});
			};
			return {
				init : function() {
					handleSubmit();
				}
			};

		}();

		MyValidator.init();
	});
	</script>
</body>
</html>