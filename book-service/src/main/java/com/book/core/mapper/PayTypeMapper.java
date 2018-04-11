package com.book.core.mapper;

import com.book.core.model.PayType;
import com.book.core.model.PayTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayTypeMapper {
    long countByExample(PayTypeExample example);

    int deleteByExample(PayTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PayType record);

    int insertSelective(PayType record);

    List<PayType> selectByExample(PayTypeExample example);

    PayType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PayType record, @Param("example") PayTypeExample example);

    int updateByExample(@Param("record") PayType record, @Param("example") PayTypeExample example);

    int updateByPrimaryKeySelective(PayType record);

    int updateByPrimaryKey(PayType record);
}