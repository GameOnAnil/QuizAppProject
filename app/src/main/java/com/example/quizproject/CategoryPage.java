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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mImagUrls.add(R.drawable.laughing );
        mNames.add("Main for now");

        mImagUrls.add(R.drawable.maths_school );
        mNames.add("Maths");

        mImagUrls.add(R.drawable.harry_potter );
        mNames.add("Harry potter");



        initRecyclerView();

    }

    private void  initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mImagUrls,mNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}