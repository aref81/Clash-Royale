package Classes;

import javafx.scene.image.Image;
import sample.GameControllers.Action;

/**
 * The type Fight card.
 */
public abstract class FightCard extends Card{
    /**
     * The Hp.
     */
    protected int HP;
    /**
     * The Damage.
     */
    protected int Damage;
    /**
     * The Target.
     */
    protected String Target;
    /**
     * The Hit speed.
     */
    protected double HitSpeed;

    public FightCard(Image cardPic,Image gamePic , String name) {
        super(cardPic, gamePic, name);
    }

    /**
     *
     *
     * @return hp of troop
     */
    public int getHP() {
        return HP;
    }

    /**
     *
     * @return the target range
     */
    public String getTarget() {
        return Target;
    }

    /**
     * implements the action of card
     *
     * @param action its action class
     * @param isRage the rage of card
     */
    public void action (Action action,boolean isRage){

    }
}
