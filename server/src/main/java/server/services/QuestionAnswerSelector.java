package server.services;

import commons.Activity;
import org.springframework.stereotype.Service;
import server.database.ActivityRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestionAnswerSelector {

    private final HashMap<Long, List<Activity>> gameAnswers;
    private final Random random;
    private final ActivityRepository repo;


    public QuestionAnswerSelector(ActivityRepository repo){
        this.repo = repo;
        gameAnswers = new HashMap<>();
        random = new Random();
    }

    /**
     * TODO check if there are enough activities of that power level
     * TODO call this when round ends
     *
     * Randomly chooses a Power level to choose from the activities
     * and chooses 2 different activities of that Power level
     * assigns those 2 activities to answerA and answers
     */
    public void setAnswers(Long gameId){
        if(!gameAnswers.containsKey(gameId)) gameAnswers.put(gameId, new LinkedList<>());
        List<Activity> answers;
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
            gameAnswers.put(gameId, List.of(answers.get(answA), answers.get(answB)));
        }

    }

    /**
     * TODO add a checker if the answers are empty
     * @return
     */
    public List<Activity> getAnswers(Long gameId) {
        return gameAnswers.get(gameId);
    }
}
