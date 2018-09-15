package com.mapper;

import com.pojo.ChangePigstyBreed;
import com.pojo.ChangePigstyBreedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangePigstyBreedMapper {
    int countByExample(ChangePigstyBreedExample example);

    int deleteByExample(ChangePigstyBreedExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChangePigstyBreed record);

    int insertSelective(ChangePigstyBreed record);

    List<ChangePigstyBreed> selectByExample(ChangePigstyBreedExample example);

    ChangePigstyBreed selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChangePigstyBreed record, @Param("example") ChangePigstyBreedExample example);

    int updateByExample(@Param("record") ChangePigstyBreed record, @Param("example") ChangePigstyBreedExample example);

    int updateByPrimaryKeySelective(ChangePigstyBreed record);

    int updateByPrimaryKey(ChangePigstyBreed record);
}