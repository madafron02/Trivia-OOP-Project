package commons;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question implements Reachable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    private String description;
    private String descriptionImagePath;
    private String correctAnswer;
    @Column
    @ElementCollection(targetClass=String.class)
    private List<String>imgPaths;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> answers;
    private QuestionType type;
    public enum QuestionType {
        MORE_ENERGY,
        ENERGY_GUESS,
        COMPARISON,
        OPEN
    }

    public Question(String description, List<String> answers) {
        this.description = description;
        this.answers = answers;
    }

    public Question() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionImagePath() {
        return descriptionImagePath;
    }

    public void setDescriptionImagePath(String descriptionImagePath) {
        this.descriptionImagePath = descriptionImagePath;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(List<String> imgPaths) {
        this.imgPaths = imgPaths;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        return new EqualsBuilder().append(id, question.id)
                .append(description, question.description)
                .append(descriptionImagePath, question.descriptionImagePath)
                .append(correctAnswer, question.correctAnswer)
                .append(imgPaths, question.imgPaths).append(answers, question.answers)
                .append(type, question.type).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id)
                .append(description).append(descriptionImagePath)
                .append(correctAnswer).append(imgPaths).append(answers).append(type).toHashCode();
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", descriptionImagePath='" + descriptionImagePath + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", imgPaths=" + imgPaths +
                ", answers=" + answers +
                ", type=" + type +
                '}';
    }
}
