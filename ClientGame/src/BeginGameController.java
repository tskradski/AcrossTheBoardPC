import java.util.List;

/**
 * Created by thomas on 12/8/15.
 */
public class BeginGameController {
    ClientController clientController;
    BeginGameUI callback;

    public BeginGameController(ClientController controller, BeginGameUI callback){
        this.clientController = controller;
        this.callback = callback;
    }

    public List<String> getPlayerList(String gameId){
        return clientController.getPlayerList(gameId);
    }
}
