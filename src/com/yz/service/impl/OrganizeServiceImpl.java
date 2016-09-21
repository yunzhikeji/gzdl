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

public class OrganizeServiceImpl implements OrganizeService {
	
	@Autowired
	private OrganizeMapper organizeMapper;
	
	@Autowired
	private OrganizeMapperCustom organizeMapperCustom;
	
	
	@Override
	public Organize selectByPrimaryKey(Integer organizeid) {
		
		return organizeMapper.selectByPrimaryKey(organizeid);
		
	}

	
	
	
	
	
	
	

}
