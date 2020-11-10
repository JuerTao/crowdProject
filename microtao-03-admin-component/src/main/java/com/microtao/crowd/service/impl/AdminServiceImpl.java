package com.microtao.crowd.service.impl;

import com.microtao.crowd.entity.Admin;
import com.microtao.crowd.mapper.AdminMapper;
import com.microtao.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    public void saveAdmin(Admin admin) {

        adminMapper.insert(admin);
    }
}
