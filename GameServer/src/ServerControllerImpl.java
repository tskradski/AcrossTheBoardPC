import Commands.AddPlayer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * This class functions as the game hub for across the board.  It has several functions.
 *
 * I. Serves as the hub for the game, a meeting place for the players.
 *      a. Server waits to individual players to create a new game.
 *      b. A new game request spins a thread off to run game.  This allows multiple concurrently run games.
 *      c. The client that requests the game starts game when he is ready.
 *      d. The server listens for join requests and collects player data from clients as they join.
 *      e. Every change to state must be send to each client. For example, as players join, the sever will send
 *          state change data to each client that has already joined.
 *      f. Clients will display a list of all new games started.  When that game is joined, they will be updated with
 *          all new players joining.
 *
 * I. Runs the auction phase of the game.
 *      a. Spins off a thread for each player in the game listening for bids.
 *      b. When the client sends the message to bid. lock the state so the thread can update horse price safely.
 *      c. Update the high bid on horse send updates to all clients and unlock the game state.
 *      d. The server runs the auction clock and resets the clock to 10 seconds after every bid.
 *      e. The server will alert each client that time is about to expire.
 *          - possible issues...elapsed time between when the server starts the 10 second clock and when the client
 *              receives the state change info.  Network issues could cause a delay resulting in 10 seconds elapsing
 *              before client can be notified of bidding on a horse ending.
 *              TODO: investigate possible solutions.  Might need to leave potential problem due to time restraints
 *
 * Created by thomas on 11/7/15.
 */
public class ServerControllerImpl extends UnicastRemoteObject implements ServerController{

    List<PendingGame> pendingGames;
    List<ActiveGame> activeGames;
    ServerUI callback;
    private InetAddress hostAddress;
    private Thread broadcastCommand;

    public ServerControllerImpl(ServerUI callback) throws RemoteException{
        this.callback = callback;
        pendingGames = new ArrayList<>();
        activeGames = new ArrayList<>();

        try {
            hostAddress = InetAddress.getLocalHost();
        } catch (IOException e){
           LOGGER.error("Unable to get InetAddress: " + e);
        }

    }

    // getters and setters
    public String getHostAddress() {
        return hostAddress.getHostAddress();
    }
    public List<ActiveGame> getActiveGames() {
        return activeGames;
    }

    /**

     * Starts the server for process game request. Returns true if successful and false if unsuccessful.
     *
     * @return
     */
    public boolean startServer(){
        try{
            // start RMI registry
            Thread t = new RMIRegThread(Globals.RMI_PORT);
            t.start();

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Stops the server.  Successful stop closes the ServerSocket, empties the game lists and returns true.  If it
     * fails it returns false;
     *
     * @return
     */
    public boolean stopServer(){
        try {
            pendingGames.clear();
            activeGames.clear();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public String addNewGame(Player player) throws RemoteException{
        PendingGame pg = new PendingGame(player);
        pendingGames.add(pg);
        callback.update();
        return pg.getGameId();
    }

    @Override
    public boolean addPlayerToGame(String id, Player player)throws RemoteException{
        for (PendingGame game: pendingGames){
            if (game.getGameId().equals(id)){

                // notify creator of game that a new player has joined.
                Player creator = game.getPlayerList().get(0);
                try {
                    Socket socket = new Socket(creator.getClientAddress(), Globals.CLIENT_PORT);
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(new AddPlayer(player.getName()));
                    output.flush();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                game.addPlayerToGame(player);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isGameActive(String id) throws RemoteException {
        for (String gameId: getPendingGames()){
            if (gameId.equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getPendingGames()throws RemoteException {
        List<String> pendingGamesList = new ArrayList<>();
        for (PendingGame game: pendingGames){
            pendingGamesList.add(game.getGameId());
        }
        return pendingGamesList;
    }

    @Override
    public void startPendingGame(String id) throws RemoteException{
        // TODO: change pending to active


    }

    @Override
    public List<String> getPlayerListByGameId(String id) throws RemoteException {
        List<String> playerList = new ArrayList<>();
        for(PendingGame game : pendingGames){
            if (game.getGameId().equals(id)){
                playerList = game.getPlayerListByName();
            }
        }
        return playerList;
    }


}

