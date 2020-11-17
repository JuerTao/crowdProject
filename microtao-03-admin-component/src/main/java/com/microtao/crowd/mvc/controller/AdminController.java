package com.microtao.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.microtao.crowd.constant.CrowdConstant;
import com.microtao.crowd.entity.Admin;
import com.microtao.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 登录操作
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录
     * */
    @RequestMapping("admin/do/login.html")
    public String doLogin(@RequestParam("LoginAcct") String LoginAcct,
                          @RequestParam("userPswd") String userPswd, HttpSession session) {
        Admin admin = adminService.getAdminByLoginAcct(LoginAcct, userPswd);
        session.setAttribute(CrowdConstant.LOGIN_ADMIN, admin.getUserName());
        return "redirect:/admin/do/main/page.html";
    }
    /**
     * 退出登录
     * */
    @RequestMapping("admin/do/logout.html")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/do/login/page.html";
    }
    /**
     * 查询分页信息
     * */
    @RequestMapping("admin/get/page.html")
    public String getPageInfo(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                              @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "20") int pageSize, ModelMap modelMap){
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);
        return "admin-page";
    }
}
