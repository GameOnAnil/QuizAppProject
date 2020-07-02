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
    private TextView main_txt,txt_description;
    private Button btn_again;
    int score =0;
    int total ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_page);


        Intent intent = getIntent();
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            score  = intent.getIntExtra("score",0);
            total = intent.getIntExtra("total",0);
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
        txt_description = (TextView) highscore.findViewById(R.id.txt_description);

        if(score>=8){
            txt_description.setText("You must have cheated");
        }else if(score>5){
            txt_description.setText("Not bad for an Idiot!");
        }else
        {
            txt_description.setText("GO Back To School Idiot!");
        }


        main_txt.setText("High score: "+score + "/"+total);

        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighScorePage.this,CategoryPage.class);
                startActivity(intent);
            }
        });

    }
}