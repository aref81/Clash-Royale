package Classes;

import javafx.scene.image.Image;
import sample.GameControllers.Action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Archer.
 */
public class Archer extends RangeTroop {

    /**
     * Instantiates a new Archer.
     */
    public Archer() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Troops/ArchersCard.png")),new Image(new FileInputStream("src/sample/Game/Troops/Archer.png")),"Archer");
        HitSpeed = 1.2;
        Target = "AG";
        Speed = "Medium";
        AreaSplash = false;
        Count = 2;
        Range = 2;
        Cost = 3;
        Level = 1;
        HP = 125;
        Damage = 33;
    }

    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 127;
                Damage = 44;
                break;
            case 3:
                HP = 151;
                Damage = 48;
                break;
            case 4:
                HP = 166;
                Damage = 53;
                break;
            case 5:
                HP = 182;
                Damage = 58;
        }
    }

    @Override
    public synchronized void action (Action action,boolean isRage){
        Action enemy = action.inRange(Range);
        if (enemy != null){
            try {
                Thread.sleep((long) (1200 * (isRage?0.6:1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enemy.getHit((int) (Damage * (isRage?1.4:1)));
        }
        else {
            try {
                Thread.sleep((long) (1000* (isRage?0.6:1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            action.move(action.getTroop(),action.getMapContent());
        }
    }
}