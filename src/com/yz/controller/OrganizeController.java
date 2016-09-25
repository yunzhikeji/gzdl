package com.yz.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yz.po.Camera;
import com.yz.po.Organize;
import com.yz.service.OrganizeService;
import com.yz.service.UserService;
import com.yz.vo.CameraQueryVO;
import com.yz.vo.OrganizeQueryVO;

@Controller
@RequestMapping("/organize")
public class OrganizeController {
	@Autowired
	private OrganizeService organizeService;
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/organizeList")
	public ModelAndView organizeList(OrganizeQueryVO organizeQueryVO,HttpServletRequest request) throws Exception {
		
		
		List<Organize> organizeList = organizeService.getOrganizesByOrganizeQueryVO(organizeQueryVO);// 根据查询条件获得组织列表
		
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
	
	
	//去编辑施工单位页面，并回显需要修改的施工单位信息
	@RequestMapping("/toCompanyUpdate")
	public String toUpdateOrganize(HttpServletRequest request,Model model,Integer id) throws Exception {
		Organize organize = organizeService.selectByPrimaryKey(id);
		model.addAttribute("organize",organize);
		return "organize/companyUpdate";
	}
	
	//去编辑施工单位页面，并回显需要修改的施工单位信息
	@RequestMapping("/toGovermentUpdate")
	public String toGovermentUpdate(HttpServletRequest request,Model model,Integer id) throws Exception {
		Organize organize = organizeService.selectByPrimaryKey(id);
		model.addAttribute("organize",organize);
		return "organize/govermentUpdate";
	}
	
	
	
	
	//请求添加一个组织
	@RequestMapping("/addOrganize")
	public String addOrganize(HttpServletRequest request,Organize organize) throws Exception {
		Organize or = new Organize();
		or.setName(organize.getName());
		or.setType(organize.getType());
		or.setAddress(organize.getAddress());
		or.setContact(organize.getContact());
		or.setPhone(organize.getPhone());
		or.setLevel(organize.getLevel());
		if(organize.getProvince().equals("省份")) {
			or.setProvince("");
		}else or.setProvince(organize.getProvince());
		if(organize.getCity().equals("地级市")) {
			or.setCity("");
		}else or.setCity(organize.getCity());
		if(organize.getArea().equals("市、县级市")){
			or.setArea("");
		}else or.setArea(organize.getArea());
		
		organizeService.insertOrganize(or);
		return "success";

//		if(organize.getType()==1){
//			return "redirect:/organize/toGovermentAdd";
//		}else
//		return "redirect:/organize/toCompanyAdd";
	}
	
	
	//请求删除一个组织,删除组织的同时，删除该组织下的所有用户
	@RequestMapping("/deleteOrganize")
	public String  deleteOrganize(HttpServletRequest request,Integer id)throws Exception {
		organizeService.deleteOrganizeById(id);
	//删除组织的同时，删除该组织下的所有用户	
		userService.deleteUserByOrganizeId(id);
		return "redirect:/organize/organizeList";
	}
	
	//组织信息修改提交
	@RequestMapping("/updateOrganizeSubmit")
	public String updateOrganizeSubmit(Model model,HttpServletRequest request,Integer id,Organize organize) throws Exception {
		organizeService.updateOrganize(id, organize);
		
		return "success";
	}
	
	
	
	/**
	 * ajax 三级联动查询组织
	 * @param organizeQueryVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getOrganizes")
	public @ResponseBody List<Organize> getOrganizes(OrganizeQueryVO organizeQueryVO) throws Exception {
														
		List<Organize> organizes = new ArrayList<Organize>();
		
		organizes =  organizeService.getOrganizesByOrganizeQueryVO(organizeQueryVO);
		
		return organizes;
	}
	
	
	
}
