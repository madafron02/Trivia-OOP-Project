package server.services;

import commons.Activity;
import server.database.ActivityRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class QuestionAnswerSelector {

    private List<Activity> answers;
    private Random random;


    public QuestionAnswerSelector(){
        random = new Random();
    }

    /**
     * TODO check if there are enough activities of that power level
     *
     * Randomly chooses a Power level to choose from the activities
     * and chooses 2 different activities of that Power level
     * assigns those 2 activities to answerA and answers
     */
    public void setAnswers(ActivityRepository repo){
        switch(random.nextInt(4)+1){
            case 1:{
                answers = repo.findAll().stream()
                        .filter(x -> x.getPowerLevel().equals("low"))
                        .collect(Collectors.toList());
                break;
            }
            case 2:{
                answers = repo.findAll().stream()
                        .filter(x -> x.getPowerLevel().equals("mid"))
                        .collect(Collectors.toList());
                break;
            }
            case 3:{
                answers = repo.findAll().stream()
                        .filter(x -> x.getPowerLevel().equals("high"))
                        .collect(Collectors.toList());
                break;
            }
            case 4:{
                answers = repo.findAll().stream()
                        .filter(x -> x.getPowerLevel().equals("deyum"))
                        .collect(Collectors.toList());
                break;
            }
            default:{
                answers = repo.findAll();
            }
            int answA = random.nextInt(answers.size());
            int answB = random.nextInt(answers.size());
            if(answA == answB){
                while (answA == answB){
                    answB = random.nextInt(answers.size());
                }
            }
            answers = List.of(answers.get(answA), answers.get(answB));
        }

    }

    /**
     * TODO add a checker if the answers are empty
     * @return
     */
    public List<Activity> getAnswers() {
        return answers;
    }
}
