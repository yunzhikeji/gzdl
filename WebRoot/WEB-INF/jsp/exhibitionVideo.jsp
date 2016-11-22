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
						url : '${pageContext.request.contextPath }/facecloud/getAlarmVOsss',
						dataType : 'json',
						data : 'id=' + id,
						success : function(msg) {

							errorCode = msg.errorCode;

							if (msg.alarmMessages != null) {
								errorCode = 1;
								var tbody = "";
								for (var i = 0; i < msg.alarmMessages.length; i++) {
									if(i<3){
										var val = msg.alarmMessages[i];
										var person_name = val.person_name;
										tbody = tbody
											+ "<tr>"
												+ "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片：</td>"	
												+ "<td>"
												+ "<img style='width:120px;height:80px;' src='data:image/jpg;base64,"
												+ val.photo_url
												+ "' />"
												+ "</td>" 
											+ "</tr>"
											+ "<tr>"
												+ "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>"
												+ "<td>"
												+ person_name
												+ "</td>"
											+ "</tr>"
											+ "<tr>"
											    + "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;司：</td>"
											    + "<td>科腾技术有限公司</td>"
										    + "</tr>"
										  	+ "<tr style='color:#ff6c00;'>"
											  	+ "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;告警时间：</td>"
											  	+ "<td>"
											   	+ val.alarm_time
											   	+ "</td>"
										  	+ "</tr>";
									}
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

<script type="text/javascript"> 
	function realSysTime(clock){ 
		var now=new Date(); //创建Date对象 
		var year=now.getFullYear(); //获取年份 
		var month=now.getMonth(); //获取月份 
		var date=now.getDate(); //获取日期 
		var day=now.getDay(); //获取星期 
		var hour=now.getHours(); //获取小时 
		var minu=now.getMinutes(); //获取分钟 
		var sec=now.getSeconds(); //获取秒钟 
		month=month+1; 
		var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
		var week=arr_week[day]; //获取中文的星期 
		var time=year+"年"+month+"月"+date+"日 "+week+" "+hour+":"+minu+":"+sec; //组合系统时间 
		clock.innerHTML=time; //显示系统时间 
	} 
	window.onload=function(){ 
	window.setInterval("realSysTime(clock)",1000); //实时获取并显示系统时间 
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
			地址:${organize.address}&nbsp;&nbsp;
			设备编号:${camera.cnumber}&nbsp;&nbsp;
			电压:${camera.voltage} V &nbsp;&nbsp;
			温度:${camera.temperature}  ℃&nbsp;&nbsp; 
			时间:<span id="clock" ></span>
		</div>
		<div class="yzvedio00">
			<div
				style="width: 60%; margin-top: 5px; margin-left: 1%; height: 700px;float:left">
				<object CLASSID="CLSID:7A2B1F67-6568-4466-A0A5-EA7FF6EBE820"
					id="ocx" style="margin-right: 10%;" width="100%" height="100%"
					VIEWASTEXT> </object>
			</div>
			<div style="margin-left: 1%; margin-top: 5px;height: 660px;float:left;background-color:#ededed;padding:20px 10px;">
			<div class="exh">
			<div style="text-align:center;font-weight:bold;margin-bottom:15px;font-size:25px">当前识别人员</div></div>
<div class="ex">
<table style="height:120px;text-align:left;font-size:14px;" border="0" cellspacing="0" cellpadding="0">
	<tbody id="tbody"></tbody>
</table>
<div class="exl"></div>
</div>	

<table width="400" border="0" cellspacing="5" cellpadding="0" style="margin-top: 8px;">
  <tr>
    <td class="white1"><a style="color: #606060;" href="#" onClick="big();"><i class="iconfont">&#xe601;</i></a></td>
    <td class="white1"><a style="color: #606060;" href="#" onClick="up();"><i class="iconfont">&#xe623;</i></a></td>
    <td class="white1"><a style="color: #606060;" href="#" onClick="play();"> <i class="iconfont">&#xe624;</i></a></td>
    <td class="white1"><a style="color: #606060;" href="#" onclick="addcamera();">开始布控</a></td>
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
    <td class="white1"><a style="color: #606060;" href="#" onclick="ypgy('历史告警记录','${pageContext.request.contextPath }/alarm/alarmList?cameraid=${camera.id }','1024px','768px')">历史记录查询</a></td>
  </tr>
</table>
 
        

			</div>
			
		</div>
	<div style="padding-top:10px;text-align:center;vertical-align:middle;font-size:20px;font-family:Arial, Helvetica, sans-serif; color: #0157e7; font-weight: bold;clear:both;">
	<img style="height:50px;vertical-align:middle" src="${pageContext.request.contextPath}/images/yhmlogo.jpg" />广州亿弘淼科技有限公司</div>      
	</div>
	
	
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/2.1/layer.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/H-ui.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/H-ui.admin.js"></script>
	<script type="text/javascript">
		function openWin(u, w, h) {
			var l = (screen.width - w) / 2;
			var t = (screen.height - h) / 2;
			var s = 'width=' + w + ', height=' + h + ', top=' + t + ', left='
					+ l;
			s += ', toolbar=no, scrollbars=yes, menubar=no, location=no, resizable=no';
			open(u, 'oWin', s);
		}
		
		function ypgy(title, url, w, h) {
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
	</script>


</body>
</html>
