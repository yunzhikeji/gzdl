function $(id){return document.getElementById(id);}

function OnSend()
{  
  var ocx = $("ocx");  
  
 ocx.PlayReviewVideo("<business id='PlanTaskNumber14'><businessNode  type='入库作业'  ip='192.168.1.100'  port='37777'  channel='6'  timebegin='20140813:120500'  timestop='20140813:121000' ><messages><message>扦样人：张三；样品数量：1份；</message><messagetime>时间：2014-05-22 13:00；</messagetime></messages></businessNode>  <businessNode  type='入库作业2'  ip='192.168.1.100'  port='37777'  channel='6'  timebegin='20140813:140500'  timestop='20140813:141000' ><messages><message>扦样人：张三；样品数量：1份；</message><messagetime>时间：2014-05-22 13:00；</messagetime></messages></businessNode></business>"); 
}


function OnPlay1()
{  
  var ocx = $("ocx");  
  ocx.Login("test","test","115.28.135.48",20000); 
}
function OnPlay2()
{  
  var ocx = $("ocx");  
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
