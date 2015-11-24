import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 11/18/15.
 */
public class PendingGame extends Thread {

    public static int id = 1;
    private GameServer callback;
    List<PlayerConnection> playerList;      // playerList(0) is the host socket
    private String gameId;

    public String getGameId() {
        return gameId;
    }

    public List<PlayerConnection> getPlayerList() {
        return playerList;
    }

    public PendingGame(GameServer callback, Socket socket, Player player) {
        playerList = new ArrayList<>(6);

        playerList.add(new PlayerConnection(player, socket));
        gameId = player.getName() + id;
        id++;
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            callback.addPendingGame(this);

            PrintWriter output = new PrintWriter(playerList.get(0).getSocket().getOutputStream());
            output.write("received");
            output.flush();
        } catch (IOException e) {
            System.err.println("Error reading info from socket on game ID: " + gameId + ": " + e);
        }
    }

    public void addPlayerToGame(Player player, Socket socket) {
        playerList.add(new PlayerConnection(player, socket));
        LOGGER.echo("New player added to list");
    }
}
