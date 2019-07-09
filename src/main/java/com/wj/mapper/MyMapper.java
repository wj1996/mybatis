package com.wj.mapper;

import com.wj.mybatis.My;
import com.wj.mybatis.MyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyMapper {
    long countByExample(MyExample example);

    int deleteByExample(MyExample example);

    int insert(My record);

    int insertSelective(My record);

    List<My> selectByExample(MyExample example);

    int updateByExampleSelective(@Param("record") My record, @Param("example") MyExample example);

    int updateByExample(@Param("record") My record, @Param("example") MyExample example);
}