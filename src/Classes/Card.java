package Classes;

import javafx.scene.image.Image;

/**
 * The type Card.
 */
public class Card {
    private final Image cardPic;
    private final Image gamePic;

    /**
     * The Cost.
     */
    protected int Cost;
    /**
     * The Level.
     */
    protected int Level;

    public Card(Image cardPic, Image gamePic) {
        this.cardPic = cardPic;
        this.gamePic = gamePic;
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
}
