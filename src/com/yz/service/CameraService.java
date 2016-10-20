package com.yz.service;

import java.util.List;

import com.yz.po.Camera;
import com.yz.vo.CameraQueryVO;
import com.yz.vo.OrganizeQueryVO;

public interface CameraService {

	// 根据id查询摄像头信息
	public Camera findCameraById(Integer id) throws Exception;

	// 添加一个摄像头
	public void insertCamera(Camera camera) throws Exception;

	// 根据id删除摄像头
	public void deleCamera(Integer id) throws Exception;
	
	// 修改摄像头信息
	public void updateCamera(Integer id,Camera camera) throws Exception;

	// 根据有效经纬度，查询摄像头列表
	public List<Camera> getCameraByUsefulLatlng();
	
    // 查询所有摄像头
	public List<Camera> findCameraList () throws Exception;

	// 根据设备编号,查询单个摄像头(设备)列表
	public Camera findCameraByNumber(String number);

	// 修改摄像头信息
	public void updateCamera(Camera camera);

	// 根据设备编号,模糊查询所有摄像头(设备)列表
	public List<Camera> findCameraListByNumber(String number);
	
	//根据CameraQueryVO,查询所有摄像头(设备)列表
	public List<Camera> getCamerasByNumberAndOrganizeid(CameraQueryVO cameraQueryVO);
	
	//查询已经出租的设备列表
	public List<Camera> findHiredCameralist();
	
	//查询未出租的设备列表
	public List<Camera> findUnhiredCameralist();

	//根据OrganizeQueryVO查询设备列表
	public List<Camera> findCameraListByOrganizeQueryVO(OrganizeQueryVO organizeQueryVO);

	//根据organizeid查询设备列表
	public List<Camera> findCameraListByOrganizeId(Integer organizeId);
	
	// 重置地图坐标为空
	public void setLngAndLatNull();
}
