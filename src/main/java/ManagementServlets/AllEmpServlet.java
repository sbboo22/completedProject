package ManagementServlets;

import UserDao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AllEmpServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<head><link rel=\"stylesheet\" href=\"style.css\"></head>\n");

        request.getRequestDispatcher("managerNav.html").include(request, response);

        out.println("<div><table border=1px>\n" +
                "        <tr>\n" +
                "            <th>Id</th>\n" +
                "            <th>Name</th>\n" +
                "            <th>Email</th>\n" +
                "            <th>Username</th>\n" +
                "            <th>Requests</th>\n" +
                "        </tr>\n");

        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        List<User> empList = session.createQuery("from User", User.class).list();

        System.out.println(empList.size());
        UserDao userdao = UserDaoFactory.getUserDao();

        for (User emp : empList) {

            int requests = userdao.getReimbursement(emp.getUsername()).size();
            out.println("<tr><td>" + emp.getId() + "</td>");
            out.println("<td>" + emp.getName() + "</td>");
            out.println("<td>" + emp.getEmail() + "</td>");
            out.println("<td>" + emp.getUsername() + "</td>");
            out.println("<td>" + requests + "</td>");

        }

        out.println("</table></div>");
        t.commit();

    }

}
