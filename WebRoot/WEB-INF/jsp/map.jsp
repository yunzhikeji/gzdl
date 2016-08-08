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
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=MfZWrGmV9tab4tOxRB5zOCTdAGApBqU6"></script>
    <script src="js/jquery-1.8.2.js"></script>
</head>
<body>
    <div id="allmap"></div>

</body>
</html>
<script type="text/javascript">
    var json = [{ id: "1", type: "a", lng: "119.819074", lat: "31.345948" },
    { id: "2", type: "a", lng: "119.838908", lat: "31.33762" },
    { id: "3", type: "a", lng: "119.811025", lat: "31.333734" },
    { id: "4", type: "a", lng: "119.82238", lat: "31.354521" },
    { id: "5", type: "b", lng: "119.846167", lat: "31.349957" },
    { id: "6", type: "b", lng: "119.83898", lat: "31.341568" },
    { id: "7", type: "b", lng: "119.830069", lat: "31.350944" },
    { id: "8", type: "b", lng: "119.849113", lat: "31.354706" },
    { id: "9", type: "b", lng: "119.812175", lat: "31.350142" },
    { id: "10", type: "b", lng: "119.835171", lat: "31.337127" },
    { id: "11", type: "c", lng: "119.831693", lat: "31.346594" },
    { id: "12", type: "c", lng: "119.821964", lat: "31.361265" }];


    // 百度地图API功能
    var map = new BMap.Map("allmap");
    map.enableScrollWheelZoom();
    map.enableContinuousZoom();
    map.centerAndZoom("宜兴", 15);

    $(function () {
        addMarker();
    });
    function attribute(e) {
        alert("id:" + this.data["id"]);
    }
    var i = 0;
    function addMarker() {

        var convertor = new BMap.Convertor();
        var pointArr = [];
        pointArr.push(json[i]);
        convertor.translate(pointArr, 1, 5, translateCallback)


    }
    //坐标转换完之后的回调函数
    translateCallback = function (data) {
        if (data.status === 0) {
            var myIcon;
            var point = new BMap.Point(json[i]["lng"], json[i]["lat"]);

            switch (json[i]["type"]) {
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
                    myIcon = new BMap.Icon("./images/camera.png", new BMap.Size(30, 30));
                    break;
            }
            var marker2 = new BMap.Marker(data.points[0], { icon: myIcon });  // 创建标注

            var label = new BMap.Label("ID:" + json[i]["id"] + "  TYPE:" + json[i]["type"], { offset: new BMap.Size(20, -10) });

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
