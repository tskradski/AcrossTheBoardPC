import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 11/18/15.
 */
public class ServerUI extends JFrame {

    GameServer gameServer;

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

        gameServer = new GameServer(this);

        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);

        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameServer.startServer()){
                    startServerButton.setEnabled(false);
                    hostAddressTextField.setText(gameServer.getHostAddress());
                    portTextField.setText(gameServer.getPort()+"");
                    stopServerButton.setEnabled(true);
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
                    gameServer.stopServer();
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
        pendingGameTextArea.setText("");
        for (PendingGame game : gameServer.getPendingGames()){
            pendingGameTextArea.append(game.getGameId()+"\n");
        }

        activeGamesTextArea.setText("");
        for (ActiveGame game : gameServer.getActiveGames()){
            // TODO: add functionally after active game is impl
            activeGamesTextArea.append(game.getClass().toString());
        }
    }

}
