package com.yz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.po.Camera;
import com.yz.service.CameraService;

@Controller
@RequestMapping("/camera")
public class CameraController {

	@Autowired
	private CameraService cameraService;

	@RequestMapping(value = "singleVideo", method = { RequestMethod.GET })
	public String singleVideo(@RequestParam(value = "id", required = false) Integer id, ModelMap map) throws Exception {
		System.out.println(id);
		Camera camera = cameraService.findCameraById(id);
		map.put("camera", camera);

		return "singleVideo";

	}

	@RequestMapping("/getcameras")
	public @ResponseBody List<Camera> getAllCameras() throws Exception {
		return cameraService.findCameraList();
	}

}
