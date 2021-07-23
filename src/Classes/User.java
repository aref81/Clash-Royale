package Classes;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Implements a User in the game
 * contains information for a user
 *
 * @author Mohammad Hosein Aref
 * @version 1.0
 *
 */
public class User implements Serializable {
    private String userName;
    private String password;
    private String points;
    private ArrayList<Card> deck;

    /**
     * initializes a use using passed arguments
     *
     * @param userName user name
     * @param password password
     * @param points thr total points
     */
    public User(String userName, String password , String points , ArrayList<Card> deck) {
        this.userName = userName;
        this.password = password;
        this.points = points;
        this.deck = deck;
    }

    /**
     * returns the username
     *
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * returns password
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * return the total points
     *
     * @return points
     */
    public String getPoints() {
        return points;
    }

    /**
     * changes the password of user
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * changes the points of a user
     *
     * @param points the new points
     */
    public void setPoints(String points) {
        this.points = points;
    }

    /**
     * returnns the deck of user
     *
     * @return the deck
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * changes the deck of user
     *
     * @param deck the new deck
     */
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
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
     * saves a user
     *
     * @param inituser the passed user
     * @throws FileNotFoundException prints stack trace
     */
    public static void print (User inituser) throws FileNotFoundException {
        ArrayList<User> users = new ArrayList<>();
        users.add(inituser);
        File file = new File("./Saves/Users.xml");
        if (file.exists()) {
            try (Scanner in = new Scanner(new FileInputStream(file))) {
                while (in.hasNextLine()) {
                    String userName = in.nextLine();
                    String passWord = in.nextLine();
                    String points = in.nextLine();
                    ArrayList<String> deckStr = new ArrayList<>(8);
                    for (int i = 0 ; i < 8;i++){
                        deckStr.add(in.nextLine());
                    }
                    User user = new User(userName,passWord,points,genDeck(deckStr));
                    if (!(user.getUserName().equals(inituser.userName))){
                        users.add(user);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (User user : users){
                printUser(user,file);
            }
        }
    }

    /**
     * prints a single user in file
     *
     * @param user the user
     * @param file the file
     */
    private static void printUser(User user , File file){
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file,true))){
            out.println(user.getUserName());
            out.println(user.getPassword());
            out.println(user.getPoints());
            ArrayList<String> strs = strGen(user.getDeck());
            for (int i = 0 ; i < 8 ; i++){
                if (i < user.getDeck().size()){
                    out.println(strs.get(i));
                }
                else {
                    out.println("null");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * generates deck from strings in file
     *
     * @param cards the strings
     * @return the deck
     * @throws FileNotFoundException prints stack trace
     */
    public static ArrayList<Card> genDeck (ArrayList<String> cards) throws FileNotFoundException {
        ArrayList<Card> deck = new ArrayList<>();
        for (String card : cards){
            if (card.equals("Archer")){
                deck.add(new Archer());
            }
            else if (card.equals("Arrow")){
                deck.add(new Arrow());
            }
            else if (card.equals("BabyDragon")){
                deck.add(new BabyDragon());
            }
            else if (card.equals("Barbarian")){
                deck.add(new Barbarian());
            }
            else if (card.equals("Cannon")){
                deck.add(new Cannon());
            }
            else if (card.equals("FireBall")){
                deck.add(new FireBall());
            }
            else if (card.equals("Giant")){
                deck.add(new Giant());
            }
            else if (card.equals("InfernoTower")){
                deck.add(new InfernoTower());
            }
            else if (card.equals("MiniPekka")){
                deck.add(new MiniPEKKA());
            }
            else if (card.equals("Rage")){
                deck.add(new Rage());
            }
            else if (card.equals("Valkyrie")){
                deck.add(new Valkyrie());
            }
            else if (card.equals("Wizard")){
                deck.add(new Wizard());
            }
        }
        return deck;
    }

    /**
     * generates strings from deck
     *
     * @param cards the strings
     * @return the deck
     * @throws FileNotFoundException prints stack trace
     */
    public static ArrayList<String> strGen (ArrayList<Card> cards){
        ArrayList<String> strs = new ArrayList<String>();
        for (Card card : cards){
            if (card instanceof Archer){
                strs.add("Archer");
            }
            else if (card instanceof Arrow){
                strs.add("Arrow");
            }
            else if (card instanceof BabyDragon){
                strs.add("BabyDragon");
            }
            else if (card instanceof Barbarian){
                strs.add("Barbarian");
            }
            else if (card instanceof Cannon){
                strs.add("Cannon");
            }
            else if (card instanceof FireBall){
                strs.add("FireBall");
            }
            else if (card instanceof Giant){
                strs.add("Giant");
            }
            else if (card instanceof InfernoTower){
                strs.add("InfernoTower");
            }
            else if (card instanceof MiniPEKKA){
                strs.add("MiniPekka");
            }
            else if (card instanceof Rage){
                strs.add("Rage");
            }
            else if (card instanceof Valkyrie){
                strs.add("Valkyrie");
            }
            else if (card instanceof Wizard){
                strs.add("Wizard");
            }
        }
        return strs;
    }
}
