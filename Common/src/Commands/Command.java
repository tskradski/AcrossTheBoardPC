package Commands;

/**
 * Created by thomas on 11/3/15.
 */
public interface Command {

    static final String START_GAME = "Commands.StartGame";

    public void execute();
    public void undo();

}
