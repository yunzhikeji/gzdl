<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>监控系统</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/test.css" />
<link
	href="${pageContext.request.contextPath }/css/Aliiconfont/iconfont.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
	var ocx = null;

	function login() {
		if (ocx == null) {
			ocx = document.getElementById("ocx");
		}
		var backLogin = ocx.Login("test", "test", "115.28.135.48", 20000);
		if (backLogin != 0) {
			alert("登录超时");
		}

	}
	function play() {
		if (ocx == null) {
			ocx = document.getElementById("ocx");
		}
		var backPlay = ocx.PlayVideo("44030300001320020239");
		if (backPlay != 0) {
			alert("操作失败");
		}

	}
	function stop() {
		if (ocx == null) {
			ocx = document.getElementById("ocx");
		}
		var backStop = ocx.StopRealplay();
		if (backStop != 0) {
			alert("操作失败");
		}
	}

	function up() {
		if (ocx == null) {
			ocx = document.getElementById("ocx");
		}
		var backUp = ocx.PTZCtrl(3);
		if (backUp != 0) {
			alert("操作失败");
		}
	}

	function down() {
		if (ocx == null) {
			ocx = document.getElementById("ocx");
		}
		var backDown = ocx.PTZCtrl(4);
		if (backDown != 0) {
			alert("操作失败");
		}
	}

	function left() {
		if (ocx == null) {
			ocx = document.getElementById("ocx");
		}
		var backLeft = ocx.PTZCtrl(1);
		if (backLeft != 0) {
			alert("操作失败");
		}
	}

	function right() {
		if (ocx == null) {
			ocx = document.getElementById("ocx");
		}
		var backRight = ocx.PTZCtrl(2);
		if (backRight != 0) {
			alert("操作失败");
		}
	}

	function ptzStop() {
		if (ocx == null) {
			ocx = document.getElementById("ocx");
		}
		var backPTZStop = ocx.PTZCtrl(0);
		if (backPTZStop != 0) {
			alert("操作失败");
		}
	}

	function showCls1() {
		$(".cls1").show();
	}

	function showCls2() {
		$(".cls2").show();
	}

	function checkAndLogin() {

		try {
			var ax = new ActiveXObject("YRCLOUDCLIENT.YRCloudClientCtrl.1");
			login();
		} catch (e) {
			alert("请下载安装activeX控件。");
		}
	}

	$(document).ready(function() {
		$(".btn1").click(function() {
			$("table").show();
			setTimeout("showCls1()", 3000);
			setTimeout("showCls2()", 5000);
		});

		$(".btn2").click(function() {
			$("table").show();
		});

	});
</script>
</head>
<body onload="checkAndLogin()">
	<div style="width: 100%; position: relative;">
		<div class="yzrtv">
			<span style="font-weight: bold;">工地名称：</span><span>${siteCustom.name}</span>
			<span style="font-weight: bold;">工地位置：</span><span>${siteCustom.areaname }</span>
			<span>||</span>
			<c:if test="${camera!=null }">
				<span style="font-weight: bold;">设备工作状态：</span>
				<span> <c:if test="${camera.status==-1}">未知状态</c:if> <c:if
						test="${camera.status == 0 }">关机</c:if> <c:if
						test="${camera.status == 1 }">正常工作</c:if> <c:if
						test="${camera.status == 2 }">重启</c:if>
				</span>
				<span style="font-weight: bold;">设备电压：</span>
				<span>${camera.voltage}伏</span>
				<span style="font-weight: bold;">设备温度：</span>
				<span>${camera.temperature}度</span>
			</c:if>
			<c:if test="${camera==null }">当前没有设备接入</c:if>
		</div>
		<div class="yzvedio0">
			<div
				style="width: 80%; margin-top: 5px; margin-left: 1%; height: 500px;">
				<object CLASSID='CLSID:7A2B1F67-6568-4466-A0A5-EA7FF6EBE820'
					id='ocx' style="margin-left: 1%; margin-right: 10%;" width='100%'
					height='100%' VIEWASTEXT> </object>
			</div>
			<div style="margin-left: 1%; margin-top: 5px;">
				<a href="#" class="button white" onClick="play();"> <i
					class="iconfont">&nbsp;&nbsp;&#xe624;&nbsp;&nbsp;</a> <a href="#"
					class="button white" onClick="stop();"><i class="iconfont">&nbsp;&nbsp;&#xe625;&nbsp;&nbsp;</a>

				<a href="#" class="button1 white" onClick="up();"><i
					class="iconfont">&nbsp;&#xe623;&nbsp;</i></a> <a href="#"
					class="button1 white" onClick="down();"><i class="iconfont">&nbsp;&#xe603;&nbsp;</i></a>

				<a href="#" class="button1 white" onClick="left();"><i
					class="iconfont">&nbsp;&#xe604;&nbsp;</i></a> <a href="#"
					class="button1 white" onClick="right();"><i class="iconfont">&nbsp;&#xe622;&nbsp;</i></a>

				<a href="#" class="button white" onClick="ptzStop();">停止控制</a>

			</div>
		</div>
		<div style="width: 100%; height: 200px;">
			<div class="yzrl">
				<label style="font-weight: bold;">人脸检测：</label> <input
					type="checkbox" checked="checked" />
				<button class="blue smallrouded btn1">手动</button>
				<input type="checkbox" />
				<button class="white smallrouded btn2">自动</button>
			</div>
			<div class="yzr2">
				<table class="tt" width="100%" border="0" cellspacing="1"
					cellpadding="0" align="left">
					<tr>
						<td colspan="5" style="font-weight: bold;">开始人脸检测...</td>
					</tr>
					<tr>
						<td>序号</td>
						<td>人员编号</td>
						<td>姓名</td>
						<td>检测结果</td>
						<td>身份信息</td>
					</tr>
					<tr class="nopass">
						<td class="cls1" style="display: none">1</td>
						<td class="cls1" style="display: none">12345678</td>
						<td class="cls1" style="display: none">林某</td>
						<td class="cls1" style="display: none">不通过</td>
						<td class="cls1" style="display: none">非法人员</td>
					</tr>
					<tr class="pass">
						<td class="cls2" style="display: none">2</td>
						<td class="cls2" style="display: none">987654321</td>
						<td class="cls2" style="display: none">王某</td>
						<td class="cls2" style="display: none">通过</td>
						<td class="cls2" style="display: none">技术员</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</body>
</html>
