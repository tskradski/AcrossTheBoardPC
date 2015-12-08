/**
 * Created by thomas on 12/7/15.
 */
public class RMIRegThread extends Thread {

    private int port;

    public RMIRegThread(int port){
        this.port = port;
    }

    @Override
    public void run(){
        try {
            java.rmi.registry.LocateRegistry.createRegistry(port);
            System.out.println("RMI registry ready.");
        } catch (Exception e) {
            System.out.println("Exception starting RMI registry:");
            e.printStackTrace();
        }

    }
}
