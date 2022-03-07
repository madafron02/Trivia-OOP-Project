package commons;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int timer;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Question question;

    @OneToMany
    //private List<LeaderBoardEntry> leaderBoardEntries;
    private List<Player> players;

    public Round(Question question, List<Player> players) {
        this.timer = 10;          //time in seconds, can be changed if it is decided to use number other than 10
        this.question = question;
        this.players = players;
    }

    public int getTimer() {
        return this.timer;
    }
    public void setTimer(int newTime) {
        this.timer = newTime;
    }
    public Question getQuestion() {
        return this.question;
    }
    public void setQuestion(Question newQuestion) {
        this.question = newQuestion;
    }
    public long getId() {
        return id;
    }
    public List<Player> getPlayers() {
        return this.players;
    }
    public void setPlayers(List<Player> newPlayers) {
        this.players = newPlayers;
    }

    public String toString() {
        String answer = "This round:\n " + "The question: " + this.question.getDescription() +
                "\n" + "The players: ";
        for (Player p : players) {
            answer += p.getName() + " : ";
            //answer += p.getPoint() + "\n";
        }
        return answer;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }





}
