package com.example.quizproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;



public class QuizPage extends AppCompatActivity {

    private TextView txt_question_count;
    private TextView txt_question;
    private TextView txt_timer;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button next;
    private List<Question> questionList;


    private int questionCounter;
    private int questionCounterTotal;
    private Question currentQuestion;
    private boolean answered;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        txt_question_count  = (TextView) findViewById(R.id.no_of_question);
        txt_question  = (TextView) findViewById(R.id.question);
        txt_timer  = (TextView) findViewById(R.id.timer);
        option1 = (Button)findViewById(R.id.choice1);
        option2 = (Button)findViewById(R.id.choice2);
        option3 = (Button)findViewById(R.id.choice3);
        next = (Button)findViewById(R.id.next_page);





        QuizDBHelper dbHelper = new QuizDBHelper(this);
        questionList = dbHelper.getAllQuestion();

        questionCounterTotal = questionList.size();
        Collections.shuffle(questionList);

      //  showNextQuestion();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showNextQuestion() {

        if(questionCounter < questionCounterTotal){
            currentQuestion = questionList.get(questionCounter);

            txt_question.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());

            questionCounter++;
            txt_question_count.setText("Question: "+questionCounter+"/"+questionCounterTotal);
            answered = false;
            next.setText("Confirm");
        }else{
            finishQuiz();
        }
    }

    private void finishQuiz() {
        finish();
    }
}