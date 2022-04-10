package tz.co.simplilearn.product_details;

import tz.co.simplilearn.product_details.utils.JdbcConnectionUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class DashboardServlet extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        if(session != null) {
            boolean isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
            if(isLoggedIn){
                try {
                    out.println("<html><body>");
                    out.println("<h1> Welcome: " + request.getParameter("username")+"            <a href='index.html'>Logout</a></h1>");

                    Connection connection = JdbcConnectionUtils.createConnection();
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery("Select * from product");
                    out.println("<table border='1'><tr><th>Name</th><th>Price</th></tr>");
                    while (result.next()){
                        out.println("<tr><td> " + result.getString("name") +"</td>");
                        out.println("<td> " + result.getInt("price") +"</td></tr>");
                    }
                    out.println("<table>");
                    result.close();
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    out.println("<b style='color:red'>Failed to retrieve products from database</b>");
                    out.println("<pre>" + e.getMessage()+"</pre>");
                }

                out.println("</body></html>");

                session.invalidate();
            }else {
                response.sendRedirect("index.html");
            }
        }else {
            out.println("Please login first!");
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
        }
    }

    public void destroy() {
    }
}