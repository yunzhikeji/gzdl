<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>组织管理</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${pageContext.request.contextPath }/css/datePicker1.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath }/css/build.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

</head>

<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id=" ">
	  <table class="table">
    <thead class="text-c">
    <tr>
        <th width="15%">单位名称</th>
         <td width="70%"><input type="text" class="date_picker" style="width:400px"/></td>
      </tr>
    </thead>
      <tbody>
      <tr class="text-c">
        <th>单位所属区域</th>
        <td><select class="date_picker" style="width:418px">
            <option>越秀区</option>
            <option>黄浦区</option>
			<option>白云区</option>
            </select></td>
      </tr>
      <tr class="text-c">
       <th>管理员账号</th>
        <td><input type="text" class="date_picker" style="width:400px"/></td>
      </tr>
      <tr class="text-c">
       <th>密码</th>
        <td><input type="password" class="date_picker" style="width:400px"/></td>
      </tr>
      <tr class="text-c">
        <th style="background:#FFF;border: #FFF; text-align:center;" colspan="2"><a id="save" href="#" class="button orange smallrouded"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe66c;</i>重置&nbsp;&nbsp;&nbsp;</a>  <a id="save" href="#" class="button blue smallrouded"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe632;</i>保存&nbsp;&nbsp;&nbsp;</a></th>
      </tr>
      </tbody>
    </table>
    
		
		</form>
        
</div>
</body>
</html>
