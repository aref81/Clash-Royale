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
    public void action (Action action){
        Action opponent = action.inRange(Range);
        if (opponent != null){
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            opponent.getHit(Damage);
        }
        else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            action.move();
        }
    }
}