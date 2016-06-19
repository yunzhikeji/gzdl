package com.yz.service;

import java.util.List;

import com.yz.po.Site;
import com.yz.po.SiteCustom;
import com.yz.po.SiteQueryVo;

public interface SiteService {
	// 工地查询列表
	public List<Site> findSiteList(SiteQueryVo siteQueryVo) throws Exception;

	// 根据id查询工地信息
	public SiteCustom findSiteById(Integer id) throws Exception;

	// 修改工地信息
	public void updateSite(Integer id, SiteCustom siteCustom) throws Exception;

	// 根据id删除工地
	public void deleteSite(Integer id) throws Exception;
	
	// 添加工地
	public void insertSite(Site site) throws Exception;

}
