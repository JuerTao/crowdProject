package com.microtao.crowd.service.api;

import com.microtao.crowd.entity.Admin;

import java.util.List;

public interface AdminService {
    void saveAdmin(Admin admin);
    List<Admin> getAll();
}
