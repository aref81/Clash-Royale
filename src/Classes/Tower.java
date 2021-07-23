package Classes;

import javafx.scene.image.ImageView;
import sample.GameControllers.Action;
import sample.GameControllers.GameTime;

/**
 * The type Tower.
 */
public abstract class Tower extends Action {
    private boolean destroyed;
    /**
     * The Hp.
     */
    protected int HP;
    /**
     * The Damage.
     */
    protected int Damage;
    /**
     * The Range.
     */
    protected double Range;
    /**
     * The Hit speed.
     */
    protected double HitSpeed;
    /**
     * The Level.
     */
    protected int Level;

    protected ImageView imageView;

    protected String[][] mapStatus;

    public Tower(FightCard card, ImageView[][] mapView, String[][] map, String[][] troop, Action[][] mapContent, int row, int column, GameTime gameTime, String side , int hp, ImageView imageView , String[][] mapStatus , String[][] airTroop , Action[][] airFieldContent,String[][] spellState) {
        super(card, mapView, map, troop, mapContent, row, column, gameTime, side , hp,airTroop,airFieldContent,spellState);
        destroyed = false;
        this.imageView = imageView;
        this.mapStatus = mapStatus;
    }

    /**
     * Upgrade.
     */
    public void Upgrade(){
        if (Level<=4)
            Level++;
    }

    public void updateStatus (){
        String [][] status  = getStatus();
        String opponent = getOpponent();
        for (int i = 0; i < 32; i++){
            for (int j = 0 ; j < 18 ; j++){
                status[i][j] = "Empty";
                if (getTroop()[i][j].equals(opponent + "Filled")){
                    status[i][j] = "Target";
                }
                if (getAirTroop()[i][j].equals(opponent + "Filled")){
                    status[i][j] = "Target";
                }
            }
        }
    }

    /**
     *
     * @return the status of tower
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     *
     * @param destroyed new status of tower
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     *
     *
     * @return the hp
     */
    public int getHPNum() {
        return HP;
    }
}
