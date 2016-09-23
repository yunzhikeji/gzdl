<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>供电局</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="textz/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
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
var province = ${organize.province };
var s_province = $("#s_province");
console.log(s_province);
document.getElementById("s_province")[province].selected= true;  

$("#s_province option[value=province]").attr("selected",selected);
</script>
</head>

<body>
	<div class="pd-20">
		<form action="${pageContext.request.contextPath }/organize/updateOrganizeSubmit" method="post" class="form form-horizontal" id="govermentForm">
			<table class="table">
				<thead class="text-c">
					<tr>
						<th width="15%">单位名称</th>
						<td width="70%"><input type="text" name="name" value="${organize.name }" class="date_picker"
							style="width: 400px" /></td>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c">
						<th rowspan="3">所属区域</th>
						<td style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker" id="s_province" name="province"
							style="width: 420px" value="${organize.province }"></select></td>
					</tr>
					<tr class="text-c">
						<td style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker" id="s_city" name="city"
							style="width: 420px" value="${organize.city }"></select></td>
					</tr>
					<tr class="text-c">
						<td style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker1" id="s_county" name="area"
							style="width: 420px" value=${organize.area } ></select></td>

						<script class="resources library"
							src="${pageContext.request.contextPath }/js/area.js"
							type="text/javascript"></script>

						<script type="text/javascript">
							_init_area();
						</script>
						<span id="show"></span>
					</tr>
					<tr class="text-c">
						<th>详细地址</th>
						<td><input type="text" name="address" class="date_picker"
							style="width: 400px" /></td>
					</tr>
					<tr class="text-c">
						<th>联系人</th>
						<td><input type="text" name="contact" class="date_picker"
							style="width: 400px" /></td>
					</tr>
					<tr class="text-c">
						<th>联系电话</th>
						<td><input type="text" name="phone" class="date_picker"
							style="width: 400px" /></td>
					</tr>
					<tr class="text-c">
						<th>组织级别</th>
						<td><input name="level" type="radio" value="1" checked="checked" />
							省级&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
							name="level" type="radio" value="2" />
							市级&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							name="level" type="radio" value="3" /> 区级</td>
					</tr>

					<tr class="text-c">
						<th style="background: #FFF; border: #FFF; text-align: center;"
							colspan="2"><a id="save" href="#"
							class="button orange smallrouded"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe66c;</i>重置&nbsp;&nbsp;&nbsp;</a>
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
		}
		Gid('s_county').setAttribute('onchange', 'showArea()');
	</script>
</body>
</html>
