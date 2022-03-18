package commons;

public class Activities {

    private String id;
    private String imgPath;
    private String title;
    private Long consumption;
    private String source;

    public Activities(String id, String imgPath, String title, Long consumption, String source) {
        this.id = id;
        this.imgPath = imgPath;
        this.title = title;
        this.consumption = consumption;
        this.source = source;
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return
                "id= " + id + "\n" +
                "imgPath= " + imgPath + "\n" +
                "title= " + title + "\n" +
                "consumption= " + consumption + "\n" +
                "source= " + source + "\n";
    }
}

