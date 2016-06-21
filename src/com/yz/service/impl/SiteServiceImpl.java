package com.yz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.mapper.SiteMapper;
import com.yz.po.Site;
import com.yz.po.SiteCustom;
import com.yz.po.SiteQueryVo;
import com.yz.service.SiteService;

public class SiteServiceImpl implements SiteService {
	
	@Autowired
	private SiteMapper siteMapper;

	@Override
	public List<Site> findSiteList(SiteQueryVo siteQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteCustom findSiteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSite(Integer id, SiteCustom siteCustom) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSite(Integer id) throws Exception {
		siteMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insertSite(Site site) throws Exception {
		siteMapper.insert(site);
	}

}
