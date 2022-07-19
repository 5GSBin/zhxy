package com.binge.zhxy.service.impl;

import com.binge.zhxy.mapper.AdminMapper;
import com.binge.zhxy.pojo.Admin;
import com.binge.zhxy.pojo.LoginForm;
import com.binge.zhxy.pojo.Teacher;
import com.binge.zhxy.service.AdminService;
import com.binge.zhxy.util.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public Admin login(LoginForm loginForm) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));

        Admin admin = baseMapper.selectOne(queryWrapper);
        return admin;
    }

    @Override
    public Admin getAdminById(Long userId) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        Admin admin = baseMapper.selectOne(queryWrapper);

        return admin;
    }

    @Override
    public IPage<Admin> getAdminByOpr(Page<Admin> pageParam, String adminName) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(adminName)){
            queryWrapper.like("name",adminName);
        }
        queryWrapper.orderByDesc("id");
        Page<Admin> page = baseMapper.selectPage(pageParam, queryWrapper);
        return page;
    }
}
