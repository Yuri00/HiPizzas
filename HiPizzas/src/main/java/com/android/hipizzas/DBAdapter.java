package com.android.hipizzas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.SQLException;

/**
 * Created by Sony on 10/12/13.
 */
public class DBAdapter {
    private static final String DATABASE_TABLE = "hipizzas";
    private static final String KEY_ROW_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    SQLiteDatabase mDb;
    Context mCtx;
    DataBaseHelper mDBHelper;

    public DBAdapter(Context context){
        this.mCtx = context;
    }

    public DBAdapter open() throws SQLException{
        mDBHelper = new DataBaseHelper(mCtx);
        mDb = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDBHelper.close();
    }

    public long autenticar(String user, String pw){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME,user);
        initialValues.put(KEY_PASSWORD,pw);

        return mDb.insert(DATABASE_TABLE, null, initialValues);

    }

    public boolean Login(String username, String password) throws SQLException{
        Cursor mCursor = mDb.rawQuery("SELECT FROM " + DATABASE_TABLE + "WHERE username=? AND password=?",new String[]{username,password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0){
                return true;
            }
        }
        return true;
    }

}
