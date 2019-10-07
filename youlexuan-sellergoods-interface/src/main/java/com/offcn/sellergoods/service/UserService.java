package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbUser;

import java.util.List;

public interface UserService {
    /**
     * 查询所有列表
     * @return
     */
    public List<TbUser> findAll();

    /**
     * 返回分页列表   pageNum：第几页，pageSize：每页显示多少条
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);

    void add(TbUser tbUser);

    TbUser findOne(Long id);

    void update(TbUser tbUser);

    void del(Long[] ids);

    PageResult findPage(TbUser tbUser, int pageNum, int pageSize);
}
