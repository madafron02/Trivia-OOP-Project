package commons;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private double consumption;
    private String source;

    public Activity() {
    }

    public Activity(String title, double consumption, String source) {
        this.title = title;
        this.consumption = consumption;
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getConsumption() {
        return consumption;
    }

    public String getSource() {
        return source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return Double.compare(activity.getConsumption(), getConsumption()) == 0
                && getTitle().equals(activity.getTitle())
                && getSource().equals(activity.getSource());
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity that = (Activity) o;
        return id == that.id && Objects.equals(description, that.description);
    }*/

}
