package com.yz.mapper;

import com.yz.po.Organize;
import com.yz.po.OrganizeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrganizeMapper {
    int countByExample(OrganizeExample example);

    int deleteByExample(OrganizeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Organize record);

    int insertSelective(Organize record);

    List<Organize> selectByExample(OrganizeExample example);

    Organize selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Organize record, @Param("example") OrganizeExample example);

    int updateByExample(@Param("record") Organize record, @Param("example") OrganizeExample example);

    int updateByPrimaryKeySelective(Organize record);

    int updateByPrimaryKey(Organize record);
}