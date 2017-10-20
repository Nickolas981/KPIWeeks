package com.example.nickolas.kpiweeks.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase


/**
 * Created by Nickolas on 19.10.2017.
 */
public class DBController private constructor() {

    private object Holder {
        val INSTANCE = DBController()
    }

    companion object {
        val instance: DBController by lazy { Holder.INSTANCE }
    }

    var dbh: DBHelper? = null
    var db: SQLiteDatabase? = null
    lateinit var context: Context

    fun initiate(c: Context) {
        context = c
        dbh = DBHelper(context)
    }

    fun start(){
        db = dbh!!.writableDatabase
    }

    fun finish(){
        dbh!!.close()
    }

    fun insert( value: String) {
        val cv = ContentValues()
        cv.put("val", value)
        db?.beginTransaction()
        db?.insert("schedule", null, cv)
        db?.endTransaction()
    }

    fun read(): String {
        val c = db?.query("schedule", null, null, null, null, null, null)
        var res: String = ""
        if (c != null) {
            c.moveToFirst()
            res = c.getString(c.getColumnIndex("val"))
            c.close()
        }
        return res
    }

    fun delete(table: String = "schedule") {
        db?.delete(table, null, null)
    }
}