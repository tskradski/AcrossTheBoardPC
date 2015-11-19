import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 11/18/15.
 */
public class PendingGame extends Thread{

    long gameId;
    String gameName;

    // playerSockets(0) is the host socket
    List<Socket> playerSockets;

    public PendingGame(Socket socket){
        playerSockets = new ArrayList<>(6);
        playerSockets.add(socket);
        this.gameId = this.getId();
    }

    @Override
    public void run() {
        try {
            // get name of game from socket
            BufferedReader input = new BufferedReader(new InputStreamReader(playerSockets.get(0).getInputStream()));
            gameName = input.readLine();

            PrintWriter output = new PrintWriter(playerSockets.get(0).getOutputStream());
            output.write("received");
            output.flush();
        } catch (IOException e){
            System.err.println("Error reading info from socket on game ID: "+gameId+": "+e);
        }
    }

    public void stopPendingGame(){

    }
}
