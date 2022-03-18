package server;

import commons.Activities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ActivityParse {

    public ActivityParse()  {
    }

    public ArrayList<Activities> fileReader() throws FileNotFoundException {
        File file = new File("C:\\Users\\razer\\Desktop\\OOPPP\\" +
                "repository-template\\activities\\activities.json");
        ArrayList<Activities> activities = new ArrayList<>();
        String s = "";
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            if(scanner.nextLine().equals("    {")){
             String id = scanner.nextLine();
             id = id.replace(",", "");
             id = id.replaceAll("\"", "");
             id = id.substring(12);
             String imagePath = scanner.nextLine();
             imagePath = imagePath.replace(",", "");
             imagePath = imagePath.replaceAll("\"", "");
             imagePath = imagePath.substring(20);
             String title = scanner.nextLine();
             title = title.replace(",", "");
             title = title.replaceAll("\"", "");
             title = title.substring(15);
             String cons = scanner.nextLine();
             cons = cons.replaceAll("\"", "");
             cons = cons.replace("        consumption_in_wh: ", "");
             cons = cons.replace(",", "");
             Long consumption = Long.parseLong(cons);
             String source = scanner.nextLine();
             source = source.replaceAll("\"", "");
             source = source.replace(",", "");
             source = source.substring(16);
             scanner.nextLine();
             Activities activity = new Activities(id,imagePath,title,consumption,source);
             activities.add(activity);
            }
        }
        return activities;
    }
}
