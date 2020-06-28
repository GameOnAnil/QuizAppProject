package com.example.quizproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuizPage extends AppCompatActivity {

    private TextView txt_question_count;
    private TextView txt_question;
    private TextView txt_timer;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button next;
    private Button dismiss;
    private Map<String, String> question;
    private int questionNo;
    private List<Question> questionList;
    private int questionCounter = 0;
    ;
    private int questionCounterTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;
    private int opSelected = 0;
    Dialog rightDiaglog, wrongDialog;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        ImageView rightImage = (ImageView) findViewById(R.id.right_image);
        TextView rightText = (TextView) findViewById(R.id.right_text);


        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();

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
        next.setText("Next");
        if (opSelected == currentQuestion.getAnswerNr()) {
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


        } else {
            finishQuiz();
        }
    }


    private void finishQuiz() {
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

}

