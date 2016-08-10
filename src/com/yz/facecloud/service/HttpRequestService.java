package com.yz.facecloud.service;

import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.CameraResultMessage;
import com.yz.facecloud.model.FaceDBResultMessage;
import com.yz.facecloud.model.LoginResultMessage;

public interface HttpRequestService {
	
	public LoginResultMessage login(String username,String password);//登陆
	
	public LoginResultMessage logout();//注销
	
	public CameraResultMessage getCameras();//获得摄像机列表
	
	public CameraResultMessage addCamera(CameraMessage cameraMessage);//增加摄像机
	
	public CameraResultMessage deleteCamera(int camera_id);//删除摄像机
	
	public CameraResultMessage statreset(int camera_id);//清空摄像机数据
	
	public CameraResultMessage recognition(int camera_id);//开始人脸布控
	
	public CameraResultMessage recognitionStop(int camera_id);//停止人脸布控
	
	public FaceDBResultMessage getFaceDBs();//获得人脸库列表
	
}
