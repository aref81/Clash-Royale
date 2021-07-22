package sample.GameControllers;

import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicInteger;

public class GameTime implements Runnable{
    private int time;
    private boolean endGame;
    private Text ElBar;
    private Text timer;
    private AtomicInteger elixir;


    public GameTime(Text elBar, Text timer, AtomicInteger elixir) {
        endGame = false;
        ElBar = elBar;
        this.timer = timer;
        this.elixir = elixir;
    }

    @Override
    public void run() {
        for (time = 180 ; time > 0 && !(endGame) ; time--){
            if (elixir.get() < 10) {
                elixir.set(elixir.get() + ((time>60)?1:2));
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
        endGame = true;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public int getTime() {
        return time;
    }
}
