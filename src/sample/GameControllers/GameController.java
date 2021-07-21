package sample.GameControllers;

import Classes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController implements Initializable {
    private AtomicInteger elixir;
    private User user;
    private final ImageView [][] mapView = new ImageView [32][18];
    private final String [][] map = new String [32][18];
    private final String [][] mapStatus = new String[32][18];
    private final Action [][] mapContent = new Action[32][18];
    private  String bridgeRight;
    private  String bridgeLeft;
    private ImageView bridgeRightView;
    private ImageView bridgeLeftView;
    private  String bridgeRightStatus;
    private  String bridgeLeftStatus;
    private Action leftBridgeContent;
    private Action RightBridgeContent;
    private final Card[] cards = new Card[4];
    private Card nextCard;
    private ExecutorService pool;
    private GameTime gameTime;

    public void setUser(User user) {
        this.user = user;
        setFirstCards();
        updateDeckView();
        pool = Executors.newCachedThreadPool();
        gameTime = new GameTime(ElBar,timer,elixir);
        pool.execute(gameTime);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 16;i++){
            for (int j = 0; j < 18;j++){
                map[i][j] = "Field";
                mapStatus [i][j] = "Blocked";
            }
        }
        for (int i = 16; i < 32;i++){
            for (int j = 0; j < 18;j++){
                map[i][j] = "Field";
                mapStatus [i][j] = "Free";
            }
        }

        for (int j = 3; j < 15 ; j++){
            map[2][j] = "Way";
        }
        for (int i = 3; i < 29; i++){
            map[i][3] = "Way";
            map[i][14] = "Way";
        }
        for (int j = 3; j < 15 ; j++){
            map[29][j] = "Way";
        }


        for (int i = 0; i < 5 ; i++){
            for (int j = 7 ; j < 11 ; j++){
                map[i][j] = "RedKing";
            }
        }
        for (int i = 27; i < 32 ; i++){
            for (int j = 7 ; j < 11 ; j++){
                map[i][j] = "BlueKing";
            }
        }

        for (int i = 5; i < 8 ; i++){
            for (int j = 2; j < 5 ; j++){
                map[i][j] = "RedPrincess";
                map[i][j + 11] = "RedPrincess";
            }
        }
        for (int i = 24; i < 27 ; i++){
            for (int j = 2; j < 5 ; j++){
                map[i][j] = "BluePrincess";
                map[i][j + 11] = "BluePrincess";
            }
        }

        bridgeRight = "BridgeRight";
        bridgeLeft = "BridgeLeft";
        bridgeRightStatus = "FreeBlocked";
        bridgeLeftStatus = "FreeBlocked";

        for (int i = 0 ; i < 32 ; i++){
            for (int j = 0; j < 18 ; j++){
                ImageView imageView = new ImageView();
                imageView.setFitHeight((double) 226/16);
                imageView.setFitWidth((double) 359/18);


//                EventHandler<DragEvent> dragDetect = dragEvent -> {
//                    dragEvent.acceptTransferModes(TransferMode.MOVE);
//                    ImageView image = (ImageView) dragEvent.getSource();
//                    image.setImage(dragEvent.getDragboard().getDragView());
//                };
//
//                imageView.addEventFilter(DragEvent.DRAG_OVER,dragDetect);
//
//
//                EventHandler<DragEvent> dragExit = dragEvent -> {
//                    ImageView image = (ImageView) dragEvent.getSource();
//                    image.setImage(null);
//                };
//
//                imageView.addEventFilter(DragEvent.DRAG_EXITED,dragExit);

                imageView.setImage(null);
                mapView[i][j] = imageView;
                if (i < 16){
                    GridRed.add(imageView,j,i);
                }
                else {
                    GridBlue.add(imageView,j,i - 16);
                }
            }
        }

        ImageView imageLeft = new ImageView();
        imageLeft.setFitHeight((double) 226/16);
        imageLeft.setFitWidth((double) 359/18);
        bridgeLeftView = imageLeft;
        ImageView imageRight = new ImageView();
        imageRight.setFitHeight((double) 226/16);
        imageRight.setFitWidth((double) 359/18);
        bridgeRightView = imageRight;

        BridgeLeft.add(imageLeft,0,0);
        BridgeRight.add(imageRight,0,0);

        ElBar.setText("");
        elixir = new AtomicInteger(0);
    }

    private void setFirstCards (){
        for (int i = 0; i < 4;i++) {
            cards[i] = user.getRandomCard(cards[0], cards[1], cards[2], cards[3]);
        }
        nextCard = user.getRandomCard(cards[0],cards[1],cards[2],cards[3]);
    }

    private void updateDeckView (){
        d1.setImage(cards[0].getCardPic());
        d2.setImage(cards[1].getCardPic());
        d3.setImage(cards[2].getCardPic());
        d4.setImage(cards[3].getCardPic());
        dn.setImage(nextCard.getCardPic());
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

    @FXML
    private Text timer;

    @FXML
    private GridPane BridgeRight;

    @FXML
    private GridPane BridgeLeft;

    @FXML
    void cardDragHandler(MouseEvent event) {
        ImageView image = ((ImageView) event.getSource());
        String id = image.getId();
        Dragboard db = image.startDragAndDrop(TransferMode.MOVE);
        int i = Integer.parseInt(String.valueOf(id.charAt(1))) - 1;
        Card card = cards[i];

//        if (id.equals("d1")){
//            card = cards[0];
//            db = d1.startDragAndDrop(TransferMode.MOVE);
//        }
//        else if (id.equals("d2")){
//            card = cards[1];
//            db = d2.startDragAndDrop(TransferMode.MOVE);
//        }
//        else if (id.equals("d3")){
//            card = cards[2];
//            db = d3.startDragAndDrop(TransferMode.MOVE);
//        }
//        else if (id.equals("d4")){
//            card = cards[3];
//            db = d4.startDragAndDrop(TransferMode.MOVE);
//        }

        ClipboardContent cbc = new ClipboardContent();
        cbc.putString(String.valueOf(i));
        db.setContent(cbc);
        db.setDragView(card.getGamePic());
        event.consume();
    }


//
//
//    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
//        for (Node node : gridPane.getChildren()) {
//            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
//                return node;
//            }
//        }
//        return null;
//    }

    private int getRow (double y){
        return (int) Math.floor(y / (226/16));
    }

    private int getColumn (double y){
        return (int) Math.floor(y / (360/18));
    }

    @FXML
    void handleDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
    }


    @FXML
    void handleDragDropped(DragEvent event) {
        GridPane gridPane = (GridPane) event.getSource();
        int index = Integer.parseInt(event.getDragboard().getString());
        FightCard card = (FightCard) cards[index];
        if (elixir.get() >= cards[index].getCost()) {
            if (gridPane.getId().equals("GridBlue") || gridPane.getId().equals("GridRed")) {
                int row = (gridPane.getId().equals("GridRed")) ? getRow(event.getY()) : getRow(event.getY()) + 16;
                int col = getColumn(event.getX());
                int xExtend = (col == 17) ? -1 : 1;
                int yExtend = (row == 15 || row == 31) ? -1 : 1;
                if (!(cards[index] instanceof Spell)) {
                    if (cards[index] instanceof BabyDragon){

                    }
                    else {
                        if (mapStatus[row][col].equals("Free")) {
                            if (cards[index] instanceof Barbarian) {
                                if (mapStatus[row + yExtend][col] == "Free" || mapStatus[row][col + xExtend] == "Free" || mapStatus[row + yExtend][col + xExtend] == "Free") {
                                    mapStatus[row][col] = "BlueFilled";
                                    Action action1 = new Action(card, mapView, map, mapStatus, mapContent, row, col,gameTime);
                                    mapContent[row][col] = action1;
                                    mapView[row][col].setImage(cards[index].getGamePic());
                                    mapStatus[row + yExtend][col] = "BlueFilled";
                                    Action action2 = new Action(card, mapView, map, mapStatus, mapContent, row + yExtend, col,gameTime);
                                    mapContent[row + yExtend][col] = action2;
                                    mapView[row + yExtend][col].setImage(cards[index].getGamePic());
                                    mapStatus[row][col + xExtend] = "BlueFilled";
                                    Action action3 = new Action(card, mapView, map, mapStatus, mapContent, row, col + xExtend,gameTime);
                                    mapContent[row][col + xExtend] = action3;
                                    mapView[row][col + xExtend].setImage(cards[index].getGamePic());
                                    mapStatus[row + yExtend][col + xExtend] = "BlueFilled";
                                    Action action4 = new Action(card, mapView, map, mapStatus, mapContent, row + yExtend, col + xExtend,gameTime);
                                    mapContent[row + yExtend][col + xExtend] = action4;
                                    mapView[row + yExtend][col + xExtend].setImage(card.getGamePic());
                                    ejectCard(index);
                                    pool.execute(action1);
                                    pool.execute(action2);
                                    pool.execute(action3);
                                    pool.execute(action4);
                                }
                            } else if (cards[index] instanceof Archer) {
                                if (mapStatus[row][col + xExtend] == "Free") {
                                    mapStatus[row][col] = "BlueFilled";
                                    Action action1 = new Action(card, mapView, map, mapStatus, mapContent, row, col,gameTime);
                                    mapContent[row][col] = action1;
                                    mapView[row][col].setImage(cards[index].getGamePic());
                                    mapStatus[row][col + xExtend] = "BlueFilled";
                                    Action action2 = new Action(card, mapView, map, mapStatus, mapContent, row, col + xExtend,gameTime);
                                    mapContent[row][col + xExtend] = action2;
                                    mapView[row][col + xExtend].setImage(cards[index].getGamePic());
                                    ejectCard(index);
                                    pool.execute(action1);
                                    pool.execute(action2);
                                }
                            } else {
                                mapStatus[row][col] = "BlueFilled";
                                Action action = new Action(card, mapView, map, mapStatus, mapContent, row, col,gameTime);
                                mapContent[row][col] = action;
                                mapView[row][col].setImage(cards[index].getGamePic());
                                ejectCard(index);
                                pool.execute(action);
                            }
                        }
                    }
                }
//            else {
//                if (gridPane.getId().equals("BridgeRight")) {
//                    if (bridgeRightStatus.equals("Free")) {
//                        bridgeRightStatus = "Filled";
//                        bridgeRightView.setImage(cards[index].getGamePic());
//                        ejectCard(index);
//                    }
//                }
//                if (gridPane.getId().equals("BridgeLeft")) {
//                    if (bridgeLeftStatus.equals("Free")) {
//                        bridgeLeftStatus = "Filled";
//                        bridgeLeftView.setImage(cards[index].getGamePic());
//                        ejectCard(index);
//                    }
//                }
//            }
            }
        }
    }

    private void ejectCard (int index){
        elixir.set(elixir.get() - cards[index].getCost());
        cards[index] = nextCard;
        nextCard = user.getRandomCard(cards[0],cards[1],cards[2],cards[3]);
        updateDeckView();
    }

    public GameTime getGameTime() {
        return gameTime;
    }
}
