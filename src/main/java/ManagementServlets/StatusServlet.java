package ManagementServlets;

import ManagementDao.IManagerDao;
import ManagementDao.ManagerDaoFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class StatusServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        IManagerDao mdao = ManagerDaoFactory.getManagerDao();
        String stringI = "";
        String statholder = "";
        if (EmpReqServlet.i != 0) {
            for (int comb = 0; comb != EmpReqServlet.i; comb++) {
                stringI = Integer.toString(comb);
                statholder = request.getParameter("approveDeny" + stringI);
                if (statholder != null) {
                    if (statholder.equals("Approve"))
                        mdao.updateRequest("Approved", EmpReqServlet.idList[comb]);
                    else if (statholder.equals("Deny"))
                        mdao.updateRequest("Denied", EmpReqServlet.idList[comb]);
                }
            }
        }
        if (PendingServlet.i != 0) {
            for (int comb = 0; comb != PendingServlet.i; comb++) {
                stringI = Integer.toString(comb);
                statholder = request.getParameter("approveDeny" + stringI);
                if (statholder != null) {
                    if (statholder.equals("Approve"))
                        mdao.updateRequest("Approved", PendingServlet.idList[comb]);
                    else if (statholder.equals("Deny"))
                        mdao.updateRequest("Denied", PendingServlet.idList[comb]);
                }
            }
        }
        if (ResolvedServlet.i != 0) {
            for (int comb = 0; comb != ResolvedServlet.i; comb++) {
                stringI = Integer.toString(comb);
                statholder = request.getParameter("approveDeny" + stringI);
                if (statholder != null) {
                    if (statholder.equals("Approve"))
                        mdao.updateRequest("Approved", ResolvedServlet.idList[comb]);
                    else if (statholder.equals("Deny"))
                        mdao.updateRequest("Denied", ResolvedServlet.idList[comb]);
                }
            }
        }
        out.println("<p>All selected reimbursement request resolved</p>");
        request.getRequestDispatcher("managerNav.html").include(request, response);
    }

}
