package app.controllers;

import app.helpers.Database;
import app.models.Question;
import app.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuizController {



    public ArrayList<Question> getQuestions()
    {
        try
        {
            // create the mysql database connection
            String query = "SELECT * FROM TBL_QUESTIONS as QUESTIONS " +
                    "LEFT JOIN TBL_QUESTION_OPTIONS as OPTIONS " +
                    "ON QUESTIONS.ID=OPTIONS.QUESTION_ID";

            Database database = new Database();

            Connection connection = database.getConnection();

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = null;
            preparedStmt = connection.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            HashMap<Integer,ArrayList> questionAnswerList = new HashMap<>();
            HashMap<Integer,String> questionList = new HashMap<>();
            HashMap<Integer,String> answerList = new HashMap<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                if(!questionList.containsKey(id)){
                    questionList.put(id,rs.getString("question"));
                }

                if(rs.getInt("verdict")==1 && !answerList.containsKey(id)){
                    answerList.put(id,rs.getString("option"));
                }
                if(!questionAnswerList.containsKey(id)){
                    ArrayList<String> list = new ArrayList<>();
                    list.add(rs.getString("option"));
                    questionAnswerList.put(id,list);

                }else{
                    questionAnswerList.get(id).add(rs.getString("option"));
                }


            }
                connection.close();
            return Question.getQuestions(questionList,answerList,questionAnswerList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }







    public static void  main(String[] args){
        QuizController quizController = new QuizController();
        //userController.createUser();
        quizController.getQuestions();
    }

}
