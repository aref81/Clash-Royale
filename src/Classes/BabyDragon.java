package Classes;

import javafx.scene.image.Image;
import sample.GameControllers.Action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Baby dragon.
 */
public class BabyDragon extends RangeTroop {
    /**
     * Instantiates a new Baby dragon.
     */
    public BabyDragon() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Troops/BabyDragonCard.png")),new Image(new FileInputStream("src/sample/Game/Troops/Baby Dragon.png")),"BabyDragon");
        HitSpeed = 1.8;
        Speed = "Fast";
        Target = "AG";
        AreaSplash = true;
        Count = 1;
        Range = 3;
        Cost = 4;
        Level = 1;
        HP = 800;
        Damage = 100;
    }

    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 880;
                Damage = 110;
                break;
            case 3:
                HP = 968;
                Damage = 121;
                break;
            case 4:
                HP = 1064;
                Damage = 133;
                break;
            case 5:
                HP = 1168;
                Damage = 146;
        }
    }

    public void action (Action action){
        Action enemy = action.inRange(Range);
        if (enemy != null){
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enemy.getHit(Damage);
            action.updateStatus();
            enemy.areaSplash(action.getStatus(),Damage);
        }
        else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            action.move(action.getAirTroop(),action.getAirContent());
        }
    }
}
