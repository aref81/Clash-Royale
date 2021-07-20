package Classes;

import sample.GameControllers.Action;

/**
 * The type Queen tower.
 */
public class QueenTower extends Tower{
    /**
     * Instantiates a new Queen tower.
     */
    public QueenTower(){
        Range = 7.5;
        HitSpeed = 0.8;
        HP = 2400;
        Damage = 50;
        Level = 1;
    }

    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 1512;
                Damage = 54;
                break;
            case 3:
                HP = 1624;
                Damage = 58;
                break;
            case 4:
                HP = 1750;
                Damage = 62;
                break;
            case 5:
                HP = 1890;
                Damage = 69;
        }
    }

    public void action (Action action){

    }
}
