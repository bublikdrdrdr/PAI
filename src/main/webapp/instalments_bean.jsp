<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loan" class="app.bean.LoanBean" scope="session" />
<jsp:setProperty name="loan" property="*" />
<html>
<head>
    <title>Instalment Calculator via Bean</title>
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
    <h2>Kalkulator rat</h2>
    <form action="instalments_bean.jsp" method="post">
        <table>
            <tr>
                <td>Kwota pożyczki:</td>
                <td><input type="number" step="any" name="price" value="<%=loan.getPrice()%>"></td>
            </tr>
            <tr>
                <td>Oprocentowanie:</td>
                <td><input type="number" step="any" name="rate" value="<%=loan.getRate()%>"></td>
            </tr>
            <tr>
                <td>Liczba rat:</td>
                <td><input type="number" name="count" value="<%=loan.getCount()%>"></td>
            </tr>
        </table>
        <input type="submit" value="Oblicz">
    </form>
    <%
        if (request.getMethod().toUpperCase().equals("POST")) {
            try {
                double result = loan.getInstallment();
    %><p class="bg-success">Miesięczna rata do spłaty: <%=result%></p><%
            } catch (Exception e) {
                String errorMessage;
                if (e instanceof NullPointerException) errorMessage = "Jeden lub kilka parametrów są NULL"; else
                if (e instanceof IllegalArgumentException) errorMessage = "Błędne dane!"; else
                    errorMessage = e.getMessage();
    %><p class="bg-danger"><%=errorMessage%></p><%
            }
    }
%>
    <hr>
    <br>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>