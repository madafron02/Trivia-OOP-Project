package commons;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "activity")

public class Activity implements Reachable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String activityName;
    private String imgPath;
    private String title;
    private Long consumption;

    private String powerLevel; //between 1-3 based on consumption

    public Activity() {
    }

    public Activity(String activityName, String imgPath, String title, Long consumption) {
        this.activityName = activityName;
        this.imgPath = imgPath;
        this.title = title;
        this.consumption = consumption;
    }

    public void setPowerLevel(){
        if(consumption<1000) powerLevel = "low";
        else if(consumption<10000) powerLevel = "mid";
        else if(consumption<=40000) powerLevel = "high";
        else powerLevel = "deyum";
    }


    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(Long consumption) {
        this.consumption = consumption;
    }

    public String getPowerLevel() {
        return powerLevel;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id) && Objects.equals(imgPath, activity.imgPath) &&
                Objects.equals(title, activity.title) &&
                Objects.equals(consumption, activity.consumption) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imgPath, title, consumption);
    }

    @Override
    public String toString() {
        return
                "id= " + id + "\n" +
                        "imgPath= " + imgPath + "\n" +
                        "title= " + title + "\n" +
                        "consumption= " + consumption + "\n";
    }

    @Override
    public long getId() {
        return id;
    }
}
