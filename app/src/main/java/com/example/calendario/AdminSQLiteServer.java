package com.example.calendario;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class AdminSQLiteServer extends SQLiteOpenHelper {


    public AdminSQLiteServer(@Nullable Context context) {
        super(context, "calendario", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE apoiment(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, direccion TEXT, telefono INTEGER, tiempo TEXT, fecha TEXT)");
            db.execSQL("CREATE TABLE cuenta(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
