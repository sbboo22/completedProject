package ManagementDao;

public class ManagerDaoFactory {

    private static IManagerDao managerDao;

    private ManagerDaoFactory(){}

    public static IManagerDao getManagerDao(){
        if(managerDao == null){
            managerDao = new ManagerDaoImplement();
        }
        return managerDao;
    }



}
