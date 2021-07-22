package Classes;

import javafx.scene.image.Image;
import sample.GameControllers.Action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Inferno tower.
 */
public class InfernoTower extends Building{
    /**
     * Instantiates a new Inferno tower.
     */
    public InfernoTower() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Buildings/InfernoTowerCard.png")),new Image(new FileInputStream("src/sample/Game/Buildings/InfernoTower.png")),"InfernoTower");
        Target = "AG";
        HitSpeed = 0.4;
        Range = 6;
        LifeTime = 40;
        Cost = 5;
        Level = 1;
        Damage = 20;
        HP = 800;
    }
    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 880;
                Damage = 22;
                break;
            case 3:
                HP = 968;
                Damage = 24;
                break;
            case 4:
                HP = 1064;
                Damage = 26;
                break;
            case 5:
                HP = 1168;
                Damage = 29;
        }
    }

    public void action (Action action){
        Action enemy = action.inRange((int) Math.floor(Range));
        if (enemy != null){
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enemy.getHit(Damage);
        }
    }
}
