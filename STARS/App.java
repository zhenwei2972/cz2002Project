import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * This is the root of the program/ the start of program
 * Launching the login interface.
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public class App {
    /**
     * This is the start of the program
     * @param args empty
     * @throws IOException Exception load error
     * @throws ParseException Exception casting parsing error
     * @throws FileNotFoundException Exception of data file not found
     */
    public static void main(String args[]) throws IOException, ParseException, FileNotFoundException {
        System.out.println("Welcome to STARS Planner!");
        LoginInterface app = new LoginInterface();
        app.StartupInterface();
    }
}
