package server.services;

import commons.Activity;
import org.springframework.stereotype.Service;
import server.database.ActivityRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionAnswerSelector {

    private final HashMap<Long, List<List<Activity>>> gameAnswers;
    private final Random random;
    private final ActivityRepository repo;

    private List<List<Activity>> separated;


    public QuestionAnswerSelector(ActivityRepository repo){
        this.repo = repo;
        gameAnswers = new HashMap<>();
        random = new Random();
        separated = new ArrayList<>(4);
    }

    /**
     * TODO respond if there are not enough activities of that power level
     * TODO call this when a game begins
     * TODO possibly add a List of answer sizes for each question
     *
     * based on gameID and a List of Integers depending on what amount
     * of activities there should be for each question returns a List
     * of Lists of activities
     *
     */
    public void setGameAnswers(Long gameId){
        if(!gameAnswers.containsKey(gameId)) gameAnswers.put(gameId, new LinkedList<>());

        setSeparated();

        List<List<Activity>> answersSet = new ArrayList<>(20);
        for(int i=0; i<20; i++) {
            List<Activity> questionAnswers = new ArrayList<>(4);
            List<Activity> activitiesInUse = separated.get(random.nextInt(4));
            for(int h=0; h < 4 && activitiesInUse.size() >= 4; h++){
                Activity tempActivity = activitiesInUse
                        .remove(random.nextInt(activitiesInUse.size() - 1));
                questionAnswers.add(tempActivity);
            }
            answersSet.add(questionAnswers);
        }
        gameAnswers.put(gameId, answersSet);
    }

    /**
     * separates the activities repository into sublists (copies),
     * based on power level and makes a List of those sublists,
     * for ease of use
     */
    public void setSeparated(){
        List<Activity> lowList = repo.findAll().stream()
                .filter(x -> x.getPowerLevel().equals("low"))
                .collect(Collectors.toList());
        List<Activity> midList = repo.findAll().stream()
                .filter(x -> x.getPowerLevel().equals("mid"))
                .collect(Collectors.toList());
        List<Activity> highList = repo.findAll().stream()
                .filter(x -> x.getPowerLevel().equals("low"))
                .collect(Collectors.toList());
        List<Activity> deyumList = repo.findAll().stream()
                .filter(x -> x.getPowerLevel().equals("low"))
                .collect(Collectors.toList());
        separated.add(lowList);
        separated.add(midList);
        separated.add(highList);
        separated.add(deyumList);
    }

    /*
    public void setGameAnswers(Long gameId, int count){
        if(!gameAnswers.containsKey(gameId)) gameAnswers.put(gameId, new LinkedList<>());
        List<Activity> copyActivList;
        switch(random.nextInt(4)+1){
            case 1:{
                copyActivList = List.copyOf(repo.findAll().stream()
                        .filter(x -> x.getPowerLevel().equals("low"))
                        .collect(Collectors.toList()));
                break;
            }
            case 2:{
                copyActivList = List.copyOf(repo.findAll().stream()
                        .filter(x -> x.getPowerLevel().equals("mid"))
                        .collect(Collectors.toList()));
                break;
            }
            case 3:{
                copyActivList = List.copyOf(repo.findAll().stream()
                        .filter(x -> x.getPowerLevel().equals("high"))
                        .collect(Collectors.toList()));
                break;
            }
            case 4:{
                copyActivList = List.copyOf(repo.findAll().stream()
                        .filter(x -> x.getPowerLevel().equals("deyum"))
                        .collect(Collectors.toList()));
                break;
            }
            default:{
                copyActivList = List.copyOf(repo.findAll());
            }
        }
        List<List<Activity>> answersSet = List.of();
        for(int i=0; i<20 && copyActivList.size()>=count; i++) {
            List<Activity> questionAnswers = List.of();
            for(int h=0; h<count; h++){
                Activity tempActiv = copyActivList
                        .remove(random.nextInt(copyActivList.size() - 1));
                questionAnswers.add(tempActiv);
            }
            answersSet.add(questionAnswers);
        }
        gameAnswers.put(gameId, answersSet);
    }
     */

    /**
     * TODO add a checker if the answers are empty
     * @return
     */
    public List<List<Activity>> getGameAnswers(Long gameId) {
        return gameAnswers.get(gameId);
    }
}
