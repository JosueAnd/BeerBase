package com.example.beerrecycler;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

class BeerBase {

    static SQLiteDatabase openDatabase(String fileLocation) {

        // Variables
        SQLiteDatabase database;

        try {
            database = SQLiteDatabase.openDatabase(fileLocation, null, 0);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
            database = null;
        }

        return database;

    }

    static Cursor getData(SQLiteDatabase database, String rawSqlQuery) {

        // Variables
        Cursor results = null;

        if(database != null) {
            try {
                results = database.rawQuery(rawSqlQuery, null);
            } catch(SQLiteException exception) {
                exception.printStackTrace();
                System.out.println(exception.getMessage());
                results = null;
            }
        }

        return results;

    }

    static boolean closeDatabase(SQLiteDatabase database) {
        try {
            database.close();
            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
            return false;
        }
    }

} // end class BeerBase





























































































