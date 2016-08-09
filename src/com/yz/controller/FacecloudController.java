package com.yz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.facecloud.model.CameraResultMessage;
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

}
