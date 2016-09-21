package com.yz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {
	
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
	

}
