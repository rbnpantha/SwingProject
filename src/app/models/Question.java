package app.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pratik Acharya on 9/28/2016.
 */
public class Question {
   public int id;
    public String question;
    public ArrayList<String> answers;
   
   public String answer;




    public static ArrayList<Question> getQuestions(HashMap<Integer,String> questionList,HashMap<Integer,String> answerList,HashMap<Integer,ArrayList> questionAnswerList){

        ArrayList<Question> questions= new ArrayList<>();
        for (int questionId: questionList.keySet()){
            Question question = new Question();
            question.id = questionId;
            question.question = questionList.get(questionId);
            question.answers = questionAnswerList.get(questionId);
            question.answer = answerList.get(questionId);
            questions.add(question);

        }
            return  questions;


    }
}
