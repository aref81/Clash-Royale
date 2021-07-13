package sample.GameMenuControllers;

import Classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ProfPage implements GameMenController {

    private User user;

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
