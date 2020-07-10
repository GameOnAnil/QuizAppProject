package com.example.quizproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private ArrayList<Integer> mImage = new ArrayList<>();
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext, ArrayList<Integer> mImage, ArrayList<String> mImageNames,ArrayList<String> mDescription) {
        this.mContext = mContext;
        this.mImage = mImage;
        this.mImageNames = mImageNames;
        this.mDescription = mDescription;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //setting image
        Glide.with(mContext)
                .asBitmap()
                .load(mImage.get(position))
                .into(holder.image);
//setting txt name and description
        holder.imageName.setText(mImageNames.get(position));
        holder.discription.setText(mDescription.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));

                if (mImageNames.get(position) == "General Knowledge") {
                    Intent intent = new Intent(mContext, QuizPage.class);
                    intent.putExtra("category_no", 1);
                    mContext.startActivity(intent);
                } else if (mImageNames.get(position) == "Maths") {
                    Intent intent = new Intent(mContext, QuizPage.class);
                    intent.putExtra("category_no", 2);
                    mContext.startActivity(intent);

                } else if (mImageNames.get(position) == "Harry potter") {
                    Intent intent = new Intent(mContext, QuizPage.class);
                    intent.putExtra("category_no", 3);
                    mContext.startActivity(intent);
                } else if (mImageNames.get(position) == "Game Of Thrones") {
                    Intent intent = new Intent(mContext, QuizPage.class);
                    intent.putExtra("category_no", 4);
                    mContext.startActivity(intent);

                } else if (mImageNames.get(position) == "Money Heist") {
                    Intent intent = new Intent(mContext, QuizPage.class);
                    intent.putExtra("category_no", 5);
                    mContext.startActivity(intent);

                   // Toast.makeText(mContext, "Not available right now", Toast.LENGTH_SHORT).show();

                }
                else if (mImageNames.get(position) == "Naruto") {
//                    Intent intent = new Intent(mContext, QuizPage.class);
//                    intent.putExtra("category_no", 6);
//                    mContext.startActivity(intent);

                    Toast.makeText(mContext, "Not available right now", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parentLayout;
        ImageView image;
        TextView imageName;
        TextView discription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            discription = itemView.findViewById(R.id.image_description);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
