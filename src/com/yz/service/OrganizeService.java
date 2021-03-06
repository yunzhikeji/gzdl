package com.yz.service;

import java.util.List;

import com.yz.po.Camera;
import com.yz.po.Organize;
import com.yz.vo.CameraQueryVO;
import com.yz.vo.OrganizeQueryVO;

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

	//根据组织名称查询组织
	public Organize findOrganizeByName(String organizeName);

	//根据organizeQueryVO查询组织列表
	public List<Organize> getOrganizesByOrganizeQueryVO(OrganizeQueryVO organizeQueryVO);

	
	//验证组织名称重复
	public List<Organize> findOrganizeByOrganizeName(String organizeName);
	
}
