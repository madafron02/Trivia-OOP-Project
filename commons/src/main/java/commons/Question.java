package commons;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private String description;
    private List<Activity> answers;

    public Question(String description, List<Activity> answers, long id) {
        this.description = description;
        this.answers = answers;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Activity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Activity> answers) {
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return id == question.id && Objects.equals(description, question.description) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, answers);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", answers=" + answers +
                '}';
    }
}