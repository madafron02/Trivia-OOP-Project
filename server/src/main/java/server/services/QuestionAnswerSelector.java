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
    private final HashMap<Long, List<Question>>gameQuestions;
    private final Random random;
    private final ActivityRepository repo;

    private List<List<Activity>> separated;

    public List<List<Activity>> getSeparated() {
        return separated;
    }

    public QuestionAnswerSelector(ActivityRepository repo){
        this.repo = repo;
        gameAnswers = new HashMap<>();
        gameQuestions = new HashMap<>();
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
                .filter(x -> x.getPowerLevel().equals("high"))
                .collect(Collectors.toList());
        List<Activity> deyumList = repo.findAll().stream()
                .filter(x -> x.getPowerLevel().equals("deyum"))
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

    /**
     * call this method when game start,it will generate 20 questions of different question types
     * now only generate more energy questions, if let the type be random chosen it can generate
     * all time questions
     * @param gameId id of the game which will contains those questions
     */
    public void setGameQuestions(Long gameId){
        if(gameQuestions.containsKey(gameId))return;
        setGameAnswers(gameId);
        List<Question>currentQuestions = new ArrayList<>();
        for(int round = 0;round<20;round++){
            Question.QuestionType type = Question.QuestionType.values()[random.nextInt(4)];
            //Question.QuestionType type = Question.QuestionType.MORE_ENERGY;
            switch (type){
                case OPEN -> currentQuestions.add(getOpenQuestion(gameId,round));
                case COMPARISON -> currentQuestions.add(getComparisonQuestion(gameId,round));
                case ENERGY_GUESS -> currentQuestions.add(getEnergyGuessQuestion(gameId,round));
                case MORE_ENERGY -> currentQuestions.add(getMoreEnergyQuestion(gameId,round));
                default -> {}
            }
        }
        gameQuestions.put(gameId,currentQuestions);
    }

    /**
     * generate the more energy question base on the answer list
     * the answers(choices) are 3 different activities(string)
     * ImgPaths is the path of image of the 3 activities
     * this question dont have a description
     * the correct answer indicates which choice has the least energy consumption
     * it can only be 1, 2 or 3
     * @param gameId indicates which game the question belongs to
     * @param roundNumber indicates the position of the question on the question list
     * @return a question with all the required field setted
     */
    public Question getMoreEnergyQuestion(long gameId,int roundNumber) {
        List<Activity> finalList =  gameAnswers.get(gameId).get(roundNumber).subList(0,3);
        Question q = new Question();
        q.setType(Question.QuestionType.MORE_ENERGY);
        q.setImgPaths(finalList.stream().map(p->p.getImgPath()).collect(Collectors.toList()));
        q.setAnswers(finalList.stream().map(p->p.getTitle()).collect(Collectors.toList()));
        if(finalList.get(0).getConsumption()<finalList.get(1).getConsumption()
                && finalList.get(0).getConsumption()<finalList.get(2).getConsumption())
            q.setCorrectAnswer(String.valueOf(1));
        else if(finalList.get(1).getConsumption()<finalList.get(0).getConsumption()
                && finalList.get(1).getConsumption()<finalList.get(2).getConsumption())
            q.setCorrectAnswer(String.valueOf(2));
        else q.setCorrectAnswer(String.valueOf(3));
        return q;
    }

    /**
     * generate the energy guess question base on the answer list
     * the answers are 3 different integers which indicates the guesses of energy consumption
     * description means the name the activity which the player is going to guess
     * DescriptionImagePath means the image of activity which the player is going to guess
     * the correct answer indicates the consumption of the activity
     * it can only be 1, 2 or 3
     * @param gameId indicates which game the question belongs to
     * @param roundNumber indicates the position of the question on the question list
     * @return a question with all the required field setted
     */
    public Question getEnergyGuessQuestion(long gameId,int roundNumber) {
        List<Activity> finalList =  gameAnswers.get(gameId).get(roundNumber).subList(0,3);
        Question q = new Question();
        int correctAnswer = random.nextInt(3);
        q.setType(Question.QuestionType.ENERGY_GUESS);
        q.setCorrectAnswer(String.valueOf(correctAnswer + 1));
        q.setAnswers(finalList.stream().map(p->String.valueOf(p.getConsumption()))
                .collect(Collectors.toList()));
        q.setDescriptionImagePath(finalList.get(correctAnswer).getImgPath());
        q.setDescription(finalList.get(correctAnswer).getTitle());
        return q;
    }
    /**
     * generate the comparison question base on the answer list
     * for example lets say the question format is
     * "instead A, I will have B,C or D"
     * description and DescriptionImagePath indicate A
     * answers and imgpaths indicate B,C and D
     * the correct answer indicates which one in B,C,D
     * has energy consumption less than A it can only be 1, 2 or 3
     * @param gameId indicates which game the question belongs to
     * @param roundNumber indicates the position of the question on the question list
     * @return a question with all the required field setted
     */
    public Question getComparisonQuestion(long gameId,int roundNumber) {
        List<Activity> finalList =  gameAnswers.get(gameId).get(roundNumber);
        Question q = new Question();
        q.setImgPaths(finalList.stream().map(p->p.getImgPath()).collect(Collectors.toList()));
        q.setAnswers(finalList.stream().map(p->p.getTitle()).collect(Collectors.toList()));
        finalList.sort(new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                if(o1.getConsumption()>o2.getConsumption())return 1;
                else return -1;
            }
        });
        q.setDescriptionImagePath(finalList.get(1).getImgPath());
        q.setDescription(finalList.get(1).getTitle());
        q.setType(Question.QuestionType.COMPARISON);
        for(int i=0;i<4;i++){
            if(q.getAnswers().get(i).equals(q.getDescription())){
                q.getAnswers().remove(i);
                q.getImgPaths().remove(i);
                break;
            }
        }
        String t = finalList.get(0).getTitle();
        for(int i=0;i<3;i++) {
            if(q.getAnswers().get(i).equals(t)){
                q.setCorrectAnswer(String.valueOf(i+1));
                break;
            }
        }
        return q;
    }
    /**
     * generate the open question base on the answer list
     * for example lets say the question format is
     * "guess the energy consumption of A"
     * description and DescriptionImagePath indicate A
     * the correct answer an integer which is the energy consumption of A
     * @param gameId indicates which game the question belongs to
     * @param roundNumber indicates the position of the question on the question list
     * @return a question with all the required field setted
     */
    public Question getOpenQuestion(long gameId,int roundNumber) {
        Activity activity = gameAnswers.get(gameId).get(roundNumber).get(0);
        Question q = new Question();
        q.setType(Question.QuestionType.OPEN);
        q.setDescriptionImagePath(activity.getImgPath());
        q.setDescription(activity.getTitle());
        q.setCorrectAnswer(String.valueOf((int)activity.getConsumption()));
        return q;
    }

    /**
     * get question of a certain round in a certain game
     * @param gameId the certain game
     * @param roundNumber the certain round
     * @return the question that the round is supposed to have
     */
    public Question getQuestion(long gameId,int roundNumber){
        if(!gameQuestions.containsKey(gameId))return null;
        return gameQuestions.get(gameId).get(roundNumber);
    }
}
