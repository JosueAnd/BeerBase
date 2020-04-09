package com.example.beerrecycler;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView    saTVtitle,
                saTVdesc,
                saTVbrewery,
                saTVcategory,
                saTVstyle;
    ImageView   saIVimg;
    String      saTitle,
                saDesc,
                saBrewery,
                saCategory,
                saStyle,
                label;
    byte[]      saImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        saTVtitle       = findViewById(R.id.saTVtitle);
        saTVdesc        = findViewById(R.id.saTVdesc);
        saTVbrewery     = findViewById(R.id.saTVbrewery);
        saTVcategory    = findViewById(R.id.saTVcategory);
        saTVstyle       = findViewById(R.id.saTVstyle);
        saIVimg         = findViewById(R.id.saIVimg);

        getData();
        setData();

        setTitle(label);

    }

    private void getData() {
        if(
                getIntent().hasExtra("title")       &&
                getIntent().hasExtra("description") &&
                getIntent().hasExtra("image")       &&
                getIntent().hasExtra("brewery")     &&
                getIntent().hasExtra("category")    &&
                getIntent().hasExtra("style")
        ) {
            saTitle     = getIntent().getStringExtra("title");
            saDesc      = getIntent().getStringExtra("description");
            saBrewery   = getIntent().getStringExtra("brewery");
            saCategory  = getIntent().getStringExtra("category");
            saStyle     = getIntent().getStringExtra("style");
            saImg       = getIntent().getByteArrayExtra("image");
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        saTVtitle.setText(saTitle);
        saTVdesc.setText(saDesc);
        saTVbrewery.setText(saBrewery);
        saTVcategory.setText(saCategory);
        saTVstyle.setText(saStyle);
        try {
            saIVimg.setImageBitmap(BitmapFactory.decodeByteArray(saImg, 0, saImg.length));
        } catch(NullPointerException exception) {
            saIVimg.setImageResource(R.drawable.beer_icon2);
        }
        label = saTitle + " Details";
    }
}
