package com.yz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.exception.CustomException;
import com.yz.po.Site;
import com.yz.po.SiteQueryVo;
import com.yz.service.SiteService;

@Controller
public class MapController {
	
	@Autowired
	private SiteService siteService;
	
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
	public String showMap(ModelMap map) throws Exception {
		
		List<Site> sites = siteService.getSites();
		
		if(sites!=null&&sites.size()>0)
		{
			Site site = sites.get(0);
			map.put("site", site);
		}
		return "map";
	}
	
	@RequestMapping("/getMarkers")
	public @ResponseBody List<Site> getMarkers(@RequestBody SiteQueryVo siteQueryVo){
		//@ResponseBody将itemsCustom转成json输出
		List<Site> sites = new ArrayList<Site>();
		try {
			if(siteQueryVo!=null&&siteQueryVo.getSiteCustom()!=null)
			{
				sites =  siteService.findSiteList(siteQueryVo);//暂未具体实现
			}else
			{
				sites = siteService.getSites();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sites;
	}

}
