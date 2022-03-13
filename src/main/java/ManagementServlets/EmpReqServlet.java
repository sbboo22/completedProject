package ManagementServlets;

import ManagementDao.IManagerDao;
import ManagementDao.ManagerDaoFactory;
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

public class EmpReqServlet extends HttpServlet {
    public static int i;
    public static int[] idList = new int[40];
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<head><link rel=\"stylesheet\" href=\"style.css\"></head>\n");

        request.getRequestDispatcher("managerNav.html").include(request, response);

        String name = request.getParameter("empName");

        out.println("<div class=\"tableDiv\" style='width: fit-content; padding: 10px;'><h3>"+ name +"</h3><table border=1px>\n" +
                "        <tr class=\"tableHead\">\n" +
                "            <th>Id</th>\n" +
                "            <th>Amount</th>\n" +
                "            <th>Date</th>\n" +
                "            <th>Reason</th>\n" +
                "            <th>Requester</th>\n" +
                "            <th>Status</th>\n" +
                "            <th>Approve/Deny</th>\n" +
                "        </tr>\n");
        out.println("<form action =\"ManagementServlets.StatusServlet\" method=\"post\">");
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        UserDao userdao = UserDaoFactory.getUserDao();
        IManagerDao managerdao = ManagerDaoFactory.getManagerDao();
        List<Reimbursement> reqList = userdao.getReimbursement(name);

        System.out.println(reqList.size());
//        Iterator itr = reqList.iterator();
        i = 0;
        int[] idli = new int[40];
        String stringI = "";
        for (Reimbursement req: reqList) {
            // Reimbursement req = (Reimbursement) itr.next();
            out.println("<tr><td>" + req.getId() + "</td>");
            out.println("<td>" + req.getAmount() + "</td>");
            out.println("<td>" + req.getDate() + "</td>");
            out.println("<td>" + req.getReason() + "</td>");
            out.println("<td>" + req.getRequester() + "</td>");
            out.println("<td>" + req.getStatus() + "</td>");
            stringI = Integer.toString(i);
            out.println("<td>" +
                    "    Approve<input type=\"radio\" name=\"approveDeny"+stringI+"\" value=\"Approve\">" +
                    "    Deny<input type=\"radio\" name=\"approveDeny" +stringI+"\" value=\"Deny\"></td></tr>");
//            idList[i] = req.getId();
            idli[i] = req.getId();
            i++;
        }
        idList = idli;
        out.println("</table><input type=\"submit\" value = \"submit\"></form></div>");

        t.commit();
        session.close();


    }

}
