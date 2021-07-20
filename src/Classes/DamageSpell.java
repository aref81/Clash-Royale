package Classes;

import javafx.scene.image.Image;

/**
 * The type Damage spell.
 */
public abstract class DamageSpell extends Spell{
    /**
     * The Area damage.
     */
    protected int AreaDamage;

    public DamageSpell(Image cardPic, Image gamePic , String name) {
        super(cardPic, gamePic , name);
    }
}
