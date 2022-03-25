package server.services;

import commons.Activity;
import commons.Question;
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

    public List<Activity> getActivities(int capacity){
        List<Activity>activities = repo.findAll();
        List<Activity>tmp = activities.stream().filter(a->a.getConsumption()>600)
                .collect(Collectors.toList());
        double upper = tmp.get(random.nextInt(tmp.size())).getConsumption();
        activities = activities.stream().
                filter(p -> p.getConsumption()<=upper && p.getConsumption()>upper-600).toList();
        List<Activity> finalList  = new ArrayList<>();
        while(finalList.size()<capacity){
            Activity cur = activities.get(random.nextInt(activities.size()));
            boolean flag = true;
            for (int i = 0; i < finalList.size(); i++) {
                if(finalList.get(i).getConsumption() == cur.getConsumption()){
                    flag = false;
                    break;
                }
            }

            if(!finalList.contains(cur)&&flag)finalList.add(cur);
        }
        return finalList;
    }
    public Question getMoreEnergyQuestion() {
        List<Activity> finalList = getActivities(3);
        Question q = new Question();
        q.setType(Question.QuestionType.MORE_ENERGY);
        q.setImgPaths(finalList.stream().map(p->p.getImgPath()).collect(Collectors.toList()));
        q.setAnswers(finalList.stream().map(p->p.getTitle()).collect(Collectors.toList()));
        if(finalList.get(0).getConsumption()>finalList.get(1).getConsumption()
                && finalList.get(0).getConsumption()>finalList.get(2).getConsumption())
            q.setCorrectAnswer(String.valueOf(1));
        else if(finalList.get(1).getConsumption()>finalList.get(0).getConsumption()
                && finalList.get(1).getConsumption()>finalList.get(2).getConsumption())
            q.setCorrectAnswer(String.valueOf(2));
        else q.setCorrectAnswer(String.valueOf(3));
        return q;
    }

    public Question getEnergyGuessQuestion() {
        List<Activity> finalList = getActivities(3);
        System.out.println(finalList);
        Question q = new Question();
        int correctAnswer = random.nextInt(3);
        q.setType(Question.QuestionType.ENERGY_GUESS);
        q.setCorrectAnswer(String.valueOf(correctAnswer+1));
        q.setAnswers(finalList.stream().map(p->String.valueOf(p.getConsumption()))
                .collect(Collectors.toList()));
        q.setDescriptionImagePath(finalList.get(correctAnswer).getImgPath());
        q.setDescription(finalList.get(correctAnswer).getTitle());
        System.out.println(q);
        return q;
    }

    public Question getComparisonQuestion() {
        List<Activity>finalList = getActivities(4);
        List<Activity> copyed = List.copyOf(finalList);
        copyed.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                if(o1.getConsumption()>o2.getConsumption())return 1;
                else return -1;
            }
        });
        Activity finalTitle = copyed.get(1);
        finalList.remove(finalTitle);
        Question q = new Question();
        q.setDescriptionImagePath(finalTitle.getImgPath());
        q.setDescription(finalTitle.getTitle());
        q.setType(Question.QuestionType.COMPARISON);
        if(copyed.get(0) == finalList.get(0))q.setCorrectAnswer(String.valueOf(1));
        else if(copyed.get(0) == finalList.get(1))q.setCorrectAnswer(String.valueOf(2));
        else if(copyed.get(0) == finalList.get(2))q.setCorrectAnswer(String.valueOf(3));
        q.setImgPaths(finalList.stream().map(p->p.getImgPath()).collect(Collectors.toList()));
        q.setAnswers(finalList.stream().map(p->p.getTitle()).collect(Collectors.toList()));
        return q;
    }

    public Question getOpenQuestion() {
        List<Activity>activities = repo.findAll();
        Activity activity = activities.get(random.nextInt(activities.size()));
        Question q = new Question();
        q.setType(Question.QuestionType.OPEN);
        q.setDescriptionImagePath(activity.getImgPath());
        q.setDescription(activity.getTitle());
        q.setCorrectAnswer(String.valueOf(activity.getConsumption()));
        return q;
    }
}
