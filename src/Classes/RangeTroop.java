package Classes;

import javafx.scene.image.Image;

/**
 * The type Range troop.
 */
public abstract class RangeTroop extends Troop{
    /**
     * The Range.
     */
    protected int Range;

    /**
     * Instantiates a new Range troop.
     */
    public RangeTroop(Image cardPic,Image gamePic , String name){
        super(cardPic,gamePic , name);
        IsMelee = false;
    }
}
