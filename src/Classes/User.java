package Classes;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;
    private String points;

    public User(String userName, String password , String points) {
        this.userName = userName;
        this.password = password;
        this.points = points;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPoints() {
        return points;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
