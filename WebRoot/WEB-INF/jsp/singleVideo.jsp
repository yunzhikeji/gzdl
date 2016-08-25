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
<Script language="javascript">
	function GetRequest() {
		var url = location.search; //获取url中"?"符后的字串 
		var theRequest = new Object();
		if (url.indexOf("?") != -1) {
			var str = url.substr(1);
			strs = str.split("&");
			for (var i = 0; i < strs.length; i++) {
				theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
			}
		}
		return theRequest;
	}
</Script>
<script type="text/javascript">
	var Request = new Object();
	Request = GetRequest();
	var id;
	id = Request['id'];
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
		var backPlay = ocx.PlayVideo("44030300001320020246");
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

	function addcamera() {
		$
				.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath }/facecloud/addcameratocloud.action',
					//请求是key/value这里不需要指定contentType，因为默认就 是key/value类型
					//contentType:'application/json;charset=utf-8',
					//数据格式是json串，商品信息
					data : 'id=' + id,
					success : function(data) {//返回json结果
					 	alert(data);
					}

				});

	}
	
	function video()
	{
		alert("暂无视频");
	}
	
	function sayTo()
	{
		alert("开始对讲");
		
		$("#says").val("正在对讲....");
	}
	
	function recordToWeb()
	{
		alert("录制失败，未配置云存储服务器.");
		$("#check").attr("checked",false);
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
		setInterval("alarms()", 3000);
	});

	function alarms() {
		$.ajax({
			type : 'post',
			url : '${pageContext.request.contextPath }/facecloud/getalarmvos',
			//请求是key/value这里不需要指定contentType，因为默认就 是key/value类型
			//contentType:'application/json;charset=utf-8',
			//数据格式是json串，商品信息
			data : 'id=' + id,
			success : function(data) {//返回json结果
				if(data!=null)
					{
					
					console.log(data);
					var obj = data;
					var tbody = $('<tbody></tbody>');
					$(obj).each(
							function(index) {
								var val = obj[index];
								var tr = $('<tr></tr>');
								tr.append('<td>' + val.alarm_id + '</td>' + '<td>'
										+ val.camera_name + '</td>' + '<td>'
										+ val.alarm_time + '</td>' + '<td>'
										+ val.alarm_typename + '</td>');
								tbody.append(tr);
							});
					$('#myTable tbody').replaceWith(tbody);
					
					}
			}

		});
	}
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
				<input type="button" onclick="addcamera()" value="开始布控" />
				<input type="button" onclick="sayTo();" value="开始对讲"  id="says"/>
				<input type="button" onclick="recordToWeb();" value="手动录制"  id="record"/>
				<input type="checkbox" id="check" onclick="recordToWeb();" >报警时录制</input>
				<input type="button" onclick="video();" value="录像回放"  />
			</div>
			<div class="yzr2">
				<table id="myTable" class="tt" width="100%" border="0"
					cellspacing="1" cellpadding="0" align="left">
					<thead>
						<tr>
							<td>告警ID</td>
							<td>相机名称</td>
							<td>告警时间</td>
							<td>人员类别</td>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>
