package com.hoangnhm.ultimatealarm.data;

import android.content.ContentValues;
import android.database.Cursor;

import static com.hoangnhm.ultimatealarm.data.model.Alarm.*;

public class Db {

    public Db() { }

    // for separate many tables
    public abstract static class AlarmsTable {

        public static final String TABLE_NAME = "alarms";

        public static final String COLUMN_ID = "id";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TIME_POINT = "time_point";
        public static final String COLUMN_DAYS = "days";
        public static final String COLUMN_RINGTONE = "ringtone";

        public static final String[] COLUMN_ALL = {
                COLUMN_ID,
                COLUMN_TITLE,
                COLUMN_DESCRIPTION,
                COLUMN_TIME_POINT,
                COLUMN_DAYS,
                COLUMN_RINGTONE};

        public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " TEXT PRIMARY KEY ON CONFLICT REPLACE, " +
//                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_TIME_POINT + " TEXT, " +
                    COLUMN_DAYS + " TEXT, " +
                    COLUMN_RINGTONE + " TEXT " +
                " ); ";

        public static final String DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static ContentValues toContentValues(Item item) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, item.getId());
            values.put(COLUMN_TITLE, item.getTitle());
            values.put(COLUMN_DESCRIPTION, item.getDescription());
            values.put(COLUMN_TIME_POINT, item.getTimePoint());
            values.put(COLUMN_RINGTONE, item.getRingtone());
            values.put(COLUMN_DAYS, item.getDaysString());
            return values;
        }

        public static Item parseCursor(Cursor cursor) {
            Item.Builder builder = new Item.Builder();
            builder.setId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)))
                    .setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)))
                    .setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)))
                    .setTimePoint(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_POINT)))
                    .setRingtone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RINGTONE)))
                    .setDaysList(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DAYS)));

            return builder.create();
        }

    }
}
