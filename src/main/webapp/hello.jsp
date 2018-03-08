<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.concurrent.atomic.AtomicLong" %>
<%!private volatile AtomicLong applicationVisits = new AtomicLong(0);%>
<% String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    boolean first = true;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) for (Cookie c : cookies) if ("entry".equals(c.getName())) first = false;
    Cookie cookie = new Cookie("entry", "not first time");
    cookie.setMaxAge(60 * 60 * 24 * 365 * 100);
    if (first) response.addCookie(cookie);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="well">
    <%=date%>
    <br>
    <h3>Witaj po raz <%=first ? "pierwszy" : "kolejny"%>
    </h3>
    <% Object sessionVisitsObject = request.getSession(true).getAttribute("showCount");
    long sessionVisits = (sessionVisitsObject instanceof Long)?(Long)sessionVisitsObject:0;
        sessionVisits++;
    request.getSession().setAttribute("showCount", sessionVisits);
    %>
    <h1>Hello JSP!</h1>
    <b>Strona była odwiedzona <%=sessionVisits%> raz(y) w tej sesji, oraz <%=applicationVisits.incrementAndGet()%> raz(y) od momentu uruchomienia applikacji</b>
</div>
</body>
</html>
