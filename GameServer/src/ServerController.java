import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by thomas on 12/7/15.
 */
public interface ServerController extends Remote{

    int addNewGame(Player player) throws RemoteException;

    boolean addPlayerToGame(String id, Player player) throws RemoteException;

    boolean isGameActive(String id) throws RemoteException;

    List<String> getPendingGames() throws RemoteException;
}
