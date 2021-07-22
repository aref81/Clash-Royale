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
        else if (user.getDeck().size() < 8){
            addCard(archer);
            Archer archer = new Archer();
            upgrade(archer);
            user.getDeck().add(archer);
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
        else if (user.getDeck().size() < 8) {
            addCard(arrows);
            Arrow arrow = new Arrow();
            upgrade(arrow);
            user.getDeck().add(arrow);
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
        else if (user.getDeck().size() < 8) {
            addCard(babyD);
            BabyDragon babyDragon = new BabyDragon();
            upgrade(babyDragon);
            user.getDeck().add(babyDragon);
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
        else if (user.getDeck().size() < 8) {
            addCard(barbarian);
            Barbarian barbarian = new Barbarian();
            upgrade(barbarian);
            user.getDeck().add(barbarian);
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
        else if (user.getDeck().size() < 8) {
            addCard(cannon);
            Cannon cannon = new Cannon();
            upgrade(cannon);
            user.getDeck().add(cannon);
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
        else if (user.getDeck().size() < 8) {
            addCard(fireBall);
            FireBall fireBall = new FireBall();
            upgrade(fireBall);
            user.getDeck().add(fireBall);
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
        else if (user.getDeck().size() < 8) {
            addCard(giant);
            Giant giant= new Giant();
            upgrade(giant);
            user.getDeck().add(giant);
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
        else if (user.getDeck().size() < 8) {
            addCard(inferno);
            InfernoTower infernoTower = new InfernoTower();
            upgrade(infernoTower);
            user.getDeck().add(infernoTower);
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
        else if (user.getDeck().size() < 8) {
            addCard(peka);
            MiniPEKKA miniPEKKA = new MiniPEKKA();
            upgrade(miniPEKKA);
            user.getDeck().add(miniPEKKA);
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
        else if (user.getDeck().size() < 8) {
            addCard(rage);
            Rage rage = new Rage();
            upgrade(rage);
            user.getDeck().add(rage);
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
        else if (user.getDeck().size() < 8) {
            addCard(val);
            Valkyrie valkyrie = new Valkyrie();
            upgrade(valkyrie);
            user.getDeck().add(valkyrie);
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
        else if (user.getDeck().size() < 8) {
            addCard(wizard);
            Wizard wizard = new Wizard();
            upgrade(wizard);
            user.getDeck().add(wizard);
        }
    }

    private void upgrade (Card card){
        int level = 0;
        switch (Integer.parseInt(user.getPoints())){
            case 300: {
                level = 1;
                break;
            }
            case 500: {
                level = 2;
                break;
            }
            case 900: {
                level = 3;
                break;
            }
            case 1700: {
                level = 4;
                break;
            }
            case 2500: {
                level = 5;
                break;
            }
        }

        for (int i = 0 ; i < level ; i++) {
            card.Upgrade();
        }
    }
}
