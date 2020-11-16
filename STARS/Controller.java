import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    
    public ArrayList<Module> ImportAllModules() {
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
        return CreateModuleList(list);
    }
    public ArrayList<Module> CreateModuleList(ArrayList<String> list){
        ArrayList<Module> fullmodulelist = new ArrayList<Module>();

        for (String i : list) {
            String[] splitdata = i.split(",");
            fullmodulelist.add(new Module(Integer.parseInt(splitdata[0]), splitdata[1], splitdata[2],
                    Integer.parseInt(splitdata[3]), splitdata[4], splitdata[5], splitdata[6], splitdata[7],
                    Integer.parseInt(splitdata[8])));
        }
        //Testing Purpose
        for (Module x : fullmodulelist) {
            System.out.println(x.coursename);
        }
        return fullmodulelist;
    }
}