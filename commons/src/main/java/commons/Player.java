package commons;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

import java.io.Serializable;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

@Entity
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int points;
    private long gameId;

    public Player() {
    }

    public Player(String name, long gameId) {
        this.name = name;
        this.points = 0;
        this.gameId = gameId;
    }

    public void setPoints(int addition){
        this.points+=addition;
    }

    public int getPoints() {
        return points;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getGameId() {
        return gameId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public void setPoints(int points) {
        this.points = points;
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
        return ToStringBuilder.reflectionToString(this, MULTI_LINE_STYLE);
    }

}
