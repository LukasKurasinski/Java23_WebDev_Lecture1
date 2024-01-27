package servlets;

import models.MySQLConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =0;

        LinkedList<String[]> queryReturn = MySQLConnector.getConnector().selectQuery("allFromGritAcademy");

        String top = "<head><title>Hello " + req.getParameter("name") +  "</title></head>"
                    + "<body>"
                    + "<h2>Hello from Java Servlet!</h2>"
                    + "<table style='margin-left: auto; margin-right: auto;'>";

        String bottom = "</table>"
                        + "</body>"
                        + "</html>";

        resp.setContentType("text/HTML");
        PrintWriter out = resp.getWriter();

        out.println(top);

        queryReturn.forEach(row -> {

            out.println("<tr>");
            Arrays.stream(row).forEach(dataPoint -> {
                out.println("<td style='border: 1px solid black; background-color: #96D4D4;'>" + dataPoint + "</td>");
            });
            out.println("</tr>");
        });

        out.println(bottom);
    }
}
