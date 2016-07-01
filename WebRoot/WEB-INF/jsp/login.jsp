<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript">
//解决页面嵌套问题
		$(function(){
			document.forms[0].username.focus();
		});
		
		// 在被嵌套时就刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}
	</script>
<link href="css/logo.css" rel="stylesheet" type="text/css" />
<title>系统登录</title>
</head>

<body>
	<div class="yzbg">
		<div class="yzlgbg">
			<div id="tab">
				<div class="tabList">
					<ul>
						<li>云知账号登录</li>
					</ul>
				</div>
				<!-- 显示错误信息 -->
				<c:if test="${loginError!=null }">

					<center>
						<font color="red">${ loginError}<br /></font>
					</center>

				</c:if>

				<form action="${pageContext.request.contextPath }/login.action"
					method="post">
					<div class="tabCon">
						<li class="inputbox"><label class="u-label"></label> <input
							name="user.username" type="text" class="j-inputtext" tabindex="1" id="username"
							autocomplete="off" placeholder="云知账号"
							style="font-family: Microsoft YaHei; background: url(images/login.png) no-repeat 0% 50%; padding-left: 18px"
							spellcheck="false"></li>
						<li class="inputboxp"><label class="p-label"></label> <input
							name="user.password" class="j-inputtext" type="password"
							autocomplete="off" tabindex="2" placeholder="密码"
							style="font-family: Microsoft YaHei; background: url(images/loginp.png) no-repeat 0% 50%; padding-left: 18px"
							spellcheck="false"></li>
						<li class="inputboxs"><input class="y-inputtext" type="text"
							placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}"
							onclick="if(this.value=='验证码:'){this.value='';}"> <img
							class="y-inputtext" src="${pageContext.request.contextPath }/images/20160623142829.png"> <a
							href="javascript:;" style="background: #CCC">看不清，换一张</a></li>
						<li class="inputboxs"><input class="u-loginbtn" tabindex="6"
							type="submit" value="登&nbsp;&nbsp;录" /> <a class="u-loginbtnp"
							href="http://www.baidu.com" target="_blank" tabindex="7"
							style="margin-left: 1%">取&nbsp;&nbsp;消</a></li>

					</div>
				</form>
			</div>
		</div>
	</div>


</body>
</html>

