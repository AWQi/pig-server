package com.mapper;

import com.pojo.ChangePigstyCommercial;
import com.pojo.ChangePigstyCommercialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangePigstyCommercialMapper {
    int countByExample(ChangePigstyCommercialExample example);

    int deleteByExample(ChangePigstyCommercialExample example);

    int insert(ChangePigstyCommercial record);

    int insertSelective(ChangePigstyCommercial record);

    List<ChangePigstyCommercial> selectByExample(ChangePigstyCommercialExample example);

    int updateByExampleSelective(@Param("record") ChangePigstyCommercial record, @Param("example") ChangePigstyCommercialExample example);

    int updateByExample(@Param("record") ChangePigstyCommercial record, @Param("example") ChangePigstyCommercialExample example);
}