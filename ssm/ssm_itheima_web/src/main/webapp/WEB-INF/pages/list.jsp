<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

</head>

<body>
<script>
    window.onload = function (ev) {
        document.getElementById("delSelected").onclick = function (ev2) {
            //给按钮添加点击事件,添加判断是否删除
            if(confirm("您是否要删除选中的?")){
                //默认条目未被选中
                var flag = false;
                //如果确定要删除,首先判断选中条目
                var cbs = document.getElementsByName("uid");
                for(var i = 0;i<cbs.length;i++){
                    if(cbs[i].checked){
                        flag = true;
                        //不加break试试
                    }
                }
                if(flag){
                    //如果条目被选中,提交表单
                    document.getElementById("form").submit();
                }


            }

        }
        //1.获取第一个cb
        document.getElementById("firstCb").onclick = function(){
            //2.获取下边列表中所有的cb
            var cbs = document.getElementsByName("uid");
            //3.遍历
            for (var i = 0; i < cbs.length; i++) {
                //4.设置这些cbs[i]的checked状态 = firstCb.checked
                cbs[i].checked = this.checked;

            }

        }

    }
</script>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/user/findAll" method="post">
            <div class="form-group">
                <label for="exampleInputName">姓名</label>
                <input type="text" name="user.username" class="form-control" value="${pb.user.username}" id="exampleInputName" >
            </div>
            <div class="form-group">
                <label for="address">籍贯</label>
                <input type="text" name="user.address" value="${pb.user.address}" class="form-control" id="address" >
            </div>

            <div class="form-group">
                <label for="exampleInputEmail">邮箱</label>
                <input type="text" name="user.email"  value="${pb.user.email}" class="form-control" id="exampleInputEmail"  >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>
    <div style="float: right;margin: 5px;">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/add">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
    </div>

    <form id="form" action="${pageContext.request.contextPath}/user/delSelectedUser" method="post">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>地址</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${userList}" var="user" varStatus="s">
            <tr>
                <td><input type="checkbox" name="uid"  value="${user.id}"></td>
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/user/findUserById?id=${user.id}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/user/deleteUserById?id=${user.id}">删除</a></td>
            </tr>
        </c:forEach>

    </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage == 1}">
                <li class="disabled">
                    </c:if>
                    <c:if test="${pb.currentPage != 1}">
                <li>
                    </c:if>
                    <%--第一个小箭头--%>
                    <a href="${pageContext.request.contextPath}/user/findAll?currentPage=${pb.currentPage <= 1 ? 1: pb.currentPage-1}&user.username=${pb.user.username}&user.address=${pb.user.address}&user.email=${pb.user.email}">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                    <%--中间的页码--%>
                    <c:forEach begin="1" end="${pb.totalPage}" var="i" >
                    <c:if test="${pb.currentPage == i}">
                <li class="active"><a href="${pageContext.request.contextPath}/user/findAll?currentPage=${i}&user.username=${pb.user.username}&user.address=${pb.user.address}&user.email=${pb.user.email}">${i}</a></li>
                </c:if>
                <c:if test="${pb.currentPage != i}">
                    <li><a href="${pageContext.request.contextPath}/user/findAll?currentPage=${i}&user.username=${pb.user.username}&user.address=${pb.user.address}&user.email=${pb.user.email}">${i}</a></li>
                </c:if>
                </c:forEach>
                <%--最后一个小箭头--%>
                <li>
                    <a href="${pageContext.request.contextPath}/user/findAll?currentPage=${pb.currentPage >= pb.totalPage ?pb.totalPage:pb.currentPage + 1}&user.username=${pb.user.username}&user.address=${pb.user.address}&user.email=${pb.user.email}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <%--提示页--%>
                <span style="font-size: 25px;margin-left: 5px;">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                    </span>


            </ul>
        </nav>
    </div>
</div>
</body>
</html>
