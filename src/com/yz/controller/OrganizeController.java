package com.yz.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/organize")
public class OrganizeController {
	@RequestMapping("/organizeList")
	public String organizeList() throws Exception {
		System.out.println("hello");
		return "organizelist";
	}

}
