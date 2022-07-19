package com.binge.zhxy.service;

import com.binge.zhxy.pojo.Clazz;
import com.binge.zhxy.pojo.LoginForm;
import com.binge.zhxy.pojo.Student;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface StudentService extends IService<Student> {


    Student login(LoginForm loginForm);

    Student getStudentById(Long userId);

    IPage<Student> getStudentByOpr(Page<Student> page, Student student);

}
