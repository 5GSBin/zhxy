package com.binge.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binge.zhxy.pojo.Admin;
import com.binge.zhxy.service.AdminService;
import com.binge.zhxy.util.MD5;
import com.binge.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("管理员控制器")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("分页带条件查询所有管理员信息")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(
            @ApiParam("分页页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("分页页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询条件") String adminName
    ){
        Page<Admin> pageParam = new Page<>(pageNo, pageSize);
        IPage<Admin> adminPage = adminService.getAdminByOpr(pageParam, adminName);
        return Result.ok(adminPage);
    }
    @ApiOperation("增加或修改管理员信息")
    @PostMapping("/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(
            @ApiParam("添加或修改的信息") @RequestBody Admin admin
    ){
        Integer id = admin.getId();
        if(null == id || 0 == id){
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        }
        adminService.saveOrUpdate(admin);
        return Result.ok();
    }

    @ApiOperation("单个或批量删除管理员信息")
    @DeleteMapping("/deleteAdmin")
    public Result deleteAdmin(
            @ApiParam("要删除的管理员学号") @RequestBody List<Integer> ids
    ){
        adminService.removeByIds(ids);
        return Result.ok();
    }


}
