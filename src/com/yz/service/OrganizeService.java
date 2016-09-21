package com.yz.service;

import java.util.List;

import com.yz.po.Camera;
import com.yz.po.Organize;

public interface OrganizeService {

	public Organize selectByPrimaryKey(Integer organizeid);

	public List<Camera> getCamerasByNumberAndOrganizeid(String number, Integer organizeid);

}
