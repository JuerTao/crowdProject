package com.microtao.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.microtao.crowd.entity.Admin;

import java.util.List;

public interface AdminService {
    void saveAdmin(Admin admin);
    List<Admin> getAll();

    /**
     * 通过用户名和密码验证登录
     * */
    Admin getAdminByLoginAcct(String loginAcct, String userPswd);
    /**
     * 通过关键字查询分页数据
     * */
    PageInfo<Admin> getPageInfo(String keyword,int pageCount,int pageSize);

    void remove(Integer adminId);
}
