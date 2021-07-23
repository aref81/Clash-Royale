package sample.GameControllers;

import Classes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController implements Initializable {
    private AtomicInteger elixir;
    private AtomicInteger comElixir;
    private User user;
    private AI ai;
    private final ImageView [][] mapView = new ImageView [32][18];
    private final String [][] map = new String [32][18];
    private final String [][] mapStatusBlue = new String[32][18];
    private final String [][] mapStatusRed = new String[32][18];
    private final Action [][] mapContent = new Action[32][18];
    private final String [][] troop = new String [32][18];
    private final ImageView [][] airFieldView = new ImageView [32][18];
    private final String [][] airTroop = new String [32][18];
    private final Action [][] airContent = new Action[32][18];
    private final String [][] spellState = new String [32][18];
    private final Pane [][] spellView = new Pane [32][18];
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
    private KingTower blueKing;
    private KingTower redKing;
    private QueenTower blueRightQueen;
    private QueenTower blueLeftQueen;
    private QueenTower redRightQueen;
    private QueenTower redLeftQueen;
    private Stage current;

    public void setUser(User user , boolean hard , Stage current) {
        this.user = user;
        this.current = current;
        setFirstCards();
        updateDeckView();
        upgradeTowers(blueKing);
        upgradeTowers(redKing);
        upgradeTowers(blueLeftQueen);
        upgradeTowers(blueRightQueen);
        upgradeTowers(redLeftQueen);
        upgradeTowers(redRightQueen);

        if (hard) {
            this.ai = new AdvancedAI(comElixir, this, mapStatusRed, map, troop, airTroop);
        }
        else {
            this.ai = new SimpleAI(comElixir,this,mapStatusRed,map);
        }

        pool.execute(gameTime);
        pool.execute(ai);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ElBar.setText("");
        elixir = new AtomicInteger(0);
        comElixir = new AtomicInteger(0);

        gameTime = new GameTime(ElBar,timer,elixir,comElixir,this);
        blueKing = new KingTower(null,mapView,map,troop,mapContent,27,7,gameTime,"Blue" , KingBlue,mapStatusRed,airTroop,airContent,spellState);
        redKing = new KingTower(null,mapView,map,troop,mapContent,0,7,gameTime,"Red" , KingRed,mapStatusBlue,airTroop,airContent,spellState);
        blueRightQueen = new QueenTower(null,mapView,map,troop,mapContent,24,13,gameTime,"Blue" , PrincessBlueRight,mapStatusRed,blueKing,airTroop,airContent,spellState);
        blueLeftQueen = new QueenTower(null,mapView,map,troop,mapContent,24,2,gameTime,"Blue", PrincessBlueLeft,mapStatusRed,blueKing,airTroop,airContent,spellState);
        redRightQueen = new QueenTower(null,mapView,map,troop,mapContent,5,13,gameTime,"Red", PrincessRedRight,mapStatusBlue,redKing,airTroop,airContent,spellState);
         redLeftQueen = new QueenTower(null,mapView,map,troop,mapContent,5,2,gameTime,"Red",PrincessRedLeft,mapStatusBlue,redKing,airTroop,airContent,spellState);

        for (int i = 0; i < 16;i++){
            for (int j = 0; j < 18;j++){
                map[i][j] = "Field";
                mapStatusBlue [i][j] = "Blocked";
                mapStatusRed [i][j] = "Free";
                troop[i][j] = "Empty";
                airTroop[i][j] = "Empty";
                spellState[i][j] = "NoSpell";
            }
        }
        for (int i = 16; i < 32;i++){
            for (int j = 0; j < 18;j++){
                map[i][j] = "Field";
                mapStatusBlue [i][j] = "Free";
                mapStatusRed [i][j] = "Blocked";
                troop[i][j] = "Empty";
                airTroop[i][j] = "Empty";
                spellState[i][j] = "NoSpell";
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
                mapContent[i][j] = redKing;
            }
        }
        for (int i = 27; i < 32 ; i++){
            for (int j = 7 ; j < 11 ; j++){
                map[i][j] = "BlueKing";
                mapContent[i][j] = redKing;
            }
        }

        for (int i = 5; i < 8 ; i++){
            for (int j = 2; j < 5 ; j++){
                map[i][j] = "RedPrincess";
                mapContent[i][j] = redLeftQueen;
                map[i][j + 11] = "RedPrincess";
                mapContent[i][j + 11] = redRightQueen;
            }
        }
        for (int i = 24; i < 27 ; i++){
            for (int j = 2; j < 5 ; j++){
                map[i][j] = "BluePrincess";
                mapContent[i][j] = blueLeftQueen;
                map[i][j + 11] = "BluePrincess";
                mapContent[i][j + 11] = blueRightQueen;
            }
        }

        bridgeRight = "BridgeRight";
        bridgeLeft = "BridgeLeft";
        bridgeRightStatus = "FreeBlocked";
        bridgeLeftStatus = "FreeBlocked";

        for (int i = 0 ; i < 32 ; i++){
            for (int j = 0; j < 18 ; j++){
                ImageView imageView = new ImageView();
                imageView.setFitHeight((double) 226/5);
                imageView.setFitWidth((double) 359/10);
                imageView.setImage(null);
                mapView[i][j] = imageView;

                ImageView airView = new ImageView();
                airView.setFitHeight((double) 226/5);
                airView.setFitWidth((double) 359/10);
                airView.setImage(null);
                airFieldView[i][j] = airView;

                Pane spell = new Pane();
                spell.setPrefHeight((double) 226/5);
                spell.setPrefWidth((double) 359/10);
                spell.setStyle("-fx-background-color: transparent;");
                spellView[i][j] = spell;
                ;
                if (i < 16){
                    GridRed.add(imageView,j,i);
                    GridRedAir.add(spell,j,i);
                    GridRedAir.add(airView,j,i);
                }
                else {
                    GridBlue.add(imageView,j,i - 16);
                    GridBlueAir.add(spell,j,i - 16);
                    GridBlueAir.add(airView,j,i - 16);
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

        pool = Executors.newCachedThreadPool();

        pool.execute(redKing);
        pool.execute(blueKing);
        pool.execute(redLeftQueen);
        pool.execute(redRightQueen);
        pool.execute(blueLeftQueen);
        pool.execute(blueRightQueen);
    }

    private void upgradeTowers (Tower tower){
        int level = 0;
        switch (Integer.parseInt(user.getPoints())){
            case 300: {
                level = 1;
                break;
            }
            case 500: {
                level = 2;
                break;
            }
            case 900: {
                level = 3;
                break;
            }
            case 1700: {
                level = 4;
                break;
            }
            case 2500: {
                level = 5;
                break;
            }
        }

        for (int i = 0 ; i < level ; i++) {
            tower.Upgrade();
        }
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
    private GridPane GridRedAir;

    @FXML
    private ImageView KingRed;

    @FXML
    private ImageView PrincessRedRight;

    @FXML
    private ImageView PrincessRedLeft;

    @FXML
    private GridPane GridBlue;

    @FXML
    private GridPane GridBlueAir;

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

        ClipboardContent cbc = new ClipboardContent();
        cbc.putString(String.valueOf(i));
        db.setContent(cbc);
        db.setDragView(card.getGamePic());
        event.consume();
    }

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
    synchronized void handleDragDropped(DragEvent event) {
        GridPane gridPane = (GridPane) event.getSource();
        int index = Integer.parseInt(event.getDragboard().getString());
        if (elixir.get() >= cards[index].getCost()) {
            if (gridPane.getId().equals("GridBlueAir") || gridPane.getId().equals("GridRedAir")) {
                int row = (gridPane.getId().equals("GridRedAir")) ? getRow(event.getY()) : getRow(event.getY()) + 16;
                int col = getColumn(event.getX());
                insertCard(index,row,col,"Blue",mapStatusBlue);
            }
        }
    }

    public boolean insertCard (int index,int row,int col,String side,String[][] mapStatus) {
        int xExtend = (col == 17) ? -1 : 1;
        int yExtend = (row == 15 || row == 31) ? -1 : 1;
        if (!(cards[index] instanceof Spell)) {
            FightCard card = (FightCard) cards[index];
            if (cards[index] instanceof BabyDragon){
                airTroop[row][col] = side + "Filled";
                Action action = new Action(card,airFieldView,map,troop, mapContent, row, col, gameTime, side, 0,airTroop,airContent,spellState);
                airContent[row][col] = action;
                airFieldView[row][col].setImage(cards[index].getGamePic());
                if (side.equals("Blue")) {
                    ejectCard(index);
                }
                pool.execute(action);
                return true;
            }
            else {
                if (mapStatus[row][col].equals("Free")) {
                    if (troop[row][col].equals("Empty")) {
                        if (cards[index] instanceof Barbarian) {
                            if (mapStatus[row + yExtend][col] == "Free" && mapStatus[row][col + xExtend] == "Free" && mapStatus[row + yExtend][col + xExtend] == "Free") {
                                if (troop[row + yExtend][col] == "Empty" && troop[row][col + xExtend] == "Empty" && troop[row + yExtend][col + xExtend] == "Empty") {
                                    troop[row][col] = side + "Filled";
                                    Action action1 = new Action(card, mapView, map, troop, mapContent, row, col, gameTime, side, 0,airTroop,airContent,spellState);
                                    mapContent[row][col] = action1;
                                    mapView[row][col].setImage(cards[index].getGamePic());
                                    troop[row + yExtend][col] = side + "Filled";
                                    Action action2 = new Action(card, mapView, map, troop, mapContent, row + yExtend, col, gameTime, side, 0,airTroop,airContent,spellState);
                                    mapContent[row + yExtend][col] = action2;
                                    mapView[row + yExtend][col].setImage(cards[index].getGamePic());
                                    troop[row][col + xExtend] = side + "Filled";
                                    Action action3 = new Action(card, mapView, map, troop, mapContent, row, col + xExtend, gameTime, side, 0,airTroop,airContent,spellState);
                                    mapContent[row][col + xExtend] = action3;
                                    mapView[row][col + xExtend].setImage(cards[index].getGamePic());
                                    troop[row + yExtend][col + xExtend] = side + "Filled";
                                    Action action4 = new Action(card, mapView, map, troop, mapContent, row + yExtend, col + xExtend, gameTime, side, 0,airTroop,airContent,spellState);
                                    mapContent[row + yExtend][col + xExtend] = action4;
                                    mapView[row + yExtend][col + xExtend].setImage(card.getGamePic());
                                    if (side.equals("Blue")) {
                                        ejectCard(index);
                                    }
                                    pool.execute(action1);
                                    pool.execute(action2);
                                    pool.execute(action3);
                                    pool.execute(action4);
                                    return true;
                                }
                            }
                        } else if (cards[index] instanceof Archer) {
                            if (mapStatus[row][col + xExtend] == "Free") {
                                if (troop[row][col + xExtend] == "Empty") {
                                    troop[row][col] = side + "Filled";
                                    Action action1 = new Action(card, mapView, map, troop, mapContent, row, col, gameTime, side, 0,airTroop,airContent,spellState);
                                    mapContent[row][col] = action1;
                                    mapView[row][col].setImage(cards[index].getGamePic());
                                    troop[row][col + xExtend] = side + "Filled";
                                    Action action2 = new Action(card, mapView, map, troop, mapContent, row, col + xExtend, gameTime, side + "Filled", 0,airTroop,airContent,spellState);
                                    mapContent[row][col + xExtend] = action2;
                                    mapView[row][col + xExtend].setImage(cards[index].getGamePic());
                                    if (side.equals("Blue")) {
                                        ejectCard(index);
                                    }
                                    pool.execute(action1);
                                    pool.execute(action2);
                                    return true;
                                }
                            }
                        } else {
                            troop[row][col] = side + "Filled";
                            Action action = new Action(card, mapView, map, troop, mapContent, row, col, gameTime, side, 0,airTroop,airContent,spellState);
                            mapContent[row][col] = action;
                            mapView[row][col].setImage(cards[index].getGamePic());
                            if (side.equals("Blue")) {
                                ejectCard(index);
                            }
                            pool.execute(action);
                            return true;
                        }
                    }
                }
            }
        }
        else {
            if (cards[index] instanceof DamageSpell){
                DamageSpell damageSpell = (DamageSpell) cards[index];
                if (side.equals("Blue")) {
                    ejectCard(index);
                }
                damageSpell.attack(row,col,troop,map,mapContent,mapView,side,spellView);
                return true;
            }
            else {
                try {
                    Rage rage = new Rage(row,col,spellState,spellView,side);
                    if (side.equals("Blue")) {
                        ejectCard(index);
                    }
                    pool.execute(rage);
                    return true;
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }

        return false;
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

    public String drawCase () {
        int blue = 0;
        blue += blueKing.getHPNum();
        if (!blueRightQueen.isDestroyed()){
            blue += blueRightQueen.getHPNum();
        }
        if (!blueLeftQueen.isDestroyed()){
            blue += blueLeftQueen.getHPNum();
        }

        int red = 0;
        red += redKing.getHPNum();
        if (!redRightQueen.isDestroyed()){
            red += redRightQueen.getHPNum();
        }
        if (!redLeftQueen.isDestroyed()){
            red += redLeftQueen.getHPNum();
        }

        if (blue > red){
            return "Blue";
        }
        else {
            return "Red";
        }
    }

    public User getUser() {
        return user;
    }

    public Stage getCurrent() {
        return current;
    }
}
