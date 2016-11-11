package com.yz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yz.po.Camera;
import com.yz.service.CameraService;

@Controller
public class MapController {
	
	@Autowired
	private CameraService cameraService;
	
	// 显示测试页面
	@RequestMapping("/test")
	public String test() {
		return "redirect:test.jsp";
	}	

	// 显示主页面
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	//显示地图页面 
	@RequestMapping("/map")
	public String showMap(ModelMap map) throws Exception {
		
		return "map";
	}
	
	
	//显示展厅页面
	@RequestMapping("/zt")
	public String showZt(ModelMap map) throws Exception {
		
		Camera camera = cameraService.findCameraById(16);
		map.put("camera", camera);
		return "exhibitionVideo";
	}
	

}
