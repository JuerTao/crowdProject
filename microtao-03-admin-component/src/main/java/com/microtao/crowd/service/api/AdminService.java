package com.microtao.crowd.service.api;

import com.microtao.crowd.entity.Admin;

import java.util.List;

public interface AdminService {
    void saveAdmin(Admin admin);
    List<Admin> getAll();

    /**
     * 通过用户名和密码验证登录
     * */
    Admin getAdminByLoginAcct(String loginAcct, String userPswd);
}
