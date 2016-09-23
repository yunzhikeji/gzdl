<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设别管理</title>
<link href="${pageContext.request.contextPath }/css/datePicker1.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />

</head>

<body style="background:#e9eef0;">
 <div class="bht">
 <div class="bh text-d">设备列表
 <button class="button white bigrounded bla" style="font-size:14px; line-height:14px;margin-left:5px;";>&nbsp;全部&nbsp;</button>
 <button class="button white bigrounded bla" style="font-size:14px; line-height:14px;";>已出租</button>
 <button class="button white bigrounded bla" style="font-size:14px; line-height:14px;";>未出租</button>
 <button class="button blue bla smallrounded" style="font-size:14px; line-height:14px;margin-left:75%"; onClick="ypgy('设备入库','${pageContext.request.contextPath }/camera/toAdd','1024px','768px')" ><i class="Hui-iconfont">&nbsp;&#xe600;&nbsp;</i>设备入库</button></div>
 <div class="sJ0"></div>
 <div style="height:740px">
  <table class="table" style="width:98%; margin:5px auto">
    <thead class="text-c">
    <tr bgcolor="#f6f6f6">
        
        <th width="5%" style="font-size:14px;">序号</th>
       <th width="12%" style="font-size:14px;">设备编号</th>
        <th width="12%" style="font-size:14px;">SIP编号</th>
        <th width="22%" style="font-size:14px;">单位名称</th>
        <th width="13%" style="font-size:14px;"></th>
        <th width="13%" style="font-size:14px;"></th>
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
        <td><a href="javascript:;"onClick="ypgy('设备租售','${pageContext.request.contextPath }/camera/toUpdate?id=${camera.id }','1024px','768px')" title="设备租售" target="_self" class="button blue bigrounded bla" style="font-size:14px;line-height:14px"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe647;</i>编辑&nbsp;&nbsp;&nbsp;</a></td>
        <td><a href="javascript:;" onClick="ypgy('设备租售','${pageContext.request.contextPath }/camera/toUpdate','1024px','768px')" title="设备租售" target="_self" class="button blue bigrounded bla" style="font-size:14px;line-height:14px"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe672;</i>出租&nbsp;&nbsp;&nbsp;</a></td>
        <td><button class="button orange bla smallrounded" style="font-size:14px;line-height:14px" onClick="OnUp();"><i class="Hui-iconfont">&nbsp;&#xe609;&nbsp;</i>删除</button></td>

      </tr>
</c:forEach>
      </tbody>
    </table>
    </div>
     <div class="sJ0"></div>
    <div class="text-e bb">
    <button class="button white bla smallrounded" style="font-size:14px;line-height:14px"><i class="Hui-iconfont">&nbsp;&#xe678;&nbsp;</i>上一页</button>
    <button class="button white bla smallrounded" style="font-size:14px;line-height:14px"><i class="Hui-iconfont">&nbsp;&#xe67a;&nbsp;</i>下一页</button>
    <span style="margin-left:10px">共&nbsp;<input type="text" class="date_picker" placeholder="&nbsp;1"  style="width:20px"/>&nbsp;页</span>
    </div>

    </div>
  

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/2.1/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/H-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/H-ui.admin.js"></script>
<script type="text/javascript">
$(function(){
$.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","0");
});

/*修改密码*/
function ypgj(title,url,w,h){
	layer.open({
    type: 2,
    title: title,
    shadeClose: true,
    shade: 0.8,
    area: [w, h],
    content: url //iframe的url
}); 
}
function ypgy(title,url,w,h){
	layer.open({
    type: 2,
    title: title,
    shadeClose: true,
    shade: 0.8,
    area: [w, h],
    content: url //iframe的url
}); 
}	
</script>

</body>
</html>