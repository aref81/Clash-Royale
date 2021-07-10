import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        switchPage(48,"Sample/StartPage/SignIn.fxml");
    }

    @FXML
    private void openSignIn(ActionEvent event){
        switchPage(48,"Sample/StartPage/SignIn.fxml");
    }

    private void switchPage(int pos,String address) {
        TranslateTransition t= new TranslateTransition(Duration.seconds(0.5),vbox);
        t.setToX(vbox.getLayoutX() * pos);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource(address));
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
        switchPage(0,"Sample/StartPage/SignUp.fxml");
    }
}
