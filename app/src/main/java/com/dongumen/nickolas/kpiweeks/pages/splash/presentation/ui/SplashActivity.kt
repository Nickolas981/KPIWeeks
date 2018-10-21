package com.dongumen.nickolas.kpiweeks.pages.splash.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dongumen.nickolas.kpiweeks.activities.ScheduleActivity
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation.ui.GroupSearchActivity
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (defaultSharedPreferences.getString("name", null) == null) {
            startActivity<GroupSearchActivity>()
        } else {
            startActivity<ScheduleActivity>()
        }

        finish()
    }
}
