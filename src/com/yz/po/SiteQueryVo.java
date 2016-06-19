package com.yz.po;

import java.util.List;

public class SiteQueryVo {
	private Site site;
	
	private SiteCustom siteCustom;
	
	private List<SiteCustom> siteList;

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public SiteCustom getSiteCustom() {
		return siteCustom;
	}

	public void setSiteCustom(SiteCustom siteCustom) {
		this.siteCustom = siteCustom;
	}

	public List<SiteCustom> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<SiteCustom> siteList) {
		this.siteList = siteList;
	}

}
