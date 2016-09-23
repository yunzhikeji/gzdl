<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>添加用户</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${pageContext.request.contextPath }/css/datePicker1.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath }/css/build.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/1.0.7/iconfont.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

</head>

<body>
	<div class="pd-20">
		<form action="${pageContext.request.contextPath }/user/editUserSubimt" method="post" class="form form-horizontal" id="userForm ">
			<input type="hidden" name="id" value="${user.id }">
			<table class="table">
				<thead class="text-c">
					<tr>
						<th>用户名</th>
						<td width="70%">${user.username }</td>
					</tr>
				</thead>
				<tbody>
					
					
					<tr class="text-c">
						<th>修改密码</th>
						<td width="70%"><input type="text" name="password" value="${user.password }" class="date_picker"
							style="width: 400px" /></td>
					</tr>
					<tr class="text-c">
						<th style="background: #FFF; border: #FFF; text-align: center;"
							colspan="2"><a href="${pageContext.request.contextPath }/user/queryUsers"
							class="button orange smallrouded"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe66c;</i>返回&nbsp;&nbsp;&nbsp;</a>
							 <input type="submit" class="button blue smallrouded Hui-iconfont" style="font-size:14px;padding:6px 10px 5px 10px;" value="&nbsp;&nbsp;&nbsp;&nbsp;&#xe632;保存&nbsp;&nbsp;&nbsp;&nbsp;"/></th>
					</tr>
				</tbody>
			</table>


		</form>

	</div>
</body>
</html>
