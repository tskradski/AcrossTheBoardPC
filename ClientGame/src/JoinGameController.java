import java.util.List;

/**
 * Created by thomas on 11/21/15.
 */
public class JoinGameController {

    ClientController clientController;

    public JoinGameController(ClientController clientController){
        this.clientController = clientController;
    }

    public List<String> getGameList(){
        return clientController.getPendingGames();
    }

    public boolean connectToGame(String gameId, String playerName){

        if (clientController.isGameActive(gameId)){
            clientController.sendJoinGameRequest(gameId, playerName);

            return true;
        }
        else {
            return false;
        }
    }
}
