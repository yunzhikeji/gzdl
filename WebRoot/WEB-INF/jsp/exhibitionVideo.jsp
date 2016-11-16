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
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/test.css" />
<link href="${pageContext.request.contextPath }/css/Aliiconfont/iconfont.css" rel="stylesheet" type="text/css" />
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
		var backPlay = ocx.PlayVideo("${camera.sipid}");
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

	function big() {
		if (ocx == null) {
			ocx = document.getElementById("ocx");
		}
		var backUp = ocx.PTZCtrl(5);
		if (backUp != 0) {
			alert("操作失败");
		}
	}

	function small() {
		if (ocx == null) {
			ocx = document.getElementById("ocx");
		}
		var backDown = ocx.PTZCtrl(6);
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

									} else {
										url = "${pageContext.request.contextPath }/facecloud/face?person_id="
												+ val.person_id;
									}

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
<body style="background-color:#FFF;margin:0 8%;
background-image:url(${pageContext.request.contextPath}/images/beijing.png);background-attachment:fixed;
background-repeat:no-repeat;background-position:100% 100%;" onload="checkAndLogin()">
	<div style="width: 100%; position: relative;margin-top: 30px;">		
		<div style="margin-left:1%; margin-top: 5px; font-family:'方正北魏楷书简体';font-size:18px;
		color: #666; font-weight: bold;vertical-align:middle;">
			<iframe  style="vertical-align:middle;color: #666" width="200" scrolling="no" height="60" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=1"></iframe>
			地址:广东省广州市越秀区&nbsp;&nbsp;
			设备编号:${camera.cnumber}&nbsp;&nbsp; 
			电压:13 V &nbsp;&nbsp; 
			温度:27  ℃&nbsp;&nbsp; 
			时间:2016.10.26 08:09:56</div>
		<div class="yzvedio00">
			<div
				style="width: 60%; margin-top: 5px; margin-left: 1%; height: 700px;float:left">
				<object CLASSID="CLSID:7A2B1F67-6568-4466-A0A5-EA7FF6EBE820"
					id="ocx" style="margin-right: 10%;" width="100%" height="100%"
					VIEWASTEXT> </object>
			</div>
			<div style="margin-left: 1%; margin-top: 5px;height: 660px;float:left;background-color:#ededed;padding:20px 10px;">
			<table width="400" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td class="white1"><a style="color: #606060;" href="#" onClick="big();"><i class="iconfont">&#xe601;</i></a></td>
    <td class="white1"><a style="color: #606060;" href="#" onClick="up();"><i class="iconfont">&#xe623;</i></a></td>
    <td class="white1"><a style="color: #606060;" href="#" onClick="play();"> <i class="iconfont">&#xe624;</i></a></td>
    <td class="white1"><a style="color: #606060;" href="#" onclick="addcamera();">开始控制</a></td>
  </tr>
  <tr>
    <td class="white1"><a style="color: #606060;" href="#" onClick="left();"><i class="iconfont">&#xe604;</i></a></td>
    <td class="white1"></td>
    <td class="white1"><a style="color: #606060;" href="#" onClick="right();"><i class="iconfont">&#xe622;</i></a></td>
    <td class="white1"><a style="color: #606060;" href="#" onClick="ptzStop();">停止控制</a></td>
  </tr>
  <tr>
    <td class="white1"><a style="color: #606060;" href="#" onClick="small();"><i class="iconfont">&#xe600;</i></a></td>
    <td class="white1"><a style="color: #606060;" href="#" onClick="down();"><i class="iconfont">&#xe603;</i></a></td>
    <td class="white1"><a style="color: #606060;" href="#" onClick="stop();"><i class="iconfont">&#xe625;</i></a></td>
    <td class="white1"></td>
  </tr>
</table>
			  
<div class="ex">
<div style="text-align:center;font-weight:bold;margin-bottom:15px;font-size:25px">当前识别人员</div>
<div style="width:150px;height:150px;float:left;padding-left:15px"><img style="width:150px;height:150px;" src="${pageContext.request.contextPath }/images/140.jpg" alt="暂无图片"/></div>
<div><table style="margin-left:10px;height:150px;text-align:left;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>姓名：</td>
    <td>林大大</td>
  </tr>
   <tr>
    <td>性别：</td>
    <td>男</td>
  </tr>
  <tr>
    <td>年龄：</td>
    <td>28</td>
  </tr>
  <tr>
    <td>职位：</td>
    <td>软件工程师</td>
  </tr>
  <tr>
    <td>公司：</td>
    <td>科腾技术有限公司</td>
  </tr>
</table>
</div>
<div style="margin-top:20px;"><span style="color:#ff6c00;font: 20px/100% bold Arial, Helvetica, sans-serif;">告警时间：2016.10.26 08:09:56</span>
<h1 style="color:#00ccff;font-family:'方正北魏楷书简体';font-size:50px;">热烈欢迎</h1>
<h1 style="color:#00ccff;font-family:'方正北魏楷书简体';font-size:50px;">领导莅临</h1>
</div>
<div style="text-align:left;padding-left:15px;"><a style="padding:0.5em 0.5em;" href="#" class="button1 white1" onClick="">上一个告警记录</a>
<a style="margin-left:140px;padding:0.5em 0.5em;" href="#" class="button1 white1" onClick="">历史记录查询</a></div>
</div>	 
        

			</div>
			
		</div>
	<div style="padding-top:10px;text-align:center;vertical-align:middle;font-size:20px;font-family:Arial, Helvetica, sans-serif; color: #0157e7; font-weight: bold;clear:both;">
	<img style="height:50px;vertical-align:middle" src="${pageContext.request.contextPath}/images/yhmlogo.jpg" />广州亿弘淼科技有限公司</div>      
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
