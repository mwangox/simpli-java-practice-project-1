package tz.co.simplilearn.product_details;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        HttpSession session = req.getSession();
        PrintWriter out = res.getWriter();

        if("root".equals(req.getParameter("username")) && "password".equals(req.getParameter("password"))){
            RequestDispatcher rd = req.getRequestDispatcher("dashboardServlet");
            session.setAttribute("isLoggedIn", true);
            rd.forward(req, res);
        }else {
            out.println("<b style='color: red'>Please supply correct username and password!</b>");
            RequestDispatcher rd = req.getRequestDispatcher("/index.html");
            rd.include(req, res);
        }
    }
}
