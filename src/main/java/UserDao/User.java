package UserDao;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1)
    private int id;
    private String name;
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    private int type;


    public User(){}
    public User(String name, String email, String username, String password, int type){
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.type = type;
    }
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserDao.User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
