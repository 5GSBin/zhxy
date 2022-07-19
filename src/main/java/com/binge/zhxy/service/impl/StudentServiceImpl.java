package com.binge.zhxy.service.impl;

import com.binge.zhxy.mapper.StudentMapper;
import com.binge.zhxy.pojo.Admin;
import com.binge.zhxy.pojo.Clazz;
import com.binge.zhxy.pojo.LoginForm;
import com.binge.zhxy.pojo.Student;
import com.binge.zhxy.service.StudentService;
import com.binge.zhxy.util.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**

 */
@Service("stuService")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {


    @Override
    public Student login(LoginForm loginForm) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));

        Student student = baseMapper.selectOne(queryWrapper);
        return student;
    }

    @Override
    public Student getStudentById(Long userId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        Student student = baseMapper.selectOne(queryWrapper);
        return student;
    }

    @Override
    public IPage<Student> getStudentByOpr(Page<Student> pageParam, Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        String clazzName = student.getClazzName();
        if(!StringUtils.isEmpty(clazzName)){
            queryWrapper.like("clazz_name", clazzName);
        }
        String name = student.getName();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("id");
        Page<Student> studentPage = baseMapper.selectPage(pageParam, queryWrapper);
        return studentPage;
    }

}
