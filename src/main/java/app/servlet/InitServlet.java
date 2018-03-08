package app.servlet;

import app.util.HtmlDocument;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "InitServlet", urlPatterns = "/init")
public class InitServlet extends HttpServlet {

    private Date dateFromInit;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss:SS");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("initialization time: "+dateFormat.format(dateFromInit));
        writer.println("server current time: "+dateFormat.format(new Date()));
    }

    @Override
    public void init() throws ServletException {
        super.init();
        dateFromInit = new Date();
    }
}
