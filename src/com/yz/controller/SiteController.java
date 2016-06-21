package com.yz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.po.Site;
import com.yz.service.SiteService;

@Controller
@RequestMapping("/site")
public class SiteController {
	@Autowired
	private SiteService siteService;
	@RequestMapping("/saveSite")
	public @ResponseBody String saveSite(@RequestBody Site site) throws Exception{
		
		siteService.insertSite(site);
		return "success";
		
	}
	
	@RequestMapping("/deleteSite")
	public @ResponseBody String deleteSite(@RequestBody Integer id) throws Exception {
		
		siteService.deleteSite(id);
		return "success";
		
	}

}
