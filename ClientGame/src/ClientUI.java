import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 11/4/15.
 */
public class ClientUI extends JFrame{

     ClientService clientService;

    private JPanel rootPanel;
    private JPanel eastPanel;
    private JPanel westPanel;
    private JPanel southPanel;
    private JLabel titleLabel1;
    private JLabel titleLabel3;
    private JLabel titleLabel2;
    private JButton joinGameButton;
    private JButton hostGameButton;
    private JTextField connectionToServerTextField;


    public ClientUI(String serverAddress){
        super("Set up Game");

        clientService = new ClientService();

        if (clientService.connectToServer(serverAddress)){
            connectionToServerTextField.setText("Connected to server");
        }
        else {
            connectionToServerTextField.setText("Unable to connect to server");
            joinGameButton.setEnabled(false);
            hostGameButton.setEnabled(false);
        }

        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        hostGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartNewGameUI(clientService);
            }
        });

        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JoinGameUI(clientService);
            }
        });
    }

}
