package client.services;

import commons.Activity;

import javax.naming.InsufficientResourcesException;
import java.util.Random;


public class QuestionAnswerSelector {

    private ActivitiesService activities;
    private int levelOfConsumption;
    private Random random;
    private Activity answerA;
    private Activity answerB;

    public QuestionAnswerSelector(){
        activities.updateActivities();
        random = new Random();
    }

    public Activity getAnswerA() {
        return answerA;
    }

    public Activity getAnswerB() {
        return answerB;
    }

    /**
     * TODO check if there are enough activities of that power level
     * TODO optimize the choosing, possibly make a list of answers
     *
     * Randomly chooses a Power level to choose from the activities
     * and chooses 2 different activities of that Power level
     * assigns those 2 activities to answerA and answerB
     * @throws InsufficientResourcesException
     */
    private void getNewAnswers() throws InsufficientResourcesException {
        if(activities.getActivities().size()<2) throw new InsufficientResourcesException();
        levelOfConsumption = random.nextInt(4) + 1;
        switch (levelOfConsumption){
            case 1: {
                answerA = activities.getLow()
                        .get(random.nextInt(activities.getLow().size()));
                do{
                    answerB = activities.getLow()
                            .get(random.nextInt(activities.getLow().size()));
                }while(answerA == answerB);
                break;
            }
            case 2: {
                answerA = activities.getMid()
                        .get(random.nextInt(activities.getLow().size()));
                do{
                    answerB = activities.getMid()
                            .get(random.nextInt(activities.getLow().size()));
                }while(answerA == answerB);
                break;
            }
            case 3: {
                answerA = activities.getHigh()
                        .get(random.nextInt(activities.getLow().size()));
                do{
                    answerB = activities.getHigh()
                            .get(random.nextInt(activities.getLow().size()));
                }while(answerA == answerB);
                break;
            }
            case 4: {
                answerA = activities.getDeyum()
                        .get(random.nextInt(activities.getLow().size()));
                do{
                    answerB = activities.getDeyum()
                            .get(random.nextInt(activities.getLow().size()));
                }while(answerA == answerB);
                break;
            }
            default:{
                throw new IndexOutOfBoundsException();
            }
        }
    }
}
