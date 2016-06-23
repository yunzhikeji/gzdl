package com.yz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.po.Site;
import com.yz.po.SiteCustom;
import com.yz.po.SiteQueryVo;
import com.yz.service.SiteService;

@Controller
@RequestMapping("/site")
public class SiteController {
	@Autowired
	private SiteService siteService;

	@RequestMapping(value = "checkSite", method = { RequestMethod.POST })
	public @ResponseBody String checkSite(@RequestBody SiteCustom siteCustom) {

		String backJson = "0";// 返回json状态码(0：表示没有查询到数据 1：表示查询到数据 -1:异常)
		try {
			List<Site> sites = siteService.checkSite(siteCustom);
			if (sites != null && sites.size() > 0) {
				backJson = "1";
			}
		} catch (Exception e) {
			backJson = "-1";
		}
		return backJson;
	}

	@RequestMapping(value = "saveSite", method = { RequestMethod.POST })
	public @ResponseBody String saveSite(@RequestBody Site site) {

		int backJson = 0;// 返回json状态码(0：表示初始化 1：表示操作成功 -1:异常)
		try {
			backJson = siteService.insertSite(site);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			backJson = -1;
		}
		return backJson+"";
	}
	
	
	@RequestMapping("/deleteSite")
	public @ResponseBody String deleteSite(@RequestBody Integer id) throws Exception {
		siteService.deleteSite(id);
		return "success";
	}

	@RequestMapping("/deleteSiteByMarkid")
	public @ResponseBody String deleteSiteByMarkid(@RequestBody String markid) throws Exception {

		String backJson = "0";// 返回json状态码(0：表示正常 -1:异常)
		try {
			int del = siteService.deleteSiteByMarkid(markid);
			System.out.println(del);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			backJson = "-1";
		}
		return backJson;

	}

}
