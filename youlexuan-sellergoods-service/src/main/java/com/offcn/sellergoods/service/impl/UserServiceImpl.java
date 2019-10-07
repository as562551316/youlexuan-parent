package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offcn.entity.PageResult;
import com.offcn.mapper.TbUserMapper;
import com.offcn.pojo.TbUser;

import com.offcn.pojo.TbUserExample;
import com.offcn.sellergoods.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public List<TbUser> findAll() {
        return tbUserMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbUser> list = tbUserMapper.selectByExample(null);
        PageInfo<TbUser> pageInfo = new PageInfo(list);
        long total = pageInfo.getTotal();
        List<TbUser> users = pageInfo.getList();
        PageResult pageResult = new PageResult(total, users);
        return pageResult;
    }

    @Override
    public void add(TbUser tbUser) {
        tbUserMapper.insert(tbUser);
    }

    @Override
    public TbUser findOne(Long id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbUser tbUser) {
        tbUserMapper.updateByPrimaryKey(tbUser);
    }

    @Override
    public void del(Long[] ids) {
        for (Long id:ids) {
            tbUserMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(TbUser tbUser, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbUserExample example=new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if(tbUser!=null){
            if(tbUser.getUsername()!=null && tbUser.getUsername().length()>0){
                criteria.andUsernameLike("%"+tbUser.getUsername()+"%");
            }
        }
        Page<TbUser> page= (Page<TbUser>)tbUserMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
