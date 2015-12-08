import java.net.Socket;

/**
 * Created by thomas on 12/7/15.
 */
public class Listener extends Thread {

    ClientController callback;
    Socket socket;

    public Listener(ClientController controller){
        this.callback = controller;


    }

    @Override
    public void run(){

    }
}
