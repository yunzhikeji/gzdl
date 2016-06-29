package com.yz.mapper;

import com.yz.po.Camera;
import com.yz.po.CameraExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CameraMapperCustom {
	public List<Camera> getCameraByUsefulLatlng();

	// 查询所有

	public List<Camera> findCameraList();

	public Camera findCameraByNumber(String number);
}