import java.net.Socket;

/**
 * Created by thomas on 11/19/15.
 */
public class PlayerConnection {

    Socket socket;
    Player player;

    public PlayerConnection(Player player, Socket socket){
        this.player = player;
        this.socket = socket;
    }

    public Player getPlayer() {
        return player;
    }

    public Socket getSocket() {
        return socket;
    }


}
