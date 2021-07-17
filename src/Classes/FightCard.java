package Classes;

import javafx.scene.image.Image;

/**
 * The type Fight card.
 */
public class FightCard extends Card{
    /**
     * The Hp.
     */
    protected int HP;
    /**
     * The Damage.
     */
    protected int Damage;
    /**
     * The Hit speed.
     */
    protected double HitSpeed;

    public FightCard(Image cardPic,Image gamePic) {
        super(cardPic, gamePic);
    }
}
