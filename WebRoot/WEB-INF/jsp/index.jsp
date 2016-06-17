<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>江苏云知智能科技有限公司</title>
	<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<header style="z-index: 25;">
	<div class="tY0">
		<h1>
			<a href="https://95598.gd.csg.cn/"><img src="${pageContext.request.contextPath }/images/NFlogo.png"
				alt="广东南方电网公司网上营业厅" name="NFlogo" height="50" id="NFlogo" /></a>
		</h1>
		<ul class="tO0">
			<li class="pR0">林某某，你好！</li>
			<li class="rZ0 pn1">|</li>
			<li class="rZ0"><a href="javascript:;"
				onClick="ypgj('手机版','mobile.html','500px','300px')" title="手机版"
				target="_self" class="sh0 nui-txt-link">手机版</a></li>
			<li class="rZ0 pn1">|</li>
			<li class="rZ0"><a href="#" class="sh0 nui-txt-link">设置</a>
				<ul class="tO1">
					<li class="rZ1"><a href="#" target="_self"
						class="sh0 nui-txt-link">密码修改</a></li>
					<li class="rZ1"><a href="#" target="_self"
						class="sh0 nui-txt-link">个人资料</a></li>
				</ul></li>
			<li class="rZ0 pn1">|</li>
			<li class="rZ0"><a href="login.html" class="sh0 nui-txt-link"
				target="_self">注销</a></li>
		</ul>
	</div>
	</header>

	<div id="tab_demo" class="HuiTab">
		<div class="tabBar cl">
			<span class="tA0">分布图</span> <span class="tA0">越秀区</span> <span
				class="tA0">白云区</span> <span class="tA0">荔枝区</span> <span
				class="tA0">天河区</span> <span class="tA0">海珠区</span> <span
				class="tA0">黄埔区</span>
		</div>
		<div class="sJ0"></div>
		<div class="tabCon">
			<div class="yzrtv">
				<div class="yzrtvh">实时视频</div>
				<div class="yzrtvb">
					<object CLASSID='CLSID:B2F105BB-B7D5-44E0-9C77-C4FB6811481A'
						id='ocx' width='100%' height='100%' VIEWASTEXT>
						<img src="images/vedio.png" width="100%" height="100%" />

					</object>
				</div>
			</div>
			<div style="width: 100%; float: left;">
				<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
				<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="keywords" content="百度地图,百度地图API，百度地图自定义工具，百度地图所见即所得工具" />
<meta name="description" content="百度地图API自定义地图，帮助用户在可视化操作下生成百度地图" />
<title>百度地图API自定义地图</title>
<!--引用百度地图API-->
<style type="text/css">
html, body {
	margin: 0;
	padding: 0;
}

.iw_poi_title {
	color: #CC5522;
	font-size: 14px;
	font-weight: bold;
	overflow: hidden;
	padding-right: 13px;
	white-space: nowrap
}

.iw_poi_content {
	font: 12px arial, sans-serif;
	overflow: visible;
	padding-top: 4px;
	white-space: -moz-pre-wrap;
	word-wrap: break-word
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
</head>

<body>
	<!--百度地图容器-->
	<div
		style="width: 100%; height: 885px; box-shadow: 1px 0px 2px #000; margin-top: 1px"
		id="dituContent"></div>
</body>
<script type="text/javascript">
	//创建和初始化地图函数：
	function initMap() {
		createMap();//创建地图
		setMapEvent();//设置地图事件
		addMapControl();//向地图添加控件
		addMarker();//向地图中添加marker
	}

	//创建地图函数：
	function createMap() {
		var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
		var point = new BMap.Point(113.278257, 23.131662);//定义一个中心点坐标
		map.centerAndZoom(point, 13);//设定地图的中心点和坐标并将地图显示在地图容器中
		window.map = map;//将map变量存储在全局
	}

	//地图事件设置函数：
	function setMapEvent() {
		map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
		map.enableScrollWheelZoom();//启用地图滚轮放大缩小
		map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
		map.enableKeyboard();//启用键盘上下左右键移动地图
	}

	//地图控件添加函数：
	function addMapControl() {
		//向地图中添加缩放控件
		var ctrl_nav = new BMap.NavigationControl({
			anchor : BMAP_ANCHOR_TOP_LEFT,
			type : BMAP_NAVIGATION_CONTROL_LARGE
		});
		map.addControl(ctrl_nav);
		//向地图中添加缩略图控件
		var ctrl_ove = new BMap.OverviewMapControl({
			anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
			isOpen : 1
		});
		map.addControl(ctrl_ove);
		//向地图中添加比例尺控件
		var ctrl_sca = new BMap.ScaleControl({
			anchor : BMAP_ANCHOR_BOTTOM_LEFT
		});
		map.addControl(ctrl_sca);
	}

	//标注点数组
	var markerArr = [ {
		title : "工地一",
		content : "我的备注",
		point : "113.272365|23.137511",
		isOpen : 0,
		icon : {
			w : 21,
			h : 21,
			l : 0,
			t : 0,
			x : 6,
			lb : 5
		}
	} ];
	//创建marker
	function addMarker() {
		for (var i = 0; i < markerArr.length; i++) {
			var json = markerArr[i];
			var p0 = json.point.split("|")[0];
			var p1 = json.point.split("|")[1];
			var point = new BMap.Point(p0, p1);
			var iconImg = createIcon(json.icon);
			var marker = new BMap.Marker(point, {
				icon : iconImg
			});
			var iw = createInfoWindow(i);
			var label = new BMap.Label(json.title, {
				"offset" : new BMap.Size(json.icon.lb - json.icon.x + 10, -20)
			});
			marker.setLabel(label);
			map.addOverlay(marker);
			label.setStyle({
				borderColor : "#808080",
				color : "#333",
				cursor : "pointer"
			});

			(function() {
				var index = i;
				var _iw = createInfoWindow(i);
				var _marker = marker;
				_marker.addEventListener("click", function() {
					this.openInfoWindow(_iw);
				});
				_iw.addEventListener("open", function() {
					_marker.getLabel().hide();
				})
				_iw.addEventListener("close", function() {
					_marker.getLabel().show();
				})
				label.addEventListener("click", function() {
					_marker.openInfoWindow(_iw);
				})
				if (!!json.isOpen) {
					label.hide();
					_marker.openInfoWindow(_iw);
				}
			})()
		}
	}
	//创建InfoWindow
	function createInfoWindow(i) {
		var json = markerArr[i];
		var iw = new BMap.InfoWindow(
				"<b class='iw_poi_title' title='" + json.title + "'>"
						+ json.title + "</b><div class='iw_poi_content'>"
						+ json.content + "</div>");
		return iw;
	}
	//创建一个Icon
	function createIcon(json) {

		var icon = new BMap.Icon(
				"http://app.baidu.com/map/images/us_mk_icon.png",
				new BMap.Size(json.w, json.h), {
					imageOffset : new BMap.Size(-json.l, -json.t),
					infoWindowOffset : new BMap.Size(json.lb + 5, 1),
					offset : new BMap.Size(json.x, json.h)
				})
		return icon;
	}

	initMap();//创建和初始化地图
</script>
				</html>
			</div>
		</div>
		<div class="tabCon">
			<div style="float: left; width: 10%;">
				<ul style="margin: 0; padding: 0">
					<li class="white" role="presentation"><a href="map.html"
						target="myiframe">分布图</a></li>
					<li class="white" role="presentation"><a
						href="javascript:void(0)" id="tz" onClick="changeToTz();"
						target="myiframe">城北施工工地一</a></li>
					<li class="white" role="presentation"><a
						href="javascript:void(0)" id="td" onClick="changeToTd();"
						target="myiframe">城西施工工地三</a></li>
					<li class="white" role="presentation"><a
						href="javascript:void(0)" id="tj" onClick="changeToTj();"
						target="myiframe">城西施工工地三</a></li>
				</ul>
			</div>
			<div style="float: left; width: 90%">

				<iframe name="myiframe" src="map.html" id="iframepage"
					frameborder="0" scrolling="no" width="100%" height="883px"
					marginheight="0" marginwidth="0"></iframe>

			</div>
		</div>
		<div class="tabCon">内容三</div>
		<div class="tabCon">内容二</div>
		<div class="tabCon">内容三</div>
		<div class="tabCon">内容二</div>
		<div class="tabCon">内容三</div>
	</div>

	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/2.1/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/H-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/H-ui.admin.js"></script>
	<script type="text/javascript">
		$(function() {
			$.Huitab("#tab_demo .tabBar span", "#tab_demo .tabCon", "current",
					"click", "0");
		});

		/*修改密码*/
		function ypgj(title, url, w, h) {
			layer.open({
				type : 2,
				title : title,
				shadeClose : true,
				shade : 0.8,
				area : [ w, h ],
				content : url
			//iframe的url
			});
		}

		function changeToTj() {
			$("#iframepage").attr('src', "ws2.html");
		}

		function changeToTz() {
			$("#iframepage").attr('src', "test.html");
		}

		function changeToTd() {
			$("#iframepage").attr('src', "ws1.html");
		}
	</script>
</body>
</html>