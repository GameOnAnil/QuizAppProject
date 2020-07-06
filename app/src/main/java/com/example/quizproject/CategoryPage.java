package com.example.quizproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class CategoryPage extends AppCompatActivity {

    private static final String TAG = "CategoryPage";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImagUrls = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mImagUrls.add(R.drawable.laughing );
        mNames.add("Test category");
        mDescription.add("This is for testing only");

        mImagUrls.add(R.drawable.maths_school );
        mNames.add("Maths");
        mDescription.add("If you like Maths.");

        mImagUrls.add(R.drawable.harry );
        mNames.add("Harry potter");
        mDescription.add("Cause I fucking love harry potter!");

        mImagUrls.add(R.drawable.game_of_thrones );
        mNames.add("Game Of Thrones");
        mDescription.add("Ok who hasn't seen GOT");

        mImagUrls.add(R.drawable.naruto );
        mNames.add("Naruto");
        mDescription.add("Ok i haven't seen Naruto, but this shit has 500 episodes!!");

        initRecyclerView();

    }

    private void  initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mImagUrls,mNames,mDescription);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}