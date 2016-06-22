package com.yz.mapper;

import com.yz.po.Site;
import com.yz.po.SiteCustom;
import com.yz.po.SiteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteMapperCustom {
    List<Site> querySiteBySiteCustom(SiteCustom siteCustom);
}