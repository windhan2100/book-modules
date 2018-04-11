package com.book.core.mapper;

import com.book.core.model.AdminFunctions;
import com.book.core.model.AdminFunctionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminFunctionsMapper {
    long countByExample(AdminFunctionsExample example);

    int deleteByExample(AdminFunctionsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminFunctions record);

    int insertSelective(AdminFunctions record);

    List<AdminFunctions> selectByExample(AdminFunctionsExample example);

    AdminFunctions selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminFunctions record, @Param("example") AdminFunctionsExample example);

    int updateByExample(@Param("record") AdminFunctions record, @Param("example") AdminFunctionsExample example);

    int updateByPrimaryKeySelective(AdminFunctions record);

    int updateByPrimaryKey(AdminFunctions record);
}