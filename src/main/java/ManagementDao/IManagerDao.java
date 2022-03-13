package ManagementDao;

import UserDao.*;

import java.util.List;

public interface IManagerDao {

    User mLogin(String username, String pass);

    void mLogout();

    void approve(int id);
    void deny(int id);
    List<Reimbursement> allPending();
    List<Reimbursement> allResolved();
    Reimbursement getByEmp(User user);
    List<User> allEmp();
    public void updateRequest(String newStatus, int id);

}