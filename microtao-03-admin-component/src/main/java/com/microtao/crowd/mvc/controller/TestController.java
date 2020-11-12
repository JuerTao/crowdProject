package com.microtao.crowd.mvc.controller;

import com.microtao.crowd.util.ResultEntity;
import com.microtao.crowd.entity.Admin;
import com.microtao.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private AdminService adminService;
    @RequestMapping("/getAdminAll")
    public String test(ModelMap model) {
        List<Admin> adminList = adminService.getAll();
        model.addAttribute("list", adminList);
        int i = 1/0;
        return "target/success";
    }

    @ResponseBody
    @RequestMapping("/ajaxTest/get")
    public ResultEntity<List<Integer>> ajaxTest(@RequestBody List<Integer> list) {
        int i= 1/0;
        for (Integer num : list) {
            System.out.println(num);
        }
        return ResultEntity.successWithData(list);
    }
}
