package com.example.beerrecycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String[] data1, data2;
    int[] images;
    Context context;

    public MyAdapter(Context context, String[] s1, String[] s2, int[] images) {
        this.context = context;
        this.data1 = s1;
        this.data2 = s2;
        this.images = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.title.setText(this.data1[position]);
        holder.description.setText(this.data2[position]);
        holder.myImage.setImageResource(this.images[position]);

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("title", data1[position]);
                intent.putExtra("description", data2[position]);
                intent.putExtra("image", images[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        ImageView myImage;
        ConstraintLayout rowLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.beerText);
            this.description = itemView.findViewById(R.id.descriptionText);
            this.myImage = itemView.findViewById(R.id.myImageView);
            this.rowLayout = itemView.findViewById(R.id.rowLayout);
        }
    } // end inner class MyViewHolder

} // end outer class MyAdapter