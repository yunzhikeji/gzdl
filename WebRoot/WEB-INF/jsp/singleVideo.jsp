<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>监控系统</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/test.css" />
<script type="text/javascript">
function $(id){return document.getElementById(id);}

function OnSend()
{  
  var ocx = $("ocx");  
  
 ocx.PlayReviewVideo("<business id='PlanTaskNumber14'><businessNode  type='入库作业'  ip='192.168.1.100'  port='37777'  channel='6'  timebegin='20140813:120500'  timestop='20140813:121000' ><messages><message>扦样人：张三；样品数量：1份；</message><messagetime>时间：2014-05-22 13:00；</messagetime></messages></businessNode>  <businessNode  type='入库作业2'  ip='192.168.1.100'  port='37777'  channel='6'  timebegin='20140813:140500'  timestop='20140813:141000' ><messages><message>扦样人：张三；样品数量：1份；</message><messagetime>时间：2014-05-22 13:00；</messagetime></messages></businessNode></business>"); 
}


function OnPlay1()
{  
	
  var ocx = $("ocx");  
  console.log(ocx);
  ocx.Login("test","test","115.28.135.48",20000); 
}
function OnPlay2()
{  
  var ocx = $("ocx");  
  console.log(ocx);
  ocx.PlayVideo("44030300001320020247"); /*   */

}
function OnPlay3()
{  
  var ocx = $("ocx");  
  ocx.StopRealplay(); 
  ocx.PlayReviewVideoByID("PlanTaskNumber3"); /*   */
}



function OnUp()
{  
  var ocx = $("ocx");  
  ocx.CamUp(); 
 
}

function OnDown()
{  
  var ocx = $("ocx");  
  ocx.CamDown();
}


function OnLeft()
{  
  var ocx = $("ocx");  
  ocx.CamLeft();
}

function OnRight()
{  
  var ocx = $("ocx");  
  ocx.CamRight();
}


function OnStop()
{  
  var ocx = $("ocx");  
  ocx.StopVideo(); 
}



function Onload()
{ 

}
</script> 
</head>
<body>
<div style="width:100%; position:relative;"> 
 <div class="yzrtv">
   <span style="font-weight:bold;">工地名称：</span><span style="padding-right:6px;">阳光一百</span><span style="font-weight:bold;">|</span>
   <span style="font-weight:bold;">工地位置：</span><span>解放东路2.35.67425655</span>   
</div>  
 <div class="yzvedio0" onLoad="Onload();">   
  <object CLASSID='CLSID:7A2B1F67-6568-4466-A0A5-EA7FF6EBE820' id='ocx' width='100%' height='197%' VIEWASTEXT>
  </object>
 <div style="margin-top:5px;">
<a href="#" class="button white" onClick="OnPlay1();">登录</a>
<a href="#" class="button white" onClick="OnPlay2();">播放</a>
<a href="#" class="button1 white" onClick="OnUp();">上</a>
<a href="#" class="button1 white" onClick="OnDown();">下</a>
<a href="#" class="button1 white" onClick="OnLeft();">左</a>
<a href="#" class="button1 white" onClick="OnRight();">右</a>
</div>
</div>

</div>






</body>
</html>
