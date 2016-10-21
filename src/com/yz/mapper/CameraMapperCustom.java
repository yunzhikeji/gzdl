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

	//查询所有设备
	public List<Camera> findCameraList();

	//根据编号查询单个设备
	public Camera findCameraByNumber(String number);

	//根据编号查询设备列表
	public List<Camera> findCameraListByNumber(String number);

	//根据编号及当前用户所在的组织id查询设备列表
	public List<Camera> getCameraListByNumberAndUserOrganizeid(CameraQueryVO cameraQueryVO);

	//根据组织id查询设备列表
	public List<Camera> getCameraListByOrganizeid(Integer organizeid);

	//查询已出租设备列表
	public List<Camera> findHiredCameralist();

	//查询未出租设备列表
	public List<Camera> findUnhiredCameralist();

	//根据组织列表查询设备
	public List<Camera> findCameraListByOrganizes(List<Organize> organizes);
	
	//重置地图坐标为空用
	public int setLngNull();
	
	//重置地图坐标为空用
	public int setLatNull();

	//根据cameraid查询设备
	public Camera findCameraByCameraid(Integer cameraid);

}