<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
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
        <th width="4%" style="font-size:14px;">序号</th>
        <th width="10%" style="font-size:14px;">工地名称</th>
        <th width="15%" style="font-size:14px;">位置</th>
        <th width="10%" style="font-size:14px;">监控视频显示个数</th>
        <!--  
        <th width="16%" style="font-size:14px;">承包单位信息</th>
        <th width="16%" style="font-size:14px;">监理单位信息</th>
        -->
        <th colspan="2" width="20%" style="font-size:14px;">操作</th>
      </tr>
    </thead>
      <tbody>
      <c:forEach items="${siteList }" var="site" varStatus="index">
      <tr class="text-c">
        <td>${index.count }</td>
        <td>${site.name }</td>
        <td>经度${site.lng }<br />纬度${site.lat }</td>
        <td>
        <c:if test="${site.viewstyle==null||site.viewstyle==0}">
        		当前无摄像机
        </c:if>
        ${site.viewstyle}
        </td>
        <!-- 
        <td>单位名称：阳光一百<br />负责人：宋永康<br />联系电话：18251569424</td>
        <td>单位名称：阳光一百<br />负责人：宋永康<br />联系电话：18251569424</td>
         -->
        <td><a href="javascript:;" onClick="ypgy('工地管理','${pageContext.request.contextPath }/site/editSite.action?id=${site.id}','1024px','768px')" title="工地管理" target="_self" class="button blue bigrounded bla" style="font-size:14px;"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe647;</i>编辑&nbsp;&nbsp;&nbsp;</a></td>
        <td><a  onclick="return confirm('你确定删除该工地信息吗？')" href="${pageContext.request.contextPath }/site/deleteSite.action?id=${site.id}" class="button orange bla smallrounded" style="font-size:14px;line-height:14px;"><i class="Hui-iconfont">&nbsp;&#xe609;&nbsp;</i>删除</a></td>
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
