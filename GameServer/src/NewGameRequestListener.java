import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 *
 * Created by thomas on 11/18/15.
 */
public class NewGameRequestListener extends Thread {

    ServerSocket serverSocket;
    Socket socket;

    public NewGameRequestListener(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                socket = serverSocket.accept();

                Thread t = new PendingGame(socket);
                t.start();

            } catch (IOException e) {

            }
        }
    }
}