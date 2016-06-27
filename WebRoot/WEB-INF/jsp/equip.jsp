<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
        <th width="18%" style="font-size:14px;">经纬度</th>
        <th width="12%" style="font-size:14px;">工作电压</th>
        <th width="20%" style="font-size:14px;">工作温度</th>
        <th width="20%" style="font-size:14px;">工作状态</th>
      </tr>
    </thead>
      <tbody>
      <tr class="text-c">
        <td>1</td>
        <td>越秀城南区</td>
        <td>经度415°<br />纬度325°</td>
        <td>4</td>
        <td>单位名称：阳光一百<br />负责人：宋永康<br />联系电话：18251569424</td>
        <td>单位名称：阳光一百<br />负责人：宋永康<br />联系电话：18251569424</td>
      </tr>
     
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