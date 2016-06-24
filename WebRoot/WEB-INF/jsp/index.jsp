<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>江苏云知智能科技有限公司</title>
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />

</head>

<body>
<header style="z-index: 25;">
<div class="tY0">
<h1><a href="https://95598.gd.csg.cn/"><img src="images/NFlogo.png" alt="广东南方电网公司网上营业厅" name="NFlogo" height="50" id="NFlogo"/></a></h1>
<ul class="tO0">
		<c:if test="${username!=null }">
<li class="pR0">${username }，你好！</li>
</c:if>
<li class="rZ0 pn1">|</li>
<li class="rZ0"><a href="javascript:;" onClick="ypgj('用户管理','${pageContext.request.contextPath }/user/queryUsers','1024px','768px')" title="用户管理" target="_self" class="sh0 nui-txt-link">用户管理</a></li>
<li class="rZ0 pn1">|</li>

			<c:if test="${username!=null }">
				<li class="rZ0"><a
					href="${pageContext.request.contextPath }/logout.action"
					class="sh0 nui-txt-link" target="_self">注销</a></li>
			</c:if>
</ul>
</div>
</header>

<div id="tab_demo" class="HuiTab">
  <div class="tabBar cl">
  <span class="tA0">分布图</span>
  <span class="tA0">工地管理</span>
  <span class="tA0">视频管理</span>
  </div>
  <div class="sJ0"></div>
  <div class="tabCon">
  <div style="width:100%; float:left;">
 <iframe name="map" src="map" frameborder="0" scrolling="no" width="100%" height="862px" marginheight="0" marginwidth="0" style="background-color:#FFF"></iframe>  
</div>
</div>
 
  <div class="tabCon"> 
 <iframe name="site" src="${pageContext.request.contextPath }/site/showSites" id="iframepage" frameborder="0" scrolling="no" width="100%" height="862px" marginheight="0" marginwidth="0"></iframe> 
    </div>
  <div class="tabCon"> <iframe name="" src="vedio.html" id="iframepage" frameborder="0" scrolling="no" width="100%" height="862px" marginheight="0" marginwidth="0"></iframe>  </div>
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
	