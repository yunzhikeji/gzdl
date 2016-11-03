package com.yz.mapper;

import com.yz.po.Face;
import com.yz.po.FaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceMapper {
    int countByExample(FaceExample example);

    int deleteByExample(FaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Face record);

    int insertSelective(Face record);

    List<Face> selectByExample(FaceExample example);

    Face selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Face record, @Param("example") FaceExample example);

    int updateByExample(@Param("record") Face record, @Param("example") FaceExample example);

    int updateByPrimaryKeySelective(Face record);

    int updateByPrimaryKey(Face record);

}