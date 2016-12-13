package com.yz.facecloud.service;

import com.yz.facecloud.model.AlarmRequestMessage;
import com.yz.facecloud.model.AlarmResultMessage;
import com.yz.facecloud.model.BasicResultMessage;
import com.yz.facecloud.model.CameraMessage;
import com.yz.facecloud.model.CameraRequestMessage;
import com.yz.facecloud.model.CameraResultMessage;
import com.yz.facecloud.model.FaceDBRequestMessage;
import com.yz.facecloud.model.FaceDBResultMessage;
import com.yz.facecloud.model.FaceDataResultMessage;
import com.yz.facecloud.model.FaceRequestMessage;
import com.yz.facecloud.model.FaceResultMessage;
import com.yz.facecloud.model.ImageMessage;
import com.yz.facecloud.model.ImageRecognitionResultMessage;
import com.yz.facecloud.model.ImageRequestMessage;
import com.yz.facecloud.model.ImageResultMessage;
import com.yz.facecloud.model.LoginRequestMessage;
import com.yz.facecloud.model.LoginResultMessage;
import com.yz.facecloud.model.PolicyMessage;
import com.yz.facecloud.model.PolicyRequestMessage;
import com.yz.facecloud.model.PolicyResultMessage;

public interface HttpRequestService {
	
	public BasicResultMessage reboot();//重启服务只有root权限用户才可以执行
	
	public LoginResultMessage login(LoginRequestMessage requestMessage);//登陆
	
	public LoginResultMessage logout();//注销
	
	public PolicyResultMessage getPolices(PolicyRequestMessage requestMessage);//查询布控策略
	
	public PolicyResultMessage addPolice(PolicyMessage policyMessage);//新增布控策略
	
	public BasicResultMessage updatePolice(PolicyMessage policyMessage);//修改布控策略
	
	public BasicResultMessage deletePolice(PolicyRequestMessage requestMessage);//删除布控策略
	
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
	
	public ImageResultMessage getImage(ImageRequestMessage requestMessage);//获取抓拍照片
	
	public ImageResultMessage getFaceImage(ImageRequestMessage requestMessage);//获取抓拍人脸
	
	public FaceResultMessage getFaces(FaceRequestMessage requestMessage);//获得人脸列表
	
	public FaceDataResultMessage getFace(FaceRequestMessage requestMessage);//获得指定人脸列表
	
	public ImageRecognitionResultMessage getRecognition(ImageMessage imageMessage);//人脸识别
	
}
