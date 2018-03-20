package app.servlet;

import app.bean.CustomerBean;
import app.util.Customers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HibernateListServlet", urlPatterns = "/hibernate_list")
public class HibernateListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showListInJsp(request, response);
    }

    private void showListInJsp(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        Customers customers = new Customers();
        try {
            session.setAttribute("list", customers.getCustomers());
        } catch (Exception e) {
            session.setAttribute("exception", e);
        }
        response.sendRedirect("list.jsp");
    }
}
