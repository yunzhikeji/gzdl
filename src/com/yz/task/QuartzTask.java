package com.yz.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.service.CameraService;

public class QuartzTask {
	
	@Autowired
	private CameraService cameraService;
	
	
	public void doJob() {
		// TODO Auto-generated method stub
		cameraService.setLngAndLatNull();
	}


}
