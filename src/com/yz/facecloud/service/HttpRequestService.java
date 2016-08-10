package com.yz.facecloud.service;

import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.CameraResultMessage;
import com.yz.facecloud.model.LoginResultMessage;

public interface HttpRequestService {
	
	public LoginResultMessage login(String username,String password);
	
	public LoginResultMessage logout();
	
	public CameraResultMessage getCameras();
	
	public CameraResultMessage addCamera(CameraMessage cameraMessage);
	
	public CameraResultMessage deleteCamera(int camera_id);
	
	public CameraResultMessage statreset(int camera_id);
	

}
