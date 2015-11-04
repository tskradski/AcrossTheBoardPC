package GUI;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 11/4/15.
 */
public class SetUpUI extends JFrame{
    private JPanel rootPanel;
    private JPanel eastPanel;
    private JPanel westPanel;
    private JPanel southPanel;
    private JTextField descriptionBarTextField;
    private JLabel titleLabel1;
    private JLabel titleLabel3;
    private JLabel titleLabel2;
    private JButton joinGameButton;
    private JButton hostGameButton;
    private JButton gameHubButton;

    public SetUpUI(){
        super("Set up Game");

        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        hostGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HostGameUI();
            }
        });

        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JoinGameUI();
            }
        });
    }
}
