package ManagementServlets;

import ManagementDao.IManagerDao;
import ManagementDao.ManagerDaoFactory;
import UserDao.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class MLoginServlet extends HttpServlet {

    IManagerDao managerDao = ManagerDaoFactory.getManagerDao();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<head><link rel=\"stylesheet\" href=\"style.css\"></head>\n");

        User manager;
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");

        manager = managerDao.mLogin(uname, pass);

        if (manager.getName() == null){
            out.println("<p style=\"color: red\">You're Not a Manager</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }else {
            HttpSession session = request.getSession(true);
            session.setAttribute("_susername",manager.getUsername());

            request.getRequestDispatcher("managerNav.html").include(request, response);
            out.println("<div><h1>Welcome "+ manager.getName() +"</h1>");
            out.println("<ul id=\"mOptions\">\n" +
                    "    <li><a href=\"ManagementServlets.PendingServlet\">View All Pending Requests</a></li>\n" +
                    "    <li><a href=\"ManagementServlets.ResolvedServlet\">View All Resolved Requests</a></li>\n" +
                    "    <li><a href=\"chooseEmp.html\">View An Employees Requests</a></li>\n" +
                    "    <li><a href=\"ManagementServlets.AllEmpServlet\">View All Employees</a></li>\n" +
                    "    <li><a href=\"newEmp.html\">Add New Employee</a></li>\n" +
                    "  </ul>\n" +
                    "</div>");
        }
    }

}
