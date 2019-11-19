/**
 * Created by chenf on 2019/11/19.
 */
/**
 * Created by yfyuan on 2016/7/19.
 */
cBoard.controller('requestDemo', function ($rootScope, $scope, $location, $http, $q, $filter, $uibModal, ModalUtils) {

    var translate = $filter('translate');

    $rootScope.alert = function (msg) {
        ModalUtils.alert(msg);
    };

    $http.get("commons/getUserDetail.do").success(function (response) {
        $scope.user = response;
        var avatarUrl = 'dist/img/user-male-circle-blue-128.png';
        $scope.user.avatar = avatarUrl;
    });

    var getMenuList = function () {
        $http.get("commons/getMenuList.do").success(function (response) {
            $scope.menuList = response;
        });
    };



   // getMenuList();




    function doGenWordCenter() {
        console.log("doGenWordCenter...")

        var data=$('#ajaxFrm').serialize()
        $http.post("genWord/center.do",data).success(function (serviceStatus) {
            if (serviceStatus.status == '1') {
                ModalUtils.alert(translate("COMMON.SUCCESS"), "modal-success", "sm");

                $("#ajaxDiv").html(data);	//将返回的结果显示到ajaxDiv中

            } else {
                ModalUtils.alert(serviceStatus.msg, "modal-warning", "lg");
                alert("发送请求失败！");

            }
        });

        // $.ajax({
        //     cache: true,
        //     type: "POST",
        //     url:"/genWord/center",
        //     data:$('#ajaxFrm').serialize(),	//要发送的是ajaxFrm表单中的数据
        //     async: true,
        //     error: function(request) {
        //         alert("发送请求失败！");},
        //     success: function(data) {
        //         $("#ajaxDiv").html(data);	//将返回的结果显示到ajaxDiv中
        //     }
        // });

    }


    function doGenWordPart() {
        console.log("doGenWordPart...")

        var data=$('#ajaxFrm').serialize()
        $http.post("genWord/part.do",data).success(function (serviceStatus) {
            if (serviceStatus.status == '1') {
                ModalUtils.alert(translate("COMMON.SUCCESS"), "modal-success", "sm");

            } else {
                ModalUtils.alert(serviceStatus.msg, "modal-warning", "lg");

            }
        });

        // $.ajax({
        //     cache: true,
        //     type: "POST",
        //     url:"/genWord/part",
        //     data:$('#ajaxFrm').serialize(),	//要发送的是ajaxFrm表单中的数据
        //     async: true,
        //     error: function(request) {
        //         alert("发送请求失败！");},
        //     success: function(data) {
        //         $("#ajaxDiv").html(data);	//将返回的结果显示到ajaxDiv中
        //     }
        // });

    }



});
