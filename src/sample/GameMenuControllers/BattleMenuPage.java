package sample.GameMenuControllers;

import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javax.swing.*;

/**
 * controller class for battle menu page in main menu
 * contain methods and fields for corresponding fxml
 *
 * @author Mohammad hosein Aref
 * @version 1.0
 */
public class BattleMenuPage implements GameMenController{

    private User user;

    /**
     * set users field
     *
     * @param user  the logged in user
     */
    @Override
    public void setUser(User user) {
        this.user = user;
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
        JOptionPane.showMessageDialog(null,"Enter Training Camp!");
    }
}
