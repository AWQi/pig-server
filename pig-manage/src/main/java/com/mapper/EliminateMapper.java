package com.mapper;

import com.pojo.Eliminate;
import com.pojo.EliminateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EliminateMapper {
    int countByExample(EliminateExample example);

    int deleteByExample(EliminateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Eliminate record);

    int insertSelective(Eliminate record);

    List<Eliminate> selectByExample(EliminateExample example);

    Eliminate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Eliminate record, @Param("example") EliminateExample example);

    int updateByExample(@Param("record") Eliminate record, @Param("example") EliminateExample example);

    int updateByPrimaryKeySelective(Eliminate record);

    int updateByPrimaryKey(Eliminate record);
}