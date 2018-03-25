<%@ page import="java.util.List" %>
<%@ page import="app.bean.CustomerBean" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista klientów</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        table {
            border-collapse: separate;
            border-spacing: 1em;
        }
    </style>
</head>
<body>
<div class="well">
    <h2>Lista klientów</h2>
    <% try {
        Object exception = session.getAttribute("exception");
        if (exception instanceof Exception) throw (Exception) exception;%>

    <%
        Object listObject = session.getAttribute("list");
        if (!(listObject instanceof List)) throw new Exception("Lista jest null");
        List<CustomerBean> list = (List) listObject;
    %>
    <a href="add_customer.jsp<%=request.getParameterMap().containsKey("hibernate")?"?hibernate":""%>" class="bg-success">Add new customer</a>
<div class="panel panel-primary">
    <table>
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Phone</th>
            <th scope="col">Email</th>
            <th scope="col">City</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <%
            int index = 0;
            for (CustomerBean customer : list) {
                out.println("<tr>");

                td(out, customer.getId());
                td(out, customer.getFullname());
                td(out, customer.getPhone());
                td(out, customer.getEmail());
                td(out, customer.getCity());
                td(out, "<a href=\"/details.jsp?index="+index+"\">Details</a>");

                out.println("</tr>");
                index++;
            }
        %>
        </tbody>
    </table>
</div>
    <%} catch (Exception e) {%>
    <p class="bg-danger">Servlet exception: <%=e.toString()%>
    </p>
    <%}%>
</div>
</body>
</html>

<%!
private void td(JspWriter out, Object o) throws IOException{
    out.println("<td>");
    out.println(o);
    out.println("</td>");
}
%>