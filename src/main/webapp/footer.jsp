<%@ page import="java.util.concurrent.atomic.AtomicLong" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!private volatile AtomicLong applicationVisits = new AtomicLong(0);%>
<% Object sessionVisitsObject = request.getSession(true).getAttribute("showCount");
    long sessionVisits = (sessionVisitsObject instanceof Long)?(Long)sessionVisitsObject:0;
    sessionVisits++;
    request.getSession().setAttribute("showCount", sessionVisits);
%>
<b>Strona była odwiedzona <%=sessionVisits%> raz(y) w tej sesji, oraz <%=applicationVisits.incrementAndGet()%> raz(y) od momentu uruchomienia applikacji</b>