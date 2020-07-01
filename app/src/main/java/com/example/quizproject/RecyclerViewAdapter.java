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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private ArrayList<Integer> mImage = new ArrayList<>();
    private ArrayList<String> mImageNames = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext, ArrayList<Integer> mImage, ArrayList<String> mImageNames) {
        this.mContext = mContext;
        this.mImage = mImage;
        this.mImageNames = mImageNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImage.get(position))
                .into(holder.image);

        holder.imageName.setText(mImageNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+mImageNames.get(position));

                if(mImageNames.get(position)=="Test category"){
                    Intent intent = new Intent(mContext,QuizPage.class);
                    intent.putExtra("category_no",1);
                    mContext.startActivity(intent);
                }
                else if(mImageNames.get(position)=="Maths"){
                    Intent intent = new Intent(mContext,QuizPage.class);
                    intent.putExtra("category_no",2);
                    mContext.startActivity(intent);

                }else if(mImageNames.get(position)=="Harry potter") {
                    Toast.makeText(mContext,"Not available right now",Toast.LENGTH_SHORT).show();
                }
                else if(mImageNames.get(position)=="Science"){
                    Toast.makeText(mContext,"Not available right now",Toast.LENGTH_SHORT).show();

                }else if(mImageNames.get(position)=="Arts") {
                    Toast.makeText(mContext,"Not available right now",Toast.LENGTH_SHORT).show();
                }
                else if(mImageNames.get(position)=="Music"){
                    Toast.makeText(mContext,"Not available right now",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout parentLayout;
        ImageView image;
        TextView imageName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}