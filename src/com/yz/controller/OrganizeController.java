package com.yz.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/organize")
public class OrganizeController {
	@RequestMapping("/organizeList")
	public String organizeList() throws Exception {
		return "organize/organizeList";
	}
	
	@RequestMapping("/toCompanyAdd")
	public String toCompanyAdd() throws Exception {
		return "organize/companyAdd";
	}

	@RequestMapping("/toGovermentAdd")
	public String toGovermentAdd() throws Exception {
		return "organize/govermentAdd";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate() throws Exception {
		return "organize/organizeUpdate";
	}
}
