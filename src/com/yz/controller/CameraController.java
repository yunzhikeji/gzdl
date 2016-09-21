package com.yz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yz.po.Camera;
import com.yz.po.Organize;
import com.yz.service.CameraService;
import com.yz.service.OrganizeService;
import com.yz.vo.CameraQueryVO;

@Controller
@RequestMapping("/camera")
public class CameraController {

	@Autowired
	private CameraService cameraService;
	
	@Autowired
	private OrganizeService organizeService;

	// 单个监控画面
	@RequestMapping(value = "singleVideo", method = { RequestMethod.GET })
	public String singleVideo(@RequestParam(value = "id", required = false) Integer id, ModelMap map) throws Exception {
		System.out.println(id);
		Camera camera = cameraService.findCameraById(id);
		map.put("camera", camera);
		return "singleVideo";

	}

	//地图获取设备列表
	/**
	 * @param number
	 * @return
	 * @throws Exception
	 */
	// 地图返回
	@RequestMapping("/getCameras")
	public @ResponseBody List<Camera> getAllCameras(String number,HttpSession session) throws Exception {
		
		List<Camera> cameras = new ArrayList<Camera>();
		
		Integer userOrganizeid = (Integer) session.getAttribute("organizeid");
		
		CameraQueryVO cameraQueryVO = new CameraQueryVO();
		cameraQueryVO.setNumber(number);
		cameraQueryVO.setUserOrganizeid(userOrganizeid);
		
		cameras =  cameraService.getCamerasByNumberAndOrganizeid(cameraQueryVO);
		
		return cameras;
	}
	
	//设备管理列表
	@RequestMapping("/cameraList")
	public ModelAndView cameraList() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("camera/camera");
		return modelAndView;
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() throws Exception {
		return "camera/cameraAdd";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate() throws Exception {
		return "camera/cameraUpdate";
	}

}
