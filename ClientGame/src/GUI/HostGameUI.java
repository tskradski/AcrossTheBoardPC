package GUI;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by thomas on 11/4/15.
 */
public class HostGameUI extends JFrame{
    private JPanel rootPanel;
    private JLabel label1;
    private JTextField playerNameTextField;
    private JButton getHostButton;
    private JLabel hostAddress;

    public HostGameUI(){
        super("Host a Game");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getHostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               getHostAddress();
            }
        });
    }

    public void getHostAddress(){

        try {
            InetAddress address = InetAddress.getLocalHost();
            hostAddress.setText(address.getHostName());

        }
        catch (UnknownHostException e){
            hostAddress.setText("Error retrieving this host address: "+e);
        }
    }
}
