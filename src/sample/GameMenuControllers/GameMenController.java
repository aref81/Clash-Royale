package sample.GameMenuControllers;

import Classes.User;

/**
 * interface for different menu pages controllers
 * contains general methods for controllers;
 *
 * @author Mohammad hosein Aref
 * @version 1.0
 *
 */
public interface GameMenController {
    /**
     * sets the user field in controllers
     *
     * @param user  the logged in user
     */
    public void setUser(User user);
}
