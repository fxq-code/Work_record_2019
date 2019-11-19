<%--
  Created by IntelliJ IDEA.
  User: chenf
  Date: 2019/11/19
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>echarts demo</title>

    <style>
        *{
            padding:0;
            margin:0;
            font-family:"微软雅黑";
        }
        .header{
            height:72px;
            background:#458fce ;
        }
        .header .logo{
            color:#fff ;
            line-height:70px;
            font-size:30px;
            margin-left:20px;
            display:inline-block;
            text-align:center;

        }
        a {
            color: #fff ;
            text-decoration: none ;
        }
        .header .login{
            float:right;
            color:#fff ;
            line-height:72px;
            margin-right:2px;
            display:inline-block;
        }
        .banner{
            height:380px;
            overflow:hidden;
            background: #ddd;
        }
    </style>


    <script  type="text/javascript" src="../lib/My97DatePicker/WdatePicker.js"></script>

    <script type="text/javascript" src="../lib/jquery-3.4.1.min.js"></script>

    <script type="text/javascript">

        function doGenWord() {
            alert("doGenWord...")

//            $.ajax({
//                cache: true,
//                type: "POST",
//                url:"ajax.jsp",	//把表单数据发送到ajax.jsp
//                data:$('#ajaxFrm').serialize(),	//要发送的是ajaxFrm表单中的数据
//                async: false,
//                error: function(request) {
//                    alert("发送请求失败！");},
//                success: function(data) {
//                    $("#ajaxDiv").html(data);	//将返回的结果显示到ajaxDiv中
//                }
//            });

        }

    </script>

</head>
<body>
<div class="header">

    <div class="logo">echarts Demo</div>
    <div class ="login">

    </div>
    <div id="main">


        <form id="ajaxFrm" method="post">

            <table>

                请选择年份：<input type="text"  id="qwe" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/></br>
                <tr>
                    <td colspan="2">
                        <input type="submit"  onClick="doGenWord();" name="提交">
                        <input type="reset" name="重置">
                </tr>
            </table>

        </form>



    </div>
</div>
</body>
</html>
