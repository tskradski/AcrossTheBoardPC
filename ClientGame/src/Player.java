import javax.swing.*;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 11/3/15.
 */
public class Player {

    private static int numPlayers = 0;

    private List<Horse> horses;
    private int money;
    private String name;
    private InetAddress clientAddress;

    public Player()
    {
        numPlayers++;
        horses = new ArrayList<Horse>();
        money = 0;
        name = "Default player"+numPlayers;
    }

    public Player(String name){
        horses = new ArrayList<Horse>();
        money = 0;
        try {
            clientAddress = InetAddress.getLocalHost();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error getting local address on player "+
            name +". + e");
        }
        numPlayers++;
    }

    public Player(String name, InetAddress clientAddress) {
        this.name = name;
        this.clientAddress = clientAddress;
        horses = new ArrayList<Horse>();
        money = 0;
        numPlayers++;
    }
}
