<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/daily.css" rel="stylesheet" type="text/css">
<link href="css/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
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
	<div style="padding-left: 15px; padding-bottom:5px; padding-top:5px;">
		<span>设备查询条件:</span>&nbsp;&nbsp;&nbsp;
		<span>设备编号:</span>
         &nbsp;&nbsp;&nbsp;
		<input type="text"  class="date_picker2" name="convalue" />
		
		<button class="loginBtn1 blue" onClick="refresh();" style="margin-left:5px;"><i class="Hui-iconfont">&#xe665;</i>查询</button>
	</div>
	<div id="allmap"></div>

</body>
</html>
<script type="text/javascript">
	var map, json, i = 0;
	;
	//$.ajax({
	//	type : 'post',
	//	//async:false,
	//	url : '${pageContext.request.contextPath }/camera/getcameras.action',
	//	contentType : 'application/json;charset=utf-8',
	//	//数据格式是json串，商品信息
	//	success : function(data) {//返回json结果
	//		//json=JSON.stringify(data);//JSON object 转json串
	//		json = data;
	//		addMarker();
	//	}

    //});
	json = [{ lng: 119.825542, lat: 31.350327, type: "已施工", name: "工程1" },
	{ lng: 119.809085, lat: 31.349772, type: "已施工", name: "工程2" },
	{ lng: 119.844514, lat: 31.350697, type: "已施工", name: "工程3" },
	{ lng: 119.826835, lat: 31.345269, type: "已施工", name: "工程4" },
	{ lng: 119.836968, lat: 31.342432, type: "未施工", name: "工程5" },
	{ lng: 119.823098, lat: 31.338977, type: "未施工", name: "工程6" },
	{ lng: 119.833375, lat: 31.359578, type: "未施工", name: "工程7" }];
	
	console.log(json);
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	map.enableScrollWheelZoom();
	map.enableContinuousZoom();
	map.centerAndZoom("宜兴", 15);


	

	$(function() {
	    addMarker();
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
			var color = "green";
			switch (json[i]["type"]) {
			    case "已施工":
                    color="green"
			    myIcon = new BMap.Icon("./images/g.png", new BMap.Size(30, 30));
			    break;
			    case "未施工":
			        color = "red";
			    myIcon = new BMap.Icon("./images/r.png", new BMap.Size(30, 30));
			    break;
			default:
				myIcon = new BMap.Icon("./images/r.png", new BMap.Size(30,
						30));
				break;
			}
			var marker2 = new BMap.Marker(data.points[0], {
				icon : myIcon
			}); // 创建标注

			var label = new BMap.Label("工程名:" + json[i]["name"] + "&nbsp;&nbsp;&nbsp;&nbsp;工程状态:"
					+ json[i]["type"], {
				offset : new BMap.Size(20, -10)
			});
			label.setStyle({"padding":"5px","-moz-border-radius": "10px","-webkit-border-radius": "10px","border-radius":"10px","border":"2px solid "+color });
			marker2.setLabel(label);
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

