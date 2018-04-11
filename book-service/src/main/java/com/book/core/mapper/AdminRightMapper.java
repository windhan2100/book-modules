package com.book.core.mapper;

import com.book.core.model.AdminRight;
import com.book.core.model.AdminRightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminRightMapper {
    long countByExample(AdminRightExample example);

    int deleteByExample(AdminRightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminRight record);

    int insertSelective(AdminRight record);

    List<AdminRight> selectByExample(AdminRightExample example);

    AdminRight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminRight record, @Param("example") AdminRightExample example);

    int updateByExample(@Param("record") AdminRight record, @Param("example") AdminRightExample example);

    int updateByPrimaryKeySelective(AdminRight record);

    int updateByPrimaryKey(AdminRight record);
}