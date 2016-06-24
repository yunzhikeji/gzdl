package com.yz.mapper;

import com.yz.po.Camera;
import com.yz.po.CameraExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CameraMapperCustom {
	List<Camera> getCameraByUsefulLatlng();
}