import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 11/4/15.
 */
public class StartNewGameUI extends JFrame{

    ClientController clientController;

    private JPanel rootPanel;
    private JLabel label1;
    private JTextField playerNameTextField;
    private JButton startNewGameButton;

    public StartNewGameUI(ClientController clientController){
        super("Host a Game");

        this.clientController = clientController;
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        this.setVisible(true);

        startNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (playerNameTextField.getText() == null || playerNameTextField.getText().equals("")) {
                   JOptionPane.showMessageDialog(rootPane, "Invalid Player Name, try again");
                   playerNameTextField.setText("");
                   playerNameTextField.requestFocus();
               }
               else {
                   String gameid = clientController.sendNewGameRequest(playerNameTextField.getText());
                   EventQueue.invokeLater(new Runnable() {
                       @Override
                       public void run() {
                           new BeginGameUI(clientController, gameid);
                       }
                   });

                   setVisible(false);
                   dispose();

               }
            }
        });
        playerNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGameButton.setEnabled(true);
            }
        });
    }

}
