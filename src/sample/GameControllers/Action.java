package sample.GameControllers;

import Classes.FightCard;
import javafx.scene.image.ImageView;

public class Action implements Runnable{
    private int hp;
    private FightCard card;
    private ImageView[][] mapView;
    private String [][] map;
    private String [][] mapStatus;
    private String [][] status;
    private final Action [][] mapContent;
    private int row;
    private int column;


    public Action(FightCard card, ImageView[][] mapView, String[][] map, String[][] mapStatus, Action[][] mapContent, int row, int column) {
        this.card = card;
        hp = card.getHP();
        this.mapView = mapView;
        this.map = map;
        this.mapStatus = mapStatus;
        this.mapContent = mapContent;
        this.row = row;
        this.column = column;
        status = new String[32][18];
        updateStatus();
    }

    @Override
    public void run() {
        card.action(this);
    }

    private void updateStatus (){
        for (int i = 0; i < 32; i++){
            for (int j = 0 ; j < 18 ; j++){
                if (card.(map[i][j].contains("Red") || mapStatus[i][j].equals("RedFilled"))){
                    status[i][j] = "Target";
                }
            }
        }
    }

    public Action inRange () {

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
