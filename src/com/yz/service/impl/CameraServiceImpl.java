package com.yz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.mapper.CameraMapper;
import com.yz.mapper.CameraMapperCustom;
import com.yz.mapper.OrganizeMapper;
import com.yz.mapper.OrganizeMapperCustom;
import com.yz.po.Camera;
import com.yz.po.CameraCustom;
import com.yz.po.Organize;
import com.yz.service.CameraService;
import com.yz.vo.CameraQueryVO;

public class CameraServiceImpl implements CameraService {
	
	@Autowired
	private CameraMapper cameraMapper;
	
	@Autowired
	private CameraMapperCustom camearMapperCustom;
	
	@Autowired
	private OrganizeMapper organizeMapper;
	
	@Autowired
	private OrganizeMapperCustom organizeMapperCustom;

	@Override
	public Camera findCameraById(Integer id) throws Exception {
		
		return cameraMapper.selectByPrimaryKey(id);
	}

	@Override
	public void insertCamera(Camera camera) throws Exception {
		cameraMapper.insert(camera);
	}

	@Override
	public void deleCamera(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCamera(Integer id, Camera camera) throws Exception {
		if(id != null) {
			camera.setId(id);
			cameraMapper.updateByPrimaryKeySelective(camera);
		}
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
		cameraMapper.updateByPrimaryKey(camera);
	}

	@Override
	public List<Camera> findCameraListByNumber(String number) {
		// TODO Auto-generated method stub
		return camearMapperCustom.findCameraListByNumber(number);
	}
	
	@Override
	public List<Camera> getCamerasByNumberAndOrganizeid(CameraQueryVO cameraQueryVO) {
		
		List<Camera> cameras = new ArrayList<Camera>();
		
		Organize userOrganize = organizeMapper.selectByPrimaryKey(cameraQueryVO.getUserOrganizeid());//当前用户所在组织
		
		int type  = userOrganize.getType();
		
		if(type==1)//组织类型  1：供电局  2：施工单位
		{
			
		}else if(type==2)
		{
			System.out.println("UserOrganizeid:"+cameraQueryVO.getUserOrganizeid());
			cameras = camearMapperCustom.getCameraListByNumberAndUserOrganizeid(cameraQueryVO);
		}
		
		return cameras;
	}

}
