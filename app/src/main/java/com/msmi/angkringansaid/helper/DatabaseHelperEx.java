package com.msmi.angkringansaid.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperEx extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "angkringansaid.db";
    public static final String TABLE_NAME = "table_jajanan";
    public static final String col_1 = "NAMA_jajanan";
    public static final String col_2 = "harga";
    public static final String col_3 = "porsi";
    public static final String col_4 = "FOTO_jajanan";

    public DatabaseHelperEx(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (NAMA_jajanan TEXT PRIMARY KEY, harga TEXT, porsi TEXT,FOTO_jajanan INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nama, String harga, String porsi, int foto) {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1, nama);
        contentValues.put(col_2, harga);
        contentValues.put(col_3, porsi);
        contentValues.put(col_4, foto);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
