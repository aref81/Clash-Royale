package sample.GameMenuControllers;

import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.swing.*;

public class BattleMenuPage implements GameMenController{

    private User user;

    @Override
    public void setUser(User user) {
        this.user = user;
    }


    @FXML
    void doubleWar(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"Enter Solo War!");
    }

    @FXML
    void soloWar(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"Enter Double War!");
    }

    @FXML
    void trainingCamp(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"Enter Training Camp!");
    }
}
