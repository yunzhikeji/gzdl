package com.yz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yz.po.Site;
import com.yz.po.SiteCustom;
import com.yz.po.SiteQueryVo;
import com.yz.po.User;
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
			site.setViewstyle(0);
			backJson = siteService.insertSite(site);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			backJson = -1;
		}
		return backJson + "";
	}

	@RequestMapping("/deleteSiteByMarkid")
	public @ResponseBody String deleteSiteByMarkid(@RequestBody Site site) throws Exception {

		String backJson = "0";// 返回json状态码(0：表示正常 -1:异常)
		try {
			siteService.deleteSiteByMarkid(site.getMarkid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			backJson = "-1";
		}
		return backJson;

	}

	@RequestMapping("/showSites")
	public ModelAndView showSites(HttpServletRequest request) throws Exception {

		// 调用service查询数据库，查询用户列表
		List<Site> siteList = siteService.getSites();
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("siteList", siteList);
		modelAndView.setViewName("site");
		return modelAndView;

	}
	
	@RequestMapping("/showVideoSites")
	public ModelAndView showVideoSites(HttpServletRequest request) throws Exception {

		// 调用service查询数据库，查询用户列表
		List<Site> siteList = siteService.getSites();
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("siteList", siteList);
		modelAndView.setViewName("videoSites");
		return modelAndView;

	}
	
	
	
	@RequestMapping("/editSite")
	public String editSite(Model model,Integer id) throws Exception{
		Site site = siteService.findSiteById(id);
		
		model.addAttribute("site", site);
		return "siteEdit";
	}
	
	@RequestMapping("/editSiteSubmit")
	public String editSiteSubmit(Model model,
			HttpServletRequest request,@ModelAttribute("site") SiteCustom siteCustom,
			Integer id) throws Exception{
		
		siteService.updateSite(id, siteCustom);
		return "successSite";
	}
	
	@RequestMapping("deleteSite")
	public String deleteSite(Integer id) throws Exception {
		siteService.deleteSite(id);
		return "successSite";
	}

}
