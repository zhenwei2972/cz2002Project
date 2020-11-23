import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class App {
    public static void main(String args[]) throws IOException, ParseException, FileNotFoundException {
        System.out.println("Welcome to STARS Planner!");
        LoginInterface app = new LoginInterface();
        app.StartupInterface();
    }
}
