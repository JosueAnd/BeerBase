package com.example.beerrecycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    private Context     context;
    private List<Beer>  original,
                        copy;
    private byte[]      currentImg;
    private Bitmap      imgBitmap = null;
    private Filter      beerFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Beer> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(copy);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Beer beer : copy) {
                    if(beer.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(beer);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;

        } // end performFiltering

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            original.clear();
            original.addAll((Collection<? extends Beer>) results.values);
            notifyDataSetChanged();
        } // end publishResults
    }; // end beerFilter variable

    MyAdapter(Context context, List<Beer> beerList) {
        this.context    = context;
        this.original   = beerList;
        this.copy       = new ArrayList<>(beerList);
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

        holder.title.setText(original.get(position).getName());

        if(original.get(position).getImage() == null) {
            currentImg = null;
            holder.myImage.setImageResource(R.drawable.beer_icon2);
        } else {
            currentImg = original.get(position).getImage();
            imgBitmap = BitmapFactory.decodeByteArray(currentImg, 0, currentImg.length);
            holder.myImage.setImageBitmap(imgBitmap);
        }

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("title", original.get(position).getName());
                intent.putExtra("description", original.get(position).getDescription());
                intent.putExtra("brewery", original.get(position).getBrewery());
                intent.putExtra("category", original.get(position).getCategory());
                intent.putExtra("style", original.get(position).getStyle());
                intent.putExtra("abv", original.get(position).getAbv());
                if(currentImg != null) {
                    intent.putExtra("image", original.get(position).getImage());
                } else {
                    intent.putExtra("image", R.drawable.beer_icon2);
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.original.size();
    }

    @Override
    public Filter getFilter() {
        return beerFilter;
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