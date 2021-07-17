package Classes;

import javafx.scene.image.Image;
import org.w3c.dom.ranges.Range;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Barbarian.
 */
public class Barbarian extends Troop{
    /**
     * Instantiates a new Barbarian.
     */
    public Barbarian() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Troops/BarbariansCard.png")),new Image(new FileInputStream("src/sample/Game/Troops/Barbarian.png")));
        HitSpeed = 1.5;
        Speed = "Medium";
        IsMelee = true;
        AreaSplash = false;
        Count = 4;
        Cost = 5;
        Level = 1;
        HP = 300;
        Damage = 75;
    }
    public void Upgrade(){
        super.Upgrade();
        switch (Level){
            case 2:
                HP = 330;
                Damage = 82;
                break;
            case 3:
                HP = 363;
                Damage = 90;
                break;
            case 4:
                HP = 438;
                Damage = 99;
                break;
            case 5:
                HP = 480;
                Damage = 109;
        }
    }
}
