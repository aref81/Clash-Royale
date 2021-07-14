package Classes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.GameMenuControllers.MainMenu;
import java.io.IOException;

/**
 * implements the menu of game
 *
 * contains the initialization method of the game menu scene
 * contains method for controlling method
 *
 * @author Mohammad Hosein Aref
 * @version 1.0
 */
public class GameMenu {
    private Stage stage;
    private User user;

    /**
     * initializes the game menu
     *
     * @param stage the stage of application
     * @param user the logged in user
     */
    public GameMenu(Stage stage, User user) {
        this.stage = stage;
        this.user = user;
    }

    /**
     * initializes the menu scene
     *
     */
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
        stage.setX(500);
        stage.setY(50);
        stage.show();
    }
}
