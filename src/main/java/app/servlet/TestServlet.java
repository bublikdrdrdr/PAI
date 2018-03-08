package app.servlet;

import app.util.HtmlDocument;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "TestServlet")
public class TestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HtmlDocument document = new HtmlDocument("Test page", true);
        document.addElement("<div class=\"well\">");
        document.addElement("<h1>Hello <s>kebab</s> world!</h1>");
        document.addElement("</div>");
        response.getWriter().println(document.toString());
    }
}
