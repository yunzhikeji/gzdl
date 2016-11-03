package com.yz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yz.po.Alarm;
import com.yz.po.AlarmExample;

public interface AlarmMapper {
    int countByExample(AlarmExample example);

    int deleteByExample(AlarmExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Alarm record);

    int insertSelective(Alarm record);

    List<Alarm> selectByExampleWithBLOBs(AlarmExample example);

    List<Alarm> selectByExample(AlarmExample example);

    Alarm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Alarm record, @Param("example") AlarmExample example);

    int updateByExampleWithBLOBs(@Param("record") Alarm record, @Param("example") AlarmExample example);

    int updateByExample(@Param("record") Alarm record, @Param("example") AlarmExample example);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKeyWithBLOBs(Alarm record);

    int updateByPrimaryKey(Alarm record);
}