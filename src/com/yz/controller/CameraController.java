package com.yz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		List<Camera> cameraList = cameraService.findCameraList();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cameraList", cameraList);
		modelAndView.setViewName("camera/camera");
		return modelAndView;
	}
	
	
	//去设备入库页面
	@RequestMapping("/toAdd")
	public String toAdd() throws Exception {
		return "camera/cameraAdd";
	}
	
	
	// 去设备编辑和出租页面，回显设备信息
	@RequestMapping("/toUpdate")
	public String toUpdate(HttpServletRequest request,Model model,Integer id) throws Exception {
		Camera camera = cameraService.findCameraById(id);
		model.addAttribute("camera", camera);
		return "camera/cameraUpdate";
	}
	
	// 请求添加一个设备
	@RequestMapping("/addCamera")
	public void addCamera(Camera camera) throws Exception {
		cameraService.insertCamera(camera);
	}
	
	
	// 请求删除一个设备
	@RequestMapping("/deleteCamera")
	public void  deleteCamera(Integer id) throws Exception {
		cameraService.deleCamera(id);
	}
}
