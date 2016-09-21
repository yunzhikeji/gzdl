package com.yz.service;

import java.util.List;

import com.yz.po.Camera;
import com.yz.po.Organize;
import com.yz.vo.CameraQueryVO;

public interface OrganizeService {
	
	public Organize selectByPrimaryKey(Integer organizeid);


	public List<Camera> getCamerasByNumberAndOrganizeid(String number, Integer organizeid);
	
	//根据id删除组织
	public void deleteOrganizeById(Integer id);
	
	//修改组织信息
	public void updateOrganize(Integer id,Organize organize);
	
	//添加一个组织
	public void insertOrganize(Organize organize);
	
	//查询所有组织
	public List<Organize> findOrganizeList();


}
