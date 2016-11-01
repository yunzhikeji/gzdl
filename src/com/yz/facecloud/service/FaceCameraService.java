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
	public boolean checkCameraStateOnFaceServer(int cameraid) throws Exception;

	//判断当前设备在人脸服务器中是否存在
	public CameraMessage checkCameraOnFaceServer(int cameraid) throws Exception;
	
	//登录
	public LoginResultMessage login() throws Exception;
	
	//登录操作
	public Integer operationLogin (int num) throws Exception;
	
	//(判断并删除)优化人脸服务器上的摄像头,返回已优化的摄像头个数（0-4之间的值）
	public int optimizeCameraOnFaceServer() throws Exception;

	//删除人脸服务器上当前未布控的摄像头
	public void deleteCameraOnFaceServer();

}
