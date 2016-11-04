<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>用户管理</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/html5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/respond.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${pageContext.request.contextPath }/css/user.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<link href="${pageContext.request.contextPath }/css/datePicker1.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath }/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/1.0.7/iconfont.css"
	rel="stylesheet" type="text/css" />
</head>

<body style="background:#fff;">
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="">
	  <table class="table">
    <thead class="text-c">
    <tr class="text-d">
    <th colspan="8" style="text-align:left;height:35px;">
    <span id="show" style="font-size:16px;padding-left:10px;color:#8c8c8c;">告警时间查询：</span> <input type="text" class="date_picker" name="name"
				placeholder="开始时间" style="width: 100px" />
				<span id="show" style="color:#b6b6b6;">━</span> <input type="text" class="date_picker" name="number"
				placeholder="结束时间" style="width: 100px" />
			<input type="submit" class="loginBtn1 orange smallrounded" 
								 style="margin-left: 5px;" value="查询">
							</input></th>
    </tr>
    <tr class="text-d">
      
      <th width="5%" style="font-size:14px;">序号</th>
      <th width="10%" style="font-size:14px;">告警ID</th>
      <th width="12%" style="font-size:14px;">相机名称</th>
      <th width="15%" style="font-size:14px;">告警时间</th>
      <th width="10%" style="font-size:14px;">人员类别</th>
      <th width="10%" style="font-size:14px;">人员姓名</th>
      <th width="18%" style="font-size:14px;">人员照片(抓拍照片)</th>
      <th width="10%" style="font-size:14px;"></th>
    </tr>
    </thead>
      <tbody>
      <tr class="text-c">
        
        <td>1</td>
        <td>林卿</td>
        <td>广东省广州市越秀区</td>
        <td>广州供电局</td>
        <td>施工单位</td>
        <td>施工单位</td>
        <td height="100px"><img src="images/mission.png" alt="抓拍" /></td>
        <td><c:if test="${alarm.id !=-1}">
									<a id="delete"
										href="${pageContext.request.contextPath }/alarm/deletealarm?id=${alarm.id }"
										class="button orange bla smallrounded"
										style="font-size: 14px;"><i class="Hui-iconfont">&nbsp;&#xe609;&nbsp;</i>删除&nbsp;&nbsp;&nbsp;</a>
								</c:if></td>
      </tr>
     
      </tbody>
    </table>
			
		</form>
</div>
</body>
</html>
