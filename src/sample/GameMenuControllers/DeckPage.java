package sample.GameMenuControllers;

import Classes.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileNotFoundException;
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
     * and reloads the deck of user
     *
     * @param user  the logged in user
     */
    @Override
    public void setUser(User user) {
        this.user = user;
        deckGenerator();
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
     *
     * reloads the deck of user
     */
    private void deckGenerator () {
        for (Card card : user.getDeck()){
            if (card instanceof Archer){
                addCard(archer);
            }
            else if (card instanceof Arrow){
                addCard(arrows);
            }
            else if (card instanceof BabyDragon){
                addCard(babyD);
            }
            else if (card instanceof Barbarian){
                addCard(barbarian);
            }
            else if (card instanceof Cannon){
                addCard(cannon);
            }
            else if (card instanceof FireBall){
                addCard(fireBall);
            }
            else if (card instanceof Giant){
                addCard(giant);
            }
            else if (card instanceof InfernoTower){
                addCard(inferno);
            }
            else if (card instanceof MiniPEKKA){
                addCard(peka);
            }
            else if (card instanceof Rage){
                addCard(rage);
            }
            else if (card instanceof Valkyrie){
                addCard(val);
            }
            else if (card instanceof Wizard){
                addCard(wizard);
            }
        }
        updateDeck();
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
    void archer(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(archer)){
            user.getDeck().remove(deck.indexOf(archer));
            removeCard(archer);
        }
        else {
            addCard(archer);
            user.getDeck().add(new Archer());
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
    void arrows(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(arrows)){
            user.getDeck().remove(deck.indexOf(arrows));
            removeCard(arrows);
        }
        else {
            addCard(arrows);
            user.getDeck().add(new Arrow());
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
    void babyD(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(babyD)){
            user.getDeck().remove(deck.indexOf(babyD));
            removeCard(babyD);
        }
        else {
            addCard(babyD);
            user.getDeck().add(new BabyDragon());
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
    void barbarian(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(barbarian)){
            user.getDeck().remove(deck.indexOf(barbarian));
            removeCard(barbarian);
        }
        else {
            addCard(barbarian);
            user.getDeck().add(new Barbarian());
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
    void cannon(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(cannon)){
            user.getDeck().remove(deck.indexOf(cannon));
            removeCard(cannon);
        }
        else {
            addCard(cannon);
            user.getDeck().add(new Cannon());
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
    void fireBall(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(fireBall)){
            user.getDeck().remove(deck.indexOf(fireBall));
            removeCard(fireBall);
        }
        else {
            addCard(fireBall);
            user.getDeck().add(new FireBall());
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
    void giant(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(giant)){
            user.getDeck().remove(deck.indexOf(giant));
            removeCard(giant);
        }
        else {
            addCard(giant);
            user.getDeck().add(new Giant());
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
    void inferno(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(inferno)){
            user.getDeck().remove(deck.indexOf(inferno));
            removeCard(inferno);
        }
        else {
            addCard(inferno);
            user.getDeck().add(new InfernoTower());
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
    void peka(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(peka)){
            user.getDeck().remove(deck.indexOf(peka));
            removeCard(peka);
        }
        else {
            addCard(peka);
            user.getDeck().add(new MiniPEKKA());
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
    void rage(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(rage)){
            user.getDeck().remove(deck.indexOf(rage));
            removeCard(rage);
        }
        else {
            addCard(rage);
            user.getDeck().add(new Rage());
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
    void val(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(val)){
            user.getDeck().remove(deck.indexOf(val));
            removeCard(val);
        }
        else {
            addCard(val);
            user.getDeck().add(new Valkyrie());
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
    void wizard(ActionEvent event) throws FileNotFoundException {
        if (deck.contains(wizard)){
            user.getDeck().remove(deck.indexOf(wizard));
            removeCard(wizard);
        }
        else {
            addCard(wizard);
            user.getDeck().add(new Wizard());
        }
    }
}
