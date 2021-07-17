package sample.GameControllers;

import Classes.Card;
import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private User user;
    private final char [][] map = new char[32][18];
    private final Card[] cards = new Card[4];
    private Card nextCard;

    public void setUser(User user) {
        this.user = user;
        setFirstCards();
        d1.setImage(cards[0].getCardPic());
        d2.setImage(cards[1].getCardPic());
        d3.setImage(cards[2].getCardPic());
        d4.setImage(cards[3].getCardPic());
        dn.setImage(nextCard.getCardPic());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 16;i++){
            for (int j = 0; j < 18;j++){
                map[i][j] = 'B';
            }
        }
        for (int i = 16; i < 32;i++){
            for (int j = 0; j < 18;j++){
                map[i][j] = 'A';
            }
        }
        ElBar.setText("1");
    }

    private void setFirstCards (){
        for (int i = 0; i < 4;i++) {
            cards[i] = user.getRandomCard(cards[0], cards[1], cards[2], cards[3]);
        }
        nextCard = user.getRandomCard(cards[0],cards[1],cards[2],cards[3]);
    }

    @FXML
    private GridPane GridRed;

    @FXML
    private ImageView KingRed;

    @FXML
    private ImageView PrincessRedRight;

    @FXML
    private ImageView PrincessRedLeft;

    @FXML
    private GridPane GridBlue;

    @FXML
    private ImageView KingBlue;

    @FXML
    private ImageView PrincessBlueRight;

    @FXML
    private ImageView PrincessBlueLeft;

    @FXML
    private Text ElBar;

    @FXML
    private ImageView d1;

    @FXML
    private ImageView d2;

    @FXML
    private ImageView d3;

    @FXML
    private ImageView d4;

    @FXML
    private ImageView dn;
}
