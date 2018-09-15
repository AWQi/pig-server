package com.mapper;

import com.pojo.Pigsty;
import com.pojo.PigstyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PigstyMapper {
    int countByExample(PigstyExample example);

    int deleteByExample(PigstyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pigsty record);

    int insertSelective(Pigsty record);

    List<Pigsty> selectByExample(PigstyExample example);

    Pigsty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pigsty record, @Param("example") PigstyExample example);

    int updateByExample(@Param("record") Pigsty record, @Param("example") PigstyExample example);

    int updateByPrimaryKeySelective(Pigsty record);

    int updateByPrimaryKey(Pigsty record);
}