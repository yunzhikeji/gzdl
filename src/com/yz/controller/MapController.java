package com.yz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yz.po.Camera;
import com.yz.po.Organize;
import com.yz.service.CameraService;
import com.yz.service.OrganizeService;

@Controller
public class MapController {
	
	@Autowired
	private CameraService cameraService;
	
	@Autowired
	private OrganizeService organizeService;
	
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
		Organize organize = organizeService.selectByPrimaryKey(camera.getOrganizeid());
		map.put("camera", camera);
		map.put("organize", organize);
		return "exhibitionVideo";
	}
	

}
