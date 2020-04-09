package com.example.beerrecycler;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String    DATABASE        = "/data/data/com.example.beerrecycler/databases/beers",
                    allDataQuery    = "SELECT * FROM beers";
    SQLiteDatabase  database;
    Cursor          queryResults;
    List<Beer>      original        = new ArrayList<>();
//    String[]        s1,
//                    s2;
//    int[]           images = {
//                        R.drawable.blue_moon_beer,
//                        R.drawable.corona_beer,
//                        R.drawable.four_loko_beer,
//                        R.drawable.corona_beer,
//                        R.drawable.shock_top_beer,
//                        R.drawable.stella_artois_beer,
//                        R.drawable.yuengling_beer,
//                        R.drawable.ada,
//                        R.drawable.add_to_path,
//                        R.drawable.advanced,
//                        R.drawable.ajaxloader,
//                        R.drawable.alert_info_32,
//                        R.drawable.alert_warning_32,
//                        R.drawable.amazonredshift,
//                        R.drawable.amazonredshiftlarge,
//                        R.drawable.ambience,
//                        R.drawable.anaconda,
//                        R.drawable.anacondaicon256x256,
//                        R.drawable.anchored_direction_arrows,
//                        R.drawable.anchored_direction_arrows_many_args,
//                        R.drawable.android_phone_template
//                    };

    RecyclerView    mainRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainRecycler = findViewById(R.id.mainRecycler);

        database = BeerBase.openDatabase(DATABASE);

//        if(database != null) {
            queryResults = BeerBase.getData(
                    database,
                    allDataQuery
            );
            try {

                queryResults.moveToFirst();

                do {
                    original.add(new Beer(queryResults));
                } while (queryResults.moveToNext());


            } catch (CursorIndexOutOfBoundsException exception) {

                exception.printStackTrace();
                System.out.println(exception.getMessage());

            } finally {
                BeerBase.closeDatabase(database);
            }
//        }

//        s1 = getResources().getStringArray(R.array.beers);
//        s2 = getResources().getStringArray(R.array.description);
//
//        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);

        MyAdapter myAdapter = new MyAdapter(this, original);

        mainRecycler.setAdapter(myAdapter);
        mainRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
