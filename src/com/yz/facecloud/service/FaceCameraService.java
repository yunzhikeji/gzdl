package com.yz.facecloud.service;

import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.LoginResultMessage;

public interface FaceCameraService {
	
	//拼装 远程流媒体服务器rtsp流地址
	public String setRtspURL(String sipid);
	
	//数据库操作保存 人脸服务器摄像机id 以及修改设备布控状态success: true：表示布控成功 false：表示布控失败
	public void updateCamera(Integer id, int cameraid,boolean success) throws Exception;
	
	//检查登录状态
	public boolean checkLoginState(int ret);

    //判断当前设备在人脸服务器中的布控状态
	public boolean checkCameraStateOnFaceServer(int cameraid);

	//判断当前设备在人脸服务器中是否存在
	public CameraMessage checkCameraOnFaceServer(int cameraid);
	
	//登录
	public LoginResultMessage login() throws Exception;
	
	//获取登录cookie
	public int setLoginState();

}
