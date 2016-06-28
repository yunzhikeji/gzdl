<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>工地管理</title>
<link href="${pageContext.request.contextPath }/css/build.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/1.0.7/iconfont.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function childPage_close() {
		parent.location.reload();//刷新父类
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
</script>

</head>

<body>
	<div class="pd-20">
		<form
			action="${pageContext.request.contextPath }/site/editSiteSubmit.action"
			method="post" class="form form-horizontal" id="siteForm">
			<input type="hidden" name="id" value="${site.id }" />
			<table class="table">
				<thead class="text-c">
					<tr>
						<th colspan="2">工地名称</th>
						<td width="70%"><input type="text" name="name"
							value="${site.name }" /><input type="hidden" name="markid"
							value="${site.markid }" /></td>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c">
						<th width="15%" rowspan="2">工地位置</th>
						<td width="15%" class="text-b">经度</td>
						<td><input type="text" name="lng" value="${site.lng }" /></td>
					</tr>
					<tr class="text-c">
						<td class="text-b">纬度</td>
						<td><input type="text" name="lat" value="${site.lat }" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">监控视频显示个数</th>
						<td><select name="viewstyle" style="width: 150px;">
								<option value="1">1</option>
								<option value="4">4</option>
								<option value="6">6</option>
						</select></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">承包单位</th>
						<td><input type="text" width="300px" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">承包单位负责人</th>
						<td><input type="text" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">联系电话号码</th>
						<td><input type="text" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">监理单位</th>
						<td><input type="text" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">监理单位负责人</th>
						<td><input type="text" /></td>
					</tr>
					<tr class="text-c">
						<th colspan="2">联系电话号码</th>
						<td><input type="text" /></td>
					</tr>
					<tr class="text-c">
						<th style="background: #FFF; border: #FFF; text-align: center;"
							colspan="3"><input class="button orange smallrouded"
							tabindex="6" type="button" value="取&nbsp;&nbsp;消"
							onclick="childPage_close();" /> <input
							class="button blue smallrouded" tabindex="6" type="submit"
							value="保&nbsp;&nbsp;存" /></th>
					</tr>
				</tbody>
			</table>


		</form>

	</div>
</body>
</html>
