package UserServlets;

import UserDao.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class ServletUPE extends HttpServlet {
    UserDao userdao = new UserDaoImplementation();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out  = response.getWriter();
        HttpSession session = request.getSession(false);

        out.println("<head><link rel=\"stylesheet\" href=\"style.css\"></head>\n");

        String currentUser = (String)session.getAttribute("_susername");
        User currentinfo = userdao.viewProfile(currentUser);
        String oldSession = currentinfo.getUsername();
        if(!request.getParameter("_name").equals("")){
            currentinfo.setName(request.getParameter("_name"));
        }
        if(!request.getParameter("_email").equals("")){
            currentinfo.setEmail(request.getParameter("_email"));
        }
        if(!request.getParameter("_username").equals("")){
            currentinfo.setUsername(request.getParameter("_username"));
            session.setAttribute("_susername", currentinfo.getUsername());
        }
        if(!request.getParameter("_password").equals("")){
            currentinfo.setPassword(request.getParameter("_password"));
        }

        userdao.updateProfile(currentinfo);
        request.getRequestDispatcher("navbar.html").include(request, response);
        out.println("<h2 style=\"color: green\">Update Completed Successfully!!!</h2>");
    }
}
