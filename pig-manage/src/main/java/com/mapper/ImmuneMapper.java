package com.mapper;

import com.pojo.Immune;
import com.pojo.ImmuneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImmuneMapper {
    int countByExample(ImmuneExample example);

    int deleteByExample(ImmuneExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Immune record);

    int insertSelective(Immune record);

    List<Immune> selectByExample(ImmuneExample example);

    Immune selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Immune record, @Param("example") ImmuneExample example);

    int updateByExample(@Param("record") Immune record, @Param("example") ImmuneExample example);

    int updateByPrimaryKeySelective(Immune record);

    int updateByPrimaryKey(Immune record);
}