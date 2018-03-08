package app.servlet;

import app.util.HtmlDocument;
import app.util.VoteHelper;
import jxl.write.WriteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    private VoteHelper voteHelper = new VoteHelper();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean allowVoting = true;
        if (request.getCookies()!=null) {
            for (Cookie cookie : request.getCookies())
                if (cookie.getName().equals("Vote")) {
                    allowVoting = false;
                    break;
                }
        }

        Cookie cookie = new Cookie("Vote", "some value");
        cookie.setMaxAge(60*60*24*2);
        response.addCookie(cookie);

        Set<String> filteredResults = new HashSet<>();
        List<String> selectedNames = voteHelper.getSelectedTechnologies(request.getParameterMap().keySet(), filteredResults);
        if (allowVoting) {
            synchronized (this) {
                voteHelper.addAndWriteResults(filteredResults);
            }
        }
        HtmlDocument document = new HtmlDocument("Vote result", true);
        document.build("<div class=\"well\">")
                .build("<h2>Wybrałeś następujące technolodie:</h2>")
                .build("<ul>");
        for (String technology: selectedNames){
            document.build("<li>"+technology+"</li>");
        }
        document.build("</ul>");
        if (!allowVoting) document.build("<p class=\"bg-danger\">Już głosowałeś!</p>");
        document.build("<h2>Zobacz wykini ankiety:</h2>" +
                        "<ul>");
        HashMap<String, Integer> resultMap = voteHelper.readResults();
        for (Map.Entry<String, Integer> entry: resultMap.entrySet()){
            document.build("<li>"+voteHelper.getFullName(entry.getKey())+" : "+entry.getValue().toString()+"</li>");
        }
        document.build("</ul>")
                .build("<a href=\"/vote\">Pokaż wyniki ankiety w arkuszu kalkulacyjnym</a><br>")
                .build("<a href=\"/vote?chart\">Pokaż wyniki ankiety jako wykres</a>")
                .build("</div>");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(document.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("chart")!=null) generateChart(response); else generateExcel(response);
    }

    private void generateChart(HttpServletResponse response) throws IOException{
        response.setContentType("image/jpeg");
        voteHelper.writeChart(response.getOutputStream());
    }

    private void generateExcel(HttpServletResponse response) throws IOException{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=results.xls");
        try {
            voteHelper.writeExcel(response.getOutputStream());
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }



}
