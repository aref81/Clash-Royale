package Classes;

import javafx.scene.image.Image;

/**
 * The type Spell.
 */
public abstract class Spell extends Card{
    /**
     * The Radius.
     */
    protected double Radius;

    public Spell(Image cardPic , Image gamePic , String name) {
        super(cardPic, gamePic, name);
    }
}
