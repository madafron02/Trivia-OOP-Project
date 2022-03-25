package commons;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Question implements Reachable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    private String description;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> answers;

    public Question() {
    }

    public Question(String description, List<String> answers) {
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

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
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
        return id == question.id && Objects.equals(description, question.description)
                && Objects.equals(answers, question.answers);
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