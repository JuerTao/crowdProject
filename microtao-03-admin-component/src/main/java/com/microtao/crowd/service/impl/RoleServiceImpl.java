package com.microtao.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.microtao.crowd.entity.Role;
import com.microtao.crowd.mapper.RoleMapper;
import com.microtao.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : Microtao
 * @Date: 2020/11/24 22:38
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public PageInfo<Role> getPageInfo(String keyword, int pageCount, int pageSize) {

        PageHelper.startPage(pageCount,pageSize);
        List<Role> roles = roleMapper.selectRoleByKeyword(keyword);
        return new PageInfo<Role>(roles);
    }
}
