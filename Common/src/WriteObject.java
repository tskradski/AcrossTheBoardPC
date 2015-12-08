import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by thomas on 11/23/15.
 */
public class WriteObject extends Thread {

    Socket socket;
    Object object;

    public WriteObject(Socket socket, Object object){
        this.socket = socket;
        this.object = object;

    }

    @Override
    public void run(){
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(object);
            output.flush();
        } catch (Exception e){

        }
    }


}
