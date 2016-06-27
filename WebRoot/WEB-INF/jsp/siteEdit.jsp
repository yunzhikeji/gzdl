<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>工地管理</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
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
			action="${pageContext.request.contextPath }/site/editSiteSubmit.action"
			method="post" class="form form-horizontal" id="siteForm">
			<input type="hidden" name="id" value="${site.id }" />
			<table class="table">
				<thead class="text-c">
					<tr>
						<th colspan="2">工地名称</th>
						<td width="70%"><input type="text" name="name"
							value="${site.name }" /></td>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c">
						<th width="15%" rowspan="2">工地位置</th>
						<td width="15%" class="text-b">经度</td>
						<td><input type="text" name="lng" value="${site.lng }" /></td>
					</tr>
					<tr class="text-c">
						<td class="text-b">纬度</td>
						<td><input type="text" name="lat" value="${site.lat }" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">监控视频显示个数</th>
						<td><input name="viewstyle" type="radio" value="1" />1 <input
							name="vd" type="radio" value="4" checked="checked" />4 <input
							name="vd" type="radio" value="6" />6</td>
					</tr>
					<tr class="text-c">
						<th colspan="2">承包单位</th>
						<td><input type="text" width="300px" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">承包单位负责人</th>
						<td><input type="text" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">联系电话号码</th>
						<td><input type="text" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">监理单位</th>
						<td><input type="text" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">监理单位负责人</th>
						<td><input type="text" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">联系电话号码</th>
						<td><input type="text" /></td>
					</tr>
					<tr class="text-c">
						<th style="background: #FFF; border: #FFF; text-align: center;"
							colspan="3"><a id="save" href="#"
							class="button orange smallrouded"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe66c;</i>重置&nbsp;&nbsp;&nbsp;</a>
							<input class="button blue smallrouded" tabindex="6" type="submit"
							value="保&nbsp;&nbsp;存" /></th>
					</tr>
				</tbody>
			</table>


		</form>

	</div>
</body>
</html>
