import javax.swing.*;

/**
 * Created by thomas on 11/21/15.
 */
public class LOGGER {

    public static void error(String s) {
        System.err.println(s);
    }

    public static void echo(String s){
        System.out.println(s);
    }

    public static void display(String s){
        JOptionPane.showMessageDialog(null, s);
    }
}
