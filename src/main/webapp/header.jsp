<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" %>
<%boolean first = true;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) for (Cookie c : cookies) if ("entry".equals(c.getName())) first = false;
    Cookie cookie = new Cookie("entry", "not first time");
    cookie.setMaxAge(60 * 60 * 24 * 365 * 100);
    if (first) response.addCookie(cookie);%>
<%=new SimpleDateFormat("dd.MM.yyyy").format(new Date())%>
<br>
<h4>Witaj po raz <%=first ? "pierwszy" : "kolejny"%>
</h4>