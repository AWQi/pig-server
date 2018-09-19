package com.mapper;

import com.pojo.BreedingPig;
import com.pojo.BreedingPigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BreedingPigMapper {
    int countByExample(BreedingPigExample example);

    int deleteByExample(BreedingPigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BreedingPig record);

    int insertSelective(BreedingPig record);

    List<BreedingPig> selectByExample(BreedingPigExample example);

    BreedingPig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BreedingPig record, @Param("example") BreedingPigExample example);

    int updateByExample(@Param("record") BreedingPig record, @Param("example") BreedingPigExample example);

    int updateByPrimaryKeySelective(BreedingPig record);

    int updateByPrimaryKey(BreedingPig record);
}