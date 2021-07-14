package sample.GameMenuControllers;

import Classes.User;

/**
 * controller class for match page in main menu
 * contain methods and fields for corresponding fxml
 *
 * @author Mohammad hosein Aref
 * @version 1.0
 */

public class MatchPage implements GameMenController{
    private User user;

    /**
     * set users field
     *
     *
     * @param user  the logged in user
     */
    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
