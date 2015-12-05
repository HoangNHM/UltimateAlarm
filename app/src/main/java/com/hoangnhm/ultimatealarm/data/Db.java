package com.hoangnhm.ultimatealarm.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.hoangnhm.ultimatealarm.data.model.Alarm;

public class Db {

    public Db() { }

    // for separate many tables
    public abstract static class AlarmsTable {

        public static final String TABLE_NAME = "alarms";

        public static final String COLUMN_ID = "id";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TIME_POINT = "time_point";

        public static final String[] COLUMN_ALL = {
                COLUMN_ID,
                COLUMN_TITLE,
                COLUMN_DESCRIPTION,
                COLUMN_TIME_POINT};

        public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " TEXT PRIMARY KEY ON CONFLICT REPLACE, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_TIME_POINT + " TEXT " +
                " ); ";

        public static ContentValues toContentValues(Alarm alarm) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, alarm.id);
            values.put(COLUMN_TITLE, alarm.title);
            values.put(COLUMN_DESCRIPTION, alarm.description);
            values.put(COLUMN_TIME_POINT, alarm.timePoint);
            return values;
        }

        public static Alarm parseCursor(Cursor cursor) {
            Alarm alarm = new Alarm();
            alarm.id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
            alarm.title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
            alarm.description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
            alarm.timePoint = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_POINT));
            return alarm;
        }

    }
}
