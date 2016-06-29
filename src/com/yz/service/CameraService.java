package com.yz.service;

import java.util.List;

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

	public List<Camera> getCameraByUsefulLatlng();
	
    // 查询所有摄像头
	public List<Camera> findCameraList () throws Exception;

	public Camera findCameraByNumber(String number);

	public void updateCamera(Camera camera);

}
