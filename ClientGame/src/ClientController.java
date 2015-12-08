import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by thomas on 11/19/15.
 */
public class ClientController {

    Player player;
    ServerController server; //rmi server
    boolean waitingOnGameStart = false;
    Thread listener;
    private int port;

    public ClientController(){

    }

    public boolean connectToServer(String address){
        try {
            Globals.serverAddress = address;
            Object o = Naming.lookup("rmi://"+address+":"+Globals.RMI_PORT+"/Server");
            server = (ServerController)o;
            LOGGER.echo("Connected to server on "+address+":"+Globals.RMI_PORT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendNewGameRequest(String playerName){
        player = new Player(playerName);
        try {
            port = server.addNewGame(player);
            LOGGER.echo("New game started");
        } catch (Exception e){LOGGER.display("New game request failed: "+e);}
    }

    public void sendJoinGameRequest(String gameId, String playerName){
        player = new Player(playerName);
        try {
            listener = new Listener(this);
            listener.start();

            server.addPlayerToGame(gameId, player);
            waitingOnGameStart = true;

            LOGGER.echo("New game started");
        } catch (Exception e){LOGGER.display("Add player to  game request failed: "+e);}
    }

    public List<String> getPendingGames(){
        try{
            return server.getPendingGames();
        } catch (RemoteException re){
            LOGGER.error("Unable to get pending game list from server: "+re);
            return null;
        }
    }

    public boolean isGameActive(String gameId){
        try {
            return server.isGameActive(gameId);
        } catch (Exception e){
            LOGGER.error("Error retrieving game status "+e.getMessage());
            return false;
        }
    }

    // ************************
    //
    // Callback methods
    //
    // ************************



}
