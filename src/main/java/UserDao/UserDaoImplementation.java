package UserDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoImplementation implements UserDao{

    @Override
    public void createReimbursement(Reimbursement newReimbursement) {
        // create a configuration object
        org.hibernate.cfg.Configuration config = new Configuration();
        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");
        // create Session factory
        SessionFactory factory = config.buildSessionFactory();
        // ope the session
        Session session = factory.openSession();
        // begin transaction
        Transaction t = session.beginTransaction() ;
        // save the object
        session.persist(newReimbursement);
        // commit
        t.commit();
    }

    @Override
    public List<Reimbursement> getReimbursement(String name) {
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
        List<Reimbursement> reimbursements = session.createQuery("from UserDao.Reimbursement where requester = \"" + name + "\"" , Reimbursement.class).list();
        t.commit();



        return reimbursements;
    }

    @Override
    public Reimbursement getReimbursementById(int id) {
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
        Reimbursement reimbursement = session.createQuery("from UserDao.Reimbursement where id = \"" + id + "\"" , Reimbursement.class).getSingleResult();

        t.commit();

        return reimbursement;
    }

    @Override
    public List<Reimbursement> getAllReimbursement() {
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
        List<Reimbursement> reimbursements = session.createQuery("from UserDao.Reimbursement" , Reimbursement.class).list();
        t.commit();



        return reimbursements;
    }

    @Override
    public User viewProfile(String username) {
        User foundUser = new User();
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
        List<User> users = session.createQuery("from UserDao.User where username = \"" + username + "\"" ,User.class).list();
        t.commit();
        foundUser.setId(users.get(0).getId());
        foundUser.setName(users.get(0).getName());
        foundUser.setEmail(users.get(0).getEmail());
        foundUser.setUsername(username);
        foundUser.setPassword(users.get(0).getPassword());
        foundUser.setType(users.get(0).getType());

        return foundUser;
    }

    @Override
    public User Login(String username, String password){
        User foundUser = new User();
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
        List<User> users = session.createQuery("from UserDao.User where username = \"" + username + "\"AND password = \""+password+"\"" ,User.class).list();
        t.commit();
        try {
            foundUser.setId(users.get(0).getId());
            foundUser.setName(users.get(0).getName());
            foundUser.setEmail(users.get(0).getEmail());
            foundUser.setUsername(username);
            foundUser.setPassword(password);
            foundUser.setType(users.get(0).getType());
        }catch (Exception e){
            System.out.println("Bad Credentials");
        }


        return foundUser;
    }

    @Override
    public void updateProfile(User user) {
        // create a configuration object
        org.hibernate.cfg.Configuration config = new Configuration();
        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");
        // create Session factory
        SessionFactory factory = config.buildSessionFactory();
        // ope the session
        Session session = factory.openSession();
        // begin transaction
        Transaction t = session.beginTransaction() ;
        // create query
        String QHQL = "UPDATE UserDao.User set name=\""+user.getName()+"\", email=\"" +user.getEmail()+
                "\", username=\"" +user.getUsername()+"\", password=\"" +user.getPassword()+"\" Where id=\"" + user.getId()+"\"";

        //create query
        session.createMutationQuery(QHQL).executeUpdate();

        // commit
        t.commit();
    }


}
