package sample.GameControllers;

import Classes.*;
import javafx.scene.image.ImageView;

public class Action implements Runnable{
    private String side;
    private String opponent;
    private int hp;
    private FightCard card;
    private ImageView[][] mapView;
    protected String [][] map;
    private String [][] troop;
    private String [][] status;
    private Action [][] mapContent;
    private String [][] airTroop;
    private Action [][] airContent;
    private String [][] spellState;
    private int row;
    private int column;
    private GameTime gameTime;


    public Action(FightCard card, ImageView[][] mapView, String[][] map, String[][] troop, Action[][] mapContent, int row, int column, GameTime gameTime, String side, int hp, String[][] airTroop, Action[][] airContent,String[][] spellState) {
        this.card = card;
        if (card == null){
            this.hp = hp;
        }
        else {
            this.hp = card.getHP();
        }
        this.mapView = mapView;
        this.map = map;
        this.troop = troop;
        this.mapContent = mapContent;
        this.row = row;
        this.column = column;
        status = new String[32][18];
        this.gameTime = gameTime;
        this.side = side;
        this.airTroop = airTroop;
        this.airContent = airContent;
        this.spellState = spellState;
        if (side.equals("Red")){
            opponent = "Blue";
        }
        else {
            opponent = "Red";
        }
        if (!(card == null)){
            updateStatus();
        }

    }

    @Override
    public void run() {
        if (card instanceof Building){
            Building building = (Building) card;
            int deadLine = gameTime.getTime() - building.getLifeTime();

            while ((hp > 0) && !gameTime.isEndGame() && gameTime.getTime() > deadLine) {
                card.action(this,isRage());
                updateStatus();
            }
        }
        else {
            while ((hp > 0) && !gameTime.isEndGame()) {
                card.action(this,isRage());
                updateStatus();
            }
        }
        die();
    }

    private boolean isRage () {
        return spellState[row][column].contains(side);
    }

    private void die () {
        troop[row][column] = "Empty";
        mapContent[row][column] = null;
        mapView[row][column].setImage(null);
    }

    public void updateStatus (){
        for (int i = 0; i < 32; i++){
            for (int j = 0 ; j < 18 ; j++){
                if (map[i][j].contains(opponent)){
                    status[i][j] = "Target";
                }
                else {
                    status[i][j] = "Empty";
                }
                if (card.getTarget().equals("G") || card.getTarget().equals("AG")){
                    if (troop[i][j].equals(opponent + "Filled")){
                        status[i][j] = "Target";
                    }
                }
                if (card.getTarget().equals("AG")){
                    if (airTroop[i][j].equals(opponent + "Filled")){
                        status[i][j] = "Target";
                    }
                }
            }
        }
    }

    public Action inRange (int range) {
        for (int i = 1 ; i <= range && (row + i < 32) ; i++) {
            if (status[row + i][column].equals("Target")) {
                if (mapContent[row + i][column] != null) {
                    return mapContent[row + i][column];
                }
                else if (airContent[row + i][column] != null){
                    return airContent[row + i][column];
                }
            }
        }
        for (int i = 1 ; i <= range && (row - i >= 0) ; i++){
            if (status[row - i][column].equals("Target")) {
                if (mapContent[row - i][column] != null) {
                    return mapContent[row - i][column];
                }
                else if (airContent[row - i][column] != null){
                    return airContent[row - i][column];
                }
            }
        }
        for (int i = 1 ; i <= range && (column + i < 18) ; i++) {
            if (status[row][column + i].equals("Target")) {
                if (mapContent[row][column + i] != null) {
                    return mapContent[row][column + i];
                }
                else if (airContent[row][column + i] != null){
                    return airContent[row][column + i];
                }
            }
        }
        for (int i = 1 ; i <= range && (column - i >= 0) ; i++) {
            if (status[row][column - i].equals("Target")) {
                if (mapContent[row][column - i] != null) {
                    return mapContent[row][column - i];
                }
                else if (airContent[row][column - i] != null){
                    return airContent[row][column - i];
                }
            }
        }
        return null;
    }

    public void areaSplash (String[][] status1 , int hit) {
        if ((row + 1 < 32) && status1[row + 1][column].equals("Target")){
            mapContent[row + 1][column].getHit(hit);
        }
        if ((column + 1 < 18) && status1[row][column + 1].equals("Target")){
            mapContent[row][column + 1].getHit(hit);
        }
        if ((row - 1 >= 0) && status1[row - 1][column].equals("Target")){
            mapContent[row - 1][column].getHit(hit);
        }
        if ((column - 1 >= 0) && status1[row][column - 1].equals("Target")){
            mapContent[row][column - 1].getHit(hit);
        }
        if ((row + 1 < 32) && (column + 1 < 18) && status1[row + 1][column + 1].equals("Target")){
            mapContent[row + 1][column + 1].getHit(hit);
        }
        if ((row - 1 >= 0) && (column + 1 < 18) && status1[row - 1][column + 1].equals("Target")){
            mapContent[row - 1][column + 1].getHit(hit);
        }
        if ((row + 1 < 32) && (column - 1 >= 0) && status1[row + 1][column - 1].equals("Target")){
            mapContent[row + 1][column - 1].getHit(hit);
        }
        if ((row - 1 >= 0) && (column - 1 >= 0) && status1[row - 1][column - 1].equals("Target")){
            mapContent[row - 1][column - 1].getHit(hit);
        }
    }

    public void getHit(int hit){
        hp -= hit;
    }

    public synchronized void move (String[][] troop,Action[][] mapContent) {
        if (!(map[row][column].equals("Way"))){
            if (row <= 29 && row >= 2) {
                int left = 0;
                int right = 0;
                for (int i = 0; column + i < 18 && !(map[row][column + i].equals("Way")); i++) {
                    right = i;
                }
                for (int i = 0; column - i >= 0 && !(map[row][column - i].equals("Way")); i++) {
                    left = i;
                }
                troop[row][column] = "Empty";
                mapContent[row][column] = null;
                mapView[row][column].setImage(null);
                if (left > right) {
                    column++;
                    if (troop[row][column].contains("Filled")) {
                        if (side.equals("Blue")){
                            if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            }
                            else if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            }
                            else {
                                column--;
                            }
                        }
                        else {
                            if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            } else {
                                column--;
                            }
                        }
                    }
                    if (side.equals("Blue")) {
                        troop[row][column] = "BlueFilled";
                    } else {
                        troop[row][column] = "RedFilled";
                    }
                    mapContent[row][column] = this;
                    mapView[row][column].setImage(card.getGamePic());
                } else  if (right > left){
                    column--;
                    if (troop[row][column].contains("Filled")) {
                        if (side.equals("Blue")){
                            if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            }
                            else if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            }
                            else {
                                column++;
                            }
                        }
                        else {
                            if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            } else {
                                column++;
                            }
                        }
                    }
                    if (side.equals("Blue")) {
                        troop[row][column] = "BlueFilled";
                    } else {
                        troop[row][column] = "RedFilled";
                    }
                    mapContent[row][column] = this;
                    mapView[row][column].setImage(card.getGamePic());
                }
                else {
                    if (column < 3){
                        column++;
                        if (troop[row][column].contains("Filled")) {
                            if (side.equals("Blue")){
                                if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                    row--;
                                }
                                else if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                    row++;
                                }
                                else {
                                    column--;
                                }
                            }
                            else {
                                if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                    row++;
                                } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                    row--;
                                } else {
                                    column--;
                                }
                            }
                        }
                        if (side.equals("Blue")) {
                            troop[row][column] = "BlueFilled";
                        } else {
                            troop[row][column] = "RedFilled";
                        }
                        mapContent[row][column] = this;
                        mapView[row][column].setImage(card.getGamePic());
                    }
                    else {
                        column--;
                        if (troop[row][column].contains("Filled")) {
                            if (side.equals("Blue")){
                                if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                    row--;
                                }
                                else if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                    row++;
                                }
                                else {
                                    column++;
                                }
                            }
                            else {
                                if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                    row++;
                                } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                    row--;
                                } else {
                                    column++;
                                }
                            }
                        }
                        if (side.equals("Blue")) {
                            troop[row][column] = "BlueFilled";
                        } else {
                            troop[row][column] = "RedFilled";
                        }
                        mapContent[row][column] = this;
                        mapView[row][column].setImage(card.getGamePic());
                    }
                }
            }
            else {
                if (row > 29){
                    troop[row][column] = "Empty";
                    mapContent[row][column] = null;
                    mapView[row][column].setImage(null);

                    row--;

                    if (troop[row][column].contains("Filled")) {
                        if (column + 1 < 18 && !(troop[row][column + 1].contains("Filled"))) {
                            column++;
                        } else if (column - 1 >= 0 && !(troop[row][column - 1].contains("Filled"))) {
                            column--;
                        } else {
                            row++;
                        }
                    }

                    if (side.equals("Blue")) {
                        troop[row][column] = "BlueFilled";
                    } else {
                        troop[row][column] = "RedFilled";
                    }
                    mapContent[row][column] = this;
                    mapView[row][column].setImage(card.getGamePic());
                }
                else if (row < 2){
                    troop[row][column] = "Empty";
                    mapContent[row][column] = null;
                    mapView[row][column].setImage(null);

                    row++;

                    if (troop[row][column].contains("Filled")) {
                        if (column + 1 < 18 && !(troop[row][column + 1].contains("Filled"))) {
                            column++;
                        } else if (column - 1 >= 0 && !(troop[row][column - 1].contains("Filled"))) {
                            column--;
                        } else {
                            row--;
                        }
                    }

                    if (side.equals("Blue")) {
                        troop[row][column] = "BlueFilled";
                    } else {
                        troop[row][column] = "RedFilled";
                    }
                    mapContent[row][column] = this;
                    mapView[row][column].setImage(card.getGamePic());
                }
            }
        }
        else {
            if (side.equals("Blue")) {
                troop[row][column] = "Empty";
                mapContent[row][column] = null;
                mapView[row][column].setImage(null);

                row--;
                if (column < 9){
                    if (map[row + 1][column + 1].equals("Way")){
                        row++;
                        column++;
                        if (troop[row][column].contains("Filled")) {
                            if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            } else {
                                column--;
                            }
                        }
                    }
                    else if (map[row + 1][column - 1].equals("Way")){
                        row++;
                        column--;
                        if (troop[row][column].contains("Filled")) {
                            if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            } else {
                                column++;
                            }
                        }
                    }
                    else {
                        if (troop[row][column].contains("Filled")) {
                            if (column + 1 < 18 && !(troop[row][column + 1].contains("Filled"))) {
                                column++;
                            } else if (column - 1 >= 0 && !(troop[row][column - 1].contains("Filled"))) {
                                column--;
                            } else {
                                row++;
                            }
                        }
                    }
                }
                else {
                    if (map[row + 1][column - 1].equals("Way")) {
                        row++;
                        column--;
                        if (troop[row][column].contains("Filled")) {
                            if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            } else {
                                column++;
                            }
                        }
                    } else if (map[row + 1][column + 1].equals("Way")) {
                        row++;
                        column++;
                        if (troop[row][column].contains("Filled")) {
                            if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            } else {
                                column--;
                            }
                        }
                    } else {
                        if (troop[row][column].contains("Filled")) {
                            if (column + 1 < 18 && !(troop[row][column + 1].contains("Filled"))) {
                                column++;
                            } else if (column - 1 >= 0 && !(troop[row][column - 1].contains("Filled"))) {
                                column--;
                            } else {
                                row++;
                            }
                        }
                    }
                }

                troop[row][column] = "BlueFilled";
                mapContent[row][column] = this;
                mapView[row][column].setImage(card.getGamePic());
            }
            else {
                troop[row][column] = "Empty";
                mapContent[row][column] = null;
                mapView[row][column].setImage(null);

                row++;

                if (column < 9){
                    if (map[row + 1][column + 1].equals("Way")){
                        row--;
                        column++;
                        if (troop[row][column].contains("Filled")) {
                            if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            } else {
                                column--;
                            }
                        }
                    }
                    else if (map[row + 1][column - 1].equals("Way")){
                        row--;
                        column--;
                        if (troop[row][column].contains("Filled")) {
                            if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            } else {
                                column++;
                            }
                        }
                    }
                    else {
                        if (troop[row][column].contains("Filled")) {
                            if (column + 1 < 18 && !(troop[row][column + 1].contains("Filled"))) {
                                column++;
                            } else if (column - 1 >= 0 && !(troop[row][column - 1].contains("Filled"))) {
                                column--;
                            } else {
                                row--;
                            }
                        }
                    }
                }
                else {
                    if (map[row + 1][column - 1].equals("Way")) {
                        row--;
                        column--;
                        if (troop[row][column].contains("Filled")) {
                            if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            } else {
                                column++;
                            }
                        }
                    } else if (map[row + 1][column + 1].equals("Way")) {
                        row--;
                        column++;
                        if (troop[row][column].contains("Filled")) {
                            if (row + 1 < 32 && !(troop[row + 1][column].contains("Filled"))) {
                                row++;
                            } else if (row - 1 >= 0 && !(troop[row - 1][column].contains("Filled"))) {
                                row--;
                            } else {
                                column--;
                            }
                        }
                    } else {
                        if (troop[row][column].contains("Filled")) {
                            if (column + 1 < 18 && !(troop[row][column + 1].contains("Filled"))) {
                                column++;
                            } else if (column - 1 >= 0 && !(troop[row][column - 1].contains("Filled"))) {
                                column--;
                            } else {
                                row--;
                            }
                        }
                    }
                }

                troop[row][column] = "RedFilled";
                mapContent[row][column] = this;
                mapView[row][column].setImage(card.getGamePic());
            }
        }
    }


    public ImageView[][] getMapView() {
        return mapView;
    }

    public String[][] getTroop() {
        return troop;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String[][] getStatus() {
        return status;
    }

    public Action[][] getMapContent() {
        return mapContent;
    }

    public GameTime getGameTime() {
        return gameTime;
    }

    public int getHp() {
        return hp;
    }

    public String getSide() {
        return side;
    }

    public String getOpponent() {
        return opponent;
    }

    public Action[][] getAirContent() {
        return airContent;
    }

    public String[][] getAirTroop() {
        return airTroop;
    }

    public String[][] getSpellState() {
        return spellState;
    }

}
