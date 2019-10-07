package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbBrand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    /**
     * 查询所有列表
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 返回分页列表   pageNum：第几页，pageSize：每页显示多少条
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);

    void add(TbBrand tbBrand);

    TbBrand findOne(Long id);

    void update(TbBrand tbBrand);

    void del(Long[] ids);

    PageResult findPage(TbBrand brand, int pageNum,int pageSize);

    List<Map> selectOptionList();
}
