<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="customer" class="app.bean.CustomerBean" scope="session"/>
<jsp:setProperty name="customer" property="*"/>
<%
    if (request.getMethod().toUpperCase().equals("POST")){
        response.sendRedirect("list?post");
    }
%>
<html>
<head>
    <title>Add customer</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        td {
            padding-bottom: .5em;
        }
    </style>
</head>
<body>
<div class="well">
    <jsp:include page="header.jsp"/>
    <hr>
    <h2>Add new customer</h2>
    <form action="add_customer.jsp" method="post">
        <table>
            <tr>
                <td>Imie</td>
                <td><input type="text" name="fullname"></td>
            </tr>
            <tr>
                <td>Telefon</td>
                <td><input type="text" name="phone"></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>Miasto</td>
                <td><input type="text" name="city"></td>
            </tr>
        </table>
        <input type="submit" value="Dodaj">
    </form>
    <%
        Object exception = request.getSession().getAttribute("addCustomerException");
        if (exception instanceof Exception) {
    %><p class="bg-danger"><%=((Exception) exception).getMessage()%>
</p><%
    }
%>
    <hr>
    <br>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
