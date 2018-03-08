package app.servlet;

import app.util.CalcValidator;
import app.util.HtmlDocument;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "CalculationServlet", urlPatterns = "/calculate")
public class CalculationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        String message = "";
        HttpSession session = request.getSession(true);
        Object historyObject = session.getAttribute("history");
        StringBuilder history = new StringBuilder();
        List<String> operationHistory = (historyObject instanceof List)?(List<String>)historyObject:new ArrayList<>();
        if (request.getParameter("clear")!=null) {
            if (operationHistory.size()>0) message = operationHistory.get(operationHistory.size()-1);
            session.removeAttribute("history");
        } else {
            String firstNumberValue = request.getParameter("firstNumber");
            String operation = request.getParameter("operation");
            String secondNumberValue = request.getParameter("secondNumber");
            try {
                if (!CalcValidator.checkNumber(firstNumberValue)) throw new Exception("first number is invalid");
                if (!CalcValidator.checkNumber(secondNumberValue)) throw new Exception("second number is invalid");
                if (!CalcValidator.checkOperation(operation)) throw new Exception("operation is invalid");
                double first = Double.valueOf(firstNumberValue);
                double second = Double.valueOf(secondNumberValue);
                String operationSymbol;
                double result;
                switch (operation) {
                    case "add":
                        operationSymbol = "+";
                        result = first + second;
                        break;
                    case "sub":
                        operationSymbol = "-";
                        result = first - second;
                        break;
                    case "mul":
                        operationSymbol = "*";
                        result = first * second;
                        break;
                    case "div":
                        if (second == 0) throw new Exception("You can't divide by 0");
                        operationSymbol = "/";
                        result = first / second;
                        break;
                    default:
                        throw new Exception("Unknown operation exception");
                }
                message = CalcValidator.formatDouble(first) + " " + operationSymbol + " " + (second < 0 ? "(" : "") + CalcValidator.formatDouble(second) + (second < 0 ? ")" : "") + " = " + CalcValidator.formatDouble(result);
                for (String item: operationHistory){
                    history.append(item).append("<br>");
                }
                operationHistory.add(message);
                session.setAttribute("history", operationHistory);
            } catch (NullPointerException e) {
                message = "needed value is null";
            } catch (Exception e) {
                message = e.getMessage();
            }
        }
        HtmlDocument document = new HtmlDocument("Calculation servlet result", true);
        document.addElement("<div class=\"well\">");
        document.addElement("<h2>Wynik operacji:</h2>");
        document.addElement(message);
        document.addElement("<br>");
        document.addElement("<a href=\"/calc.html\">Kolejna operacja</a><br>");
        document.addElement("<a href=\"/calculate?clear\">Wyczyść historię</a>");
        document.addElement("<br>");
        document.addElement("<h3>Historia operacji:</h3>");
        document.addElement("<br>");
        document.addElement(history.toString());
        document.addElement("</div>");
        response.getWriter().println(document.toString());
    }
}
