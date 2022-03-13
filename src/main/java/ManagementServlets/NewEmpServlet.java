package ManagementServlets;

import UserDao.User;
import jakarta.servlet.RequestDispatcher;
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

public class NewEmpServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<head><link rel=\"stylesheet\" href=\"style.css\"></head>\n");

        request.getRequestDispatcher("managerNav.html").include(request, response);

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");


        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(password);
        if (type.equals("Employee")){
            newUser.setType(0);
        }else if(type.equals("Manager")){
            newUser.setType(1);
        }


        out.println("New User Added!");
        System.out.println(newUser);
        RequestDispatcher rd = request.getRequestDispatcher("/newEmp.html");
        rd.include(request, response);
        session.persist(newUser);
        System.out.println("Test 4");
        t.commit();
        System.out.println("Test 5");




    }

}
