package Classes;

import sample.GameControllers.GameController;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * implements the simple ai of gane
 * a weak opponent!
 *
 *
 *
 * @author Mohammad Hosein Aref
 * @version 1.0
 *
 */
public class SimpleAI extends AI{

    /**
     * initializes an ai
     *
     * @param elixir the elixir
     * @param gameController the game
     * @param mapStatus map status for red
     * @param map map of game
     */
    public SimpleAI(AtomicInteger elixir, GameController gameController, String[][] mapStatus, String[][] map) {
        super(elixir, gameController, mapStatus, map);
    }

    /**
     * ai actions handled
     *
     */
    @Override
    public void run() {
        Random random = new Random();

        while (!(gameController.getGameTime().isEndGame())){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int index = random.nextInt(4);
            int row = random.nextInt(16);
            int col = random.nextInt(18);
            insertCard(index,row,col);
        }
    }
}
