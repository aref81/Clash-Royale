package Classes;

import javafx.scene.image.Image;
import sample.GameControllers.Action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Fire ball.
 */
public class FireBall extends DamageSpell{
    /**
     * Instantiates a new Fire ball.
     */
    public FireBall() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Spells/FireballCard.png")),new Image(new FileInputStream("src/sample/Game/Spells/Fireball.png")),"FireBall");
        Radius = 2.5;
        Cost = 4;
        AreaDamage = 325;
        Level = 1;
    }

    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2 :
                AreaDamage = 357;
                break;
            case 3 :
                AreaDamage = 393;
                break;
            case 4 :
                AreaDamage = 432;
                break;
            case 5 :
                AreaDamage = 474;
                break;
        }
    }

    public void action (Action action){

    }
}
