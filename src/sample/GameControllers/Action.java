package sample.GameControllers;

import Classes.FightCard;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class Action implements Runnable{
    private String side;
    private String opponent;
    private int hp;
    private FightCard card;
    private ImageView[][] mapView;
    private String [][] map;
    private String [][] mapStatus;
    private String [][] status;
    private Action [][] mapContent;
    private int row;
    private int column;
    private GameTime gameTime;


    public Action(FightCard card, ImageView[][] mapView, String[][] map, String[][] mapStatus, Action[][] mapContent, int row, int column,GameTime gameTime) {
        this.card = card;
        hp = card.getHP();
        this.mapView = mapView;
        this.map = map;
        this.mapStatus = mapStatus;
        this.mapContent = mapContent;
        this.row = row;
        this.column = column;
        status = new String[32][18];
        this.gameTime = gameTime;
        updateStatus();
    }

    @Override
    public void run() {
        while ((hp > 0) && !gameTime.isEndGame()) {
            card.action(this);
        }
    }

    private void updateStatus (){
        for (int i = 0; i < 32; i++){
            for (int j = 0 ; j < 18 ; j++){
                if (map[i][j].contains("Red")){
                    status[i][j] = "Target";
                }
                else {
                    status[i][j] = "Empty";
                }
                if (card.getTarget().equals("G") || card.getTarget().equals("AG")){
                    if (mapStatus[i][j].equals("RedFilled")){
                        status[i][j] = "Target";
                    }
                }
            }
        }
    }

    public Action inRange (int range) {
        for (int i = 0 ; i < range && (row + i < 32) && (row - i >= 0) && (column + i < 18) && (column - i >= 0) ; i++){
            if (status[row + i][column].equals("Target")){
                return mapContent[row + i][column];
            }
            else if (status[row - i][column].equals("Target")){
                return mapContent[row - i][column];
            }
            else if (status[row][column + i].equals("Target")){
                return mapContent[row][column + i];
            }
            else if (status[row][column - i].equals("Target")){
                return mapContent[row][column - i];
            }
        }
        return null;
    }

    public void getHit(int hit){
        hp -= hit;
    }

    public void move () {
        if (!(map[row][column].equals("Way"))){
            int left = 0;
            int right = 0;
            for (int i = 0; !(map[row][column + i].equals("Way")) && column + i < 18 ; i++){
                right = i;
            }
            for (int i = 0; !(map[row][column - i].equals("Way")) && column - i >= 0 ; i++){
                left = i;
            }
            mapStatus[row][column] = "Free";
            mapContent[row][column] = null;
            mapView[row][column].setImage(null);
            if (left > right){
                mapStatus[row][column + 1] = "BlueFilled";
                mapContent[row][column + 1] = this;
                mapView[row][column + 1].setImage(card.getGamePic());
            }
            else {
                mapStatus[row][column - 1] = "BlueFilled";
                mapContent[row][column - 1] = this;
                mapView[row][column - 1].setImage(card.getGamePic());
            }
        }
        else {
            mapStatus[row][column] = "Free";
            mapContent[row][column] = null;
            mapView[row][column].setImage(null);

            mapStatus[row + 1][column] = "BlueFilled";
            mapContent[row + 1][column] = this;
            mapView[row + 1][column].setImage(card.getGamePic());
        }
    }


    public ImageView[][] getMapView() {
        return mapView;
    }

    public String[][] getMap() {
        return map;
    }

    public String[][] getMapStatus() {
        return mapStatus;
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

}
