/**
 * Created by thomas on 11/2/15.
 */
public class StartClient {

    public static void main (String args[]){

        new StartClient(args[0]);
    }

    public StartClient(String serverAddress){
        new ClientUI(serverAddress);
    }

}
