package com.msmi.angkringansaid;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "profile.db";
    public static final String TABLE_NAME = "profile_table";
    public static final String TABLE_EX = "ex_table";
    public static final String col_1 = "NIM";
    public static final String col_2 = "NAMA";
    public static final String col_3 = "NOMOR_WA";
    public static final String col_4 = "ALAMAT";
    public static final String col_5 = "SOSMED";
    public static final String col_6 = "FOTO";

    public DatabaseHelper(Context context ) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (NIM INTEGER PRIMARY KEY, NAMA TEXT,NOMOR_WA TEXT, ALAMAT TEXT, SOSMED TEXT, FOTO INTEGER)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nim, String nama, String nomor, String alamat, String sosmed, int foto){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,nim);
        contentValues.put(col_2,nama);
        contentValues.put(col_3,nomor);
        contentValues.put(col_4,alamat);
        contentValues.put(col_5,sosmed);
        contentValues.put(col_6,foto);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return  true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }
}