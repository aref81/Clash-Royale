package sample.GameMenuControllers;

import Classes.User;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    private User user;
    private GameMenController initcontroller;

    /**
     * sets the user of the menu
     *
     * @param user the logged in user
     */
    public void setUser(User user) {
        this.user = user;
        initcontroller.setUser(user);
    }

    /**
     * initializes the menu page
     *
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(vbox);
        keyDiselect(prof);
        keyDiselect(match);
        keyDiselect(deck);
        try {
            FXMLLoader loader  = new FXMLLoader((getClass().getResource("../GameMenu/BattleMenuPage.fxml")));
            fxml = loader.load();
            initcontroller = loader.getController();
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fade.play();
    }

    @FXML
    private VBox vbox;
    private Parent fxml;

    @FXML
    private Button prof;

    @FXML
    private Button match;

    @FXML
    private Button battle;

    @FXML
    private Button deck;

    /**
     * performs action while show deck key is pressed
     * changes menu page to deck page
     *
     * @param event the "show deck" key pressing
     */
    @FXML
    void showDeck(ActionEvent event) {
        switchPage("../GameMenu/DeckPage.fxml",prof,match,battle,deck);
    }

    /**
     * performs action while show game key is pressed
     * changes menu page to battle menu page page
     *
     * @param event the "show game" key pressing
     */

    @FXML
    void showGame(ActionEvent event) {
        switchPage("../GameMenu/BattleMenuPage.fxml",prof,match,deck,battle);
    }

    /**
     * performs action while show matches key is pressed
     * changes menu page to match page
     *
     * @param event the "show match" key pressing
     */
    @FXML
    void showMatches(ActionEvent event) {
        switchPage("../GameMenu/MatchPage.fxml",prof,deck,battle,match);
    }

    /**
     * performs action while show prof key is pressed
     * changes menu page to prof page
     *
     * @param event the "show prof" key pressing
     */
    @FXML
    void showProfile(ActionEvent event) {
        switchPage("../GameMenu/ProfPage.fxml",deck,match,battle,prof);
    }

    /**
     * diselect other keys while a key is pressed
     *
     *
     * @param key the key intended to be diselected
     */
    private void keyDiselect (Button key) {
        key.setStyle("-fx-background-color: rgba(0,0,0,0.5);");
    }

    /**
     * select key while its selected
     *
     *
     * @param key the key intended to be selected
     */
    private void keySelect (Button key) {
        key.setStyle("-fx-background-color: transparent;");
    }

    /**
     * handles the menu page switching
     * select the key pressed and diselect others
     *
     *
     * @param url the new page fxml file address
     * @param db1 disabled key
     * @param db2 disabled key
     * @param db3 disabled key
     * @param ab1 selected key
     *
     */
    private void switchPage (String url,Button db1,Button db2,Button db3,Button ab1){
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(vbox);
        keyDiselect(db1);
        keyDiselect(db2);
        keyDiselect(db3);
        keySelect(ab1);
        fade.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            fxml = loader.load();
            GameMenController controller = loader.getController();
            controller.setUser(user);
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
