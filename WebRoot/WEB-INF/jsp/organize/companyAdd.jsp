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
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
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

</head>

<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id=" ">
			<table class="table">
				<thead class="text-c">
					<tr>
						<th width="15%">单位名称</th>
						<td width="70%"><input type="text" class="date_picker"
							style="width: 400px" /></td>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c">
						<th rowspan="3">所属区域</th>
						<td style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker" id="s_province" name="s_province"
							style="width: 420px"></select></td>
					</tr>
					<tr class="text-c">
						<td style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker" id="s_city" name="s_city"
							style="width: 420px"></select></td>
					</tr>
					<tr class="text-c">
						<td style="padding-top: 7px; padding-bottom: 7px;"><select
							class="date_picker1" id="s_county" name="s_county"
							style="width: 420px"></select></td>

						<script class="resources library" src="js/area.js"
							type="text/javascript"></script>

						<script type="text/javascript">
							_init_area();
						</script>
						<span id="show"></span>
					</tr>
					<tr class="text-c">
						<th>联系人</th>
						<td><input type="text" class="date_picker"
							style="width: 400px" /></td>
					</tr>
					<tr class="text-c">
						<th>联系电话</th>
						<td><input type="text" class="date_picker"
							style="width: 400px" /></td>
					</tr>

					<tr class="text-c">
						<th style="background: #FFF; border: #FFF; text-align: center;"
							colspan="2"><a id="save" href="#"
							class="button orange smallrouded"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe66c;</i>重置&nbsp;&nbsp;&nbsp;</a>
							<a id="save" href="#" class="button blue smallrouded"><i
								class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe632;</i>保存&nbsp;&nbsp;&nbsp;</a></th>
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
