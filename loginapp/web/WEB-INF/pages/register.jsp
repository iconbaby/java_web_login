<%--
  Created by IntelliJ IDEA.
  User: slkk
  Date: 18.4.23
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body style="text-align: center;">
<form action="${pageContext.request.contextPath}/registerServlet" method="post">
    <table width="60%" border="1">
        <tr>
            <td>用户名</td>
            <td><input type="text" name="userName" value="${formbean.userName}">${formbean.errors.userName}</td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="userPwd"value="${formbean.userPwd}">${formbean.errors.userPwd}</td>

        </tr>
        <tr>
            <td>确认密码：</td>
            <td> <input type="password" name="confirmPwd" value="${formbean.confirmPwd}">${formbean.errors.confirmPwd}</td>

        </tr>
        <tr>
            <td>邮箱</td>
            <td><input type="text" name="email"value="${formbean.email}">${formbean.errors.email}</td>

        </tr>
        <tr>
            <td>生日</td>
            <td> <input type="text" name="birthday" value="${formbean.birthday}">${formbean.errors.birthday}</td>

        </tr>
        <tr>
            <td>
                <input type="reset" name="清空">
            </td>
            <td>
                <input type="submit" value="注册">
            </td>
        </tr>

    </table>
</form>

</body>
</html>
