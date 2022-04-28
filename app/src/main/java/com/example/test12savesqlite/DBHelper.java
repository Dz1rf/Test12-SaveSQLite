package com.example.test12savesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;                                                   //переменная для версии базы данных (начинать с 1)
    public static final String DATABASE_NAME = "contactDB";                                         //переменная, содержащая имя базы данных
    public static final String TABLE_CONTACTS = "contacts";                                         //переменная, содержащая имя таблицы в БД

    public static final String KEY_ID = "_id";                                                      //переменная, содержащая имя столбца
    public static final String KEY_NAME = "name";                                                   //переменная, содержащая имя столбца
    public static final String KEY_MAIL = "mail";                                                   //переменная, содержащая имя столбца

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID + " integer primary key," + KEY_NAME + " text," + KEY_MAIL + " text" + ")");
    }

    @Override                                                                                       //срабатывает, когда версия AQL отличается
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);                                       //удаление старой таблицы
        onCreate(db);                                                                               //создание новой таблицы

    }
}
