package Classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.GameMenuControllers.MainMenu;

import java.io.IOException;

public class GameMenu {
    private Stage stage;
    private User user;

    public GameMenu(Stage stage, User user) {
        this.stage = stage;
        this.user = user;
    }

    public void run (){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Sample/GameMenu/Menu.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainMenu mainMenu = loader.getController();
        mainMenu.setUser(user);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
