import javax.swing.*;

import java.util.List;

/**
 * Created by thomas on 12/8/15.
 */
public class BeginGameUI extends JFrame{
    private JPanel rootPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JList playerList;
    private List<String> list;
    private JButton beginGameButton;
    private String gameId;

    Thread listener;

    private BeginGameController beginGameController;

    public BeginGameUI(ClientController controller, String gameId){
        super("Begin Game");
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        beginGameController = new BeginGameController(controller, this);
        this.gameId = gameId;

        // populate player list
        update();

        listener = new Listener(this);
        listener.start();
    }

    //
    // CALLBACK METHODS
    //
    public void update(){
        list = beginGameController.getPlayerList(gameId);
        String[] listData;
        if (list != null && list.size() > 0){
            listData = list.toArray(new String[0]);
        }
        else {
            listData = new String[]{"No players added"};
        }
        playerList.setListData(listData);

    }
}
