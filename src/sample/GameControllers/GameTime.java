package sample.GameControllers;

import Classes.GameMenu;
import Classes.User;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * handles the game timeline and ending
 */
public class GameTime implements Runnable{
    private int time;
    private boolean endGame;
    private boolean timeEnd;
    private Text ElBar;
    private Text timer;
    private AtomicInteger elixir;
    private AtomicInteger comElixir;
    private int blue;
    private int red;
    private GameController gameController;

    /**
     * initializes a new timer
     * @param elBar the elbar text
     * @param timer timer text
     * @param elixir blue elixir count
     * @param comElixir red elixir count
     * @param gameController the game
     */
    public GameTime(Text elBar, Text timer, AtomicInteger elixir, AtomicInteger comElixir,GameController gameController) {
        timeEnd = false;
        endGame = false;
        blue = 0;
        red = 0;
        ElBar = elBar;
        this.timer = timer;
        this.elixir = elixir;
        this.comElixir = comElixir;
        this.gameController = gameController;
    }

    /**
     * starts a game timer
     */
    @Override
    public void run() {
        for (time = 180 ; time > 0 && !(endGame) ; time--){
            if (elixir.get() < 10) {
                elixir.set(elixir.get() + ((time>60)?1:2));
            }
            if (comElixir.get() < 10) {
                comElixir.set(comElixir.get() + ((time>60)?1:2));
            }
            timer.setText( (time/60) + ":" + ((time%60) > 9? (time%60):("0" + (time%60))));
            String str = "";
            for (int j = 0; j < elixir.get() ; j++){
                str += (j+1) + " ";
            }
            ElBar.setText(str);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        timeEnd = true;
        if (blue > red){
            setEndGame(true,"Blue");
        }
        else if (red > blue){
            setEndGame(true,"Red");
        }
        else {
            setEndGame(true,null);
        }
    }

    /**
     *
     * @return the game status
     */
    public boolean isEndGame() {
        return endGame;
    }

    /**
     * ends the game and announce winner
     * saves the info in file
     *
     * @param endGame the new status
     * @param Winner the winner of game
     */
    public synchronized void setEndGame(boolean endGame,String Winner) {
        if (!this.endGame) {
            this.endGame = endGame;
            User user = gameController.getUser();
            Stage current = gameController.getCurrent();

            if (Winner == null) {
                Winner = gameController.drawCase();
            }

            if (Winner.equals("Blue")) {
                blue = 3;
                JOptionPane.showMessageDialog(null, "You Won!");
                user.setPoints("" + (Integer.parseInt(user.getPoints()) + 200));
            } else {
                red = 3;
                JOptionPane.showMessageDialog(null, "You Lost!");
                user.setPoints("" + (Integer.parseInt(user.getPoints()) + 70));
            }
            File file = new File("./Saves/Games.xml");
            try (PrintWriter out = new PrintWriter(new FileOutputStream(file,true))) {
                User.print(user);
                if (!file.exists()){
                    file.createNewFile();
                }
                out.println("" + user.getUserName() + "  " + blue + "  " + red + " " + "COM");

            } catch (IOException e){
                e.printStackTrace();
            }
            GameMenu gameMenu = new GameMenu(current, user);
            Platform.runLater(gameMenu);
        }
    }

    /**
     *
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     *
     * @param blue the blue point
     */
    public void setBlue(int blue) {
        if (!endGame) {
            this.blue = blue;
        }
    }

    /**
     *
     * @param red the red point
     */
    public void setRed(int red) {
        if (!endGame) {
            this.red = red;
        }
    }

    /**
     *
     * @return the blue points
     */
    public int getBlue() {
        return blue;
    }

    /**
     *
     *
     * @return the red points
     */
    public int getRed() {
        return red;
    }
}
