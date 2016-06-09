package com.animationbureau.r8r;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    //database name
    private static final String DATABASE_NAME = "r8sManager";
    //table name
    private static final String TABLE_RATINGS = "ratings";
    //column names
    private static final String KEY_ID = "id";
    private static final String KEY_WHAT = "what";
    private static final String KEY_R8 = "r8";
    private static final String KEY_WHY = "why";
    private static final String KEY_DATE="date";

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RATINGS_TABLE = "CREATE TABLE " + TABLE_RATINGS + "("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_WHAT+" TEXT,"+KEY_R8+" INTEGER,"+KEY_WHY+" TEXT,"+KEY_DATE+" TEXT"+")";
        db.execSQL(CREATE_RATINGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_RATINGS);
        onCreate(db);
    }

    public void addR8s(R8s r8s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WHAT, r8s.getWhat());
        values.put(KEY_R8, r8s.getr8());
        values.put(KEY_WHY, r8s.getWhy());
        values.put(KEY_DATE, r8s.getDate());

        db.insert(TABLE_RATINGS, null, values);
        db.close();
    }

    public R8s getR8s(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RATINGS, new String[] {KEY_ID,KEY_WHAT,KEY_R8,KEY_WHY,KEY_DATE}, KEY_ID+"=?", new String[] {String.valueOf(id)},null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        R8s r8s = new R8s(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3),cursor.getString(4));
        return r8s;
    }

    public ArrayList<R8s> getAllR8s() {
        ArrayList<R8s> r8sList = new ArrayList<R8s>();
        //select all queries
        String selectQuery = "SELECT * FROM "+ TABLE_RATINGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                R8s r8s = new R8s();
                r8s.setID(Integer.parseInt(cursor.getString(0)));
                r8s.setWhat(cursor.getString(1));
                r8s.setr8(Integer.parseInt(cursor.getString(2)));
                r8s.setWhy(cursor.getString(3));
                r8s.setDate(cursor.getString(4));

                r8sList.add(r8s);
            } while (cursor.moveToNext());
        }
        db.close();
        return r8sList;
    }

    public int getR8sCount() {
        String countQuery = "SELECT * FROM" + TABLE_RATINGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }

    public int updateR8s(R8s r8s) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WHAT, r8s.getWhat());
        values.put(KEY_R8, r8s.getr8());
        values.put(KEY_WHY,r8s.getWhy());
        values.put(KEY_DATE,r8s.getDate());

        return db.update(TABLE_RATINGS, values, KEY_ID + "=?", new String[] {String.valueOf(r8s.getID())});
    }

    public void deleteR8s(R8s r8s) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RATINGS, KEY_ID+"=?",new String[] {String.valueOf(r8s.getID())});
        db.close();
    }

    public void deleteAllR8s() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RATINGS,null,null);
        db.close();
    }
}
