package com.yz.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/organize")
public class OrganizeController {
	@RequestMapping("/organizeList")
	public String organizeList() throws Exception {
		return "organize/organizelist";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() throws Exception {
		return "organize/organizeadd";
	}

	@RequestMapping("/toUpdate")
	public String toUpdate() throws Exception {
		return "organize/organizeUpdate";
	}
}
