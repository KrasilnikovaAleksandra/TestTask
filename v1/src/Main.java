import controller.Controller;
import view.View;

/**
 * It is the start class of the program.
 */
public class Main {
    /**
     * It is the start method.
     * @param args
     * @throws Exception
     */

    public static void main (String [] args) throws Exception {
        Controller controller = new Controller();
        View view = new View(controller);
        view.count();
    }
}
