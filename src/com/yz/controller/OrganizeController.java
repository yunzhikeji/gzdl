package com.yz.controller;


import org.springframework.web.bind.annotation.RequestMapping;


public class OrganizeController {
	@RequestMapping("/organizeList")
	public String organizeList() throws Exception {
		return "organizelist";
	}

}
