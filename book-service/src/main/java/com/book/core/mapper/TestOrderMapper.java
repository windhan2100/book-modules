package com.book.core.mapper;

import com.book.core.model.TestOrder;
import com.book.core.model.TestOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestOrderMapper {
    long countByExample(TestOrderExample example);

    int deleteByExample(TestOrderExample example);

    int insert(TestOrder record);

    int insertSelective(TestOrder record);

    List<TestOrder> selectByExample(TestOrderExample example);

    int updateByExampleSelective(@Param("record") TestOrder record, @Param("example") TestOrderExample example);

    int updateByExample(@Param("record") TestOrder record, @Param("example") TestOrderExample example);
}