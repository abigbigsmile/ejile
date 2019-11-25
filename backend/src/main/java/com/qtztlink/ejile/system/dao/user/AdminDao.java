package com.qtztlink.ejile.system.dao.user;

import com.qtztlink.ejile.system.model.user.Admin;

import java.util.List;

public interface AdminDao {
    List<Admin> queryAdminByName(String username);
    List<Admin> queryAdminById(Integer id);
    int update(Admin admin);
}
