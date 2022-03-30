package server;
import commons.Activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ActivityParse {

    /**
     * ActivityParse empty constructor
     */

    public ActivityParse()  {
    }

    /**
     * A parser to parse the Activity from Json file in a human friendly format
     * @return the Activity parsed in a way that is more readable
     * @throws FileNotFoundException if the file is not found for the scanner
     */

    public ArrayList<Activity> getActivities() throws FileNotFoundException {
        //Here you have to insert your local path to the json file
        File file = new File("/Users/a123/Downloads/activities/activities.json");
        //Creating an ArrayList of activities
        ArrayList<Activity> activities = new ArrayList<>();
        //Creating the scanner for reading the information in the file
        String s = "";
        Scanner scanner = new Scanner(file);
        //Looping through every line in the file to parse the information
        while (scanner.hasNextLine()){
            if(scanner.nextLine().equals("    {")){
             //Grabbing the id attribute
             String id = scanner.nextLine();
             id = id.replace(",", "");
             id = id.replaceAll("\"", "");
             id = id.substring(12);
             //Grabbing the imagePath attribute
             String imagePath = scanner.nextLine();
             imagePath = imagePath.replace(",", "");
             imagePath = imagePath.replaceAll("\"", "");
             imagePath = imagePath.substring(20);
             //Grabbing the title attribute
             String title = scanner.nextLine();
             title = title.replace(",", "");
             title = title.replaceAll("\"", "");
             title = title.substring(15);
             //Grabbing the consumption attribute
             String cons = scanner.nextLine();
             cons = cons.replaceAll("\"", "");
             cons = cons.replace("        consumption_in_wh: ", "");
             cons = cons.replace(",", "");
             Long consumption = Long.parseLong(cons);
             //Grabbing the source attribute
             String source = scanner.nextLine();
             source = source.replaceAll("\"", "");
             source = source.replace(",", "");
             source = source.substring(16);
             scanner.nextLine();
             //Creating new Activity object with the attributes parsed from Json
             Activity activity = new Activity(id,imagePath,title,consumption);
             activities.add(activity);
            }
        }
        return activities;
    }
}

