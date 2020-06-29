package com.example.quizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighScorePage extends AppCompatActivity {

    private Dialog highscore;
    private TextView main_txt;
    private Button btn_again;
    int totalcount =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_page);


        Intent intent = getIntent();
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            totalcount  = intent.getIntExtra("score",0);
        }

        showHighscore();

    }

    private void showHighscore() {
        highscore = new Dialog(this );
        highscore.setContentView(R.layout.high_score_dialog);
        highscore.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        highscore.show();

        main_txt = (TextView) highscore.findViewById(R.id.main_text);
        btn_again = (Button) highscore.findViewById(R.id.btn_again);

        main_txt.setText("High score: "+totalcount);

        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighScorePage.this,QuizPage.class);
                startActivity(intent);
            }
        });

    }
}