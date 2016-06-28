<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	function OnSend() {
		var ocx = document.getElementById("ocx");
		ocx
				.PlayReviewVideo("<business id='PlanTaskNumber14'><businessNode  type='入库作业'  ip='192.168.1.100'  port='37777'  channel='6'  timebegin='20140813:120500'  timestop='20140813:121000' ><messages><message>扦样人：张三；样品数量：1份；</message><messagetime>时间：2014-05-22 13:00；</messagetime></messages></businessNode>  <businessNode  type='入库作业2'  ip='192.168.1.100'  port='37777'  channel='6'  timebegin='20140813:140500'  timestop='20140813:141000' ><messages><message>扦样人：张三；样品数量：1份；</message><messagetime>时间：2014-05-22 13:00；</messagetime></messages></businessNode></business>");
	}

	function OnPlay1() {

		var ocx = document.getElementById("ocx");
		console.log(ocx);
		ocx.Login("test", "test", "115.28.135.48", 20000);
	}
	function OnPlay2() {
		var ocx = document.getElementById("ocx");
		console.log(ocx);
		ocx.PlayVideo("44030300001320020247"); /*   */

	}
	function OnPlay3() {
		var ocx = document.getElementById("ocx");
		ocx.StopRealplay();
		ocx.PlayReviewVideoByID("PlanTaskNumber3"); /*   */
	}

	function OnUp() {
		var ocx = document.getElementById("ocx");
		ocx.CamUp();

	}

	function OnDown() {
		var ocx = document.getElementById("ocx");
		ocx.CamDown();
	}

	function OnLeft() {
		var ocx = document.getElementById("ocx");
		ocx.CamLeft();
	}

	function OnRight() {
		var ocx = document.getElementById("ocx");
		ocx.CamRight();
	}

	function OnStop() {
		var ocx = document.getElementById("ocx");
		ocx.StopVideo();
	}

	function Onload() {

	}
	
	
	function showCls1()
	{
		$(".cls1").show();
	}
	

	function showCls2()
	{
		$(".cls2").show();
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
<body>
	<div style="width: 100%; position: relative;">
		<div class="yzrtv">
			<span style="font-weight: bold;">工地名称：</span>
			<span style="padding-right: 6px;">${siteCustom.name}</span>
			<span style="font-weight: bold;">|</span> 
			<span style="font-weight: bold;">工地位置：</span><span>${siteCustom.areaname }</span>
		</div>
		<div class="yzvedio0" onLoad="Onload();">
			<div style="width: 80%;margin-left: 3%;height: 500px;">
			<object CLASSID='CLSID:7A2B1F67-6568-4466-A0A5-EA7FF6EBE820' id='ocx'
				style="margin-left: 10%; margin-right: 10%;" width='100%'
				height='100%' VIEWASTEXT> </object>
			</div>
			<div style="margin-top: 5px;">
				<a href="#" class="button white" onClick="OnPlay1();">登录</a> <a
					href="#" class="button white" onClick="OnPlay2();"><i
					class="iconfont">&nbsp;&nbsp;&#xe624;&nbsp;&nbsp;</a> <a href="#"
					class="button white" onClick=""><i class="iconfont">&nbsp;&nbsp;&#xe625;&nbsp;&nbsp;</a>
				<a href="#" class="button1 white" onClick="OnUp();"><i
					class="iconfont">&nbsp;&#xe623;&nbsp;</i></a> <a href="#"
					class="button1 white" onClick="OnDown();"><i class="iconfont">&nbsp;&#xe603;&nbsp;</i></a>
				<a href="#" class="button1 white" onClick="OnLeft();"><i
					class="iconfont">&nbsp;&#xe622;&nbsp;</i></a> <a href="#"
					class="button1 white" onClick="OnRight();"><i class="iconfont">&nbsp;&#xe604;&nbsp;</i></a>
			</div>
		</div>
		<div style="width: 100%;height: 200px;">
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
						<td  class="cls2" style="display: none">2</td>
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
