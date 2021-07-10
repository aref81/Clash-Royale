package Classes;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String Password;

    public User(String userName, String password) {
        this.userName = userName;
        Password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return Password;
    }
}
