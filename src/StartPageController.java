import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    @FXML
    private VBox vbox;
    private Parent fxml;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("Sample/StartPage/SignIn.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        }
        catch (IOException er){
            er.printStackTrace();
        }
        TranslateTransition t= new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX() * 50);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("Sample/StartPage/SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }
            catch (IOException er){
                er.printStackTrace();
            }
        });
    }

    @FXML
    private void openSignIn(ActionEvent event){
        TranslateTransition t= new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX() * 50);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("Sample/StartPage/SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }
            catch (IOException er){
                er.printStackTrace();
            }
        });
    }

    @FXML
    private void openSignUp(ActionEvent event){
        TranslateTransition t= new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("Sample/StartPage/SignUp.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }
            catch (IOException er){
                er.printStackTrace();
            }
        });
    }
}
