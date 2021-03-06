package com.microtao.crowd.mapper;

import com.microtao.crowd.entity.Admin;
import com.microtao.crowd.entity.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    /**通过关键字查询分页数据*/
    List<Admin> selectAdminByKeyword(String keyword);
    /**通过用户id查询当前登录的信息*/
    Admin selectAdminByAdminId(Integer adminId);
}