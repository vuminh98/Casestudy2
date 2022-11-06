package Account;

import java.io.Serializable;

public class User implements Serializable {
    private String account;
    private String password;
    private String role;
    private Role role1;

    public static Long INDEX = Long.valueOf(0);

    private Long id;


//    public User(String account, String password, String name) {
//        this.account = account;
//        this.password = password;
//        this.name = name;
//        this.role = "USER";
//    }

    public User(String account, String password) {
        this.id = Long.valueOf(++INDEX);
        this.account = account;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Role getRole1() {
        return role1;
    }

    public void setRole1(Role role1) {
        this.role1 = role1;
    }
}
