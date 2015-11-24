import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 11/4/15.
 */
public class StartNewGameUI extends JFrame{

    ClientService clientService;

    private JPanel rootPanel;
    private JLabel label1;
    private JTextField playerNameTextField;
    private JButton startNewGameButton;

    public StartNewGameUI(ClientService clientService){
        super("Host a Game");

        this.clientService = clientService;
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
                   clientService.sendNewGameRequest(playerNameTextField.getText());
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
