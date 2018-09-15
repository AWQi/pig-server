package com.mapper;

import com.pojo.Breeder;
import com.pojo.BreederExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BreederMapper {
    int countByExample(BreederExample example);

    int deleteByExample(BreederExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Breeder record);

    int insertSelective(Breeder record);

    List<Breeder> selectByExample(BreederExample example);

    Breeder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Breeder record, @Param("example") BreederExample example);

    int updateByExample(@Param("record") Breeder record, @Param("example") BreederExample example);

    int updateByPrimaryKeySelective(Breeder record);

    int updateByPrimaryKey(Breeder record);
}