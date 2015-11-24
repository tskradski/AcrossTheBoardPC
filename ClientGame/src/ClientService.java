import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 11/19/15.
 */
public class ClientService {

    Player player;
    Socket socket;
    ObjectInputStream input;
    ObjectOutputStream output;

    public ClientService(){

    }

    public boolean connectToServer(String address){
        socket = null;
        for (int port = 3000; port < 4000; port++) {
            try {

                LOGGER.echo("Trying port: " + port);
                socket = new Socket(address, port);
                LOGGER.echo("Connected to server on port: " + port);
                LOGGER.echo("streams connected");
                return true;

            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    public void sendNewGameRequest(String playerName){
        player = new Player(playerName, socket.getInetAddress());
        LOGGER.echo(socket.getInetAddress().toString());

        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(player);
            output.flush();
            LOGGER.echo("New game started");
        } catch (Exception e){LOGGER.display("New game request failed");}
    }

    public void sendJoinGameRequest(){
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject("REQUEST_GAMES");
            output.flush();
        } catch (Exception e){
            LOGGER.error(e.getMessage());
        }
    }

    public List<String> getPendingGames(){
        List<String> list = new ArrayList<>();

        try {
            input = new ObjectInputStream(socket.getInputStream());
            ArrayList<String> gameIds = (ArrayList<String>)input.readObject();
            for (String gameId : gameIds) {
                list.add(gameId);
            }
        } catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return list;
    }

    public boolean isGameActive(String gameId){
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject("IS_GAME_ACTIVE_"+gameId);
            output.flush();

            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            if (((String)input.readObject()).equals("TRUE"))
            {
                return true;
            }
            else return false;
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    public boolean connectToGame(String gameId){
        try{
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject("CONNECT_TO_GAME_"+gameId);
            output.writeObject(player);
            output.flush();
        } catch(Exception e){
            LOGGER.error(e.getMessage());
        }
        return true;
    }


}
