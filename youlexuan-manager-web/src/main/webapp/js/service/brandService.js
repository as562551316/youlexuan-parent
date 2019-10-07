app.service('brandService',function($http){
    //定义方法读取列表数据
    this.findAll=function(){
        return $http.get('../brand/findAll.do');
    }
    //获取分页数据
    this.findPage=function(page,rows){
        return $http.get('../brand/findPage.do?page='+page+'&rows='+rows);
    }
    //保存
    this.add=function(brand){
        return $http.post('../brand/add.do',brand);
    }
    //修改
    this.update=function(brand){
        return $http.post('../brand/update.do',brand);
    }
    //删除
    this.dele=function(ids){
        return $http.get('../brand/del.do?ids='+ids);
    }
    //根据id获取品牌信息
    this.findOne=function(id){
        return $http.get('../brand/findOne.do?id='+id);
    }
    //搜索（分页查询）
    this.search=function(page,rows,searchEntity){
        return $http.post('../brand/search.do?page='+page+"&rows="+rows,searchEntity);
    }

    this.selectOptionList=function(){
        return $http.get('../brand/selectOptionList.do');
    }

});