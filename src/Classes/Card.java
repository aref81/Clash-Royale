package Classes;

import javafx.scene.image.Image;
import sample.GameControllers.Action;

/**
 * The type Card.
 */
public abstract class Card {
    private final Image cardPic;
    private final Image gamePic;
    private final String name;

    /**
     * The Cost.
     */
    protected int Cost;
    /**
     * The Level.
     */
    protected int Level;

    public Card(Image cardPic, Image gamePic, String name) {
        this.cardPic = cardPic;
        this.gamePic = gamePic;
        this.name = name;
    }

    /**
     * Upgrade.
     */
    public void Upgrade(){
        if (Level<=4)
            Level++;
    }

    /**
     * returns card picture
     *
     * @return card picture Image
     */
    public Image getCardPic() {
        return cardPic;
    }

    /**
     * returns game picture
     *
     * @return game picture image
     */
    public Image getGamePic() {
        return gamePic;
    }

    /**
     * returns the name of card
     *
     * @return the name of card
     */
    public String getName() {
        return name;
    }

    /**
     * returns the cost of card
     *
     * @return the cost
     */
    public int getCost() {
        return Cost;
    }

    public void action (Action action,boolean isRage){

    }

}
