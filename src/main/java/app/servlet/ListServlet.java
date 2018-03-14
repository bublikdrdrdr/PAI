package app.servlet;

import app.bean.CustomerBean;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
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

@WebServlet(name = "ListServlet", urlPatterns = "/list")
public class ListServlet extends HttpServlet {

    private Connection connection;
    private Exception connectionException;

    private void connectDatabase(){
        connectionException = null;
        connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pai",
                    "root", null);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            connectionException = e;
        }
    }

    private void reConnect(){
        if (connection==null || connectionException!=null) connectDatabase();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object customerObject =session.getAttribute("customer");
        session.setAttribute("addCustomerException", null);
        try {
            if (!(customerObject instanceof CustomerBean)) throw new IllegalArgumentException();
            CustomerBean customer = (CustomerBean) customerObject;
            reConnect();
            if (connection==null || connectionException!=null) throw new SQLException("Can't connect to DB", connectionException);
            String insertTableSQL = "INSERT INTO customer"
                    + "(name, phone, email, city) VALUES"
                    + "(?,?,?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, customer.getFullname());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getCity());
            preparedStatement.executeUpdate();
            response.sendRedirect("/list");
        } catch (Exception e) {
            session.setAttribute("addCustomerException", e);
            response.sendRedirect("add_customer.jsp");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String deleteSQL = "DELETE FROM customer WHERE id='"+Integer.parseInt(request.getParameter("customerId"))+"'";
            connection.prepareStatement(deleteSQL).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect("/list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("post")!=null) doPost(request, response); else
        if (request.getParameter("delete")!=null) doDelete(request, response); else {
            if (connection == null || connectionException != null) connectDatabase();
            showListInJsp(request, response);
        }
    }

    private void showListInJsp(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        List<CustomerBean> list = new ArrayList<>();
        try {
            if (connectionException != null) throw connectionException;
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                CustomerBean customer = new CustomerBean();
                customer.setFullname(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setCity(rs.getString("city"));
                customer.setId(rs.getInt("id"));
                list.add(customer);
            }
            session.setAttribute("list", list);
        } catch (Exception e) {
            session.setAttribute("exception", e);
        }
        response.sendRedirect("list.jsp");
    }

    private void justPrint(HttpServletResponse response) throws IOException, ServletException{
        PrintWriter out = response.getWriter();
        if (connectionException!=null) {
            out.println("EXCEPTION: " + connectionException.toString());
            return;
        }
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM default_table");
            while (rs.next()) {
                int count = rs.getRow();
                out.println(rs.getInt(1));
                out.println(rs.getString(2));
            }
        } catch (Exception e){
            throw new ServletException(e);
        }
    }
}
