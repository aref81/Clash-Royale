package Classes;

import javafx.scene.image.Image;

/**
 * The type Building.
 */
public class Building extends FightCard{
    /**
     * The Range.
     */
    protected double Range;
    /**
     * The Target.
     */
    protected String Target;
    /**
     * The Life time.
     */
    protected int LifeTime;

    public Building(Image cardPic, Image gamePic) {
        super(cardPic, gamePic);
    }
}
