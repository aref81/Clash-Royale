package Classes;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Valkyrie.
 */
public class Valkyrie extends Troop{
    /**
     * Instantiates a new Valkyrie.
     */
    public Valkyrie() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Troops/ValkyrieCard.png")),new Image(new FileInputStream("src/sample/Game/Troops/Valkyrie.png")));
        HitSpeed = 1.5;
        Speed = "Medium";
        IsMelee = true;
        AreaSplash = true;
        Count = 1;
        Cost = 4;
        Level = 1;
        HP = 880;
        Damage = 120;
    }

    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 968;
                Damage = 132;
                break;
            case 3:
                HP = 1064;
                Damage = 145;
                break;
            case 4:
                HP = 1170;
                Damage = 159;
                break;
            case 5:
                HP = 1284;
                Damage = 175;
                break;
        }
    }
}
