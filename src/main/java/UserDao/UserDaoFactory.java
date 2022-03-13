package UserDao;

public class UserDaoFactory {
private static UserDao userdao;

private UserDaoFactory(){}

public static UserDao getUserDao(){
    if(userdao == null){
        userdao = new UserDaoImplementation();
    }
    return userdao;
}




}
