package Classes;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Arrow.
 */
public class Arrow extends DamageSpell{
    /**
     * Instantiates a new Arrow.
     */
    public Arrow() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Spells/ArrowsCard.png")),new Image(new FileInputStream("src/sample/Game/Spells/Arrows.png")),"Arrow");
        Radius = 4;
        Cost = 3;
        AreaDamage = 144;
        Level = 1;
    }

    /**
     *
     * upgrades arrow
     */
    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2 :
                AreaDamage = 156;
                break;
            case 3 :
                AreaDamage = 174;
                break;
            case 4 :
                AreaDamage = 189;
                break;
            case 5 :
                AreaDamage = 210;
                break;
        }
    }
}
