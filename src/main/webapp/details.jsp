<%@ page import="app.bean.CustomerBean" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Klient</title>
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
    <h2>Informacja o kliencie</h2>
    <br>
    <a href="list.jsp" class="bg-default">&lt;back to list</a>
    <% try {
        Object exception = session.getAttribute("exception");
        if (exception instanceof Exception) throw (Exception) exception;%>

    <%
        Object listObject = session.getAttribute("list");
        if (!(listObject instanceof List)) throw new Exception("Lista jest null");
        List<CustomerBean> list = (List) listObject;
        String indexString = request.getParameter("index");
        int index = Integer.parseInt(indexString);
        CustomerBean customer = list.get(index);
    %>

<div class="panel panel-primary">
    <table>
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Phone</th>
            <th scope="col">Email</th>
            <th scope="col">City</th>
        </tr>
        </thead>
        <tbody>
        <%
                out.println("<tr>");
                td(out, customer.getId());
                td(out, customer.getFullname());
                td(out, customer.getPhone());
                td(out, customer.getEmail());
                td(out, customer.getCity());
                out.println("</tr>");
        %>
        </tbody>
    </table>
</div>
    <a class="text-danger" data-confirm="Delete customer" data-method="delete" href="list?delete&customerId=<%=customer.getId()%>">Delete</a>
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