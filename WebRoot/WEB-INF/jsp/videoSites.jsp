<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工地监控</title>
<link href="${pageContext.request.contextPath }/css/video.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<div style="float: left; width: 10%;">
		<ul id="nav">
			<c:forEach items="${siteList }" var="site" varStatus="index">
				<li><a
					href="${pageContext.request.contextPath }/site/showSites"
					target="myiframe">${site.name }</a>
					<ul class="collapsed">
						<li><a href="javascript:void(0)" id="tz"
							onClick="changeToTz('${site.id }');" target="viframe">查看监控</a></li>
					</ul></li>
			</c:forEach>
			<li style="border-bottom: 1px solid #b7b7b7;"><a
				href="javascript:void(0)" onClick="goback();">返回</a></li>
		</ul>
	</div>

	<div style="float: left; width: 90%">
		<iframe name="viframe" src="${pageContext.request.contextPath }/blank.html" id="viframe" frameborder="0"
			scrolling="no" width="100%" height="862px" marginheight="0"
			marginwidth="0"></iframe>
	</div>


	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript">
		function changeToTz(sid) {
			$("#viframe").attr('src', "${pageContext.request.contextPath}/camera/siteVideos?sid="+sid);
		}
	</script>
	<script>
		function goback() {
			history.go(-1);//返回或者history.back();
		}
		function go() {
			history.go(1);//前进或者history.forward();
		}
	</script>


</body>
</html>
