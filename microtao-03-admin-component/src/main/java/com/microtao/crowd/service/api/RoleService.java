package com.microtao.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.microtao.crowd.entity.Role;

/**
 * @Author : Microtao
 * @Date: 2020/11/24 22:37
 * @Description:
 */
public interface RoleService {

    /**
     * 获取分页信息
     * */
    PageInfo<Role> getPageInfo(String keyword, int pageCount, int pageSize);
}
