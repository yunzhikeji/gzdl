<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工地管理</title>
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />

</head>

<body>
 <div style="background:#FFF; overflow:auto; width:100%;height:836px">
  <table class="table">
    <thead class="text-c">
    <tr class="text-d">
        <th width="5%" style="font-size:14px;">序号</th>
        <th width="20%" style="font-size:14px;">设备编号</th>
        <th width="18%" style="font-size:14px;">当前经纬度</th>
        <th width="12%" style="font-size:14px;">当前工作电压</th>
        <th width="20%" style="font-size:14px;">当前工作温度</th>
        <th width="20%" style="font-size:14px;">当前工作状态</th>
      </tr>
    </thead>
      <tbody>
      <c:forEach items="${cameraList }" var="camera" varStatus="index">
      <tr class="text-c">
        <td>${index.count }</td>
        <td>${camera.cnumber }</td>
        <td>经度${camera.lng }<br />纬度${camera.lat }</td>
        <td>${camera.voltage }伏</td>
        <td>${camera.temperature }度</td>
        <td>
        
        
       						<c:if test="${camera.status==-1}">未知状态</c:if> 
					   <c:if test="${camera.status == 0 }">关机</c:if> 
					   <c:if test="${camera.status == 1 }">正常工作</c:if> 
					   <c:if test="${camera.status == 2 }">重启</c:if>
        
        
        
        </td>
      </tr>
     </c:forEach>
      </tbody>
    </table>
    </div>
    <div class="digg"> 
   <span class="disabled">&lt; </span>
<span class="current">1</span>
<a href="#?page=2">2</a>
<a href="#?page=3">3</a>
<a href="#?page=4">4</a>
<a href="#?page=5">5</a>
<a href="#?page=6">6</a>
<a href="#?page=7">7</a>
...
<a href="#?page=199">199</a>
<a href="#?page=200">200</a>
<a href="#?page=2"> 
&gt; </a></div>

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