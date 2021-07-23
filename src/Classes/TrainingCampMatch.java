package Classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.GameControllers.GameController;

import java.io.IOException;

public class TrainingCampMatch {
    private Stage stage;
    private User user;
    private boolean hard;

    /**
     * initializes a new game
     *
     * @param stage the primary stage
     * @param user the user
     * @param hard is hard
     */
    public TrainingCampMatch(Stage stage, User user,boolean hard) {
        this.stage = stage;
        this.user = user;
        this.hard = hard;
    }

    /**
     * makes scene ready
     */
    public void run (){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Sample/Game/Game.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController gameController = loader.getController();
        gameController.setUser(user,hard,stage);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setX(400);
        stage.setY(50);
        stage.show();
    }
}
