package Classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.GameControllers.Action;

/**
 * The type Damage spell.
 */
public abstract class DamageSpell extends Spell {
    /**
     * The Area damage.
     */
    protected int AreaDamage;

    public DamageSpell(Image cardPic, Image gamePic, String name) {
        super(cardPic, gamePic , name);
    }

    /**
     * implements the action of spell
     *
     * @param row intended row
     * @param column intended column
     * @param troop the troop map of game
     * @param map the map of game
     * @param mapContent the content of map
     * @param mapView the image view of map
     * @param side the side of spell
     * @param spellView the spell view panes
     */
    public synchronized void attack (int row , int column , String[][] troop, String[][] map, Action[][] mapContent , ImageView[][] mapView, String side, Pane[][] spellView) {
        String opponent;
        if (side.equals("Red")){
            opponent = "Blue";
        }
        else {
            opponent = "Red";
        }

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

        String style = (Radius == 2.5)?("-fx-background-color: rgb(186,133,19,0.3)"):("-fx-background-color: rgb(217,30,30,0.3)");

        for (int i = rowStart ; i <= rowFin ; i++){
            for (int j = colStart ; j <= colFin ; j++){
                spellView[i][j].setStyle(style);
            }
        }

        String[][] status = new String[32][18];

        for (int i = 0; i < 32; i++){
            for (int j = 0 ; j < 18 ; j++){
                if (map[i][j].contains(opponent)){
                    status[i][j] = "Target";
                }
                else {
                    status[i][j] = "Empty";
                }

                if (troop[i][j].equals(opponent + "Filled")){
                    status[i][j] = "Target";
                }
            }
        }

        for (int i = rowStart ; i <= rowFin ; i++){
            for (int j = colStart ; j <= colFin ; j++){
                if (status[i][j].equals("Target")){
                    mapContent[i][j].getHit(AreaDamage);
                }
                spellView[i][j].setStyle("-fx-background-color: transparent");
            }
        }

        mapView[rowStart][colStart].setFitHeight((double) 226/16);
        mapView[rowStart][colStart].setFitWidth((double) 359/18);
        mapView[rowStart][colStart].setImage(null);
    }
}
