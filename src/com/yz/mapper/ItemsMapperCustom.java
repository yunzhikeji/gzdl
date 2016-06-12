package com.yz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yz.po.Items;
import com.yz.po.ItemsCustom;
import com.yz.po.ItemsExample;
import com.yz.po.ItemsQueryVo;

public interface ItemsMapperCustom {
    //商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}