package sample.GameMenuControllers;

import Classes.User;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * controller class for deck page in main menu
 * contain methods and fields for corresponding fxml
 *
 * @author Mohammad hosein Aref
 * @version 1.0
 */

public class DeckPage implements GameMenController, Initializable {

    private User user;
    private ArrayList<ImageView> deckPics;
    private ArrayList<ImageView> deck;

    /**
     * sets user field
     *
     * @param user  the logged in user
     */
    @Override
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * initializes the scene
     *
     * @param url url
     * @param resourceBundle resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deck = new ArrayList<>();
        deckPics = new ArrayList<>(8);
        deckPics.add(d1);
        deckPics.add(d2);
        deckPics.add(d3);
        deckPics.add(d4);
        deckPics.add(d5);
        deckPics.add(d6);
        deckPics.add(d7);
        deckPics.add(d8);
    }

    /**
     * adds selected card to deck
     *
     *
     * @param image the imageView Field of card
     */
    private void addCard (ImageView image) {
        if (deck.size() < 8) {
            deck.add(image);
            updateDeck();
        }
    }

    /**
     * removes selected card from deck
     *
     * @param image the imageView Field of card
     */
    private void removeCard (ImageView image) {
        deck.remove(image);
        updateDeck();
    }

    /**
     * updates the deck view
     * after adding or removing cards
     *
     */
    private void updateDeck () {
        int i = 0;
        for (ImageView imageView : deck){
            FadeTransition fade = new FadeTransition(Duration.seconds(0.3));
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.setNode(deckPics.get(i));
            deckPics.get(i).setImage(imageView.getImage());
            fade.play();
            i++;
        }
        while (i < 8){
            deckPics.get(i).setImage(null);
            i++;
        }
    }

    @FXML
    private ImageView d1;

    @FXML
    private ImageView d2;

    @FXML
    private ImageView d3;

    @FXML
    private ImageView d4;

    @FXML
    private ImageView d5;

    @FXML
    private ImageView d6;

    @FXML
    private ImageView d7;

    @FXML
    private ImageView d8;

    @FXML
    private ImageView archer;

    @FXML
    private ImageView babyD;

    @FXML
    private ImageView barbarian;

    @FXML
    private ImageView giant;

    @FXML
    private ImageView peka;

    @FXML
    private ImageView val;

    @FXML
    private ImageView wizard;

    @FXML
    private ImageView arrows;

    @FXML
    private ImageView fireBall;

    @FXML
    private ImageView rage;

    @FXML
    private ImageView cannon;

    @FXML
    private ImageView inferno;

    /**
     * performs action on archer key
     * adds if its not added
     * removes if its added
     *
     * @param event the "archer key"
     */
    @FXML
    void archer(ActionEvent event) {
        if (deck.contains(archer)){
            removeCard(archer);
        }
        else {
            addCard(archer);
        }
    }

    /**
     * performs action on arrows key
     * adds if its not added
     * removes if its added
     *
     * @param event the "arrows key"
     */
    @FXML
    void arrows(ActionEvent event) {
        if (deck.contains(arrows)){
            removeCard(arrows);
        }
        else {
            addCard(arrows);
        }
    }

    /**
     * performs action on baby D key
     * adds if its not added
     * removes if its added
     *
     * @param event the "baby D key"
     */
    @FXML
    void babyD(ActionEvent event) {
        if (deck.contains(babyD)){
            removeCard(babyD);
        }
        else {
            addCard(babyD);
        }
    }

    /**
     * performs action on barbarian key
     * adds if its not added
     * removes if its added
     *
     * @param event the "barbarian key"
     */
    @FXML
    void barbarian(ActionEvent event) {
        if (deck.contains(barbarian)){
            removeCard(barbarian);
        }
        else {
            addCard(barbarian);
        }
    }

    /**
     * performs action on cannon key
     * adds if its not added
     * removes if its added
     *
     * @param event the "cannon key"
     */
    @FXML
    void cannon(ActionEvent event) {
        if (deck.contains(cannon)){
            removeCard(cannon);
        }
        else {
            addCard(cannon);
        }
    }

    /**
     * performs action on fireBall key
     * adds if its not added
     * removes if its added
     *
     * @param event the "fireBall key"
     */
    @FXML
    void fireBall(ActionEvent event) {
        if (deck.contains(fireBall)){
            removeCard(fireBall);
        }
        else {
            addCard(fireBall);
        }
    }

    /**
     * performs action on giant key
     * adds if its not added
     * removes if its added
     *
     * @param event the "giant key"
     */
    @FXML
    void giant(ActionEvent event) {
        if (deck.contains(giant)){
            removeCard(giant);
        }
        else {
            addCard(giant);
        }
    }

    /**
     * performs action on inferno key
     * adds if its not added
     * removes if its added
     *
     * @param event the "inferno key"
     */
    @FXML
    void inferno(ActionEvent event) {
        if (deck.contains(inferno)){
            removeCard(inferno);
        }
        else {
            addCard(inferno);
        }
    }

    /**
     * performs action on peka key
     * adds if its not added
     * removes if its added
     *
     * @param event the "peka key"
     */
    @FXML
    void peka(ActionEvent event) {
        if (deck.contains(peka)){
            removeCard(peka);
        }
        else {
            addCard(peka);
        }
    }

    /**
     * performs action on rage key
     * adds if its not added
     * removes if its added
     *
     * @param event the "rage key"
     */
    @FXML
    void rage(ActionEvent event) {
        if (deck.contains(rage)){
            removeCard(rage);
        }
        else {
            addCard(rage);
        }
    }

    /**
     * performs action on val key
     * adds if its not added
     * removes if its added
     *
     * @param event the "val key"
     */
    @FXML
    void val(ActionEvent event) {
        if (deck.contains(val)){
            removeCard(val);
        }
        else {
            addCard(val);
        }
    }

    /**
     * performs action on wizard key
     * adds if its not added
     * removes if its added
     *
     * @param event the "wizard key"
     */
    @FXML
    void wizard(ActionEvent event) {
        if (deck.contains(wizard)){
            removeCard(wizard);
        }
        else {
            addCard(wizard);
        }
    }
}
