app.controller('baseController' ,function($scope){
    $scope.reloadList=function(){
    //切换页码
    $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
}

$scope.paginationConf = {
    currentPage: 1,
    totalItems: 10,
    itemsPerPage: 10,
    perPageOptions: [10, 20, 30, 40, 50],
    onChange: function(){
        $scope.reloadList();//重新加载，分页组件变化后会触发该函数
    }
};

$scope.selectIds=[];//选中的ID集合

//更新复选，每个复选框都绑定这个函数  $event.target代表点击的控件对象
$scope.updateSelection = function($event, id) {
    if($event.target.checked){//如果是被选中checked=true,则将id增加到数组
        $scope.selectIds.push(id);
    }else{
        var idx = $scope.selectIds.indexOf(id);//id在数组中的位置
        $scope.selectIds.splice(idx, 1);//删除
    }
}

    $scope.jsonToString = function(jsonString,key){
        var json = JSON.parse(jsonString);//json字符串转成json数组或对象
        var value = [];
        for(var i = 0 ; i < json.length;i++){
            value.push(json[i][key]);
        }
        //return value;//["联想","三星","华为","OPPO","小米"]
        return value.toString();//联想,三星,华为,OPPO,小米
    }

    //从集合中按照key查询对象
    $scope.searchObjectByKey=function(list,key,keyValue){
        for(var i=0;i<list.length;i++){
            if(list[i][key]==keyValue){
                return list[i];
            }
        }
        return null;
    }
})