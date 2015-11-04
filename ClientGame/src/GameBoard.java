import java.util.Random;

/**
 * Created by thomas on 11/3/15.
 */
public class GameBoard {

    private int[] horsePos = new int[12];
    private String trackName;
    private Random rand = new Random();

    static GameBoard getGameBoard(){
        GameBoard board = new GameBoard();

        // TODO: get random racetrack based on random number

        return board;
    }

}
