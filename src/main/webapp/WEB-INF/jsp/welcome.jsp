<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="../static/js/jquery-2.1.1.min.js"></script>
    <title>欢迎页面</title>

    <script>
        $(document).ready(function (){



        });

        function getUserInfoById(){
            var userId = $("#userId").val();

            $.ajax({
                url:"/demo/welcome/getUserInfoById.html",
                contentType:'application/x-www-form-urlencoded',
                data:{"userId":userId},
                type:'POST',
                dataType:'json',
                success:function (data){
                    var name = data.name;
                    var age = data.age;

                    $('#name').val(name);
                    $('#age').val(age);
                },
                error:function (data){
                    alert("Error!");
                }
            });
        }

        function modifyInfo(){
            var userId = $("#userId").val();
            var name = $("#name").val();
            var age = $("#age").val();

            $.ajax({
                url:"/demo/welcome/updateUserInfoById.html",
                contentType:'application/x-www-form-urlencoded',
                data:{"userId":userId,"name":name,"age":age},
                type:'POST',
                dataType:'json',
                success:function (data){
                    alert(data.msgFlag);
                },
                error:function (data){
                    alert("Error!");
                }
            });
        }

    </script>
</head>
<body>
    <h2>${msg}</h2>
    <br>

    <table>
        <tr>
            <td>用户ID</td>
            <td>
                <input type="text" id="userId"/>
            </td>
        </tr>

        <tr>
            <td>
                姓名
            </td>
            <td>
                <input type="text" id="name"/>
            </td>
        </tr>

        <tr>
            <td>
                年龄
            </td>
            <td>
                <input type="text" id="age"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <button onclick="getUserInfoById()">根据用户Id查询</button>
                <button onclick="modifyInfo()">修改用户名和年龄</button>
            </td>

        </tr>

    </table>
</body>
</html>
