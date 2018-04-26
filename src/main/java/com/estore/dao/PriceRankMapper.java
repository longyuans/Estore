package com.estore.dao;

import com.estore.bean.PriceRank;
import com.estore.bean.PriceRankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRankMapper {
    long countByExample(PriceRankExample example);

    int deleteByExample(PriceRankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PriceRank record);

    int insertSelective(PriceRank record);

    List<PriceRank> selectByExample(PriceRankExample example);

    PriceRank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PriceRank record, @Param("example") PriceRankExample example);

    int updateByExample(@Param("record") PriceRank record, @Param("example") PriceRankExample example);

    int updateByPrimaryKeySelective(PriceRank record);

    int updateByPrimaryKey(PriceRank record);
}