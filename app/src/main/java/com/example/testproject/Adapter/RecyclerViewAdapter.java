package com.example.testproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testproject.Model.ResponseModel;
import com.example.testproject.R;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {
   ArrayList<ResponseModel.Data>data;

    public RecyclerViewAdapter(ArrayList<ResponseModel.Data> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_xml,parent,false);
        return new myViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position)
    {

      holder.title.setText(data.get(position).getTitle());
      holder.description.setText(data.get(position).getDescription());
      holder.publishAt.setText(data.get(position).getPublishedAt());
//        Picasso.get().load(data.get(position).getUrlToImage())
//                .fit()
//                .into(holder.imageView);
        Glide.with(holder.imageView.getContext())
                .load(data.get(position).getUrlToImage())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{
       TextView title,description,publishAt;
       CircleImageView imageView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            publishAt = itemView.findViewById(R.id.publishAt);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
