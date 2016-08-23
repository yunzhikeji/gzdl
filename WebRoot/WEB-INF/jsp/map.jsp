<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style type="text/css">
body, html, #allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
<script type="text/javascript">
	function refresh()
	{
		window.location.reload();
	}


</script>
<script type="text/javascript" src="js/json2.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=MfZWrGmV9tab4tOxRB5zOCTdAGApBqU6"></script>
<script src="js/jquery-1.8.2.js"></script>
</head>
<body>
	<div style="padding-left: 15px;">
		<span style="font-size:13px;">设备查询条件:</span>
		
		<span style="font-size:13px;">作业性质</span>
		<select>
			<option>10KV以上</option>
			<option>10KV以下</option>
		</select>
		
		<select>
			<option>设备编号</option>
			<option>施工单位</option>
		</select>
		<input type="text" name="convalue" />
		
		<input type="button" value="查询" onclick="refresh();"/>
	</div>
	<div id="allmap"></div>

</body>
</html>
<script type="text/javascript">
	var map, json, i = 0;
	;
	$.ajax({
		type : 'post',
		//async:false,
		url : '${pageContext.request.contextPath }/camera/getcameras.action',
		contentType : 'application/json;charset=utf-8',
		//数据格式是json串，商品信息
		success : function(data) {//返回json结果
			//json=JSON.stringify(data);//JSON object 转json串
			json = data;
			addMarker();
		}

	});
	console.log(json);
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	map.enableScrollWheelZoom();
	map.enableContinuousZoom();
	map.centerAndZoom("宜兴", 15);

	$(function() {

	});
	function attribute(e) {
		window
				.open(
						"${pageContext.request.contextPath }/camera/singleVideo?id="
								+ this.data["id"],
						"_blank",
						"top=100,left=100,width=900,height=800,menubar=yes,scrollbars=no,toolbar=yes,status=yes");
	}

	function addMarker() {

		var convertor = new BMap.Convertor();
		var pointArr = [];
		pointArr.push(json[i]);
		convertor.translate(pointArr, 1, 5, translateCallback)

	}
	//坐标转换完之后的回调函数
	translateCallback = function(data) {
		if (data.status === 0) {
			var myIcon;
			var point = new BMap.Point(json[i]["lng"], json[i]["lat"]);

			switch (json[i]["temperature"]) {
			//case "a":
			//    myIcon = new BMap.Icon("./images/g_m.png", new BMap.Size(30, 30));
			//    break;
			//case "b":
			//    myIcon = new BMap.Icon("./images/b_m.png", new BMap.Size(30, 30));
			//    break;
			//case "c":
			//    myIcon = new BMap.Icon("./images/r_m.png", new BMap.Size(30, 30));
			//    break;
			default:
				myIcon = new BMap.Icon("./images/r_m.png", new BMap.Size(30,
						30));
				break;
			}
			var marker2 = new BMap.Marker(data.points[0], {
				icon : myIcon
			}); // 创建标注

			var label = new BMap.Label("ID:" + json[i]["id"] + "temperature:"
					+ json[i]["temperature"], {
				offset : new BMap.Size(20, -10)
			});

			marker2.setTitle("ID:" + json[i]["id"] + "temperature:"
					+ json[i]["temperature"]);
			marker2.data = json[i];
			marker2.addEventListener("click", attribute);
			map.addOverlay(marker2);
		}

		i++;
		if (i < json.length) {
			addMarker();
		}
	}
</script>
