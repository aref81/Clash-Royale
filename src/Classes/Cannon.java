package Classes;

import javafx.scene.image.Image;
import sample.GameControllers.Action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Cannon.
 */
public class Cannon extends Building{
    /**
     * Instantiates a new Cannon.
     */
    public Cannon() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Buildings/CannonCard.png")),new Image(new FileInputStream("src/sample/Game/Buildings/Cannon.png")),"Cannon");
        Target = "G";
        HitSpeed = 0.8;
        Range = 5.5;
        LifeTime = 30;
        Cost = 6;
        Level = 1;
        Damage = 60;
        HP = 380;
    }

    /**
     *
     * upgrades cannon
     */
    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 418;
                Damage = 66;
                break;
            case 3:
                HP = 459;
                Damage = 72;
                break;
            case 4:
                HP = 505;
                Damage = 79;
                break;
            case 5:
                HP = 554;
                Damage = 87;
        }
    }

    /**
     * implements the action of card
     *
     * @param action its action class
     * @param isRage the rage of card
     */
    public synchronized void action (Action action , boolean isRage){
        Action enemy = action.inRange((int) Math.floor(Range));
        if (enemy != null){
            try {
                Thread.sleep((long) (800 * (isRage?0.6:1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enemy.getHit((int) (Damage * (isRage?1.4:1)));
        }
    }
}
