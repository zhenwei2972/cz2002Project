import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    
    public ArrayList<String> ImportData() {
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("data.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}