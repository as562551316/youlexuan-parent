package com.offcn.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.offcn.pojo.TbItem;
import com.offcn.search.service.ItemSearchService;
import com.offcn.util.HighLightQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.GroupOptions;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.GroupEntry;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.data.solr.core.query.result.ScoredPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(timeout = 3000)
public class ItemSearchServiceImpl implements ItemSearchService {
    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> search(Map searchMap) {
        Map<String,Object> map = new HashMap<>();

        List<TbItem> tbItems = HighLightQueryUtils
                .searchList(solrTemplate, "<em style='color:red'>", "</em>", searchMap);
//1.按关键字查询（title字段高亮显示）
        map.put("rows",tbItems);

        List categoryList = searchCategoryList(searchMap);
        map.put("categoryList",categoryList);

        if(categoryList.size()>0){
            map.putAll(searchBrandAndSpecList((String)categoryList.get(0)));
        }
        return map;
    }

    private  List searchCategoryList(Map searchMap){

        List<String> list=new ArrayList();

        Query query=new SimpleQuery();

        //按照关键字查询
        Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);

        //设置分组选项（按照item_category字段分组）
        GroupOptions groupOptions=new GroupOptions().addGroupByField("item_category");
        query.setGroupOptions(groupOptions);

        //得到分组页
        GroupPage<TbItem> page = solrTemplate.queryForGroupPage(query, TbItem.class);

        List<GroupEntry<TbItem>> content = page.getGroupResult("item_category").getGroupEntries().getContent();

        for(GroupEntry<TbItem> entry:content){
            list.add(entry.getGroupValue());//将分组结果的名称封装到返回值中
        }
        return list;
    }

    private Map searchBrandAndSpecList(String category){
        Map map=new HashMap();
        Long typeId = (Long) redisTemplate.boundHashOps("itemCat").get(category);//获取模板ID
        if(typeId!=null){
            //根据模板ID查询品牌列表
            List brandList = (List) redisTemplate.boundHashOps("brandList").get(typeId);
            map.put("brandList", brandList);//返回值添加品牌列表
            //根据模板ID查询规格列表
            List specList = (List) redisTemplate.boundHashOps("specList").get(typeId);
            map.put("specList", specList);
        }
        return map;
    }
}
