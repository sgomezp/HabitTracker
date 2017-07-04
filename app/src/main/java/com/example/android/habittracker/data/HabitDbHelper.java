package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by sgomezp on 03/07/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "exercises.db";

    // Constructor

    public HabitDbHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){

        final String SQL_CREATE_EXERCISE_TABLE = "CREATE TABLE "
                + HabitEntry.TABLE_NAME
                + " (" + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_EXERCISE_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_EXERCISE_MINUTES + " INTEGER NOT NULL DEFAULT 0, "
                + HabitEntry.COLUMN_EXERCISE_DATE + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_EXERCISE_STATE + " INTEGER NOT NULL);";


        // execute the SQL statement
        db.execSQL(SQL_CREATE_EXERCISE_TABLE);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        final  String SQL_DELETE_ENTRIES = "DROP TABLE IF EXIST " + HabitEntry.TABLE_NAME;

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
