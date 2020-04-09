package com.example.beerrecycler;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String    DATABASE        = "/data/data/com.example.beerrecycler/databases/beers",
                    allDataQuery    = "SELECT * FROM beers ORDER BY name";
    SQLiteDatabase  database;
    Cursor          queryResults;
    List<Beer>      beersArray      = new ArrayList<>();
    MyAdapter       myAdapter;
    RecyclerView    mainRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainRecycler = findViewById(R.id.mainRecycler);

        database = BeerBase.openDatabase(DATABASE);

        if(database != null) {
            queryResults = BeerBase.getData(
                    database,
                    allDataQuery
            );
            try {

                queryResults.moveToFirst();

                do {
                    beersArray.add(new Beer(queryResults));
                } while (queryResults.moveToNext());


            } catch (CursorIndexOutOfBoundsException exception) {

                exception.printStackTrace();
                System.out.println(exception.getMessage());

            } finally {
                BeerBase.closeDatabase(database);
            }
        }

        myAdapter = new MyAdapter(this, beersArray);

        mainRecycler.setAdapter(myAdapter);
        mainRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_search) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
