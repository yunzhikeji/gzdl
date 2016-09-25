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
        function refresh() {
            i = 0;
            getData($("#no").val());

        }


    </script>
    <script type="text/javascript" src="js/json2.js"></script>
    <script type="text/javascript"
        src="http://api.map.baidu.com/api?v=2.0&ak=MfZWrGmV9tab4tOxRB5zOCTdAGApBqU6"></script>
    <script src="js/jquery-1.8.2.js"></script>
</head>
<body>
    <div style="padding-left: 15px; padding-bottom: 5px; padding-top: 5px;">
        <span>设备查询条件:</span>&nbsp;&nbsp;&nbsp;
        <span style="margin-left: 20px"><select
								class="date_picker" id="s_province" name="province"></select>  
								<select class="date_picker" id="s_city" name="city"></select>  
								<select class="date_picker1" id="s_county" name="area"></select>
								<script class="resources library"
									src="${pageContext.request.contextPath }/js/area.js"
									type="text/javascript"></script> <script type="text/javascript">
										_init_area();
									</script></span><span id="show"></span> <input type="text" class="date_picker" name="name"
							placeholder="施工单位名称" id="organizeName" style="width: 150px" />
		<input type="text" class="date_picker" name="convalue" id="no"
							placeholder="设备编号" style="width: 150px" />
		

        <button class="loginBtn1 blue" onclick="refresh();" style="margin-left: 5px;"><i class="Hui-iconfont">&#xe665;</i>查询</button>
        <button class="loginBtn1 blue" onclick="window.location.reload();" style="margin-left: 5px;">刷新</button>
    </div>
    <div id="allmap"></div>

</body>
</html>
<script type="text/javascript">
    var map, json, i = 0;


    // 百度地图API功能
    var map = new BMap.Map("allmap");
    map.enableScrollWheelZoom();
    map.enableContinuousZoom();
    map.centerAndZoom("宜兴", 15);



    $(function () {
        //页面加载完成后制动调用数据
        getData();
        //整合注释下方代码，取消注释getData函数进行调试
        //json = [{ "id": 1, "sipid": null, "sipserverid": null, "lng": "119.809085", "lat": "31.349772", "cnumber": "54321", "cname": "测试摄像头", "voltage": null, "temperature": null, "status": null, "iscontroll": null, "stat": null, "state": null, "cameraid": null, "organizeid": null }]
        //addMarker();
        //----------------------------------------------
    });
    function getData(number) {
    	
        var s_province = $("#s_province").val();
		var s_city = $("#s_city").val();
		var s_county = $("#s_county").val();
		var name =$("#organizeName").val();
		if (!number) { number = ""; }
		if (!name) { name = ""; }
		
        var data = 'province=' + s_province + '&city='
		+ s_city + '&area=' + s_county
		+ '&type=' + 2+ '&name=' + name + '&number=' + number;
        
        $.ajax({
            type: 'post',
            async: false,
            url: '${pageContext.request.contextPath }/camera/getCameras',
            data : data,
            dataType : 'json',
            success: function (data) {//返回json结果
            	map.clearOverlays(); 
                json = data;
                addMarker();
            }

        });
    }
    function attribute(e) {
        window.open(
						"${pageContext.request.contextPath }/camera/singleVideo?id="
								+ this.data["id"],
						"_blank",
						"top=100,left=100,width=900,height=800,menubar=yes,scrollbars=no,toolbar=yes,status=yes");
    }

    function addMarker() {

        var convertor = new BMap.Convertor();
        var pointArr = [];
        
        pointArr.push(json[i]);
        convertor.translate(pointArr, 1, 5, translateCallback);
    }
    //坐标转换完之后的回调函数
    translateCallback = function (data) {
        if (data.status === 0) {
            var myIcon;
            var point = new BMap.Point(json[i]["lng"], json[i]["lat"]);
            var color = "green";
            switch (json[i]["type"]) {
                case "已施工":
                    color = "green";
                    myIcon = new BMap.Icon("./images/g.png", new BMap.Size(30, 30));
                    break;
                case "未施工":
                    color = "red";
                    myIcon = new BMap.Icon("./images/r.png", new BMap.Size(30, 30));
                    break;
                default:
                    color = "green";
                    myIcon = new BMap.Icon("./images/g.png", new BMap.Size(30,
                            30));
                    break;
            }
            var marker2 = new BMap.Marker(data.points[0], {
                icon: myIcon
            }); // 创建标注


            var label = new BMap.Label("设备：" + json[i]["cname"]
                + "<br/>" + "设备编号："+json[i]["cnumber"]
                + "<br/>" + "设备电压：" + json[i]["voltage"]
                + "<br/>" + "设备温度：" + json[i]["temperature"]
                + "<br/>" + "工作状态：" + getStatus(json[i]["status"])
                + "<br/>" + "是否已经布控：" + getIscontroll(json[i]["iscontroll"])
                + "<br/>" + "是否布控成功：" + getStat(json[i]["stat"]), {
                    offset: new BMap.Size(20, -10)
                });
            label.setStyle({ "padding": "5px", "-moz-border-radius": "10px", "-webkit-border-radius": "10px", "border-radius": "10px", "border": "2px solid " + color });
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
    function getStatus(val) {
        var text = "";
        switch (val) {
            case "-1":
                text = "未知状态";
                break;
            case "0":
                text = "关机";
                break;
            case "1":
                text = "正常工作";
                break;
            case "2":
                text = "重启中";
                break;
            default:
                text = "未知状态";
                break;
        }
        return text;
    }
    function getIscontroll(val) {
        var text = "";
        switch (val) {
            case "0":
                text = "未布控";
                break;
            case "1":
                text = "已布控";
                break;
            default:
                text = "未布控";
                break;
        }
        return text;
    }
    function getStat(val) {
        var text = "";
        switch (val) {
            case "0":
                text = "未布控成功";
                break;
            case "1":
                text = "已布控成功";
                break;
            default:
                text = "未布控成功";
                break;
        }
        return text;
    }
</script>

