import javax.swing.*;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 11/3/15.
 */
public class Player implements Serializable{

    private static int numPlayers = 0;

    private List<Horse> horses;
    private int money;
    private String name;
    InetAddress clientAddress;

    public List<Horse> getHorses() {
        return horses;
    }

    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(InetAddress clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Player()
    {
        numPlayers++;
        horses = new ArrayList<Horse>();
        money = 0;
        name = "Default player"+numPlayers;
    }

    public Player(String name){
        this.name = name;
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
