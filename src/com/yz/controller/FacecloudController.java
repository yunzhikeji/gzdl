package com.yz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.CameraResultMessage;
import com.yz.facecloud.model.FaceDBResultMessage;
import com.yz.facecloud.model.LoginResultMessage;
import com.yz.facecloud.service.HttpRequestService;

@Controller
@RequestMapping("/facecloud")
public class FacecloudController {

	@Autowired
	private HttpRequestService requestService;

	@RequestMapping("/login")
	public @ResponseBody LoginResultMessage login() throws Exception {
		String username = "user";
		String password = "123456";
		return requestService.login(username, password);
	}
	
	
	@RequestMapping("/logout")
	public @ResponseBody LoginResultMessage logout() throws Exception {
		return requestService.logout();
	}
	
	
	@RequestMapping("/cameras")
	public @ResponseBody CameraResultMessage cameras() throws Exception {
		return requestService.getCameras();
	}
	
	@RequestMapping("/addCamera")
	public @ResponseBody CameraResultMessage addCamera() throws Exception {
		
		CameraMessage cameraMessage = new CameraMessage();
		
		cameraMessage.setCamera_name("aaa");
		cameraMessage.setCamera_mode(0);
		cameraMessage.setUrl("rtsp://admin:12345@192.168.0.64/h264/ch1/main/av_stream");
		cameraMessage.setDb_id_list("2+4+8");
		cameraMessage.setNode_id(11);
		cameraMessage.setFixed_host(1);
		cameraMessage.setMt_policy_id(11);
		
		return requestService.addCamera(cameraMessage);
	}
	
	
	@RequestMapping("/deleteCamera")
	public @ResponseBody CameraResultMessage deleteCamera() throws Exception {
		
		int camera_id = 1;
		return requestService.deleteCamera(1);
	}
	
	@RequestMapping("/recognition")
	public @ResponseBody CameraResultMessage recognition() throws Exception {
		
		int camera_id = 1;
		return requestService.recognition(1);
	}
	
	@RequestMapping("/recognitionStop")
	public @ResponseBody CameraResultMessage recognitionStop() throws Exception {
		
		int camera_id = 1;
		return requestService.recognitionStop(1);
	}
	
	@RequestMapping("/statreset")
	public @ResponseBody CameraResultMessage statreset() throws Exception {
		
		int camera_id = 1;
		return requestService.statreset(1);
	}
	
	@RequestMapping("/faceDBs")
	public @ResponseBody FaceDBResultMessage faceDBs() throws Exception {
		return requestService.getFaceDBs();
	}
	
	

}
