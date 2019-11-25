package com.qtztlink.ejile.system.controller;

import com.qtztlink.ejile.common.utils.MD5Util;
import com.qtztlink.ejile.system.model.user.Admin;
import com.qtztlink.ejile.system.service.user.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @Autowired
    AdminService adminService;

    @Test
    public void queryAllConsumers() {
        System.out.println(this.adminController.queryAllConsumers());
    }

    @Test
    public void queryAllShops() {
        System.out.println(this.adminController.queryAllShops());
    }

    @Test
    public void updateConsumerByID() {
        try {
            System.out.println(this.adminController.updateConsumerByID(10, "age", 13));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateShopById() {
        try {
            System.out.println(this.adminController.updateShopById(6, "name", "阿伟"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteConsumerByID() {
        try {
            System.out.println(this.adminController.deleteConsumerByID(9));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteShopByID() {
        try {
            System.out.println((this.adminController.deleteShopByID(5)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decodePassWord(){

        try {
            //Admin admin =adminService.queryAdminByName("");
            System.out.println(MD5Util.encrypt("admin","123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}