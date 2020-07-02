package com.example.quizproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class QuizPage extends AppCompatActivity {

    private static final long COUNTDOWN_TOTAL = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeft;

    private TextView txt_question_count;
    private TextView txt_question;
    private TextView txt_timer;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button next;
    private Button dismiss;
    private Map<String, String> question;
    private List<Question> questionList;
    private int questionCounter = 0;
    private int category_no = 0;

    private int questionCounterTotal;
    private Question currentQuestion;

    public int score=0;
    private boolean answered;
    private int opSelected = 0;
    Dialog rightDiaglog, wrongDialog,timeupDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        txt_question_count = (TextView) findViewById(R.id.no_of_question);
        txt_question = (TextView) findViewById(R.id.question);
        txt_timer = (TextView) findViewById(R.id.timer);
        option1 = (Button) findViewById(R.id.choice1);
        option2 = (Button) findViewById(R.id.choice2);
        option3 = (Button) findViewById(R.id.choice3);
        next = (Button) findViewById(R.id.confirm);

        Intent intent = getIntent();
        category_no = intent.getIntExtra("category_no",0);

        //connecting database and getting question
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        if(category_no == 1){
            questionList = dbHelper.getAllQuestions();
        }else if(category_no == 2){
            questionList = dbHelper.getAllQuestions2();
        }else if(category_no == 3){
            questionList = dbHelper.getAllQuestions3();
        }else if(category_no == 4){
        questionList = dbHelper.getAllQuestions4();}


        questionCounterTotal = questionList.size();

        showNextQuestion();

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opSelected = 1;
                option1.setPressed(true);
                Toast.makeText(getApplicationContext(), "A chosen", Toast.LENGTH_SHORT).show();
                option2.setEnabled(false);
                option3.setEnabled(false);


            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opSelected = 2;
                option2.setPressed(true);
                Toast.makeText(getApplicationContext(), "B chosen", Toast.LENGTH_SHORT).show();
                option1.setEnabled(false);
                option3.setEnabled(false);

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opSelected = 3;
                option3.setPressed(true);
                Toast.makeText(getApplicationContext(), "C chosen", Toast.LENGTH_SHORT).show();
                option1.setEnabled(false);
                option2.setEnabled(false);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (!answered) {
                    if (opSelected > 0) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizPage.this, "Please choose an answer", Toast.LENGTH_SHORT).show();
                    }
                } else
                    showNextQuestion();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    private void showCorrectAnswer() {
        option1.setBackground(getResources().getDrawable(R.drawable.red_button));
        option2.setBackground(getResources().getDrawable(R.drawable.red_button));
        option3.setBackground(getResources().getDrawable(R.drawable.red_button));
        switch (currentQuestion.getAnswerNr()) {
            case 1:
                option1.setBackground(getResources().getDrawable(R.drawable.green_button));
                break;
            case 2:
                option2.setBackground(getResources().getDrawable(R.drawable.green_button));
                break;
            case 3:
                option3.setBackground(getResources().getDrawable(R.drawable.green_button));
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel();
        next.setText("Next");
        if (opSelected == currentQuestion.getAnswerNr()) {
            score++;
            showCorrectPopUp();

        } else {
            showWrongPopUp();
        }
        showCorrectAnswer();
    }


    @SuppressLint("ResourceAsColor")
    private void showNextQuestion() {
        answered = false;
        opSelected = 0;
        option1.setBackground(getResources().getDrawable(R.drawable.choice_background));
        option2.setBackground(getResources().getDrawable(R.drawable.choice_background));
        option3.setBackground(getResources().getDrawable(R.drawable.choice_background));

        if (questionCounter < questionCounterTotal) {
            currentQuestion = questionList.get(questionCounter);
            txt_question.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            next.setText("Confirm");
            questionCounter++;
            txt_question_count.setText("Question: " + questionCounter + "/" + questionCounterTotal);
            option1.setEnabled(true);
            option2.setEnabled(true);
            option3.setEnabled(true);
            timeLeft = COUNTDOWN_TOTAL;
            startCountDown();


        } else {
            finishQuiz();
        }
    }

    public void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long left) {
                timeLeft = left;
                updateCountDownText();
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onFinish() {
                timerFinished();


            }
        }.start();
    }

    private  void updateCountDownText(){
        int minutes = (int) (timeLeft/1000)/60;
        int second = (int) (timeLeft/1000)%60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,second);
        txt_timer.setText(timeFormatted);

        if(timeLeft < 10000){
            txt_timer.setTextColor(Color.RED);
        }else{
            txt_timer.setTextColor(Color.WHITE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void timerFinished(){
        answered = true;
        next.setText("Next");
        showTimeupPopUp();
        showCorrectAnswer();
        category_no = 0;
    }

    private void finishQuiz() {
        Intent intent = new Intent(QuizPage.this,HighScorePage.class);
        intent.putExtra("score",score);
        startActivity(intent);
        finish();
    }

    private void showCorrectPopUp() {
        rightDiaglog = new Dialog(this);
        rightDiaglog.setContentView(R.layout.right_msg_dialog);
        rightDiaglog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        rightDiaglog.show();
        dismiss = rightDiaglog.findViewById(R.id.btn_dismiss);
        TextView cross = (TextView )rightDiaglog.findViewById(R.id.right_cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightDiaglog.dismiss();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightDiaglog.dismiss();
            }
        });
    }

    private void showWrongPopUp() {
        wrongDialog = new Dialog(this);
        wrongDialog.setContentView(R.layout.wrong_msg_dialog);
        wrongDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wrongDialog.show();
        dismiss = wrongDialog.findViewById(R.id.btn_dismiss);
        TextView cross = (TextView )wrongDialog.findViewById(R.id.wrong_cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongDialog.dismiss();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongDialog.dismiss();
            }
        });
    }

    private void showTimeupPopUp() {
        timeupDialog = new Dialog(this);
        timeupDialog.setContentView(R.layout.timeup_msg_dialog);
        timeupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timeupDialog.show();
        dismiss = timeupDialog.findViewById(R.id.btn_dismiss);
        TextView cross = (TextView )timeupDialog.findViewById(R.id.timeup_cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeupDialog.dismiss();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeupDialog.dismiss();
            }
        });
    }

}

