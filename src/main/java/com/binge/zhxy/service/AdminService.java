package com.binge.zhxy.service;

import com.binge.zhxy.pojo.Admin;
import com.binge.zhxy.pojo.LoginForm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {

    Admin login(LoginForm loginForm);

    Admin getAdminById(Long userId);

    IPage<Admin> getAdminByOpr(Page<Admin> pageParam, String adminName);
}
