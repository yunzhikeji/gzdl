<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>组织管理</title>
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
		<form action="${pageContext.request.contextPath }/organize/organizeList" method="post" class="form form-horizontal" id="">
			单位列表 <span style="margin-left: 20px"><select
				class="date_picker" id="s_province" name="province"></select>   <select
				class="date_picker" id="s_city" name="city"></select>   <select
				class="date_picker1" id="s_county" name="area"></select> <script
					class="resources library"
					src="${pageContext.request.contextPath }/js/area.js"
					type="text/javascript"></script> <script type="text/javascript">
						_init_area();
					</script></span><span id="show"></span> <input type="text" class="date_picker" name="name"
				placeholder="单位名称" style="width: 150px" />
			<input type="submit" class="loginBtn1 orange smallrounded" 
								 style="margin-left: 5px;" value="查询">
							</input>
							</form>
			<button class="button blue bla smallrounded"
				style="font-size: 14px; line-height: 14px; margin-left: 20px"
				onClick="ypgy('施工单位','${pageContext.request.contextPath }/organize/toCompanyAdd','1024px','768px')">
				<i class="Hui-iconfont">&nbsp;&#xe600;&nbsp;</i>添加施工单位
			</button>
			<button class="button blue bla smallrounded"
				style="font-size: 14px; line-height: 14px; margin-left: 5px"
				onClick="ypgy('供电局','${pageContext.request.contextPath }/organize/toGovermentAdd','1024px','768px')">
				<i class="Hui-iconfont">&nbsp;&#xe600;&nbsp;</i>添加供电局
			</button>
			<button class="button orange bla smallrounded"
				style="font-size: 14px; line-height: 14px; margin-left: 20px">
				<i class="Hui-iconfont">&nbsp;&#xe609;&nbsp;</i>批量删除
			</button>
		</div>
		<div class="sJ0"></div>
		<div style="height: 740px">
			<table class="table" style="width: 98%; margin: 5px auto">
				<thead class="text-c">
					<tr bgcolor="#f6f6f6">
						<!--  <th width="5%" style="font-size:14px;"></th> -->
						<th width="5%" style="font-size: 14px;">序号</th>
						<th width="10%" style="font-size: 14px;">单位名称</th>
						<th width="20%" style="font-size: 14px;">所属区域</th>
						<th width="10%" style="font-size: 14px;">联系人</th>
						<th width="10%" style="font-size: 14px;">联系电话</th>
						<th width="10%" style="font-size: 14px;">组织类别</th>
						<th width="10%" style="font-size: 14px;"></th>
						<th width="10%" style="font-size: 14px;"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${organizeList }" var="organize"
						varStatus="index">
						<tr class="text-c">
							<td>${index.count }</td>
							<td>${organize.name }</td>
							<td>${organize.province }${organize.city }${organize.area }</td>
							<td>${organize.contact }</td>
							<td>${organize.phone }</td>
							<td><c:if test="${organize.type ==1}"> 供电局</c:if> <c:if
									test="${organize.type ==2}"> 施工单位</c:if></td>
							<td><c:if test="${organize.type ==1}">
									<a href="javascript:;"
										onClick="ypgy('供电局管理','${pageContext.request.contextPath }/organize/toGovermentUpdate?id=${organize.id }','1024px','768px')"
										title="修改供电局信息" target="_self"
										class="button blue bigrounded bla"
										style="font-size: 14px; line-height: 14px"><i
										class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe647;</i>编辑&nbsp;&nbsp;&nbsp;</a>
								</c:if> <c:if test="${organize.type ==2}">
									<a href="javascript:;"
										onClick="ypgy('施工单位管理','${pageContext.request.contextPath }/organize/toCompanyUpdate?id=${organize.id }','1024px','768px')"
										title="修改施工单位" target="_self"
										class="button blue bigrounded bla"
										style="font-size: 14px; line-height: 14px"><i
										class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe647;</i>编辑&nbsp;&nbsp;&nbsp;</a>
								</c:if></td>

							<td><c:if test="${organize.id !=-1}">
									<a id="delete" onClick="return confirm('您确认要删除信息吗?')"
										href="${pageContext.request.contextPath }/organize/deleteOrganize?id=${organize.id }"
										class="button orange bla smallrounded"
										style="font-size: 14px;"><i class="Hui-iconfont">&nbsp;&#xe609;&nbsp;</i>删除&nbsp;&nbsp;&nbsp;</a>
								</c:if></td>

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
	<script type="text/javascript">
		var Gid = document.getElementById;
		var showArea = function() {
			Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市"
					+ Gid('s_city').value + " - 县/区" + Gid('s_county').value
					+ "</h3>"
		}
		Gid('s_county').setAttribute('onchange', 'showArea()');
	</script>

	<script type="text/javascript">
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
