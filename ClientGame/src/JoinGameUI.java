import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by thomas on 11/4/15.
 */
public class JoinGameUI extends JFrame{

    JoinGameController joinGameController;
    private JPanel rootPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JList<String> gamelist;
    private JButton joinGameButton;
    private List<String> list;

    public JoinGameUI(ClientController clientController){
        super("Join an existing game");
        joinGameController = new JoinGameController(clientController);

        // populate game list
        list = joinGameController.getGameList();
        String[] listData;
        if (list.size() > 0 && list != null){
            listData = list.toArray(new String[0]);
        }
        else {
            listData = new String[]{"No available games"};
        }
        gamelist.setListData(listData);

        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400 , 400);
        setVisible(true);

        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinGameButton_OnClick();
            }
        });
    }

    public void joinGameButton_OnClick(){
        String selection;
        String playerName = JOptionPane.showInputDialog("Please enter player name: ");
        if ((selection = gamelist.getSelectedValue()) != null){
            joinGameController.connectToGame(selection, playerName);
        }
    }
}
