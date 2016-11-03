<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设别管理</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/html5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/respond.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${pageContext.request.contextPath }/css/datePicker1.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath }/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/1.0.7/iconfont.css"
	rel="stylesheet" type="text/css" />

</head>

<body style="background: #e9eef0;">
	<div class="bht">
		<div class="bh text-d">
			设备列表
				<div style="margin-bottom: 2px;width: 100%;">
				<form action="${pageContext.request.contextPath }/camera/cameraList" method="post">
				 <select
				class="date_picker" id="s_province" name="province"></select>   <select
				class="date_picker" id="s_city" name="city"></select>   <select
				class="date_picker1" id="s_county" name="area"></select> <script
					class="resources library"
					src="${pageContext.request.contextPath }/js/area.js"
					type="text/javascript"></script> <script type="text/javascript">
						_init_area();
					</script></span><span id="show"></span> <input type="text" class="date_picker" name="name"
				placeholder="施工单位名称" style="width: 100px" />
				<span id="show"></span> <input type="text" class="date_picker" name="number"
				placeholder="设备编号" style="width: 100px" />
			<input type="submit" class="loginBtn1 orange smallrounded" 
								 style="margin-left: 5px;" value="查询">
							</input>
							</form>
							</div>
			 <a target="_self"
				href="${pageContext.request.contextPath }/camera/cameraList"
				class="button white bigrounded bla"
				style="font-size: 14px; line-height: 14px; margin-left: 5px;";>&nbsp;全部&nbsp;</a>
			<a target="_self"
				href="${pageContext.request.contextPath }/camera/cameraList?hire=1"
				class="button white bigrounded bla"
				style="font-size: 14px; line-height: 14px; margin-left: 5px;";>&nbsp;已出租&nbsp;</a>
			<a target="_self"
				href="${pageContext.request.contextPath }/camera/cameraList?hire=2"
				class="button white bigrounded bla"
				style="font-size: 14px; line-height: 14px; margin-left: 5px;";>&nbsp;未出租&nbsp;</a>
				 <span style="margin-left: 20px">
			<button class="button blue bla smallrounded"
				style="font-size: 14px; line-height: 14px; margin-left: 5px"
				; onClick="ypgy('设备入库','${pageContext.request.contextPath }/camera/toAdd','1024px','768px')">
				<i class="Hui-iconfont">&nbsp;&#xe600;&nbsp;</i>设备入库
			</button>
		</div>
		<div class="sJ0"></div>
		<div style="height: 740px">
			<table class="table" style="width: 98%; margin: 5px auto">
				<thead class="text-c">
					<tr bgcolor="#f6f6f6">
        
        <th width="5%" style="font-size:14px;">序号</th>
        <th width="10%" style="font-size:14px;">设备编号</th>
        <th width="10%" style="font-size:14px;">SIP编号</th>
        <th width="21%" style="font-size:14px;">单位名称</th>
        <th width="13%" style="font-size:14px;"></th>
        <th width="13%" style="font-size:14px;"></th>
        <th width="18%" style="font-size:14px;"></th>
        <th width="13%" style="font-size:14px;"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cameraVoList }" var="camera" varStatus="index">
						<tr class="text-c">

							<td class="text-c">${index.count }</td>
							<td>${camera.cnumber }</td>
							<td>${camera.sipid }</td>
							<td>${camera.organizeName }</td>
							
							<td><a href="javascript:;"
								onClick="ypgy('设备编辑','${pageContext.request.contextPath }/camera/toUpdate?id=${camera.id }','1024px','768px')"
								title="设备租售" target="_self" class="button blue bigrounded bla"
								style="font-size: 14px; line-height: 14px"><i
									class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe647;</i>编辑&nbsp;&nbsp;&nbsp;</a></td>
							<td><c:if test="${camera.organizeid==0 }"><a href="javascript:;"
								onClick="ypgy('设备租售','${pageContext.request.contextPath }/camera/toLease?id=${camera.id }','1024px','768px')"
								title="设备租售" target="_self" class="button blue bigrounded bla"
								style="font-size: 14px; line-height: 14px"><i
									class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe672;</i>出租&nbsp;&nbsp;&nbsp;</a></c:if><c:if test="${camera.organizeid !=0 }">已出租</c:if></td>
							<td><a href="javascript:;"
								onClick="ypgy('历史告警记录','alarm.html','1024px','768px')"
								title="历史告警记录" target="_self" class="button blue bigrounded bla"
								style="font-size: 14px; line-height: 14px"><i
									class="Hui-iconfont">&#xe603;</i>历史告警记录</a></td>		
							<td><c:if test="${camera.organizeid != 0 }"><a href="${pageContext.request.contextPath }/camera/recycle?id=${camera.id }" class="button orange bla smallrounded"
									style="font-size: 14px; line-height: 14px" onClick="OnUp();">
									<i class="Hui-iconfont">&nbsp;&#xe609;&nbsp;</i>回收
								</a></c:if></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<!--  
		<div class="sJ0"></div>
		<div class="text-e bb">
			<button class="button white bla smallrounded"
				style="font-size: 14px; line-height: 14px">
				<i class="Hui-iconfont">&nbsp;&#xe678;&nbsp;</i>上一页
			</button>
			<button class="button white bla smallrounded"
				style="font-size: 14px; line-height: 14px">
				<i class="Hui-iconfont">&nbsp;&#xe67a;&nbsp;</i>下一页
			</button>
			<span style="margin-left: 10px">共&nbsp;<input type="text"
				class="date_picker" placeholder="&nbsp;1" style="width: 20px" />&nbsp;页
			</span>
		</div>
		-->
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/2.1/layer.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/H-ui.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/H-ui.admin.js"></script>
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