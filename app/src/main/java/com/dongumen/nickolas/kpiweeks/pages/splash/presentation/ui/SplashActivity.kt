package com.dongumen.nickolas.kpiweeks.pages.splash.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dongumen.nickolas.kpiweeks.global.group.GroupManager
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation.ui.GroupSearchActivity
import com.dongumen.nickolas.kpiweeks.pages.schedule.presentation.ui.ScheduleActivity
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val groupManager by inject<GroupManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (groupManager.group.id == -1) {
            startActivity<GroupSearchActivity>()
        } else {
            startActivity<ScheduleActivity>()
        }
        finish()
    }
}
