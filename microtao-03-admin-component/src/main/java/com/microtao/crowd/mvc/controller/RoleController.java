package com.microtao.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.microtao.crowd.entity.Role;
import com.microtao.crowd.service.api.RoleService;
import com.microtao.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author : Microtao
 * @Date: 2020/11/24 22:39
 * @Description:
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;
    /**
     * 相应分页json数据
     * */

    @ResponseBody
    @RequestMapping("role/to/page.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                                                    @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                    @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){

        PageInfo<Role> pageInfo = roleService.getPageInfo(keyword, pageNum, pageSize);

        //如果抛出异常，则会在异常捕获类中进行捕获
        return ResultEntity.successWithData(pageInfo);
    }
}
