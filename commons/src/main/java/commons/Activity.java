package commons;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "activity")

public class Activity implements Reachable{

    /**
     * Activity attributes declaration with auto generated primary key
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String activityName;
    private String imgPath;
    private String title;
    private Long consumption;


    private String powerLevel; //between 1-3 based on consumption


    /**
     * Empty constructor
     */

    public Activity() {
    }

    /**
     * Constructor for Activity class
     * @param activityName represents the name of the activity
     * @param imgPath represents the path for the image file
     * @param title represents the title of the question
     * @param consumption represents the energy consumption of the Activity
     */

    public Activity(String activityName, String imgPath, String title, Long consumption) {
        this.activityName = activityName;
        this.imgPath = imgPath;
        this.title = title;
        this.consumption = consumption;
        setPowerLevel();
    }


    /**
     * A method that sets the power level of the Activity
     */


    public void setPowerLevel(){
        if(consumption<1000) powerLevel = "low";
        else if(consumption<7500) powerLevel = "mid";
        else if(consumption<=15000) powerLevel = "high";
        else powerLevel = "deyum";
    }



    /**
     * Getter for image path
     * @return the image path
     */

    public String getImgPath() {
        return imgPath;
    }

    /**
     * Setter for the image path
     * @param imgPath represents the path of the image file
     */

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * Getter for the title
     * @return the title of the activity
     */

    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title
     * @param title of the activity
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for the consumption
     * @return consumption of the activity
     */

    public double getConsumption() {
        return consumption;
    }

    /**
     * Setter for the consumption
     * @param consumption consumption of the activity
     */

    public void setConsumption(Long consumption) {
        this.consumption = consumption;
    }

    /**
     * Getter for the power level
     * @return power lovely based on consumption
     */

    public String getPowerLevel() {
        return powerLevel;
    }

    /**
     * Setter for the id
     * @param id id of the activity
     */

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for the activity
     * @return the name of the activity
     */

    public String getActivityName() {
        return activityName;
    }

    /**
     * Setter for the name of the activity
     * @param activityName name of the activity
     */

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * Equals for the Activity function
     * Determines if two Activity objects are equal
     * @param o represent an activity object
     * @return true id the object are the same type or false otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id) && Objects.equals(imgPath, activity.imgPath) &&
                Objects.equals(title, activity.title) &&
                Objects.equals(consumption, activity.consumption) ;
    }

    /**
     * Hashes the id, imgPath, title and consumption
     * @return the attributes hashed
     */

    @Override
    public int hashCode() {
        return Objects.hash(id, imgPath, title, consumption);
    }

    /**
     * Makes the activity attributes in a human friendly format
     * @return a string containing the information about an activity in a human friendly format
     */

    @Override
    public String toString() {
        return
                "id= " + id + "\n" +
                        "imgPath= " + imgPath + "\n" +
                        "title= " + title + "\n" +
                        "consumption= " + consumption + "\n" +
                        "powerLevel=" + powerLevel + "\n";
    }

    @Override
    public long getId() {
        return id;
    }
}
