<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link href="${pageContext.request.contextPath }/css/logo.css"
	rel="stylesheet" type="text/css" />
<title>江苏云知智能科技有限公司</title>
</head>

<body>
	<form action="${pageContext.request.contextPath }/login.action"
		method="post">
		<div class="yzbg">
			<div class="yzlgbg">
				<div id="tab">
					<div class="tabList">
						<ul>
							<li class="cur"><img
								src="http://mimg.127.net/index/lib/img/icon_code.png" alt="二维码"
								style="position: relative; top: 2px; right: 2px;" />二维码登录</li>
							<li>云知账号登录</li>
						</ul>
					</div>
					<div class="tabCon">
						<div class="cur" style="width: 100%;">
							<center>
								<h3>手机扫码 安全防盗</h3>
								<img
									src="${pageContext.request.contextPath }/images/getUrlQrcode.jpg"
									width="130" height="130" style="margin-top: 15px" id="appCode">
								<p class="appLogin-hint">使用 云知APP 扫描二维码登录</p>
							</center>
						</div>
						<div style="width: 100%";>
							<li class="inputbox"><center>
									<label class="u-label"><img
										src="${pageContext.request.contextPath }/images/login.png"
										alt="用户名" style="position: relative; top: 3px; right: 2px;" />用户名</label>
									<input name="username" class="j-inputtext" type="text"
										autocomplete="off" tabindex="1" spellcheck="false">
								</center></li>
							<li class="inputboxp"><center>
									<label class="p-label"><img
										src="${pageContext.request.contextPath }/images/loginp.png"
										alt="密码" style="position: relative; top: 3px; right: 2px;" />密码</label>
									<input name="password" class="j-inputtext" type="text"
										autocomplete="off" tabindex="2" spellcheck="false">
								</center></li>
							<li class="inputboxp"><center>
									<span class="u-checkbox tabfocus"><input id="un-login"
										type="checkbox" name="un-login" class="un-login"></span><label
										for="un-login" style="color: #848585">十天内免登录</label> <a
										class="forgetPwd"
										href="http://reg.163.com/getpasswd/RetakePassword.jsp?from=mail126"
										target="_blank" title="找回密码"
										style="padding-left: 15%; color: #848585">忘记密码了?</a>
								</center></li>
							<li class="inputboxp"><center>
									<input class="u-loginbtn" tabindex="6" type="submit" value="登&nbsp;&nbsp;录"/>
									<a class="u-loginbtnp" href="http://www.baidu.com"
										target="_blank" tabindex="7" style="margin-left: 5%">注&nbsp;&nbsp;册</a>
								</center></li>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script>
		window.onload = function() {
			var oDiv = document.getElementById("tab");
			var oLi = oDiv.getElementsByTagName("div")[0]
					.getElementsByTagName("li");
			var aCon = oDiv.getElementsByTagName("div")[1]
					.getElementsByTagName("div");
			var timer = null;
			for (var i = 0; i < oLi.length; i++) {
				oLi[i].index = i;
				oLi[i].onmouseover = function() {
					show(this.index);
				}
			}
			function show(a) {
				index = a;
				var alpha = 0;
				for (var j = 0; j < oLi.length; j++) {
					oLi[j].className = "";
					aCon[j].className = "";
					aCon[j].style.opacity = 0;
					aCon[j].style.filter = "alpha(opacity=0)";
				}
				oLi[index].className = "cur";
				clearInterval(timer);
				timer = setInterval(function() {
					alpha += 2;
					alpha > 100 && (alpha = 100);
					aCon[index].style.opacity = alpha / 100;
					aCon[index].style.filter = "alpha(opacity=" + alpha + ")";
					alpha == 100 && clearInterval(timer);
				}, 5)
			}
		}
	</script>


</body>
</html>
