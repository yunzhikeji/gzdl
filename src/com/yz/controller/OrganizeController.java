package com.yz.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yz.po.Organize;
import com.yz.service.OrganizeService;

@Controller
@RequestMapping("/organize")
public class OrganizeController {
	@Autowired
	private OrganizeService organizeService;
	
	@RequestMapping("/organizeList")
	public ModelAndView organizeList(HttpServletRequest request) throws Exception {
		List<Organize> organizeList = organizeService.findOrganizeList();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("organizeList", organizeList);
		modelAndView.setViewName("organize/organizeList");
		
		return modelAndView;
	}
	
	@RequestMapping("/toCompanyAdd")
	public String toCompanyAdd() throws Exception {
		return "organize/companyAdd";
	}

	@RequestMapping("/toGovermentAdd")
	public String toGovermentAdd() throws Exception {
		return "organize/govermentAdd";
	}
	
	
	//去编辑组织页面，并回显需要修改的组织信息
	@RequestMapping("/toUpdate")
	public String toUpdateOrganize(HttpServletRequest request,Model model,Integer id) throws Exception {
		Organize organize = organizeService.selectByPrimaryKey(id);
		model.addAttribute("organize",organize);
		return "organize/organizeUpdate";
	}
	
	//请求添加一个组织
	@RequestMapping("/addOrganize")
	public void addOrganize(HttpServletRequest request,Organize organize) throws Exception {
		organizeService.insertOrganize(organize);
	}
	
	
	//请求删除一个组织
	@RequestMapping("/deleteOrganize")
	public void deleteOrganize(HttpServletRequest request,Integer id)throws Exception {
		organizeService.deleteOrganizeById(id);
	}
}
