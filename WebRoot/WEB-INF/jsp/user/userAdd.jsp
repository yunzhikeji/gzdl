<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>添加用户</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.2.js"></script>
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
<script type="text/javascript">
$(function(){
	$("#userForm").submit(function(){
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
				if(inputName == "username"){
					var result = validUsername(val);
					if(result == "yes"){
						$(this).next("span").html("<font color='red'>用户名已经存在</font>");
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
	
	$("#userForm").find("[reg2]").blur(function(){
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
				if(inputName == "username"){
					var result = validUsername(val);
					if(result == "yes"){
						$(this).next("span").html("<font color='red'>用户名已经存在</font>");
						isSubmit = false;
						return false;
					}else{
						$(this).next("span").html("");
					}
				}
			}
		})
})
//校验username重复
 function validUsername(username) {
	var result = "no";
	var option = {
			url:"${pageContext.request.contextPath }/user/validUsername",
			type:"post",
			date:{
				username:username
			},
			dateType:"text",
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
</script>
</head>

<body>
	<div class="pd-20">
		<form action="${pageContext.request.contextPath }/user/addUser"
			method="post" class="form form-horizontal" id="userForm">
			<table class="table">
				<thead class="text-c">
					<tr>
						<th>用户名</th>
						<td align="left" style="padding-top: 7px; padding-bottom: 7px;"><input
							type="text" name="username" class="date_picker"
							style="width: 320px" reg2="^[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$"
							tip="必须是中英文或数字字符，长度1-20" /><span></span></td>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c">
						<th>所属组织类型</th>
						<td align="left" style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker" id="organizeType" name="organizeType"
							style="width: 320px">
								<option value="1">供电局</option>
								<option value="2">施工单位</option>
							</select></td>
					</tr>
					<tr class="text-c">
						<th rowspan="3">所属区域</th>
						<td align="left" style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker" id="s_province" name="province"
							style="width: 320px"></select></td>
					</tr>
					<tr class="text-c">
						<td align="left" style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker" id="s_city" name="city" style="width: 320px"></select></td>
					</tr>
					<tr class="text-c">
						<td align="left" style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker1" id="s_county" name="area"
							style="width: 320px"></select></td>

						<script class="resources library"
							src="${pageContext.request.contextPath }/js/area.js"
							type="text/javascript"></script>

						<script type="text/javascript">
							_init_area();
						</script>
						<span id="show"></span>
					</tr>
					<tr class="text-c">
						<th>所属机构</th>
						<td align="left"><select class="date_picker"
							style="width: 320px" id="selOrg" name="organizeName">
								<option>请选择所属机构</option>
						</select></td>
					</tr>
					<tr>
						<th colspan="2" align="center" valign="middle"
							style="background: #FFF; border: 0; text-align: right; font-size: 12px; line-height: 15px; font-weight: lighter;"><span
							style="font-size: 20px; color: #F00;">*</span><span>初始密码为1234</span></th>
					</tr>
					<tr class="text-c">
						<th style="background: #FFF; border: #FFF; text-align: center;"
							colspan="2"><a
							href="${pageContext.request.contextPath }/user/queryUsers"
							class="button orange smallrouded"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe66c;</i>返回&nbsp;&nbsp;&nbsp;</a>
							<input type="submit" class="button blue smallrouded Hui-iconfont"
							style="font-size: 14px; padding: 6px 10px 5px 10px;"
							value="&nbsp;&nbsp;&nbsp;&nbsp;&#xe632;保存&nbsp;&nbsp;&nbsp;&nbsp;" />
					</tr>
				</tbody>
			</table>


		</form>

	</div>
	<script>
		var unSelected = "#999";
		var selected = "#333";
		var selOrg = $("#selOrg");
		var s_province = $("#s_province").val();
		$(function() {
			$("select").css("color", unSelected);
			$("option").css("color", selected);
			$("select").change(function() {
				var selItem = $(this).val();
				if (selItem == $(this).find('option:first').val()) {
					$(this).css("color", unSelected);
				} else {
					$(this).css("color", selected);
				}
			});

			$("#selOrg")
					.focus(
							function() {

								var s_province = $("#s_province").val();
								var s_city = $("#s_city").val();
								var s_county = $("#s_county").val();
								var organizeType = $("#organizeType").val();
								
								var data = 'province=' + s_province + '&city='
										+ s_city + '&area=' + s_county
										+ '&type=' + organizeType;

								$
										.ajax({
											type : 'post',
											url : '${pageContext.request.contextPath }/organize/getOrganizes.action',
											data : data,
											dataType : 'json',
											success : function(data) {//返回json结果

												console.log(data);

												var options = "<option>请选择所属机构</option>";

												for (var i = 0; i < data.length; i++) {
													options = options
															+ "+<option>"
															+ data[i].name
															+ "</option>"
												}

												$("#selOrg").html(options);

											}

										});
							});

		})
		
		
		function showUserParam()
		{
			var organizeType = $("#organizeType").val();
			if(organizeType==1)
			{
				
			}
			
		}
	</script>

</body>
</html>
