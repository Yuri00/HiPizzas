package com.android.hipizzas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

private static final String DATABASE_NAME = "hipizzasdb";
private static final int DATABASE_VERSION = 1;
private static final String DATABASE_CREATE = "CREATE TABLE  (_id integer primary key autoincrement,username text not null,password text not null);";
public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

@Override
public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

        }

@Override
public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS members");
        onCreate(db);

        }

        }