package com.yz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {

	// 显示主页面
	@RequestMapping("/test")
	public String test() {
		return "redirect:test.jsp";
	}

	// 显示主页面
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/map")
	public String showMap() throws Exception {

		return "map";
	}

}
