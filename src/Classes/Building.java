package Classes;

import javafx.scene.image.Image;

/**
 * The type Building.
 */
public abstract class Building extends FightCard{
    /**
     * The Range.
     */
    protected double Range;
    /**
     * The Life time.
     */
    protected int LifeTime;

    public Building(Image cardPic, Image gamePic , String name) {
        super(cardPic, gamePic , name);
    }


    public int getLifeTime() {
        return LifeTime;
    }
}
