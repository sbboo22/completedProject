package UserServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class ServletELO extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        out.println("<head><link rel=\"stylesheet\" href=\"style.css\"></head>\n");

        session.setAttribute("_susername", "");
        out.println("<p>You have successfully logged out of the system</p>");
        request.getRequestDispatcher("index.html").include(request, response);
    }
}
