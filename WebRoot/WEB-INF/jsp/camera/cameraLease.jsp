<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>设备租售</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.2.js"></script>
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

</head>

<body>
	<div class="pd-20">
		<form
			action="${pageContext.request.contextPath }/camera/updateCameraLease"
			method="post" class="form form-horizontal" id="cameraForm ">
			<input type="hidden" name="id" value="${camera.id }">
			<table class="table">
				<thead class="text-c">
					<tr>
						<th width="15%">设备编号</th>
						<td width="70%">${camera.cnumber }</td>
					</tr>
				</thead>
				<tbody>
				<tr class="text-c">
						<th>设备名称</th>
						<td>
						${camera.cname }
							
							</td>
					</tr>
					<tr class="text-c">
						<th>SIP编号</th>
						<td>
						${camera.sipid }
							
							</td>
					</tr>
					<tr class="text-c">
						<th rowspan="3">所属区域</th>
						<td style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker" id="s_province" name="province"
							style="width: 420px"></select></td>
					</tr>
					<tr class="text-c">
						<td style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker" id="s_city" name="city"
							style="width: 420px"></select></td>
					</tr>
					<tr class="text-c">
						<td style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker1" id="s_county" name="area"
							style="width: 420px"></select></td>

						<script class="resources library" src="${pageContext.request.contextPath }/js/area.js"
							type="text/javascript"></script>

						<script type="text/javascript">
							_init_area();
						</script>
						<span id="show"></span>
					</tr>
					<tr class="text-c">
						<th>出租至</th>
						<td><select class="date_picker" style="width: 420px" id="selOrg" name="organizeName">
								<option>回收(未出租)</option>
						</select></td>
					</tr>
					<tr class="text-c">
						<th style="background: #FFF; border: #FFF; text-align: center;"
							colspan="2"><input type="submit"
							class="button blue smallrouded Hui-iconfont"
							style="font-size: 14px; padding: 6px 10px 5px 10px;"
							value="&nbsp;&nbsp;&nbsp;&nbsp;&#xe632;保存&nbsp;&nbsp;&nbsp;&nbsp;" /></th>
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
		console.log(s_province);
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
			
			$("#selOrg").focus(function(){
				
				var s_province = $("#s_province").val();
				var s_city = $("#s_city").val();
				var s_county = $("#s_county").val();
				
				var data  = 'province='+s_province+'&city='+s_city+'&area='+s_county+'&type='+2;
				
				 $.ajax({
			            type:'post',
			            url:'${pageContext.request.contextPath }/organize/getOrganizes.action',
			            data:data,
			            dataType:'json',
			            success: function (data) {//返回json结果
							
			            	console.log(data);
			            	
			            	var options = "<option>回收(未出租)</option>";
			            	
			            	for(var i=0;i<data.length;i++){   
			            	  	options = options+"+<option>" + data[i].name + "</option>"
			            	} 
			            	
			            	$("#selOrg").html(options);
			            	
			            }

			        });
			});
			
		})
		
		
		
		
		
		
	</script>

</body>
</html>