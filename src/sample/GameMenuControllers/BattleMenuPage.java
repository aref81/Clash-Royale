package sample.GameMenuControllers;

import Classes.GameMenu;
import Classes.User;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javax.swing.*;
import java.io.IOException;

/**
 * controller class for battle menu page in main menu
 * contain methods and fields for corresponding fxml
 *
 * @author Mohammad hosein Aref
 * @version 1.0
 */
public class BattleMenuPage implements GameMenController {

    private User user;
    private VBox vbox;

    /**
     * set users field
     *
     * @param user  the logged in user
     */
    @Override
    public void setUser(User user) {
        this.user = user;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    /**
     * performs action while double war key is pressed
     * starts double war
     *
     * @param event the "Double War" Key pressing
     */
    @FXML
    void doubleWar(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"Enter Double War!");
    }

    /**
     * performs action while solo war key is pressed
     * starts solo war
     *
     * @param event the "Solo War" Key pressing
     */
    @FXML
    void soloWar(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"Enter Solo War!");
    }

    /**
     * performs action while  training camp key is pressed
     * starts training camp
     *
     * @param event the "Training Camp" Key pressing
     */
    @FXML
    void trainingCamp(ActionEvent event) {
        if (user.getDeck() != null && user.getDeck().size() == 8) {
            FadeTransition fade = new FadeTransition(Duration.seconds(0.5));
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.setNode(vbox);
            fade.play();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GameMenu/TraningMatchPage.fxml"));
                Parent fxml = loader.load();
                GameMenController controller = loader.getController();
                controller.setUser(user);
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Please set your deck first");
        }

    }
}
