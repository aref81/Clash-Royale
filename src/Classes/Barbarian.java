package Classes;

import javafx.scene.image.Image;
import org.w3c.dom.ranges.Range;
import sample.GameControllers.Action;

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
        super(new Image(new FileInputStream("src/sample/GameMenu/Troops/BarbariansCard.png")),new Image(new FileInputStream("src/sample/Game/Troops/Barbarian.png")),"Barbarian");
        HitSpeed = 1.5;
        Speed = "Medium";
        Target = "G";
        IsMelee = true;
        AreaSplash = false;
        Count = 4;
        Cost = 5;
        Level = 1;
        HP = 300;
        Damage = 75;
    }

    /**
     * upgrades barbarian
     */
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

    /**
     * implements the action of card
     *
     * @param action its action class
     * @param isRage the rage of card
     */
    public synchronized void action (Action action,boolean isRage){
        Action enemy = action.inRange(1);
        if (enemy != null){
            try {
                Thread.sleep((long) (1500 * (isRage?0.6:1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enemy.getHit((int) (Damage * (isRage?1.4:1)));
        }
        else {
            try {
                Thread.sleep((long) (1000 * (isRage?0.6:1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            action.move(action.getTroop(),action.getMapContent());
        }
    }
}
