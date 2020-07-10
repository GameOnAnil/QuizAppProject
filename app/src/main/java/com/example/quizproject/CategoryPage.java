package com.example.quizproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class CategoryPage extends AppCompatActivity {

    private static final String TAG = "CategoryPage";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);


        initImageBitmaps();
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mImageUrls.add(R.drawable.general_pic);
        mNames.add("General Knowledge");
        mDescription.add("Useless general knowledge!");

        mImageUrls.add(R.drawable.maths_school);
        mNames.add("Maths");
        mDescription.add("If you like Maths.");

        mImageUrls.add(R.drawable.harry_p);
        mNames.add("Harry potter");
        mDescription.add("Cause I fucking love harry potter!");

        mImageUrls.add(R.drawable.game_of_throne);
        mNames.add("Game Of Thrones");
        mDescription.add("Ok who hasn't seen GOT");

        mImageUrls.add(R.drawable.money_heist);
        mNames.add("Money Heist");
        mDescription.add("Ok this was a binge worthy show!");

        mImageUrls.add(R.drawable.naruto);
        mNames.add("Naruto");
        mDescription.add("Ok i haven't seen Naruto, but this shit has 500 episodes!!");


        initRecyclerView();

    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mImageUrls, mNames, mDescription);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}