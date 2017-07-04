package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

import static com.example.android.habittracker.data.HabitContract.HabitEntry.STATE_GREAT;

public class MainActivity extends AppCompatActivity {

    //LOG_TAG for debugging purposes
    public static final String LOG_TAG = MainActivity.class.getName();

    private HabitDbHelper mDbHelper;
    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new HabitDbHelper(this);
        mCursor = readData();
        mCursor.close();
   }

    private void insertHabit(){

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_EXERCISE_NAME, "Aerobics");
        values.put(HabitEntry.COLUMN_EXERCISE_MINUTES, 20);
        values.put(HabitEntry.COLUMN_EXERCISE_DATE, "07/03/2017");
        values.put(HabitEntry.COLUMN_EXERCISE_STATE, STATE_GREAT);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        if (newRowId == -1){
            Log.i(LOG_TAG, "Error adding rows");
        } else {
            Log.i(LOG_TAG, "Insert method worked fine");
        }

    }

    private Cursor readData(){

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this  SQL query "SELECT * FROM exercise"
        // to get a Cursor that contains all rows from the exercise table.

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_EXERCISE_NAME,
                HabitEntry.COLUMN_EXERCISE_MINUTES,
                HabitEntry.COLUMN_EXERCISE_DATE,
                HabitEntry.COLUMN_EXERCISE_STATE
        };

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        Log.i(LOG_TAG, "Reading data. The tables has: " + cursor.getCount() + " rows");
        return cursor;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_main_activity.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertHabit();
                mCursor = readData();
                mCursor.close();
                return true;
            /*// Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;*/
        }
        return super.onOptionsItemSelected(item);
    }
}
