package com.yz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.mapper.CameraMapperCustom;
import com.yz.mapper.OrganizeMapper;
import com.yz.mapper.OrganizeMapperCustom;
import com.yz.po.Camera;
import com.yz.po.Organize;
import com.yz.service.OrganizeService;
import com.yz.vo.CameraQueryVO;
import com.yz.vo.OrganizeQueryVO;

public class OrganizeServiceImpl implements OrganizeService {

	@Autowired
	private OrganizeMapper organizeMapper;

	@Autowired
	private OrganizeMapperCustom organizeMapperCustom;

	@Override
	public Organize selectByPrimaryKey(Integer organizeid) {

		return organizeMapper.selectByPrimaryKey(organizeid);

	}

	public List<Camera> getCamerasByNumberAndOrganizeid(String number, Integer userOrganizeid) {

		Organize userOrganize = organizeMapper.selectByPrimaryKey(userOrganizeid);// 当前用户所在组织

		int type = userOrganize.getType();

		return null;
	}

	@Override
	public void deleteOrganizeById(Integer id) {
		organizeMapper.deleteByPrimaryKey(id);

	}

	@Override
	public void updateOrganize(Integer id, Organize organize) {
		organize.setId(id);
		organizeMapper.updateByPrimaryKeySelective(organize);
	}

	@Override
	public void insertOrganize(Organize organize) {
		organizeMapper.insert(organize);

	}

	@Override
	public List<Organize> findOrganizeList() {

		return organizeMapperCustom.findOrganizeList();
	}

	@Override
	public Organize findOrganizeByName(String organizeName) {
		return organizeMapperCustom.findOrganizeByName(organizeName);
	}

	@Override
	public List<Organize> getOrganizesByOrganizeQueryVO(OrganizeQueryVO organizeQueryVO) {
		// TODO Auto-generated method stub

		organizeQueryVO = setQueryVO(organizeQueryVO);
		return organizeMapperCustom.findOrganizeListByOrganizeQueryVO(organizeQueryVO);
	}

	private OrganizeQueryVO setQueryVO(OrganizeQueryVO organizeQueryVO) {
		organizeQueryVO.setProvince(setQueryMessage(organizeQueryVO.getProvince()));
		organizeQueryVO.setCity(setQueryMessage(organizeQueryVO.getCity()));
		organizeQueryVO.setArea(setQueryMessage(organizeQueryVO.getArea()));
		return organizeQueryVO;
	}

	private String setQueryMessage(String queryMessage) {
		if (queryMessage==null||queryMessage.equals("省份") || queryMessage.equals("地级市") || queryMessage.equals("市、县级市")) {
			queryMessage = "";
		}
		return queryMessage;
	}

	@Override
	public List<Organize> findOrganizeByOrganizeName(String organizeName) {
		return organizeMapperCustom.findOrganizeByOrganizeName(organizeName);
	}


}
