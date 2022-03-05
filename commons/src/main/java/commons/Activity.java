package commons;


import java.util.Objects;

public class Activity {

    private  int id;
    private String description;

    public Activity(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChoices() {
        return description;
    }

    public void setChoices(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity that = (Activity) o;
        return id == that.id && Objects.equals(description, that.description);
    }

}
