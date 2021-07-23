package sample.GameMenuControllers;


import Classes.GameMenu;
import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TrainingCampHnadler implements GameMenController{
    private User user;

    @FXML
    void Hard(ActionEvent event) {
        Stage current = ((Stage) (((Button) event.getSource()).getScene().getWindow()));
        GameMenu.startCampMatch(user, current,true);
    }

    @FXML
    void easy(ActionEvent event) {
        Stage current = ((Stage) (((Button) event.getSource()).getScene().getWindow()));
        GameMenu.startCampMatch(user, current,false);
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
