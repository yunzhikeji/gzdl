package com.yz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yz.mapper.SiteMapper;
import com.yz.mapper.SiteMapperCustom;
import com.yz.po.Site;
import com.yz.po.SiteCustom;
import com.yz.po.SiteQueryVo;
import com.yz.service.SiteService;

public class SiteServiceImpl implements SiteService {
	
	@Autowired
	private SiteMapper siteMapper;
	
	@Autowired
	private SiteMapperCustom siteMapperCustom;

	@Override
	public List<Site> findSiteList(SiteQueryVo siteQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return siteMapperCustom.getSites();
	}

	@Override
	public Site findSiteById(Integer id) throws Exception {
		
		return siteMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateSite(Integer id, SiteCustom siteCustom) throws Exception {
		siteCustom.setId(id);
		siteMapper.updateByPrimaryKey(siteCustom);

	}

	@Override
	public void deleteSite(Integer id) throws Exception {
		siteMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSite(Site site) throws Exception {
		return  siteMapper.insert(site);
	}

	@Override
	public List<Site> checkSite(SiteCustom siteCustom) throws Exception{
		// TODO Auto-generated method stub
		return siteMapperCustom.querySiteBySiteCustom(siteCustom);
	}

	@Override
	public List<Site> getSites() throws Exception {
		// TODO Auto-generated method stub
		return siteMapperCustom.getSites();
	}

	@Override
	public void deleteSiteByMarkid(String markid) throws Exception {
		// TODO Auto-generated method stub
		siteMapperCustom.deleteByMarkid(markid);
	}

	@Override
	public Site querySiteByMarkid(String markid) {
		// TODO Auto-generated method stub
		return siteMapperCustom.selectByMarkid(markid);
	}

}
