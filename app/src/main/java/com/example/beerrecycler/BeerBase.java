//package com.example.beerrecycler;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//class BeerBase extends SQLiteOpenHelper {
//
//    // Variables
//    private static final String DATABASE_LOCATION = "/data/com.example.beerrecycler/databases/";
//    private static final String DATABASE_NAME = "beers.db";
//    private static final String TABLE_NAME = "beers";
//    static final String COLUMN_ID = "id";
//    static final String COLUMN_BREWERY = "brewery";
//    static final String COLUMN_NAME = "name";
//    static final String COLUMN_CATEGORY = "category";
//    static final String COLUMN_STYLE = "style";
//    static final String COLUMN_ABV = "abv";
//    static final String COLUMN_DESCRIPTION = "description";
//    static final String COLUMN_IMAGE = "img";
//
//    public BeerBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    /**
//     * Method Name:	    getConnection()
//     * Parameters:		None.
//     * Process:			Get a connection to a SQLite database.
//     * @return          A connection to a database.
//     */
//    static Connection getConnection(Context context) {
//        // Local Variables
//        Connection attempt;
//
//        // Try to connect to the database, display an error Toast if upon failure.
//        try {
////            attempt = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_LOCATION + DATABASE_NAME);
//            attempt = DriverManager.getConnection("jdbc:sqlite:/home/josue/StudioProjects" +
//                    "/BeerRecycler/app/src/main/java/com/example/beerrecycler/beers.db");
//        } catch (SQLException exception) {
//            Toast.makeText(
//                    context,
//                    "*****\n\tDatabase could not be found:\n*****\n\n" + exception.getMessage(),
//                    Toast.LENGTH_SHORT
//            ).show();
//            exception.printStackTrace();
//            System.out.println(exception.getMessage());
//            attempt = null;
//        }
//
//        return attempt;
//
//    } // end getConnection
//
//    /**
//     * Method Name:     getDate()
//     * @param           connection:     A connection to the database.
//     * @param           sqlQuery:       A SQL query formatted string to run on a database.
//     * Process:         Take a connection to the database and query it.
//     * @return          resultSet:      Results of the query.
//     */
//    static ResultSet getData(Connection connection, @NonNull String sqlQuery) {
//
//        // Variables
//        ResultSet resultSet;
//        if(sqlQuery.trim().equals(""))
//            sqlQuery = "SELECT * FROM " + TABLE_NAME;
//        else
//            sqlQuery = sqlQuery.toLowerCase().trim();
//
//        // Querying the database.
//        try {
//            Statement statement = connection.createStatement();
//            resultSet = statement.executeQuery(sqlQuery);
//        } catch(SQLException exception) {
//            exception.printStackTrace();
//            System.out.println(exception.getMessage());
//            resultSet = null;
//        }
//
//        // Return the resultSet.
//        return resultSet;
//
//    }
//
//    /**
//     * Method Name:	    releaseConnection()
//     * @param   		connection:     A connection to a database.
//     * Process:			Terminate a connection to a SQLite database.
//     * @return          Whether or not the connection to the database was successfully terminated.
//     */
//    static Boolean endConnection(Connection connection) {
//
//        // Variables
//        boolean connectionEnded = true;
//
//        // Attempting to close the connection.
//        try {
//            if(connection != null)
//                connection.close();
//        } catch(SQLException exception) {
//            exception.printStackTrace();
//            System.out.println(exception.getMessage());
//            connectionEnded = false;
//        }
//
//        // Return result of connection termination.
//        return connectionEnded;
//
//    } // end releaseConnection
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {}
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
//} // end class BeerBase

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





























































































