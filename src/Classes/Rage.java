package Classes;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import sample.GameControllers.Action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Rage.
 */
public class Rage extends Spell implements Runnable{
    private double Duration;
    private int row;
    private int column;
    private String side;
    private String[][] spellMap;
    private Pane[][] spellView;

    /**
     * Instantiates a new Rage.
     */
    public Rage() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Spells/RageCard.png")),new Image(new FileInputStream("src/sample/Game/Spells/Rage.png")),"Rage");
        Radius = 5;
        Cost = 3;
        Duration = 6;
        Level = 1;
    }

    public Rage(int row,int column,String[][] spellMap,Pane[][] spellView,String side) throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Spells/RageCard.png")),new Image(new FileInputStream("src/sample/Game/Spells/Rage.png")),"Rage");
        Radius = 5;
        Cost = 3;
        Duration = 6;
        Level = 1;
        this.row = row;
        this.column = column;
        this.spellMap = spellMap;
        this.spellView = spellView;
        this.side = side;
    }
    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2 :
                Duration = 6.5;
                break;
            case 3 :
                Duration = 7;
                break;
            case 4 :
                Duration = 7.5;
                break;
            case 5 :
                Duration = 8;
                break;
        }
    }

    @Override
    public void run() {
        int rowStart = (int) Math.floor(row - Radius);
        int rowFin = (int) Math.floor(row + Radius);
        int colStart = (int) Math.floor(column - Radius);
        int colFin = (int) Math.floor(column + Radius);

        if (rowStart < 0){
            rowStart = 0;
        }
        if (colStart < 0){
            colStart = 0;
        }
        if (rowFin > 31){
            rowFin = 31;
        }
        if (colFin > 17){
            colFin = 17;
        }

        for (int i = rowStart ; i <= rowFin ; i++){
            for (int j = colStart ; j <= colFin ; j++){
                if (spellMap[i][j].equals("NoSpell")) {
                    spellMap[i][j] = side;
                }
                else {
                    spellMap[i][j] += side;
                }
                spellView[i][j].setStyle("-fx-background-color: rgb(110,52,131,0.3)");
            }
        }

        try {
            Thread.sleep((long) (1000 * Duration));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = rowStart ; i <= rowFin ; i++){
            for (int j = colStart ; j <= colFin ; j++){
                if (spellMap[i][j].equals(side)) {
                    spellMap[i][j] = "NoSpell";
                }
                else {
                    spellMap[i][j] = spellMap[i][j].replaceAll(side,"") ;
                }
                spellView[i][j].setStyle("-fx-background-color: transparent");
            }
        }

    }
}
