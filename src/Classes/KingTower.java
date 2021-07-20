package Classes;

import sample.GameControllers.Action;

/**
 * The type King tower.
 */
public class KingTower extends Tower {
    private boolean isActive;
    /**
     * Instantiates a new King tower.
     */
    public KingTower(){
        isActive = false;
        Range = 7;
        HitSpeed = 1;
        HP = 2400;
        Damage = 50;
        Level = 1;
    }

    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 2568;
                Damage = 53;
                break;
            case 3:
                HP = 2736;
                Damage = 57;
                break;
            case 4:
                HP = 2904;
                Damage = 60;
                break;
            case 5:
                HP = 3096;
                Damage = 64;
        }
    }

    public void action (Action action){

    }
}
