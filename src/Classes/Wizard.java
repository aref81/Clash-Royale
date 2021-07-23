package Classes;

import javafx.scene.image.Image;
import sample.GameControllers.Action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Wizard.
 */
public class Wizard extends RangeTroop{
    /**
     * Instantiates a new Wizard.
     */
    public Wizard() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Troops/WizardCard.png")),new Image(new FileInputStream("src/sample/Game/Troops/Wizard.png")),"Wizard");
        HitSpeed = 1.7;
        Speed = "Medium";
        Target = "AG";
        AreaSplash = true;
        Count = 1;
        Range = 5;
        Cost = 5;
        Level = 1;
        HP = 340;
        Damage = 130;
    }

    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 374;
                Damage = 143;
                break;
            case 3:
                HP = 411;
                Damage = 157;
                break;
            case 4:
                HP = 452;
                Damage = 172;
                break;
            case 5:
                HP = 496;
                Damage = 189;
                break;
        }
    }

    /**
     * implements the action of card
     *
     * @param action its action class
     * @param isRage the rage of card
     */
    public synchronized void action (Action action , boolean isRage){
        Action enemy = action.inRange(Range);
        if (enemy != null){
            try {
                Thread.sleep((long) (1200 * (isRage?0.6:1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enemy.getHit((int) (Damage * (isRage?1.4:1)));
            action.updateStatus();
            enemy.areaSplash(action.getStatus(), (int) (Damage * (isRage?1.4:1)));
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
