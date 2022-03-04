package client.scenes;


import java.util.Objects;

public class ActivityCtrl {

    private  int id;
    private String description;

    public ActivityCtrl(int id,String description) {
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
        if (!(o instanceof ActivityCtrl)) return false;
        ActivityCtrl that = (ActivityCtrl) o;
        return id == that.id && Objects.equals(description, that.description);
    }

}
