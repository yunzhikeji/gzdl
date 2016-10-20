<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成功提示</title>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/layer.js"></script>
<script type="text/javascript">

//关闭子页面
function childPage_close()
{
    parent.location.reload();//刷新父类
    
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	
	parent.layer.close(index);
}

</script>
</head>
<body>
操作成功！<input type="button" onclick="childPage_close();" value="关闭"/>
</body>
</html>