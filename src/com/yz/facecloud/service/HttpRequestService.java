package com.yz.facecloud.service;

import com.yz.facecloud.model.AlarmRequestMessage;
import com.yz.facecloud.model.AlarmResultMessage;
import com.yz.facecloud.model.BasicResultMessage;
import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.CameraRequestMessage;
import com.yz.facecloud.model.CameraResultMessage;
import com.yz.facecloud.model.FaceDBRequestMessage;
import com.yz.facecloud.model.FaceDBResultMessage;
import com.yz.facecloud.model.LoginResultMessage;
import com.yz.facecloud.model.PolicyRequestMessage;
import com.yz.facecloud.model.PolicyResultMessage;

public interface HttpRequestService {
	
	public BasicResultMessage reboot();//重启服务只有root权限用户才可以执行
	
	public LoginResultMessage login(String username,String password);//登陆
	
	public LoginResultMessage logout();//注销
	
	public PolicyResultMessage getPolices(PolicyRequestMessage requestMessage);//查询布控策略
	
	public CameraResultMessage getCameras(CameraRequestMessage requestMessage);//获得摄像机列表
	
	public CameraResultMessage addCamera(CameraMessage cameraMessage);//增加摄像机
	
	public CameraResultMessage updateCamera(CameraMessage cameraMessage);//配置摄像机
	
	public CameraResultMessage deleteCamera(int camera_id);//删除摄像机
	
	public CameraResultMessage statreset(int camera_id);//清空摄像机数据
	
	public CameraResultMessage recognition(int camera_id);//开始人脸布控
	
	public CameraResultMessage recognitionStop(int camera_id);//停止人脸布控
	
	public FaceDBResultMessage getFaceDBs(FaceDBRequestMessage requestMessage);//获得人脸库列表
	
	public AlarmResultMessage getAlarms(AlarmRequestMessage requestMessage);//查询告警记录
	
	public AlarmResultMessage deleteAlarms(AlarmRequestMessage requestMessage);//删除告警记录
	
	
	
}
