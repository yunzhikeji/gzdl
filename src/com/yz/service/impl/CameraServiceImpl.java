package com.yz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.mapper.CameraMapper;
import com.yz.mapper.CameraMapperCustom;
import com.yz.po.Camera;
import com.yz.po.CameraCustom;
import com.yz.service.CameraService;

public class CameraServiceImpl implements CameraService {
	
	@Autowired
	private CameraMapper camearMapper;
	
	@Autowired
	private CameraMapperCustom camearMapperCustom;

	@Override
	public CameraCustom findCameraById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCamera(Camera camera) throws Exception {
		// TODO Auto-generated method stub
		camearMapper.insert(camera);
	}

	@Override
	public void deleCamera(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCamera(Integer id, CameraCustom cameraCustom) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Camera> getCameraByUsefulLatlng() {
		// TODO Auto-generated method stub
		return camearMapperCustom.getCameraByUsefulLatlng();
	}

	@Override
	public List<Camera> findCameraList() throws Exception {
		
		return camearMapperCustom.findCameraList();
	}

	@Override
	public Camera findCameraByNumber(String number) {
		// TODO Auto-generated method stub
		return camearMapperCustom.findCameraByNumber(number);
	}

	@Override
	public void updateCamera(Camera camera) {
		// TODO Auto-generated method stub
		camearMapper.updateByPrimaryKey(camera);
	}

}
