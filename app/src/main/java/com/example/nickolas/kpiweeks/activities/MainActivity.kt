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
            startSchdule()
    }

    private fun startSchdule() {
        val intent = Intent(this, ScheduleActivity::class.java)
        startActivity(intent)
    }

}
