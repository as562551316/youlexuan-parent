app.controller('userController' ,function($scope,userService,$controller){

    $controller('baseController',{$scope:$scope});//继承
    //读取列表数据绑定到表单中
    $scope.findAll=function(){
        userService.findAll().success(
            function(response){
                $scope.list=response;
            }
        );
    }

    $scope.findPage=function(page,rows){
        userService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }



    $scope.save=function(){
        var methodName='add';//方法名称
        if($scope.user.id!=null){//如果有ID，执行修改；没有ID，执行添加
            userService.update($scope.user).success(
                function(response){
                    if(response.success){
                        //重新查询
                        $scope.reloadList();//重新加载
                    }else{
                        alert(response.message);
                    }
                }
            );
        }else {
            userService.add($scope.user).success(
                function (response) {
                    if (response.success) {
                        //重新查询
                        $scope.reloadList();//重新加载
                    } else {
                        alert(response.message);
                    }
                }
            );
        }
    }

    $scope.findOne=function(id){
        userService.findOne(id).success(
            function(response){
                $scope.user= response;
            }
        );
    }



//批量删除
    $scope.dele=function(){
        //获取选中的复选框
        userService.dele($scope.selectIds).success(
            function(response){
                if(response.success){
                    $scope.reloadList();//刷新列表
                }
            }
        );
    }


    $scope.searchEntity={};

    $scope.search=function(page,rows){
        userService.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.paginationConf.totalItems=response.total;//总记录数
                $scope.list=response.rows;//给列表变量赋值
            }
        );
    }
});