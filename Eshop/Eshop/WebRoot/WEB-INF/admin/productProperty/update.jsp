<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品属性</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/plugins/bootstrap/css/bootstrap.css" />
<script type="text/javascript">
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="view">
					<h3>修改商品属性</h3>
				</div>
				<form role="form" action="${pageContext.request.contextPath}/admin/updateProductPropertyAction" method="post" class="form-horizontal">
				<table class="table table-striped">
					<tr>
						<td>商品属性名称：</td>
						<td>
							<div class="form-group">
							<input class="form-control" id="name" name="productPropertyBean.name" type="text"
							placeholder="商品属性名称" value="${bean.name }" />
							</div>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>序号：</td>
						<td>
							<div class="form-group">
							<input name="productPropertyBean.sort"
							class="form-control" id="sort" value="${bean.sort }" placeholder="序号">
							</div>
						</td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit"
								class="btn btn-primary btn-sm" value="提交"/>
						</td>
						<td></td>
					</tr>
				</table>
				<!-- 修改时候赋值 -->
				<c:if test="${bean!=null }">
					<input type="hidden" name="productPropertyBean.id" value="${bean.id }"/>
					<input type="hidden" name="productPropertyBean.productTypeBean.id" value="${bean.productTypeBean.id }"/>
				</c:if>
				<!-- 添加时候赋值 -->
				<c:if test="${typeId>0 }">
					<input type="hidden" name="productPropertyBean.productTypeBean.id" value="${typeId }"/>
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
		var MyValidator = function() {
			var handleSubmit = function() {
				$('.form-horizontal').validate(
						{
							errorElement : 'span',
							errorClass : 'help-block',
							focusInvalid : false,
							rules : {
								'productPropertyBean.name' : {
									required : true
								},
								'productPropertyBean.sort' : {
									required : true,
									number : true
								}
							},
							messages : {
								'productPropertyBean.name' : {
									required : "请输入商品属性名称"
								},
								'productPropertyBean.sort' : {
									required : "请输入序号",
									number : "序号必须为数字"
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