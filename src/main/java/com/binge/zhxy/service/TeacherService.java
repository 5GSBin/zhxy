package com.binge.zhxy.service;

import com.binge.zhxy.pojo.LoginForm;
import com.binge.zhxy.pojo.Teacher;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface TeacherService extends IService<Teacher> {


    Teacher login(LoginForm loginForm);

    Teacher getTeacherById(Long userId);

    IPage<Teacher> getTeacherByOpr(Page<Teacher> pageParm, Teacher teacher);
}
