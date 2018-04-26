package com.estore.dao;

import com.estore.bean.Publish;
import com.estore.bean.PublishExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishMapper {
    long countByExample(PublishExample example);

    int deleteByExample(PublishExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Publish record);

    int insertSelective(Publish record);

    List<Publish> selectByExample(PublishExample example);

    Publish selectByPrimaryKey(Integer id);

    Publish selectByName(@Param("name") String name);

    int updateByExampleSelective(@Param("record") Publish record, @Param("example") PublishExample example);

    int updateByExample(@Param("record") Publish record, @Param("example") PublishExample example);

    int updateByPrimaryKeySelective(Publish record);

    int updateByPrimaryKey(Publish record);
}