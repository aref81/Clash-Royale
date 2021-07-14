package Classes;

import java.io.Serializable;

/**
 * Implements a User in the game
 * contains information for a user
 *
 * @author Mohammad Hosein Aref
 * @version 1.0
 *
 */
public class User implements Serializable {
    private String userName;
    private String password;
    private String points;

    /**
     * initializes a use using passed arguments
     *
     * @param userName user name
     * @param password password
     * @param points thr total points
     */
    public User(String userName, String password , String points) {
        this.userName = userName;
        this.password = password;
        this.points = points;
    }

    /**
     * returns the username
     *
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * returns password
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * return the total points
     *
     * @return points
     */
    public String getPoints() {
        return points;
    }

    /**
     * changes the password of user
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * changes the points of a user
     *
     * @param points the new points
     */
    public void setPoints(String points) {
        this.points = points;
    }
}
