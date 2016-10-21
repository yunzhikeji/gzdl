package com.yz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.facecloud.model.CameraMessage;
import com.yz.mapper.CameraMapper;
import com.yz.mapper.CameraMapperCustom;
import com.yz.mapper.OrganizeMapper;
import com.yz.mapper.OrganizeMapperCustom;
import com.yz.po.Camera;
import com.yz.po.Organize;
import com.yz.po.User;
import com.yz.service.CameraService;
import com.yz.service.OrganizeService;
import com.yz.vo.CameraQueryVO;
import com.yz.vo.OrganizeQueryVO;

public class CameraServiceImpl implements CameraService {
	
	@Autowired
	private CameraMapper cameraMapper;
	
	@Autowired
	private CameraMapperCustom cameraMapperCustom;
	
	@Autowired
	private OrganizeMapper organizeMapper;
	
	@Autowired
	private OrganizeMapperCustom organizeMapperCustom;
	
	@Autowired
	private OrganizeService organizeService;

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
		return cameraMapperCustom.getCameraByUsefulLatlng();
	}

	@Override
	public List<Camera> findCameraList() throws Exception {
		
		return cameraMapperCustom.findCameraList();
	}

	@Override
	public Camera findCameraByNumber(String number) {
		// TODO Auto-generated method stub
		return cameraMapperCustom.findCameraByNumber(number);
	}

	@Override
	public void updateCamera(Camera camera) {
		// TODO Auto-generated method stub
		cameraMapper.updateByPrimaryKey(camera);
	}

	@Override
	public List<Camera> findCameraListByNumber(String number) {
		// TODO Auto-generated method stub
		return cameraMapperCustom.findCameraListByNumber(number);
	}
	
	@Override
	public List<Camera> getCamerasByNumberAndOrganizeid(CameraQueryVO cameraQueryVO) {
		
		List<Camera> cameras = new ArrayList<Camera>();
		
		Organize userOrganize = organizeMapper.selectByPrimaryKey(cameraQueryVO.getUserOrganizeid());//当前用户所在组织
		
		int type  = userOrganize.getType();
		
		if(type==1)//组织类型  1：供电局  2：施工单位
		{
			int level = userOrganize.getLevel();
			List<Organize> organizes = new ArrayList<Organize>();
			OrganizeQueryVO organizeQueryVO = new OrganizeQueryVO();
			organizeQueryVO.setType(2);//设置所要查询的组织为施工单位
			switch (level) {
			case 1:
				//省级 先查询所有符合省级的施工单位
				String  province = userOrganize.getProvince();
				organizeQueryVO.setProvince(province);
				break;
			case 2:
				//市级 先查询所有符合市级的施工单位
				String  city = userOrganize.getCity();
				organizeQueryVO.setCity(city);
				break;
			case 3:
				//区级 先查询所有符合区级的施工单位
				String  area = userOrganize.getArea();
				organizeQueryVO.setArea(area);
				break;
			default:
				break;
			}
			organizes = organizeMapperCustom.findOrganizeListByOrganizeQueryVO(organizeQueryVO);//查询出当前供电局可查看施工单位
			
			if(organizes!=null&&organizes.size()>0)
			{
				cameras= cameraMapperCustom.findCameraListByOrganizes(organizes);
			}
			if(cameraQueryVO.getNumber()!=null&&!cameraQueryVO.getNumber().trim().equals(""))
			{
				List<Camera> camerasByNumber = cameraMapperCustom.findCameraListByNumber(cameraQueryVO.getNumber());
				cameras.retainAll(camerasByNumber);//取交集
			}
			
		}else if(type==2)
		{
			cameras = cameraMapperCustom.getCameraListByNumberAndUserOrganizeid(cameraQueryVO);
		}
		
		return cameras;
	}

	@Override
	public List<Camera> findHiredCameralist() {
		
		return cameraMapperCustom.findHiredCameralist();
	}

	@Override
	public List<Camera> findUnhiredCameralist() {
		return cameraMapperCustom.findUnhiredCameralist();
	}

	@Override
	public List<Camera> findCameraListByOrganizeQueryVO(OrganizeQueryVO organizeQueryVO) {
		
		List<Camera> cameras = new ArrayList<Camera>();
		
		List<Camera> cameraList =  new ArrayList<Camera>();

		List<Organize> organizes = organizeService.getOrganizesByOrganizeQueryVO(organizeQueryVO);// 根据查询条件获得符合条件的组织列表
		
		if(organizes!=null&&organizes.size()>0)
		{
		  cameraList = cameraMapperCustom.findCameraListByOrganizes(organizes);// 根据组织列表查询设备列表，不会包含没有组织的设备即未出租设备
		}
		
		List<Camera>  cameraListNoLease = cameraMapperCustom.findUnhiredCameralist();//未出租的设备
		
		cameraListNoLease.removeAll(cameraList);
		
		cameraList.addAll(cameraListNoLease);//取无重复并集

		cameras.addAll(cameraList);
		
		return cameras;
	}

	@Override
	public List<Camera> findCameraListByOrganizeId(Integer organizeId) {
		
		return cameraMapperCustom.getCameraListByOrganizeid(organizeId);
	}

	
	@Override
	public void setLngAndLatNull() {
		cameraMapperCustom.setLatNull();
		cameraMapperCustom.setLngNull();
		
	}

	@Override
	public Camera findCameraByCameraid(Integer cameraid) {
		// TODO Auto-generated method stub
		return cameraMapperCustom.findCameraByCameraid(cameraid);
	}

}
