import Classes.GameMenu;
import Classes.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Clash Royal Game
 * Main Class
 * sets the start of application
 *
 * Amir Kabir University of technology
 * summer 2021
 *
 * @author Mohammad Hosein Aref
 * @version 1.0
 *
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample/StartPage/StartPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("Clash Royal Login");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void startGame (User user,Stage stage) {
        GameMenu gameMenu = new GameMenu(stage,user);
        gameMenu.run();
    }
}
