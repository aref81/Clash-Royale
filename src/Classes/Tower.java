package Classes;

/**
 * The type Tower.
 */
public abstract class Tower {
    /**
     * The Hp.
     */
    protected int HP;
    /**
     * The Damage.
     */
    protected int Damage;
    /**
     * The Range.
     */
    protected double Range;
    /**
     * The Hit speed.
     */
    protected double HitSpeed;
    /**
     * The Level.
     */
    protected int Level;

    /**
     * Upgrade.
     */
    public void Upgrade(){
        if (Level<=4)
            Level++;
    }
}
