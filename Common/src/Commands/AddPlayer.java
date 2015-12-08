package Commands;

import java.io.Serializable;

/**
 * Created by thomas on 12/8/15.
 */
public class AddPlayer implements Command, Serializable {

    public String playerName;

    public AddPlayer(String playerName){
        this.playerName = playerName;
    }

    public String getPlayerName(){
        return playerName;
    }
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
