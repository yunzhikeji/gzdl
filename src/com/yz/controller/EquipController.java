package com.yz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yz.po.Camera;
import com.yz.service.CameraService;

@Controller
@RequestMapping("/equip")
public class EquipController {
	
	@Autowired
	private CameraService cameraService;
	
	@RequestMapping("/equipList")
	public ModelAndView equipList(HttpServletRequest request) throws Exception{
		
		List<Camera> cameraList = cameraService.findCameraList();
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("cameraList", cameraList);
		
		modelAndView.setViewName("equip");
		
		return modelAndView;
	}

}
