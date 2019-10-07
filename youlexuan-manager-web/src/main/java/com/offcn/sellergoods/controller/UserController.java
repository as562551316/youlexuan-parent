package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbUser;
import com.offcn.sellergoods.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    private UserService userService;

    @RequestMapping("/findAll")
    public List<TbUser> findAll(){
        return  userService.findAll();
    }

    @RequestMapping("/findPage")
    public PageResult findPage(int page,int rows){
        return userService.findPage(page,rows);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody TbUser tbUser){
        try {
            tbUser.setCreated(new Date());
            tbUser.setUpdated(new Date());
            userService.add(tbUser);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/findOne")
    public TbUser findOne(Long id){
        return userService.findOne(id);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody TbUser tbUser){
        try {
            userService.update(tbUser);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }

    }

    @RequestMapping("del")
    public Result del(Long[] ids){
        try {
            userService.del(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }

    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody TbUser tbUser, int page, int rows  ){
        return userService.findPage(tbUser, page, rows);
    }
}
