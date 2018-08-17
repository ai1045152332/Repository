<%@page import="com.shop.util.Constants"%>
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
			<div class="col-xs-12">
				<div class="view">
					<h3>修改商品</h3>
				</div>
				<form role="form" action="${pageContext.request.contextPath}/admin/updateProductAction" method="post" class="form-horizontal" enctype="multipart/form-data">
				<table class="table table-striped">
					<tr>
						<td>商品名称：</td>
						<td>
							<div class="form-group">
							<input class="form-control" id="name" name="productBean.name" type="text"
							placeholder="商品名称" value="${bean.name }"/>
							</div>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>价格：</td>
						<td>
							<div class="form-group">
							<input class="form-control" id="cellphone" name="productBean.price" type="text"
							placeholder="价格" value="${bean.price }"/>
							</div>
						</td>
						<td></td>
					</tr>	
					<tr>
						<td>原价：</td>
						<td>
							<div class="form-group">
							<input class="form-control" id="cellphone" name="productBean.originalPrice" type="text"
							placeholder="原价" value="${bean.originalPrice }"/>
							</div>
						</td>
						<td></td>
					</tr>	
					<tr>
						<td>数量：</td>
						<td>
							<div class="form-group">
							<input class="form-control" id="cellphone" name="productBean.num" type="text"
								placeholder="数量" value="${bean.num }"/>
							</div>
						</td>
						<td></td>
					</tr>	
					<tr>
						<td>图片：</td>
						<td>
							<div class="form-group">
							<input class="form-control" name="pic" type="file"/>
							<c:if test="${bean!=null&&bean.pic!=null }">
								<img width="50px" height="50px" alt="" src="<%=Constants.SHOW_PRODUCT_LIST %>${bean.pic }" />
							</c:if>
							</div>
						</td>
						<td></td>
					</tr>	
					<tr>
						<td>商品类型：</td>
						<td>
							<div class="form-group">
							<select onchange="getProperties(this)" name="productBean.productTypeBean.id"  class="form-control">
								<option value="0">请选择</option>
								<c:forEach items="${productTypeBeans }" var="type">
									<c:choose>
										<c:when test="${type.id==bean.productTypeBean.id }">
											<option selected="selected" value="${type.id }">${type.name }</option>
										</c:when>
										<c:otherwise>
											<option value="${type.id }">${type.name }</option>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</select>
							</div>
						</td>
						<td></td>
					</tr>
					<c:if test="${bean.id>0 }">
					<tr id='propertiesValue'><td>商品属性</td>
						<td>
							<!-- 属性信息 -->
							<c:forEach items="${propertyBeans }" var="property">
								${property.name }
								<c:forEach items="${propertyToOption }" var="toOption">
									<c:if test="${property.id==toOption.key }">
										<c:set var="optionValue" value="${toOption.value }"></c:set>
									</c:if>
								</c:forEach>
								
								<select name="properties">
									<c:forEach items="${property.productPropertyOptionBeans }" var="option">
										<c:choose>
											<c:when test="${option.id==optionValue }">
												<option selected="selected" value="${property.id }-${property.name}-${option.id}-${option.name}">${option.name }</option>
											</c:when>
											<c:otherwise>
												<option value="${property.id }-${property.name}-${option.id}-${option.name}">${option.name }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</c:forEach>
							
						</td>
						<td></td>
					</tr>
					</c:if>
					<tr>
						<td>商品介绍：</td>
						<td>
							<div class="form-group">
								<textarea name="productBean.intro" rows="" cols="">${bean.intro }</textarea>
							</div>
						</td>
						<td></td>
					</tr>	
					<tr>
						<td></td>
						<td>
							<input type="submit"
								class="btn btn-primary btn-sm" value="提交"/>
						<td></td>
					</tr>
				</table>
				<c:if test="${bean!=null }">
					<input type="hidden" name="productBean.id" value="${bean.id }"/>
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
	function getProperties(obj) {
		var id = parseInt(obj.value);
		if(id>0) {
			var trTmp = document.getElementById("propertiesValue");
			if(trTmp!=null) {
				trTmp.parentNode.removeChild(trTmp);
			}
			
			//ajax从后台获取数据
			$.post("${pageContext.request.contextPath}/admin/getPropertiesProductAction",{"typeId":id},function(data){
				var tr = obj.parentNode.parentNode.parentNode;
				var content = "<tr id='propertiesValue'><td>商品属性</td><td>";
				for(var i=0; i<data.length; i++) {
					content += data[i].name+":<select name='properties'>";
					for(var j=0; j<data[i].productPropertyOptionBeans.length; j++) {
						content += "<option value='"+data[i].id+"-"+data[i].name+"-"+data[i].productPropertyOptionBeans[j].id+"-"+data[i].productPropertyOptionBeans[j].name+"'>"+data[i].productPropertyOptionBeans[j].name+"</option>";
					}
					content += "</select>";
				}
				content += "</td><td></td></tr>"
				$(tr).after(content);
			},"json");
		}
	}
	
	
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
								'productBean.name' : {
									required : true
								},
								'productBean.price' : {
									required : true,
									number:true
								},
								'productBean.originalPrice' : {
									required : true,
									number:true 
								},
								'productBean.num' : {
									required : true,
									digits:true
								},
								'productBean.productTypeBean.id' : {
									digits:true,
									min:1
								},
								'productBean.intro' : {
									required : true
								}
							},
							messages : {
								'productBean.name' : {
									required : "请输入商品名称"
								},
								'productBean.price' : {
									required : "请输入价格",
									number:"价格必须为数字"
								},
								'productBean.originalPrice' : {
									required : "请输入原价",
									number:"原价必须为数字"
								},
								'productBean.num' : {
									required : "请输入商品数量",
									digits:"数量必须为数字"
								},
								'productBean.productTypeBean.id' : {
									digits:true,
									min:"请选择商品类型"
								},
								'productBean.intro' : {
									required : "请输入商品介绍"
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