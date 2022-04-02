package commons;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

import java.io.Serializable;


@Entity
public class Player implements Serializable,Reachable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int points;
    private int place;          //place in the leaderboard
    private boolean multi;

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public enum StatusType {
        READY,
        NOT_READY,
        ABORTED
    }
    private StatusType status;
    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.place = 0;
        this.multi = false;
        this.status = StatusType.NOT_READY;
    }

    public boolean isMulti() {
        return multi;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }


    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setPoints(int addition){
        this.points+=addition;
    }

    public int getPoints() {
        return points;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", place=" + place +
                ", multi=" + multi +
                ", status=" + status +
                '}';
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
