package com.book.core.mapper;

import com.book.core.model.Orders;
import com.book.core.model.OrdersExample;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    
    /**
     * 统计实际支付的价格合
     * @param example
     * @return
     */
    BigDecimal sumPayByExample(OrdersExample example);
}