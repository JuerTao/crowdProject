package com.microtao.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.microtao.crowd.constant.CrowdConstant;
import com.microtao.crowd.entity.Admin;
import com.microtao.crowd.entity.AdminExample;
import com.microtao.crowd.exception.LoginAcctAlreadyInUseException;
import com.microtao.crowd.exception.LoginAcctForUpdateAlreadyInUseException;
import com.microtao.crowd.exception.LoginFailedException;
import com.microtao.crowd.mapper.AdminMapper;
import com.microtao.crowd.service.api.AdminService;
import com.microtao.crowd.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public void saveAdmin(Admin admin) {
        String password = admin.getUserPswd();
        String md5Password = CrowdUtil.md5(password);
        admin.setUserPswd(md5Password);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = simpleDateFormat.format(date);
        admin.setCreateTime(dateTime);
        try {
            adminMapper.insert(admin);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }

    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    /**
     * 验证登录，通过用户名查询用户
     */
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {

        AdminExample adminExample = new AdminExample();
        // 创建查询条件
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> list = adminMapper.selectByExample(adminExample);
        if (null == list | list.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (list.size() > 1) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        Admin admin = list.get(0);
        if (null == admin) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        String pswdEncode = CrowdUtil.md5(userPswd);
        if (!pswdEncode.equals(admin.getUserPswd())) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        return admin;
    }


    /**
     * 分页查询
     */
    public PageInfo<Admin> getPageInfo(String keyword, int pageNum, int pageSize) {
        //调用PageHelper的静态方法，开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        //执行查询
        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);
        return new PageInfo<Admin>(list);

    }

    /**
     * 单个删除
     */
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
        return;
    }

    public Admin selectAdminByAdminId(Integer adminId) {

        Admin admin = adminMapper.selectAdminByAdminId(adminId);
        if(admin!=null){
            return admin;
        }
        return new Admin();
    }

    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectAdminByAdminId(adminId);
    }

    public void update(Admin admin) {

        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            if(e instanceof DuplicateKeyException){
                throw new LoginAcctForUpdateAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }
}
