package com.mapper;

import com.pojo.CommercialPig;
import com.pojo.CommercialPigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommercialPigMapper {
    int countByExample(CommercialPigExample example);

    int deleteByExample(CommercialPigExample example);

    int deleteByPrimaryKey(Integer batchNumber);

    int insert(CommercialPig record);

    int insertSelective(CommercialPig record);

    List<CommercialPig> selectByExample(CommercialPigExample example);

    CommercialPig selectByPrimaryKey(Integer batchNumber);

    int updateByExampleSelective(@Param("record") CommercialPig record, @Param("example") CommercialPigExample example);

    int updateByExample(@Param("record") CommercialPig record, @Param("example") CommercialPigExample example);

    int updateByPrimaryKeySelective(CommercialPig record);

    int updateByPrimaryKey(CommercialPig record);
}