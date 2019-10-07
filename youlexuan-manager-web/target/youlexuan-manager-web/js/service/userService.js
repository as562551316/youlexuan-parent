app.service('userService',function($http){
    //定义方法读取列表数据
    this.findAll=function(){
        return $http.get('../user/findAll.do');
    }
    //获取分页数据
    this.findPage=function(page,rows){
        return $http.get('../user/findPage.do?page='+page+'&rows='+rows);
    }
    //保存
    this.add=function(user){
        return $http.post('../user/add.do',user);
    }
    //修改
    this.update=function(user){
        return $http.post('../user/update.do',user);
    }
    //删除
    this.dele=function(ids){
        return $http.get('../user/del.do?ids='+ids);
    }
    //根据id获取品牌信息
    this.findOne=function(id){
        return $http.get('../user/findOne.do?id='+id);
    }
    //搜索（分页查询）
    this.search=function(page,rows,searchEntity){
        return $http.post('../user/search.do?page='+page+"&rows="+rows,searchEntity);
    }

});