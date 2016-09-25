package com.yz.mapper;

import com.yz.po.Camera;
import com.yz.po.CameraExample;
import com.yz.po.Organize;
import com.yz.vo.CameraQueryVO;

import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CameraMapperCustom {

	//查询所有有效经纬度的设备
	public List<Camera> getCameraByUsefulLatlng();

	//查询所有
	public List<Camera> findCameraList();

	//根据编号查询单个设备
	public Camera findCameraByNumber(String number);

	//根据编号查询设备列表
	public List<Camera> findCameraListByNumber(String number);

	//根据编号及当前用户所在的组织id查询设备列表
	public List<Camera> getCameraListByNumberAndUserOrganizeid(CameraQueryVO cameraQueryVO);

	//根据组织id查询设备列表
	public List<Camera> getCameraListByOrganizeid(Integer organizeid);

	public List<Camera> findHiredCameralist();

	public List<Camera> findUnhiredCameralist();

}