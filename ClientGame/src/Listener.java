import Commands.AddPlayer;
import Commands.Command;
import Commands.StartGame;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by thomas on 12/7/15.
 */
public class Listener extends Thread {

    ClientController callback;
    BeginGameUI beginGameUI;
    ServerSocket serverSocket;
    ObjectInputStream input;

    public Listener(ClientController controller){
        this.callback = controller;
        try {
            serverSocket = new ServerSocket(Globals.CLIENT_PORT);
        } catch (Exception e){
            LOGGER.error("Error creating client's server socket "+e);
        }
    }

    public Listener(BeginGameUI beginGameUI){
        this.beginGameUI = beginGameUI;
        try {
            serverSocket = new ServerSocket(Globals.CLIENT_PORT);
        } catch (Exception e){
            LOGGER.error("Error creating client's server socket "+e);
        }
    }

    @Override
    public void run(){
        while (true){
            try {
                Socket socket = serverSocket.accept();
                input = new ObjectInputStream(socket.getInputStream());
                Object o = input.readObject();
                Command command = (Command) o;
                if (command instanceof StartGame){
                    // start game
                } else if (command instanceof AddPlayer){
                    AddPlayer addPlayer = (AddPlayer)command;
                    beginGameUI.update();
                    LOGGER.echo("Client recieved AddPlayer Command, playerName: " + addPlayer.playerName);
                }
            } catch (Exception e){
                LOGGER.error("Error accepting server socket on client: "+e);
            }
        }
    }
}
