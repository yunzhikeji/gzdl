package com.yz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.media.jai.iterator.RectIterCSM;
import com.yz.po.Camera;
import com.yz.po.Site;
import com.yz.po.SiteCustom;
import com.yz.service.CameraService;
import com.yz.service.SiteService;

@Controller
@RequestMapping("/camera")
public class CameraController {
	
	@Autowired
	private CameraService cameraService;
	@Autowired
	private SiteService siteService;
	
	
	@RequestMapping(value = "realTime", method = { RequestMethod.GET })
	public  String realTime(@RequestParam(value="markid", required = false) String  markid) {
		
		System.out.println("markid is" + markid);
		
		Site site = siteService.querySiteByMarkid(markid);
		
		List<Camera> cameras = cameraService.getCameraByUsefulLatlng();
		
		SiteCustom siteCustom = new SiteCustom();
		
		if(site!=null)
		{
			siteCustom.setName(site.getName());
			siteCustom.setAreaname(site.getAreaname());
			siteCustom.setCameras(cameras);;
		}else
		{
			return "fail";
		}

		return "singleVideo";
	}

}
