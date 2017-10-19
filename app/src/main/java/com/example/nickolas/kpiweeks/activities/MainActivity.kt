package com.example.nickolas.kpiweeks.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.nickolas.kpiweeks.R


class MainActivity : AppCompatActivity() {

    var name: String = ""
    var id: String = ""
    var sPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadText()
        if (id == ""){
            val intent = Intent(this, GroupSearch::class.java)
            startActivityForResult(intent, 1)
        }else{
            startSchdule()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var bundle = data?.extras
        name = bundle?.getString("name").toString()
        id = bundle?.getString("id").toString()
        startSchdule()
        saveText()
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun startSchdule() {
        val intent = Intent(this, ScheduleActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    @SuppressLint("ApplySharedPref")
    fun saveText() {
        val ed = getPreferences(Context.MODE_PRIVATE).edit()
        ed.putString("name", name)
        ed.putString("id", id)
        ed.commit()
    }

    fun loadText() {
        name = getPreferences(Context.MODE_PRIVATE).getString("name", "")
        id = getPreferences(Context.MODE_PRIVATE).getString("id", "")
    }
}
