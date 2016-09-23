<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>用户管理</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${pageContext.request.contextPath }/css/user.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/1.0.7/iconfont.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

</head>

<body style="background: #fff;">
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="">
			<table class="table">
				<thead class="text-c">
					<tr class="text-d">
						<th colspan="8" style="text-align: left; height: 35px;">&nbsp;&nbsp;&nbsp;&nbsp;<a
							class="button blue bla smallrounded" href="${pageContext.request.contextPath }/user/toAddUser"
							target="_self" style="font-size: 14px;"><i
								class="Hui-iconfont">&nbsp;&#xe607;&nbsp;</i>添加用户</a>
						</th>
					</tr>
					<tr class="text-d">
						<th width="5%" style="font-size: 14px;">序号</th>
						<th width="10%" style="font-size: 14px;">用户名</th>
						<th width="30%" style="font-size: 14px;">所属区域</th>
						<th width="15%" style="font-size: 14px;">所属机构</th>
						<th width="15%" style="font-size: 14px;">组织类别</th>
						<th width="10%" style="font-size: 14px;"></th>
						<th width="10%" style="font-size: 14px;"></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${userVoList }" var="userVo" varStatus="index">
					<tr class="text-c">
						<td>${index.count }</td>
						<td>${userVo.username }</td>
						<td>${userVo.province }${userVo.city }${userVo.area }</td>
						<td>${userVo.organizeName }</td>
						<td><c:if test="${userVo.type ==1}"> 供电局</c:if><c:if test="${userVo.type ==2}"> 施工单位</c:if></td>
						<td><a id="edit" href="${pageContext.request.contextPath }/user/toEditUser?=${userVo.id }"
							class="button blue bigrounded bla" style="font-size: 14px;"><i
								class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe647;</i>编辑&nbsp;&nbsp;&nbsp;</a></td>
						<td>
						<c:if test="${userVo.organizeid !=-1}">
						<a id="edit" href="${pageContext.request.contextPath }/user/deleteUser?id=${userVo.id }"
							class="button orange bla smallrounded" style="font-size: 14px;"><i class="Hui-iconfont">&nbsp;&#xe609;&nbsp;</i>删除&nbsp;&nbsp;&nbsp;</a>
						</c:if>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

		</form>
	</div>
</body>
</html>