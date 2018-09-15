package com.mapper;

import com.pojo.Sale;
import com.pojo.SaleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleMapper {
    int countByExample(SaleExample example);

    int deleteByExample(SaleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sale record);

    int insertSelective(Sale record);

    List<Sale> selectByExample(SaleExample example);

    Sale selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sale record, @Param("example") SaleExample example);

    int updateByExample(@Param("record") Sale record, @Param("example") SaleExample example);

    int updateByPrimaryKeySelective(Sale record);

    int updateByPrimaryKey(Sale record);
}