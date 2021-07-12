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

/**
 *
 * controller for start page
 *
 * shows a background and performs a switch between
 * log in and sign in pages
 *
 * @author Mohammad Hosein Aref
 * @version 1.0
 *
 */
public class StartPageController implements Initializable {

    @FXML
    private VBox vbox;
    private Parent fxml;

    /**
     *
     * initializes the application's start page
     * using corrresponding fxml
     *
     * @param url
     * @param resourceBundle
     */
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

    /**
     * calls switchPage method with
     * arguments of sign in page to
     * switch to it
     *
     * @param event the sign in key
     */
    @FXML
    private void openSignIn(ActionEvent event){
        switchPage(48,"Sample/StartPage/SignIn.fxml");
    }

    /**
     * calls switchPage method with
     * arguments of sign Up page to
     * switch to it
     *
     * @param event the Log in key
     */
    @FXML
    private void openSignUp(ActionEvent event){
        switchPage(0,"Sample/StartPage/SignUp.fxml");
    }


    /**
     * switch the vbox on the start menu and
     * sets it witch passed fxml file
     * to switch between sign up and
     * sign in page
     *
     *
     * @param pos the position of vbox
     * @param address the addresss of intended fxml file
     */

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
}
