package Classes;

import sample.GameControllers.GameController;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * implements the opponent in game
 *
 * @author Mohammad Hosein Aref
 * @version 1.0
 *
 */
public abstract class AI implements Runnable{
    protected AtomicInteger elixir;
    protected GameController gameController;
    protected String[][] mapStatus;
    protected ArrayList<Card> deck;
    protected final Card[] cards = new Card[4];
    protected String[][] map;
    private Card nextCard;

    /**
     * initializes an ai
     *
     * @param elixir the elixir
     * @param gameController the game
     * @param mapStatus map status for red
     * @param map map of game
     */
    public AI(AtomicInteger elixir, GameController gameController, String[][] mapStatus , String[][] map) {
        this.elixir = elixir;
        this.gameController = gameController;
        this.mapStatus = mapStatus;
        this.map = map;
        try {
            deck = generateRandomDeck();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setFirstCards();
    }

    /**
     * generates random deck
     *
     * @return the deck
     * @throws FileNotFoundException prints stack trace
     */
    public static ArrayList<Card> generateRandomDeck () throws FileNotFoundException {
        Random random = new Random();
        int[] indexes = new int[8];
        ArrayList<Card> newDeck = new ArrayList<Card>(8);

        for (int i = 0 ; i < 8 ; i++) {
            boolean loop = true;
            int index = 0;
            while (loop) {
                loop = false;
                index = random.nextInt(12);
                for (int ind : indexes) {
                    if (index == ind){
                        loop = true;
                        break;
                    }
                }
            }
            indexes[i] = index;

            if (index == 0){
                newDeck.add(new Archer());
            }
            else if (index == 1){
                newDeck.add(new Arrow());
            }
            else if (index == 2){
                newDeck.add(new BabyDragon());
            }
            else if (index == 3){
                newDeck.add(new BabyDragon());
            }
            else if (index == 4){
                newDeck.add(new Cannon());
            }
            else if (index == 5){
                newDeck.add(new FireBall());
            }
            else if (index == 6){
                newDeck.add(new Giant());
            }
            else if (index == 7){
                newDeck.add(new InfernoTower());
            }
            else if (index == 8){
                newDeck.add(new MiniPEKKA());
            }
            else if (index == 9){
                newDeck.add(new Rage());
            }
            else if (index == 10){
                newDeck.add(new Valkyrie());
            }
            else if (index == 11){
                newDeck.add(new Wizard());
            }
        }

        return newDeck;
    }

    /**
     * returns random card
     *
     * @param c1 deck card 1
     * @param c2 deck card 1
     * @param c3 deck card 1
     * @param c4 deck card 1
     * @return the new card
     */
    public Card getRandomCard (Card c1, Card c2, Card c3,Card c4) {
        Random random = new Random();
        while (true){
            Card card = deck.get(random.nextInt(8));
            if (!(card == c1 || card == c2 || card == c3 || card == c4)){
                return card;
            }
        }
    }

    /**
     * sets ai's first deck
     *
     */
    private void setFirstCards (){
        for (int i = 0; i < 4;i++) {
            cards[i] = getRandomCard(cards[0], cards[1], cards[2], cards[3]);
        }
        nextCard = getRandomCard(cards[0],cards[1],cards[2],cards[3]);
    }

    /**
     * place a crad in map for ai
     *
     * @param index the index of card in deck
     * @param row intended row
     * @param column intended column
     */
    protected synchronized void insertCard (int index,int row,int column){
        if (elixir.get() >= cards[index].getCost()) {
            if (gameController.insertCard(index, row, column, "Red", mapStatus)){
                ejectCard(index);
            }
        }
    }

    /**
     * eject a card from deck
     *
     * @param index index of the card
     */
    private void ejectCard (int index){
        elixir.set(elixir.get() - cards[index].getCost());
        cards[index] = nextCard;
        nextCard = getRandomCard(cards[0],cards[1],cards[2],cards[3]);
    }
}
