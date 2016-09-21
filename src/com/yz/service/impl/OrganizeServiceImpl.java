package com.yz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.mapper.OrganizeMapper;
import com.yz.mapper.OrganizeMapperCustom;
import com.yz.po.Camera;
import com.yz.po.Organize;
import com.yz.service.OrganizeService;

public class OrganizeServiceImpl implements OrganizeService {
	
	@Autowired
	private OrganizeMapper organizeMapper;
	
	@Autowired
	private OrganizeMapperCustom organizeMapperCustom;

	@Override
	public Organize selectByPrimaryKey(Integer organizeid) {
		
		return organizeMapper.selectByPrimaryKey(organizeid);
		
	}

	@Override
	public List<Camera> getCamerasByNumberAndOrganizeid(String number, Integer userOrganizeid) {
		
		Organize userOrganize = organizeMapper.selectByPrimaryKey(userOrganizeid);//当前用户所在组织
		
		
		int type  = userOrganize.getType();
		
		
		return null;
	}
	
	
	
	

}
