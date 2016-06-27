package com.yz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/equip")
public class EquipController {
	@RequestMapping("/equipList")
	public String equipList() throws Exception{
		return "equip";
	}

}
