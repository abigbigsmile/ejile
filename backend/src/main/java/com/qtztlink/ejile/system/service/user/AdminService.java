package com.qtztlink.ejile.system.service.user;

import com.qtztlink.ejile.system.model.user.Admin;

public interface AdminService {
    Admin queryAdminByName(String username);

    Admin queryAdminById(Integer id);
    int update(Admin admin);
}
