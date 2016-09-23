<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>设备入库</title>
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
		<form action="${pageContext.request.contextPath }/camera/addCamera"
			method="post" class="form form-horizontal" id="cameraForm ">
			<table class="table">
				<thead class="text-c">
					<tr>
						<th width="15%">设备编号</th>
						<td width="70%"><input type="text" name="cnumber" class="date_picker"
							style="width: 400px" value="" /></td>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c">
						<th>设备名称</th>
						<td><input type="text" name="cname" class="date_picker"
							style="width: 400px" /></td>
					</tr>
					<tr class="text-c">
						<th>SIP编号</th>
						<td><input type="text" name="sipid" class="date_picker"
							style="width: 400px" /></td>
					</tr>
					<tr class="text-c">
						<th style="background: #FFF; border: #FFF; text-align: center;"
							colspan="2"><a id="save" href="#"
							class="button orange smallrouded"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe66c;</i>重置&nbsp;&nbsp;&nbsp;</a>
							<input type="submit" class="button blue smallrouded Hui-iconfont"
							style="font-size: 14px; padding: 6px 10px 5px 10px;"
							value="&nbsp;&nbsp;&nbsp;&nbsp;&#xe632;保存&nbsp;&nbsp;&nbsp;&nbsp;" /></th>
					</tr>
				</tbody>
			</table>


		</form>

	</div>


</body>
</html>