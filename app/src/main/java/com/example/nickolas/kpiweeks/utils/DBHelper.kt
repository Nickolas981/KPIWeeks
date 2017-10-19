package com.example.nickolas.kpiweeks.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


/**
 * Created by Nickolas on 19.10.2017.
 */
class DBHelper(context: Context) : SQLiteOpenHelper(context, "myDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("create table schedule ("
                + "id integer primary key autoincrement,"
                + "val text"
                + ");")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}