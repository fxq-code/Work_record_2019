/**
 * Created by chenf on 2019/11/19.
 */
/**
 * Created by yfyuan on 2016/7/19.
 */
var o={name:'wang',password:'123'}



function doGenImgs() {
    console.log(JSON.stringify(o));
    $.ajax({
        cache: true,
        type: "POST",

        url:"/genWord/genPartImgs.do",
        data:o,	//要发送的是ajaxFrm表单中的数据
        async: true,
        error: function(request) {
            alert("发送请求失败！");},
        success: function(data) {
            console.log(data)
            if(data!="ERROR"){

                alert("生成图片成功，路径为："+data.split("@")[1])

            }else{
                alert("生成图片失败！")
            }

        }
    });

}
function doGenWordCenter() {
    //console.log("doGenWordCenter...")
    console.log(JSON.stringify(o));
    $.ajax({
        cache: true,
        type: "POST",
        url:"/genWord/center.do",
        //url:"/genWord/genPartImgs.do",
        data:o,	//要发送的是ajaxFrm表单中的数据
        async: true,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        error: function(request) {
            alert("发送请求失败！");},
        success: function(data) {
            console.log(data)
            if(data!="ERROR"){

                alert("生成文件成功，路径为："+data.split("@")[1])

            }else{
                alert("生成word文件失败！")
            }

        }
    });

}


function doGenWordPart() {
    //console.log("doGenWordPart..."+o)

    console.log(JSON.stringify(o));
    $.ajax({
        cache: true,
        type: "POST",
        url:"/genWord/part.do",
        //url:"/genWord/genPartImgs.do",
        data:o,	//要发送的是ajaxFrm表单中的数据
        async: true,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        error: function(request) {
            alert("发送请求失败！");
        },
        success: function(data) {
            //debugger;
            console.log(data)
            if(data!="ERROR"){

                alert("生成文件成功，路径为："+data.split("@")[1])


            }else{
                alert("生成word文件失败！")
            }
        }
    });

}
