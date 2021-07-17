package Classes;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Mini pekka.
 */
public class MiniPEKKA extends Troop{
    /**
     * Instantiates a new Mini pekka.
     */
    public MiniPEKKA() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Troops/MiniPEKKACard.png")),new Image(new FileInputStream("src/sample/Game/Troops/peka.png")));
        HitSpeed = 1.8;
        Speed = "Fast";
        IsMelee = true;
        AreaSplash = false;
        Count = 1;
        Cost = 4;
        Level = 1;
        HP = 600;
        Damage = 325;
    }

    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 660;
                Damage = 357;
                break;
            case 3:
                HP = 726;
                Damage = 393;
                break;
            case 4:
                HP = 798;
                Damage = 432;
                break;
            case 5:
                HP = 876;
                Damage = 474;
        }
    }
}
