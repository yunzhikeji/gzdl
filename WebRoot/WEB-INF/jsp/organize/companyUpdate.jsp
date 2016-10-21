<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>施工单位</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/html5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/respond.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${pageContext.request.contextPath }/css/datePicker1.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath }/css/build.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/1.0.7/iconfont.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript">
//校验组织名称重复
function validUsername(name) {
	var result = "no";
	var option = {
			url:"${pageContext.request.contextPath }/organize/validOrganizeName",
			type:"post",
			data:{
				name:name
			},
			dataType:"text",
			async:false,
			success:function(responseText){
				result = responseText;
			},
			error:function(){
				alert("系统错误");
			}
	};
	$.ajax(option);
	return result;
} 

$(function(){
	$("#companyForm").submit(function(){
		var isSubmit = true;
		$(this).find("[reg2]").each(function(){
			//获得输入的值
			var val = $(this).val();
			//获得正则表达式
			var reg = $(this).attr("reg2");
			//获得提示信息
			var tip = $(this).attr("tip");
			//创建正则表达式的对象
			var regExp = new RegExp(reg);
			if(!regExp.test($.trim(val))){
				isSubmit = false;
				$(this).next("span").html("<font color='red'>"+tip+"</font>");
				return false;
			}else {
				var inputName = $(this).attr("name");
				if(inputName == "name"){
					var result = validUsername(val);
					if(result == "yes"){
						$(this).next("span").html("<font color='red'>名称已经存在</font>");
						isSubmit = false;
						return false;
					}else{
						$(this).next("span").html("");
					}
				}
				
			}
		})
		return isSubmit;
	})
	
	$("#companyForm").find("[reg2]").blur(function(){
			//获得输入的值
			var val = $(this).val();
			//获得正则表达式
			var reg = $(this).attr("reg2");
			//获得提示信息
			var tip = $(this).attr("tip");
			//创建正则表达式的对象
			var regExp = new RegExp(reg);
			if(!regExp.test($.trim(val))){
				$(this).next("span").html("<font color='red'>"+tip+"</font>");
			}else {
				var inputName = $(this).attr("name");
				if(inputName == "name"){
					var result = validUsername(val);
					if(result == "yes"){
						$(this).next("span").html("<font color='red'>名称已经存在</font>");
						
						return false;
					}else{
						$(this).next("span").html("");
					}
				}
			}
		})
})

</script>
</head>

<body>
	<div class="pd-20">
		<form action="${pageContext.request.contextPath }/organize/updateOrganizeSubmit" method="post" class="form form-horizontal" id="companyForm">
			<input type="hidden" name="id" value="${organize.id }">
			<table class="table">
				<thead class="text-c">
					<tr>
						<th width="15%">*单位名称</th>
						<td width="70%" align="left"><input type="text" name="name" value="${organize.name }" class="date_picker"
							style="width: 300px" reg2="^[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$" tip="必须是中英文或数字字符，长度1-20"/><span></span></td>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c">
						<th>所属区域</th>
						<td align="left">${organize.province }${organize.city }${organize.area }</td>
					</tr>
					<tr class="text-c">
						<th>详细地址</th>
						<td align="left"><input type="text" name="address" value="${organize.address }" class="date_picker"
							style="width: 300px" /></td>
					</tr>
					<tr class="text-c">
						<th>联系人</th>
						<td align="left"><input type="text" name="contact" value="${organize.contact }" class="date_picker"
							style="width: 300px" /></td>
					</tr>
					<tr class="text-c">
						<th>联系电话</th>
						<td align="left"><input type="text" name="phone" value="${organize.phone }" class="date_picker"
							style="width: 300px" /></td>
					</tr>

					<tr class="text-c">
						<th style="background: #FFF; border: #FFF; text-align: center;"
							colspan="2">
							<input type="submit" class="button blue smallrouded Hui-iconfont" style="font-size:14px;padding:6px 10px 5px 10px;" value="&nbsp;&nbsp;&nbsp;&nbsp;&#xe632;保存&nbsp;&nbsp;&nbsp;&nbsp;"/></th>
					</tr>
				</tbody>
			</table>


		</form>

	</div>
	<script type="text/javascript">
		var Gid = document.getElementById;
		var showArea = function() {
			Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市"
					+ Gid('s_city').value + " - 县/区" + Gid('s_county').value
					+ "</h3>"
		};
		Gid('s_county').setAttribute('onchange', 'showArea()');
	</script>
</body>
</html>
