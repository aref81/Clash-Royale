package Classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.GameControllers.Action;
import sample.GameControllers.GameTime;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type King tower.
 */
public class KingTower extends Tower {
    private boolean isActive;
    private int rowEnd;
    private int columnEnd;
    /**
     * Instantiates a new King tower.
     */
    public KingTower(FightCard card, ImageView[][] mapView, String[][] map, String[][] troop, Action[][] mapContent, int row, int column, GameTime gameTime , String side , ImageView imageView,String[][] mapStatus, String[][] airTroop , Action[][] airFieldContent,String[][] spellState){
        super(card, mapView, map, troop, mapContent, row, column, gameTime,side , 2400 , imageView,mapStatus,airTroop,airFieldContent,spellState);
        isActive = false;
        Range = 7;
        HitSpeed = 1;
        HP = 2400;
        Damage = 50;
        Level = 1;
        rowEnd = row + 4;
        columnEnd = column + 3;
    }

    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 2568;
                Damage = 53;
                break;
            case 3:
                HP = 2736;
                Damage = 57;
                break;
            case 4:
                HP = 2904;
                Damage = 60;
                break;
            case 5:
                HP = 3096;
                Damage = 64;
        }
    }

    @Override
    public void run() {
        while (getHp() > 0 && !(getGameTime().isEndGame())){

            updateStatus();
            synchronized (Thread.currentThread()) {
                if (isActive) {
                    Action opponent = inRange(7);
                    if (opponent != null) {
                        try {
                            Thread.sleep((long) (1000 * (isRage()?0.6:1)));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        opponent.getHit((int) (Damage * (isRage()?1.4:1)));
                    } else {
                        try {
                            Thread.sleep((long) (1000 * (isRage()?0.6:1)));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        die();
    }

    private boolean isRage () {
        int rowUp;
        int rowDown;
        int colLeft;
        int colRight;

        if (getRow() > rowEnd){
            rowDown = getRow();
            rowUp = rowEnd;
        }
        else {
            rowDown = rowEnd;
            rowUp = getRow();
        }

        if ( getColumn() > columnEnd){
            colRight = getColumn();
            colLeft = columnEnd;
        }
        else {
            colLeft = getColumn();
            colRight = columnEnd;
        }

        for (int i = rowUp ; i <= rowDown ; i++){
            for (int j = colLeft ; j <= colRight ; j++){
                if (getSpellState()[i][j].contains(getSide())){
                    return true;
                }
            }
        }
        return false;
    }

    private void die () {
        imageView.setImage(null);
        setDestroyed(true);
        getGameTime().setEndGame(true);
    }

    public void setActive(boolean active) {
        isActive = active;
        try {
            if (getSide().equals("Blue")) {
                imageView.setImage(new Image(new FileInputStream("src/sample/Game/Towers/King Tower Blue Active.png")));
            }
            else {
                imageView.setImage(new Image(new FileInputStream("src/sample/Game/Towers/King Tower Red Active.png")));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Action inRange (int range) {
        int rowUp;
        int rowDown;
        int colLeft;
        int colRight;

        String [][] status = getStatus();
        Action [][] mapContent = getMapContent();
        Action [][] airContent = getAirContent();

        if (getRow() > rowEnd){
            rowDown = getRow();
            rowUp = rowEnd;
        }
        else {
            rowDown = rowEnd;
            rowUp = getRow();
        }

        if ( getColumn() > columnEnd){
            colRight = getColumn();
            colLeft = columnEnd;
        }
        else {
            colLeft = getColumn();
            colRight = columnEnd;
        }

        for (int j = colLeft ; j <= colRight ; j++){
            for (int i = 1 ; i <= range && (rowDown + i < 32) ; i++) {
                if (status[rowDown + i][j].equals("Target")) {
                    if (mapContent[rowDown + i][j] != null) {
                        return mapContent[rowDown + i][j];
                    }
                    else if (airContent[rowDown + i][j] != null){
                        return airContent[rowDown + i][j];
                    }
                }
            }
        }
        for (int j = colLeft ; j <= colRight ; j++) {
            for (int i = 1; i <= range && (rowUp - i >= 0); i++) {
                if (status[rowUp - i][j].equals("Target")) {
                    if (mapContent[rowUp - i][j] != null) {
                        return mapContent[rowUp - i][j];
                    }
                    else if (airContent[rowUp - i][j] != null){
                        return airContent[rowUp - i][j];
                    }
                }
            }
        }
        for (int j = rowUp ; j <= rowDown ; j++) {
            for (int i = 1; i <= range && (colRight + i < 18); i++) {
                if (status[j][colRight + i].equals("Target")) {
                    if (mapContent[j][colRight + i] != null) {
                        return mapContent[j][colRight + i];
                    }
                    else if (airContent[j][colRight + i] != null){
                        return airContent[j][colRight + i];
                    }
                }
            }
        }
        for (int j = rowUp ; j <= rowDown ; j++) {
            for (int i = 1; i <= range && (colLeft - i >= 0); i++) {
                if (status[j][colLeft - i].equals("Target")) {
                    if (mapContent[j][colLeft - i] != null) {
                        return mapContent[j][colLeft - i];
                    }
                    else if (airContent[j][colLeft - i] != null){
                        return airContent[j][colLeft - i];
                    }
                }
            }
        }
        return null;
    }
}
