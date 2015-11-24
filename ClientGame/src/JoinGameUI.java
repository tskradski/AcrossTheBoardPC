import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by thomas on 11/4/15.
 */
public class JoinGameUI extends JFrame{

    ClientService clientService;
    JoinGameController joinGameController;
    private JPanel rootPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JList<String> gamelist;
    private JButton joinGameButton;
    private List<String> list;

    public JoinGameUI(ClientService clientService){
        super("Join an existing game");

        this.clientService = clientService;
        joinGameController = new JoinGameController(clientService);

        // populate game list
        clientService.sendJoinGameRequest();
        list = joinGameController.getGameList();
        String[] listData;
        if (list.size() > 0 && list != null){
            listData = list.toArray(new String[0]);
        }
        else {
            listData = new String[]{"No available games"};
        }
        gamelist.setListData(listData);

        LOGGER.echo(list.toString());

        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400 , 400);
        //pack();
        setVisible(true);

        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinGameButton_OnClick();
            }
        });
    }

    public void joinGameButton_OnClick(){
        LOGGER.echo("JoinGameUI.joinGameButton started");
        String selection;
        if ((selection = gamelist.getSelectedValue()) != null){
            joinGameController.connectToGame(selection);
        }
    }
}
