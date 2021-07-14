package sample.GameMenuControllers;

import Classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * controller class for prof page in main menu
 * contain methods and fields for corresponding fxml
 *
 * @author Mohammad hosein Aref
 * @version 1.0
 */

public class ProfPage implements GameMenController {

    private User user;

    /**
     * set users field
     * set page labels to
     * user information
     *
     * @param user  the logged in user
     */
    @Override
    public void setUser(User user) {
        this.user = user;
        userName.setText(user.getUserName());
        points.setText(user.getPoints());
    }

    @FXML
    private Label userName;

    @FXML
    private Label points;

}
