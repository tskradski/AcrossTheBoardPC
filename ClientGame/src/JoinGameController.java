import java.util.List;

/**
 * Created by thomas on 11/21/15.
 */
public class JoinGameController {

    ClientService clientService;

    public JoinGameController(ClientService clientService){
        this.clientService = clientService;
    }

    public List<String> getGameList(){
        return clientService.getPendingGames();
    }

    public boolean connectToGame(String gameId){

        if (clientService.isGameActive(gameId)){
            clientService.connectToGame(gameId);
            return true;
        }
        else {
            return false;
        }
    }
}
