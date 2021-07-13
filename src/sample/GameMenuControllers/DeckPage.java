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


public class DeckPage implements GameMenController, Initializable {

    private User user;
    private ArrayList<ImageView> deckPics;
    private ArrayList<ImageView> deck;

    @Override
    public void setUser(User user) {
        this.user = user;
    }

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

    private void addCard (ImageView image) {
        if (deck.size() < 8) {
            deck.add(image);
            updateDeck();
        }
    }

    private void removeCard (ImageView image) {
        deck.remove(image);
        updateDeck();
    }

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

    @FXML
    void archer(ActionEvent event) {
        if (deck.contains(archer)){
            removeCard(archer);
        }
        else {
            addCard(archer);
        }
    }

    @FXML
    void arrows(ActionEvent event) {
        if (deck.contains(arrows)){
            removeCard(arrows);
        }
        else {
            addCard(arrows);
        }
    }

    @FXML
    void babyD(ActionEvent event) {
        if (deck.contains(babyD)){
            removeCard(babyD);
        }
        else {
            addCard(babyD);
        }
    }

    @FXML
    void barbarian(ActionEvent event) {
        if (deck.contains(barbarian)){
            removeCard(barbarian);
        }
        else {
            addCard(barbarian);
        }
    }

    @FXML
    void cannon(ActionEvent event) {
        if (deck.contains(cannon)){
            removeCard(cannon);
        }
        else {
            addCard(cannon);
        }
    }

    @FXML
    void fireBall(ActionEvent event) {
        if (deck.contains(fireBall)){
            removeCard(fireBall);
        }
        else {
            addCard(fireBall);
        }
    }

    @FXML
    void giant(ActionEvent event) {
        if (deck.contains(giant)){
            removeCard(giant);
        }
        else {
            addCard(giant);
        }
    }

    @FXML
    void inferno(ActionEvent event) {
        if (deck.contains(inferno)){
            removeCard(inferno);
        }
        else {
            addCard(inferno);
        }
    }

    @FXML
    void peka(ActionEvent event) {
        if (deck.contains(peka)){
            removeCard(peka);
        }
        else {
            addCard(peka);
        }
    }

    @FXML
    void rage(ActionEvent event) {
        if (deck.contains(rage)){
            removeCard(rage);
        }
        else {
            addCard(rage);
        }
    }

    @FXML
    void val(ActionEvent event) {
        if (deck.contains(val)){
            removeCard(val);
        }
        else {
            addCard(val);
        }
    }

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
