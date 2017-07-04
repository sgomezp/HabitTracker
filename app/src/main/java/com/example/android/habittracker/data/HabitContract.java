package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by sgomezp on 03/07/2017.
 */

public class HabitContract {
    public static abstract class HabitEntry implements BaseColumns{

        public static final String TABLE_NAME = "exercise";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_EXERCISE_NAME = "name";
        public static final String COLUMN_EXERCISE_MINUTES = "minutes";
        public static final String COLUMN_EXERCISE_DATE = "date";
        public static final String COLUMN_EXERCISE_STATE = "state";

        // Possible values for state = How do you feel after the exercise

        public  static final int STATE_GREAT = 1;
        public  static final int STATE_TIRED = 2;


    }
}
