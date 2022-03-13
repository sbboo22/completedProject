package UserServlets;

import UserDao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class ServletELN extends HttpServlet {
    UserDao userdao = UserDaoFactory.getUserDao();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        User employee = new User();
        employee.setUsername(request.getParameter("_username"));
        employee.setPassword(request.getParameter("_password"));
        employee = userdao.Login(employee.getUsername(), employee.getPassword());
        if (employee.getName()==null){
            out.println("<p style=\"color: red\">Bad Credentials</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }else {
            HttpSession session = request.getSession(true);
            session.setAttribute("_susername",employee.getUsername());
            if(employee.getType() == 0 || employee.getType() == 1)
                request.getRequestDispatcher("navbar.html").forward(request, response);
            else{
                out.println("<h1>Welcome Manager</h1>");
            }
        }
    }
}