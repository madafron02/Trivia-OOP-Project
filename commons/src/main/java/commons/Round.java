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

    private double timer;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Question question;

    @OneToMany
    private List<LeaderBoardEntry> leaderBoardEntries;

    public Round(Question question, List<LeaderBoardEntry> leaderBoardEntries) {
        this.timer = 10;
        //time in seconds, can be changed if it is decided to use number other than 10
        this.question = question;
        this.leaderBoardEntries = leaderBoardEntries;
    }

    public double getTimer() {
        return this.timer;
    }
    public void setTimer(double newTime) {
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
    public List<LeaderBoardEntry> getLeaderBoardEntries() {
        return this.leaderBoardEntries;
    }
    public void setLeaderBoardEntries(List<LeaderBoardEntry> newEntries) {
        this.leaderBoardEntries = newEntries;
    }

    public String toString() {
        String answer = "This round:\n " + "The question: " + this.question.getDescription() +
                "\n" + "The players: ";
        for (LeaderBoardEntry entry : leaderBoardEntries) {
            answer += entry.getPlayer().getName() + " : ";
            answer += entry.getPoint() + "\n";
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
