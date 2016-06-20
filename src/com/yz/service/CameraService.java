package com.yz.service;

import com.yz.po.Camera;
import com.yz.po.CameraCustom;

public interface CameraService {

	// 根据id查询摄像头信息
	public CameraCustom findCameraById(Integer id) throws Exception;

	// 添加一个摄像头
	public void insertCamera(Camera camera) throws Exception;

	// 根据id删除摄像头
	public void deleCamera(Integer id) throws Exception;
	
	// 修改摄像头信息
	public void updateCamera(Integer id,CameraCustom cameraCustom) throws Exception;
	
	// 批量删除

}
