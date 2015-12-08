import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 11/18/15.
 */
public class PendingGame{

    List<Player> playerList;      // playerList(0) is the host socket

    private String gameId;
    private static int id = 8765309;

    public PendingGame(Player player) {
        playerList = new ArrayList<>(6);
        gameId = player.getName();
        gameId += id+"";
        id++;
        LOGGER.echo("New pending game created with id "+ gameId);
    }

    public String getGameId() {
        return gameId;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void addPlayerToGame(Player player) {
        playerList.add(player);
        LOGGER.echo("Player: "+player.getName()+" added to game: "+gameId);
    }
}
