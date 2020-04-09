package com.example.beerrecycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context     context;
    private List<Beer>  beers;
    private byte[]      currentImg;
    private Bitmap      imgBitmap = null;

    MyAdapter(Context context, List<Beer> beers) {
        this.context    = context;
        this.beers      = beers;
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

        holder.title.setText(beers.get(position).getName());

        if(beers.get(position).getImage() == null) {
            currentImg = null;
            holder.myImage.setImageResource(R.drawable.beer_icon2);
        } else {
            currentImg = beers.get(position).getImage();
            imgBitmap = BitmapFactory.decodeByteArray(currentImg, 0, currentImg.length);
            holder.myImage.setImageBitmap(imgBitmap);
        }

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("title", beers.get(position).getName());
                intent.putExtra("description", beers.get(position).getDescription());
                intent.putExtra("brewery", beers.get(position).getBrewery());
                if(currentImg != null) {
                    intent.putExtra("image", beers.get(position).getImage());
                } else {
                    intent.putExtra("image", R.drawable.beer_icon2);
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.beers.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView myImage;
        ConstraintLayout rowLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.beerText);
            this.myImage = itemView.findViewById(R.id.myImageView);
            this.rowLayout = itemView.findViewById(R.id.rowLayout);
        }
    } // end inner class MyViewHolder

} // end outer class MyAdapter