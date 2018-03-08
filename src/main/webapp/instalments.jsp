<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Instalment Calculator</title>
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
    <form action="instalments.jsp" method="post">
        <table>
            <tr>
                <td>Kwota pożyczki:</td>
                <td><input type="number" step="any" name="price"></td>
            </tr>
            <tr>
                <td>Oprocentowanie:</td>
                <td><input type="number" step="any" name="rate"></td>
            </tr>
            <tr>
                <td>Liczba rat:</td>
                <td><input type="number" name="count"></td>
            </tr>
        </table>
        <input type="submit" value="Oblicz">
    </form>
    <%
        if (request.getMethod().toUpperCase().equals("POST")) {
            String priceParam = request.getParameter("price");
            String rateParam = request.getParameter("rate");
            String countParam = request.getParameter("count");
            try {
                double price = Double.parseDouble(priceParam);
                double rate = Double.parseDouble(rateParam)/100;
                int count = Integer.parseInt(countParam);
                double result = (price*(rate/12))/(1-(1/Math.pow(1+(rate/12), count)));
    %><p class="bg-success">Miesięczna rata do spłaty: <%=result%></p><%
            } catch (Exception e) {
                String errorMessage;
                if (e instanceof NullPointerException) errorMessage = "Jeden lub kilka parametrów są NULL"; else
                if (e instanceof NumberFormatException) errorMessage = "Błędne dane!"; else
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