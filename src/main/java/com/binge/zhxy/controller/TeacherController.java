package com.binge.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binge.zhxy.pojo.Teacher;
import com.binge.zhxy.service.TeacherService;
import com.binge.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("教师控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("分页待条件查询教师信息")
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询条件") Teacher teacher
    ){
        Page<Teacher> pageParm = new Page<>(pageNo, pageSize);
        IPage<Teacher> teacherIPage = teacherService.getTeacherByOpr(pageParm, teacher);
        return Result.ok(teacherIPage);
    }

    @ApiOperation("添加或者修改教师信息")
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(
            @ApiParam("要添加或者修改的信息") @RequestBody Teacher teacher
    ){
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }


    @ApiOperation("单个或批量删除教师信息")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(
            @ApiParam("要单个或者批量删除的教师id集合") @RequestBody List<Integer> ids
            ){
        teacherService.removeByIds(ids);
        return Result.ok();
    }

}
