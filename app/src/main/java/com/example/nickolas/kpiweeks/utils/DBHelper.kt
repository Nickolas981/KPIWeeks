package com.example.nickolas.kpiweeks.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DBHelper(var context: Context) : SQLiteOpenHelper(context, "myDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table IF NOT EXISTS schedule ("
                + "id integer primary key autoincrement,"
                + "val text"
                + ");")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}