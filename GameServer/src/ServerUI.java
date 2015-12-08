import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Created by thomas on 11/18/15.
 */
public class ServerUI extends JFrame {

    ServerControllerImpl serverControllerImpl;

    private JPanel rootPanel;
    private JPanel centerPanel;
    private JButton startServerButton;
    private JButton stopServerButton;
    private JPanel eastPanel;
    private JPanel westPanel;
    private JTextArea activeGamesTextArea;
    private JTextArea pendingGameTextArea;
    private JTextField hostAddressTextField;
    private JTextField portTextField;

    public ServerUI(){
        super("Across the Board Server Console");

        try {
            serverControllerImpl = new ServerControllerImpl(this);
        } catch (RemoteException e){
            LOGGER.error("Unable to start server: "+e);
        }

        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);

        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(serverControllerImpl.startServer()){
                    startServerButton.setEnabled(false);
                    hostAddressTextField.setText(serverControllerImpl.getHostAddress());
                    portTextField.setText(Globals.RMI_PORT+"");
                    stopServerButton.setEnabled(true);

                    // add serverController to RMI Registry
                    try {
                        Naming.rebind("rmi://localhost:"+Globals.RMI_PORT+"/Server", serverControllerImpl);
                    } catch (Exception ex){
                        LOGGER.error("Unable to add serverControllerImpl to registry: "+ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(rootPanel, "Server unable to start. Please check console for details.");
                }
            }
        });
        stopServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(rootPanel, "Stopping the server will disconnect all games, active and pending.\n" +
                        "Are you sure?", "Alert", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION){
                    serverControllerImpl.stopServer();
                    hostAddressTextField.setText("");
                    portTextField.setText("");
                    startServerButton.setEnabled(true);
                    stopServerButton.setEnabled(false);

                }
            }
        });
    }

    public static void main (String args[]){
        new ServerUI();
    }

    public void update(){
        try {
            pendingGameTextArea.setText("");
            for (String game : serverControllerImpl.getPendingGames()) {
                pendingGameTextArea.append(game + "\n");
            }

            activeGamesTextArea.setText("");
            for (ActiveGame game : serverControllerImpl.getActiveGames()) {
                // TODO: add functionally after active game is impl
                activeGamesTextArea.append(game.getClass().toString());
            }
        } catch (RemoteException re){
            LOGGER.error(re.getMessage());
        }
    }

}
