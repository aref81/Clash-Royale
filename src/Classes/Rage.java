package Classes;

import javafx.scene.image.Image;
import sample.GameControllers.Action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Rage.
 */
public class Rage extends Spell{
    private double Duration;

    /**
     * Instantiates a new Rage.
     */
    public Rage() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Spells/RageCard.png")),new Image(new FileInputStream("src/sample/Game/Spells/Rage.png")),"Rage");
        Radius = 5;
        Cost = 3;
        Duration = 6;
        Level = 1;
    }
    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2 :
                Duration = 6.5;
                break;
            case 3 :
                Duration = 7;
                break;
            case 4 :
                Duration = 7.5;
                break;
            case 5 :
                Duration = 8;
                break;
        }
    }

    public void action (Action action){

    }
}
