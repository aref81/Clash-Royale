package Classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.GameControllers.GameController;
import sample.GameMenuControllers.MainMenu;

import java.io.IOException;

public class TrainingCampMatch {
    private Stage stage;
    private User user;

    public TrainingCampMatch(Stage stage, User user) {
        this.stage = stage;
        this.user = user;
    }

    public void run (){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Sample/Game/Game.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController gameController = loader.getController();
        gameController.setUser(user);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setX(400);
        stage.setY(50);
        stage.show();
    }
}
