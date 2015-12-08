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

    public String sendNewGameRequest(String playerName){
        String gameId = "";
        player = new Player(playerName);
        try {

            gameId = server.addNewGame(player);
            LOGGER.echo("New game started");
        } catch (Exception e){
            LOGGER.display("New game request failed: "+e);
        }
        return gameId;
    }

    public void sendJoinGameRequest(String gameId, String playerName){
        player = new Player(playerName);
        try {
            listener = new Listener(this);
            listener.start();
            waitingOnGameStart = true;

            server.addPlayerToGame(gameId, player);

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

    public List<String> getPlayerList(String gameId){
        try {
            return server.getPlayerListByGameId(gameId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ************************
    //
    // Callback methods
    //
    // ************************


}
