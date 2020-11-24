package com.microtao.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.microtao.crowd.constant.CrowdConstant;
import com.microtao.crowd.entity.Admin;
import com.microtao.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 新增用户
     * */
    @RequestMapping("admin/save.html")
    public String save(Admin admin,HttpSession session) {
        if(StringUtils.isEmpty(admin.getLoginAcct())){
            throw new RuntimeException(CrowdConstant.LOGIN_ACCT_NOU_NULL);
        }
        if(StringUtils.isEmpty(admin.getUserName())){
            throw new RuntimeException(CrowdConstant.LOGIN_USERNAME_NOU_NULL);
        }
        if(StringUtils.isEmpty(admin.getUserPswd())){
            throw new RuntimeException(CrowdConstant.LOGIN_PASSWORD_NOU_NULL);
        }
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html";
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
                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize, ModelMap modelMap){
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);
        return "admin-page";
    }

    @RequestMapping("admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(@PathVariable(value = "adminId") Integer adminId,
                         @PathVariable(value = "pageNum") String pageNum,
                         @PathVariable(value = "keyword") String keyword, HttpSession session){

        // 尝试从session对象中获取用户的信息
        String admin = (String) session.getAttribute(CrowdConstant.LOGIN_ADMIN);
        //通过adminId查询当前用户
        Admin adminDB = adminService.selectAdminByAdminId(adminId);
        if(null!=adminDB){
            String username = adminDB.getUserName();
            if(adminDB.equals(username)){
                throw new RuntimeException(CrowdConstant.DELETE_FAILED);
            }
        }

        adminService.remove(adminId);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @RequestMapping("admin/to/edit/page.html")
    public String editPage(@RequestParam("adminId") Integer adminId,ModelMap modelMap){

        Admin admin = adminService.getAdminById(adminId);
        modelMap.addAttribute("admin",admin);

        return "admin-edit";
    }

    @RequestMapping("admin/update.html")
    public String update(Admin admin,@RequestParam("pageNum") String pageNum,@RequestParam("keyword") String keyword){
        adminService.update(admin);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }
}
