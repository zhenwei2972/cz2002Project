import java.io.IOException;

public class App {
    public static void main(String args[]) throws IOException {
        System.out.println("Welcome to STARS Planner!");
        LoginInterface app = new LoginInterface();
        app.StartupInterface();
    }
}
