package com.example.yeray.propuestoadaframework.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Yeray on 22/01/2015.
 */
public class AdaptadorBD {

    public static final String KEY_ROWID = "id";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;


    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public AdaptadorBD(Context ctx) {
        this.mCtx = ctx;
    }

    public Cursor ObtenerAlumnos()
    {
        Cursor mCursor = mDb.rawQuery("Select id as _id, nombre, apellido, dni, fecha_alta, edad," +
                "activo FROM alumno",null);

                /*query("alumno", new String[] {KEY_ROWID,
                        "nombre","apellido","dni","fecha_alta","edad","activo"},
                null, null, null, null, null);*/

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;


    }

    public AdaptadorBD open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }






}