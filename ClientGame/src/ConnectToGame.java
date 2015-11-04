import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by thomas on 11/2/15.
 */
public class ConnectToGame {

    public static void main (String args[]){

        new ConnectToGame();
    }

    public ConnectToGame(){

    }

    public void hostGame(){

        try {
            InetAddress hostAddress = InetAddress.getLocalHost();
            System.out.println("Please give the following address to players who wish to join: "+hostAddress.getHostName());

        }
        catch (UnknownHostException e){
            System.err.println("Unknown Host: "+e);
        }
    }

    public void joinGame(){
        boolean isBadAddress = true;

        while (isBadAddress) {
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter address of hosting player: ");
            String s = input.nextLine();
            try {
                InetAddress hostName = InetAddress.getByName(s);
                System.out.println("Connection to host made");
            } catch (UnknownHostException e) {
                System.err.println("Unknown host or unable to connect, please try again or enter x to exit");
            }
        }
    }
}
