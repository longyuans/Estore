package com.estore.dao;

import com.estore.bean.ReportProducts;
import com.estore.bean.ReportProductsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportProductsMapper {
    long countByExample(ReportProductsExample example);

    int deleteByExample(ReportProductsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportProducts record);

    int insertSelective(ReportProducts record);

    List<ReportProducts> selectByExample(ReportProductsExample example);

    ReportProducts selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportProducts record, @Param("example") ReportProductsExample example);

    int updateByExample(@Param("record") ReportProducts record, @Param("example") ReportProductsExample example);

    int updateByPrimaryKeySelective(ReportProducts record);

    int updateByPrimaryKey(ReportProducts record);
}