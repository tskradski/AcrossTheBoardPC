/**
 * Created by thomas on 11/2/15.
 */
public class Globals {

    enum HORSE_TYPE {LONGSHOT, FIELDHORSE, FAVORITE}
    enum HORSE_STATUS {IN_HOCK, AVAILABLE}
    enum GAME_STATE {CONNECTING, BIDDING, PLAYING, FINISHED}
    enum NETWORK_STATUS {CLIENT, SERVER}
    public static final int GAME_SIZE = 2;
    public static final int RMI_PORT = 2999;
    public static final int CLIENT_PORT = 4001;
    public static String serverAddress = "";
}
