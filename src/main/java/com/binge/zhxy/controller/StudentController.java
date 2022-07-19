package com.binge.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binge.zhxy.pojo.Clazz;
import com.binge.zhxy.pojo.Student;
import com.binge.zhxy.service.StudentService;
import com.binge.zhxy.util.MD5;
import com.binge.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("单个或批量删除学生信息")
    @DeleteMapping("/delStudentById")
    public Result delStudentById(
            @ApiParam("要删除的学生学号") @RequestBody List<Integer> ids
    ){
        studentService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("添加修改学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(
            @ApiParam("要添加或修改的学生信息") @RequestBody Student student
    ){
        Integer id = student.getId();
        if(id == null || 0 == id){
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }


    @ApiOperation("根据班级姓名模糊查询，带分页")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(
            @ApiParam("分压查询的页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("分页查询的页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("分压模糊查询的条件") Student student
    ){

        Page<Student> page = new Page<>(pageNo, pageSize);
        IPage<Student> studentPage = studentService.getStudentByOpr(page, student);
        return Result.ok(studentPage);

    }

}
