<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>map</title>
<link href="${pageContext.request.contextPath }/css/map.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/mapfiles/mapapi_3.12.9.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/map_helper.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/popup_layer.js"></script>
<script type="text/javascript">
	var lng = null
	var lat = null;
	if ("${site.lng}" != "") {
		lng = "${site.lng}";// 经度
	}
	if ("${site.lng}" != "") {
		lat = "${site.lat}";// 维度
	}
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/main.js"></script>
<body>
	当前级别
	<input type="text" name="ZOOM" id="ZOOM" value="8" size="17" /> 经度
	<input type="text" name="CLNG" id="CLNG" value="" size="17" />&nbsp;&nbsp;
	维度
	<input type="text" name="CLAT" id="CLAT" value="" size="17" />
	<div class="yzrtv">
		<div class="yzrtvh">工具栏</div>
		<div class="yzrtvb">
			<button class="button white" onclick="ClearPoly()"
				style="box-shadow: 1px 2px 1px 1px rgba(51, 51, 51, 0.3);">
				<i class="iconfont">&nbsp;&#xe609;&nbsp;</i>浏览地图
			</button>
			<button class="button white" id="addsite" onclick="addSite()"
				style="margin: 5px 16px; box-shadow: 1px 2px 1px 1px rgba(51, 51, 51, 0.3);">
				<i class="iconfont">&nbsp;&#xe613;&nbsp;</i>添加工地
			</button>
			<button class="button white" id="delsite" onclick="deleteSite()"
				style="box-shadow: 1px 2px 1px 1px rgba(51, 51, 51, 0.3);">
				<i class="iconfont">&nbsp;&#xe612;&nbsp;</i>删除工地
			</button>
		</div>
	</div>
	<div id="map_canvas"
		style="margin: 0 auto; width: 100%; height: 862px;">地图加载失败....</div>
</body>
</html>
