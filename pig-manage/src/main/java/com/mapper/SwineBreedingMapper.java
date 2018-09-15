package com.mapper;

import com.pojo.SwineBreeding;
import com.pojo.SwineBreedingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SwineBreedingMapper {
    int countByExample(SwineBreedingExample example);

    int deleteByExample(SwineBreedingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SwineBreeding record);

    int insertSelective(SwineBreeding record);

    List<SwineBreeding> selectByExample(SwineBreedingExample example);

    SwineBreeding selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwineBreeding record, @Param("example") SwineBreedingExample example);

    int updateByExample(@Param("record") SwineBreeding record, @Param("example") SwineBreedingExample example);

    int updateByPrimaryKeySelective(SwineBreeding record);

    int updateByPrimaryKey(SwineBreeding record);
}