package ManagementDao;

import UserDao.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ManagerDaoImplement implements IManagerDao{

    @Override
    public User mLogin(String username, String pass) {
        org.hibernate.cfg.Configuration config = new Configuration();
        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");
        // create Session factory
        SessionFactory factory = config.buildSessionFactory();
        // open the session
        Session session = factory.openSession();
        // begin transaction
        Transaction t = session.beginTransaction() ;
        // Perform Query
        User currentUser = session.createQuery("from UserDao.User where username = \"" + username + "\" AND password = \""+pass + "\"", User.class).getSingleResult();

        System.out.println(currentUser);

        t.commit();
        int type = (Integer)currentUser.getType();

        //if((currentUser.getType() == 1)){
        if(type == 1){
            return currentUser;
        }

        return null;
    }

    @Override
    public void mLogout() {

    }

    @Override
    public void approve(int id) {
        // create a configuration object
        org.hibernate.cfg.Configuration config = new Configuration();
        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");
        // create Session factory
        SessionFactory factory = config.buildSessionFactory();
        // open the session
        Session session = factory.openSession();
        // begin transaction
        Transaction t = session.beginTransaction() ;

        Reimbursement req = session.get(Reimbursement.class, id);
        req.setStatus("Approved");
        session.saveOrUpdate(req);
        t.commit();
    }

    @Override
    public void deny(int id) {
        // create a configuration object
        org.hibernate.cfg.Configuration config = new Configuration();
        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");
        // create Session factory
        SessionFactory factory = config.buildSessionFactory();
        // open the session
        Session session = factory.openSession();
        // begin transaction
        Transaction t = session.beginTransaction() ;

        Reimbursement req = session.get(Reimbursement.class, id);
        req.setStatus("Deny");
        session.saveOrUpdate(req);
        t.commit();

    }

    @Override
    public List<Reimbursement> allPending() {
        // create a configuration object
        org.hibernate.cfg.Configuration config = new Configuration();
        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");
        // create Session factory
        SessionFactory factory = config.buildSessionFactory();
        // open the session
        Session session = factory.openSession();
        // begin transaction
        Transaction t = session.beginTransaction() ;
        // Perform Query
        List<Reimbursement> reimbursements = session.createQuery("from Reimbursement where status = \"Pending\"" ,Reimbursement.class).list();
        t.commit();

        return reimbursements;
    }

    @Override
    public List<Reimbursement> allResolved() {
        // create a configuration object
        org.hibernate.cfg.Configuration config = new Configuration();
        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");
        // create Session factory
        SessionFactory factory = config.buildSessionFactory();
        // open the session
        Session session = factory.openSession();
        // begin transaction
        Transaction t = session.beginTransaction() ;
        // Perform Query
        List<Reimbursement> reimbursements = session.createQuery("from Reimbursement where status != \"Pending\"" ,Reimbursement.class).list();
        t.commit();

        return reimbursements;
    }

    @Override
    public Reimbursement getByEmp(User user) {
        return null;
    }

    @Override
    public List<User> allEmp() {
        return null;
    }
    @Override
    public void updateRequest(String newStatus, int id) {
        // create a configuration object
        org.hibernate.cfg.Configuration config = new Configuration();
        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");
        // create Session factory
        SessionFactory factory = config.buildSessionFactory();
        // ope the session
        Session session = factory.openSession();
        // begin transaction
        Transaction t = session.beginTransaction();
        // create query
        String QHQL = "UPDATE UserDao.Reimbursement set status=\""+newStatus+"\" Where id=\"" + id +"\"";

        //create query
        session.createMutationQuery(QHQL).executeUpdate();

        // commit
        t.commit();
    }
}