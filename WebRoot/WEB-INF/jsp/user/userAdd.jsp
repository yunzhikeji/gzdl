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
		<form action="${pageContext.request.contextPath }/user/addUser"
			method="post" class="form form-horizontal" id="userForm">
			<table class="table">
				<thead class="text-c">
					<tr>
						<th>用户名</th>
						<td width="70%"><input type="text" name="username" class="date_picker"
							style="width: 400px" /></td>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c">
						<!--         <th rowspan="3">所属区域</th>
        <td style="padding-top:7px;padding-bottom:7px;"><select class="date_picker" id="s_province" name="s_province" style="width:420px"></select></td>
      </tr>
      <tr class="text-c">
        <td style="padding-top:7px;padding-bottom:7px;"><select class="date_picker" id="s_city" name="s_city" style="width:420px"></select></td>
      </tr>
      <tr class="text-c">
        <td style="padding-top:7px;padding-bottom:7px;"><select class="date_picker1" id="s_county" name="s_county" style="width:420px"></select></td>
      
      <script class="resources library" src="js/area.js" type="text/javascript"></script>
    
    <script type="text/javascript">_init_area();</script>
        <span id="show"></span>
      </tr> -->
					<tr class="text-c">
						<th>所属机构</th>
						<td><select name="organizeName" class="date_picker1" style="width: 420px">
								<c:forEach items="${organizeList }" var="organize">
									<option>${organize.name }</option>
								</c:forEach>
						</select></td>
					</tr>
					<!--      <tr class="text-c">
       <th>组织类别</th>
       <td><input name="vd" type="radio" value="1" checked="checked"/> 
          供电局&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="vd" type="radio" value="2" /> 
            施工单位</td>
     </tr> -->
					<tr>
						<th colspan="2" align="center" valign="middle"
							style="background: #FFF; border: 0; text-align: right; font-size: 12px; line-height: 15px; font-weight: lighter;"><span
							style="font-size: 20px; color: #F00;">*</span><span>初始密码为1234</span></th>
					</tr>
					<tr class="text-c">
						<th style="background: #FFF; border: #FFF; text-align: center;"
							colspan="2"><a
							href="${pageContext.request.contextPath }/user/queryUsers"
							class="button orange smallrouded"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe66c;</i>返回&nbsp;&nbsp;&nbsp;</a>
							 <input type="submit" class="button blue smallrouded Hui-iconfont" style="font-size:14px;padding:6px 10px 5px 10px;" value="&nbsp;&nbsp;&nbsp;&nbsp;&#xe632;保存&nbsp;&nbsp;&nbsp;&nbsp;"/><!-- <a id="save" href="#" class="button blue smallrouded"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe632;</i>保存&nbsp;&nbsp;&nbsp;</a>  --></th>
					</tr>
				</tbody>
			</table>


		</form>

	</div>
</body>
</html>
