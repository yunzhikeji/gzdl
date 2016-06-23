<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<link href="${pageContext.request.contextPath }/css/user.css" rel="stylesheet" type="text/css" />
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
    <tr class="text-d">
        <th colspan="8" style="text-align:left;height:35px;"><button class="button blue" style="font-size:14px;"><i class="Hui-iconfont">&nbsp;&#xe607;&nbsp;</i>添加用户</button> <button class="button orange" style="font-size:14px;"><i class="Hui-iconfont">&nbsp;&#xe609;&nbsp;</i>批量删除</button></th>
      </tr>
    <tr class="text-d">
        <th width="5%"></th>
        <th width="5%" style="font-size:14px;">序号</th>
        <th width="20%" style="font-size:14px;">用户名</th>
        <th width="20%" style="font-size:14px;">用户职位</th>
        <th width="20%" style="font-size:14px;">管理级别</th>
        <th width="10%" style="font-size:14px;">密码初始化</th>
        <th width="10%" style="font-size:14px;"></th>
        <th width="10%" style="font-size:14px;"></th>
      </tr>
    </thead>
      <tbody>
      <tr class="text-c">
        <td class="text-c"><input name="" type="checkbox" value="" /></td>
        <td>1</td>
        <td><input name="" type="text"/></td>
        <td><input name="" type="text"/></td>
        <td><input name="vd" type="radio" value="1" checked="checked"/>
        A <input name="vd" type="radio" value="4" />
        B <input name="vd" type="radio" value="6" />
        C</td>
        <td><input name="mi" type="radio" value="1" checked="checked"/>
        是 <input name="mi" type="radio" value="4" />
        否 </td>
        <td><a id="edit" href="#" class="button blue bigrounded" style="font-size:14px;"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe632;</i>保存&nbsp;&nbsp;&nbsp;</a></td>
<td><button class="button orange" style="font-size:14px;"><i class="Hui-iconfont">&nbsp;&#xe609;&nbsp;</i>删除</button></td>
      </tr>
      <tr class="text-c">
        <td class="text-c"><input name="" type="checkbox" value="" /></td>
        <td>2</td>
        <td>林某某</td>
        <td>监理</td>
        <td>B</td>
        <td>否</td>
        <td><a id="edit" href="#" class="button blue bigrounded" style="font-size:14px;"><i class="Hui-iconfont">&nbsp;&nbsp;&nbsp;&#xe647;</i>编辑&nbsp;&nbsp;&nbsp;</a></td>
<td><button class="button orange" style="font-size:14px;"><i class="Hui-iconfont">&nbsp;&#xe609;&nbsp;</i>删除</button></td>
      </tr>
     
      </tbody>
    </table>
			
		</form>
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
</div>
</body>
</html>