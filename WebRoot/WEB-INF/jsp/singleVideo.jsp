<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="0">
<title>监控系统</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/html5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/respond.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/PIE_IE678.js"></script>
<![endif]-->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/test.css" />
<link
	href="${pageContext.request.contextPath }/css/Aliiconfont/iconfont.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.2.js"></script>
<Script type="text/javascript">
	$(document).ready(function() {
		setInterval("alarms()", 3000);
	});

	var id = '${camera.id}';

	function login() {
		var ocx = document.getElementById("ocx");
		var backLogin = ocx.Login("test", "test", "183.61.108.89", 20000);
		if (backLogin != 0) {
			alert("登录超时");
		}

	}
	function play() {
		var ocx = document.getElementById("ocx");
		var backPlay = ocx.PlayVideo("${camera.sipid}");
		if (backPlay != 0) {
			alert("操作失败");
		}

	}
	function stop() {
		var ocx = document.getElementById("ocx");
		var backStop = ocx.StopRealplay();
		if (backStop != 0) {
			alert("操作失败");
		}
	}

	function up() {
		var ocx = document.getElementById("ocx");
		var backUp = ocx.PTZCtrl(3);
		if (backUp != 0) {
			alert("操作失败");
		}
	}

	function down() {
		var ocx = document.getElementById("ocx");
		var backDown = ocx.PTZCtrl(4);
		if (backDown != 0) {
			alert("操作失败");
		}
	}

	function big() {
		var ocx = document.getElementById("ocx");
		var backUp = ocx.PTZCtrl(5);
		if (backUp != 0) {
			alert("操作失败");
		}
	}

	function small() {
		var ocx = document.getElementById("ocx");
		var backDown = ocx.PTZCtrl(6);
		if (backDown != 0) {
			alert("操作失败");
		}
	}

	function left() {
		var ocx = document.getElementById("ocx");
		var backLeft = ocx.PTZCtrl(1);
		if (backLeft != 0) {
			alert("操作失败");
		}
	}

	function right() {
		var ocx = document.getElementById("ocx");
		var backRight = ocx.PTZCtrl(2);
		if (backRight != 0) {
			alert("操作失败");
		}
	}

	function addcamera() {
		$
				.ajax({
					url : '${pageContext.request.contextPath }/facecloud/addcameratocloud.action',//这里是你的action或者servlert的路径地址   
					type : 'post', //数据发送方式   
					dataType : 'json',
					async : true,
					data : {
						"id" : id
					},
					error : function(msg) {
						console.log(msg);
					},
					success : function(msg) {
						if (msg != null) {
							alert(msg.message);
							errorCode = msg.errorCode;
						}
					}
				});
	}

	function video() {
		alert("暂无视频");
	}

	function sayTo() {
		alert("开始对讲");

		$("#says").val("正在对讲....");
	}

	function recordToWeb() {
		alert("录制失败，未配置云存储服务器.");
		$("#check").attr("checked", false);
	}

	function ptzStop() {
		var ocx = document.getElementById("ocx");
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

	var errorCode = 1;

	function alarms() {

		if (errorCode == 1) {
			$
					.ajax({
						type : 'post',
						url : '${pageContext.request.contextPath }/facecloud/getalarmvos',
						dataType : 'json',
						data : 'id=' + id,
						success : function(msg) {

							errorCode = msg.errorCode;

							if (msg.alarmMessages != null) {
								errorCode = 1;
								var tbody = "";
								for (var i = 0; i < msg.alarmMessages.length; i++) {
									var val = msg.alarmMessages[i];

									var person_name = val.person_name;

									var url = "";

									if (person_name == null) {
										person_name = "无";
										url = "${pageContext.request.contextPath }/facecloud/faceimage?photo_name="
												+ val.photo_name
												+ "&host_id="
												+ val.photo_host_id;
									}
									url = "${pageContext.request.contextPath }/facecloud/faceimage?photo_name="
											+ val.photo_name
											+ "&host_id="
											+ val.photo_host_id;

									tbody = tbody
											+ "<tr>"
											+ "<td>"
											+ val.alarm_id
											+ "</td>"
											+ "<td>"
											+ val.camera_name
											+ "</td>"
											+ "<td>"
											+ val.alarm_time
											+ "</td>"
											+ "<td>"
											+ val.alarm_typename
											+ "</td>"
											+ "<td>"
											+ person_name
											+ "</td><td> <a href='"+url+"' target='_blank' > <img src='${pageContext.request.contextPath }/images/img.png' /></a></td>"
											+ "</tr>";
								}
								$("#tbody").empty()
								$("#tbody").append(tbody);
							} else {
								if (msg.message != null) {
									alert(msg.message);
									errorCode = 0;
								} else {
									alert("当前无告警记录");
									errorCode = 0;
								}
							}
						}

					});
		}

	}
</script>
</head>
<body onload="checkAndLogin()">
	<div style="width: 100%; position: relative;">
		<div
			style="margin-left: 1%; margin-top: 5px; font-family: 'Microsoft Yahei', verdana; color: #666; font-weight: bold">
			设备编号:${camera.cnumber}</div>
		<div class="yzvedio00">
			<div
				style="width: 90%; margin-top: 5px; margin-left: 1%; height: 500px;">
				<object CLASSID="CLSID:7A2B1F67-6568-4466-A0A5-EA7FF6EBE820"
					id="ocx" style="margin-right: 10%;" width="100%" height="100%"
					VIEWASTEXT> </object>
			</div>
			<div style="margin-left: 1%; margin-top: 5px;">
				<a href="#" class="button white" onClick="play();"> <i
					class="iconfont">&nbsp;&nbsp;&#xe624;&nbsp;&nbsp;</i></a> <a href="#"
					class="button white" onClick="stop();"><i class="iconfont">&nbsp;&nbsp;&#xe625;&nbsp;&nbsp;</i></a>

				<a href="#" class="button1 white" onClick="up();"><i
					class="iconfont">&nbsp;&#xe623;&nbsp;</i></a> <a href="#"
					class="button1 white" onClick="down();"><i class="iconfont">&nbsp;&#xe603;&nbsp;</i></a>

				<a href="#" class="button1 white" onClick="left();"><i
					class="iconfont">&nbsp;&#xe604;&nbsp;</i></a> <a href="#"
					class="button1 white" onClick="right();"><i class="iconfont">&nbsp;&#xe622;&nbsp;</i></a>

				<a href="#" class="button1 white" onClick="big();">放大</a> <a
					href="#" class="button1 white" onClick="small();">缩小</a> <a
					href="#" class="button white" onClick="ptzStop();">停止控制</a> <a
					href="#" onClick="alert('Welcome!')" class="gfvd smallrouded">规范视频</a>


			</div>
		</div>
		<div class="yzvedio01">
			<div class="yzr1" style="margin-left: 1%; margin-top: 5px;">
				<input type="button" class="button white" onclick="addcamera();"
					value="开始布控" /> <input type="button" class="button white"
					onclick="sayTo();" value="开始对讲" id="says" /> <input type="button"
					class="button white" onclick="recordToWeb();" value="手动录制"
					id="record" /> <input type="checkbox" id="check"
					onclick="recordToWeb();">报警时录制</input> <input type="button"
					class="button white" onclick="video();" value="录像回放" /> <input
					type="button" class="button white"
					onclick="openWin('work.html', 800, 600)" value="工作票" />
			</div>
			<div class="yzr2">
				<table id="myTable" width="100%" border="0" cellspacing="1"
					cellpadding="0" align="left">
					<thead>
						<tr>
							<td>告警ID</td>
							<td>相机名称</td>
							<td>告警时间</td>
							<td>人员类别</td>
							<td>人员姓名</td>
							<td>人员照片(抓拍照片)</td>
						</tr>
					</thead>
					<tbody id="tbody"></tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function openWin(u, w, h) {
			var l = (screen.width - w) / 2;
			var t = (screen.height - h) / 2;
			var s = 'width=' + w + ', height=' + h + ', top=' + t + ', left='
					+ l;
			s += ', toolbar=no, scrollbars=yes, menubar=no, location=no, resizable=no';
			open(u, 'oWin', s);
		}
	</script>


</body>
</html>
