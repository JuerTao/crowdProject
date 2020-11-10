package com.microtao.crowd.mvc.controller;

import com.microtao.crowd.entity.Admin;
import com.microtao.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/getAdminAll")
     public String test(ModelMap model){
        List<Admin> adminList = adminService.getAll();
        model.addAttribute("list",adminList);
         return "target/success";
     }

}
