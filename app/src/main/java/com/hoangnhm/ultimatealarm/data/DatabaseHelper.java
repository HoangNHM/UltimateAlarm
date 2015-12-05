package com.hoangnhm.ultimatealarm.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;

import static com.hoangnhm.ultimatealarm.data.model.Alarm.*;

public class DatabaseHelper {

    private DbOpenHelper mDb;

    public DatabaseHelper(Context context) {
        mDb = new DbOpenHelper(context);
    }

    public DbOpenHelper getDbOpenHelper() {
        return mDb;
    }

    public void setAlarms(final Collection<Item> newItems) {
        SQLiteDatabase db = mDb.getWritableDatabase();
        for (Item item : newItems) {
            ContentValues values = Db.AlarmsTable.toContentValues(item);
            long newRowId;
            newRowId = db.insert(Db.AlarmsTable.TABLE_NAME, null, values); // null: not insert a row when there are no values
        }

    }

    public ArrayList<Item> getAlarms() {
        SQLiteDatabase db = mDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(Db.AlarmsTable.SELECT_ALL, null);

        ArrayList<Item> items = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            items.add(Db.AlarmsTable.parseCursor(cursor));
            cursor.moveToNext();
        }

        return items;
    }

//    /**
//     * Remove all the data from all the tables in the database.
//     */
//    public Observable<Void> clearTables() {
//        return Observable.create(new Observable.OnSubscribe<Void>() {
//            @Override
//            public void call(Subscriber<? super Void> subscriber) {
//                BriteDatabase.Transaction transaction = mDb.newTransaction();
//                try {
//                    Cursor cursor = mDb.query("SELECT name FROM sqlite_master WHERE type='table'");
//                    while (cursor.moveToNext()) {
//                        mDb.delete(cursor.getString(cursor.getColumnIndex("name")), null);
//                    }
//                    cursor.close();
//                    transaction.markSuccessful();
//                    subscriber.onCompleted();
//                } finally {
//                    transaction.end();
//                }
//            }
//        });
//    }
//
//    public Observable<Ribot> setRibots(final Collection<Ribot> newRibots) {
//        return Observable.create(new Observable.OnSubscribe<Ribot>() {
//            @Override
//            public void call(Subscriber<? super Ribot> subscriber) {
//                BriteDatabase.Transaction transaction = mDb.newTransaction();
//                try {
//                    deleteAllRibotsApartFrom(newRibots);
//                    for (Ribot ribot : newRibots) {
//                        long result = mDb.insert(Db.RibotsTable.TABLE_NAME,
//                                Db.RibotsTable.toContentValues(ribot));
//                        if (result >= 0) subscriber.onNext(ribot);
//                    }
//                    transaction.markSuccessful();
//                    subscriber.onCompleted();
//                } finally {
//                    transaction.end();
//                }
//            }
//        });
//    }
//
//    public Observable<List<Ribot>> getRibots() {
//        return mDb.createQuery(Db.RibotsTable.TABLE_NAME,
//                "SELECT * FROM " + Db.RibotsTable.TABLE_NAME)
//                .mapToList(new Func1<Cursor, Ribot>() {
//                    @Override
//                    public Ribot call(Cursor cursor) {
//                        return Db.RibotsTable.parseCursor(cursor);
//                    }
//                });
//    }
//
//    private void deleteAllRibotsApartFrom(Collection<Item> ribotsToKeep) {
//        if (ribotsToKeep.isEmpty()) {
//            mDb.delete(Db.RibotsTable.TABLE_NAME, null);
//        } else {
//            mDb.delete(Db.RibotsTable.TABLE_NAME,
//                    Db.RibotsTable.COLUMN_ID +
//                            " NOT IN (" + createPlaceholders(ribotsToKeep.size()) + ")",
//                    Ribot.getIds(ribotsToKeep));
//        }
//    }

    private String createPlaceholders(int length) {
        if (length < 1) {
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(length * 2 - 1);
            sb.append('?');
            for (int i = 1; i < length; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }

}
