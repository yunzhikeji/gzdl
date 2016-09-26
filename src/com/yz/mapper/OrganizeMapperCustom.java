package com.yz.mapper;

import java.util.List;

import com.yz.po.Organize;
import com.yz.vo.OrganizeQueryVO;

public interface OrganizeMapperCustom {

	// 查询所有组织列表
	public List<Organize> findOrganizeList();

	// 根据organizeQueryVO查询所有组织列表
	public List<Organize> findOrganizeListByOrganizeQueryVO(OrganizeQueryVO organizeQueryVO);

	// 根据组织名称查询组织
	public Organize findOrganizeByName(String organizeName);
	
	
	// 验证组织名称重复用
	public List<Organize> findOrganizeByOrganizeName(String organizeName);

}