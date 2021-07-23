package Classes;

import sample.GameControllers.GameController;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * implements the advanced ai of gane
 * a worthy opponent!
 *
 *
 *
 * @author Mohammad Hosein Aref
 * @version 1.0
 *
 */
public class AdvancedAI extends AI {
    private String[][] troop;
    private String[][] airTroop;
    private ArrayList<String> available;

    /**
     * initializes an advanced ai
     *
     * @param elixir the elixir
     * @param gameController the game
     * @param mapStatus map status for red
     * @param map map of game
     * @param troop troop map of game
     * @param airTroop map of air troops
     */
    public AdvancedAI(AtomicInteger elixir, GameController gameController, String[][] mapStatus, String[][] map, String[][] troop, String[][] airTroop) {
        super(elixir, gameController, mapStatus, map);
        this.troop = troop;
        this.airTroop = airTroop;
        available = new ArrayList<>();
    }

    /**
     * ai actions handled
     *
     */
    @Override
    public void run() {
        Random random = new Random();
        while (!(gameController.getGameTime().isEndGame())) {
            try {
                Thread.sleep((random.nextInt(lastElixir()) + leastElixir()) * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Thread.currentThread()) {
                updateAvailable();
                int index = 0;
                int col = 0;
                int row = 0;
                if (isAir()){
                    if (containsDragon() != -1) {
                        index = containsDragon();
                        col = random.nextInt(9) + ((jammedSide(airTroop)) ? 0 : 9);
                        row = findAvailable(col, true);
                        if (row != -1) {
                            insertCard(index, row, col);
                            continue;
                        }
                    }
                    else if (containsAirTarget() != -1){
                        index = containsAirTarget();
                        col = random.nextInt(9) + ((jammedSide(airTroop)) ? 0 : 9);
                        row = findAvailable(col, true);
                        if (row != -1) {
                            insertCard(index, row, col);
                            continue;
                        }
                    }
                }
                if (containsDamageSpell() != -1){
                    index = containsDamageSpell();
                    int randomNum = random.nextInt(10);
                    if (randomNum < 3){
                        insertCard(index,29,9);
                        continue;
                    }
                    else if (randomNum < 6){
                        insertCard(index,25,3);
                        continue;
                    }
                    else {
                        insertCard(index,25,14);
                        continue;
                    }
                }
                if (containsRage() != -1){
                    index = containsRage();
                    col = random.nextInt(9) + ((jammedSide(troop)) ? 0 : 9);
                    row = findAvailable(col, true);
                    if (row != -1){
                        insertCard(index,row,col);
                        continue;
                    }
                }
                if (containsBuilding() != -1){
                    index = containsBuilding();
                    col = random.nextInt(10) + 4;
                    row = findAvailable(col,true);
                    if (row != -1){
                        insertCard(index,row,col);
                    }
                }
                else {
                    index = random.nextInt(4);
                    col = random.nextInt(9) + ((jammedSide(troop)) ? 0 : 9);
                    row = findAvailable(col, true);
                    if (row != -1){
                        insertCard(index,row,col);
                    }
                }
            }
        }
    }

    /**
     * returns the index of card with least elixir
     *
     * @return the index of card with least elixir
     */
    private int leastElixir() {
        int elixir = cards[0].getCost();
        for (int i = 1; i < 4; i++) {
            if (cards[i].getCost() < elixir) {
                elixir = cards[i].getCost();
            }
        }
        return elixir;
    }

    /**
     * returns the index of card with last elixir
     *
     * @return the index of card with last elixir
     */
    private int lastElixir() {
        int elixir = cards[0].getCost();
        for (int i = 1; i < 4; i++) {
            if (cards[i].getCost() > elixir) {
                elixir = cards[i].getCost();
            }
        }
        return elixir;
    }

    /**
     * checks available cells to place card
     *
     */
    private void updateAvailable () {
        available.clear();
        for (int i = 0 ; i < 32 ; i++){
            for (int j = 0; j < 18 ; j++){
                if ((mapStatus[i][j].equals("Free") && (!troop[i][j].contains("Filled")))){
                    available.add("" + i + "," + j);
                }
            }
        }
    }

    /**
     * checks if is a cell available in the passed row or column
     * and returns the first of it
     *
     * @param num the row or column num
     * @param colRow indicates if its row o r column
     * @return the cell
     */
    private int findAvailable (int num,boolean colRow){
        if (colRow) {
            for (String str : available){
                String sub = str.substring(0,str.indexOf(','));
                if (Integer.parseInt(sub) == num){
                    return Integer.parseInt(str.substring(str.indexOf(',') + 1));
                }
            }
        }
        else {
            for (String str : available){
                String sub = str.substring(str.indexOf(',') + 1);
                if (Integer.parseInt(sub) == num){
                    return Integer.parseInt(str.substring(0,str.indexOf(',')));
                }
            }
        }
        return -1;
    }

    /**
     * checks the busy side of map
     *
     * @param state the troop state
     * @return true if left is busy,false if right is
     */
    private boolean jammedSide (String[][] state){
        int left = 0;
        int right = 0;
        for (int i = 0; i < 32 ; i++){
            for (int j = 0 ; j < 9 ; j++){
                if (state[i][j].contains("Filled")){
                    left++;
                }
            }
            for (int j = 9 ; j < 18 ; j++){
                if (state[i][j].contains("Filled")){
                    right++;
                }
            }
        }
        return left > right;
    }

    /**
     * checks if there is a troop in air
     *
     * @return true if it is
     */
    private boolean isAir () {
        for (int i = 0 ; i < 32 ; i++){
            for (int j = 0 ; j < 18 ; j++){
                if (airTroop[i][j].contains("Filled")){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks if current deck contains damage spell
     *
     * @return the index if it is,-1 if not
     */
    private int containsDamageSpell () {
        for (int i = 0 ; i < 4 ; i++){
            if (cards[i] instanceof DamageSpell){
                return i;
            }
        }
        return -1;
    }

    /**
     * checks if current deck contains Rage
     *
     * @return the index if it is,-1 if not
     */
    private int containsRage () {
        for (int i = 0 ; i < 4 ; i++){
            if (cards[i] instanceof Rage){
                return i;
            }
        }
        return -1;
    }
    /**
     * checks if current deck contains Dragon
     *
     * @return the index if it is,-1 if not
     */
    private int containsDragon () {
        for (int i = 0 ; i < 4 ; i++){
            if (cards[i] instanceof BabyDragon){
                return i;
            }
        }
        return -1;
    }

    /**
     * checks if current deck contains building
     *
     * @return the index if it is,-1 if not
     */
    private int containsBuilding () {
        for (int i = 0 ; i < 4 ; i++){
            if (cards[i] instanceof Building){
                return i;
            }
        }
        return -1;
    }

    /**
     * checks if current deck contains AirTarget
     *
     * @return the index if it is,-1 if not
     */
    private int containsAirTarget () {
        for (int i = 0 ; i < 4 ; i++){
            if (cards[i] instanceof FightCard){
                FightCard card = (FightCard) cards[i];
                if (card.getTarget().equals("AG"));
                return i;
            }
        }
        return -1;
    }
}
