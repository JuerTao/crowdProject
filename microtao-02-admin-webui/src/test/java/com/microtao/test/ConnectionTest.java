package com.microtao.test;

import com.microtao.crowd.entity.Admin;
import com.microtao.crowd.mapper.AdminMapper;
import com.microtao.crowd.service.api.AdminService;
import com.microtao.crowd.util.CrowdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-tx.xml","classpath:spring-mybatis.xml"})
public class ConnectionTest {

    @Autowired
    DataSource dataSource;
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    public void testConn() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getTransactionIsolation());
    }

    @Test
    public void addAdmin() {
        adminMapper.insert(new Admin(null, "admin", "admin", "小明", "wqw@qq.com", "2002-10-1"));
    }

    @Test
    public void testTx(){
        adminService.saveAdmin(new Admin(null, "admin", "admin", "小刚", "wqw@qq.com", "2002-10-1"));
    }

    @Test
    public void testMD5(){
        String str = "123456";
        System.out.println(CrowdUtil.md5(str));
    }

    @Test
    public void insert(){
        for(int i = 0;i<245;i++){
        adminMapper.insert(new Admin(null, "admin"+i, "admin"+i, "小明"+i, "wqw@qq.com", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
        }
    }
}

