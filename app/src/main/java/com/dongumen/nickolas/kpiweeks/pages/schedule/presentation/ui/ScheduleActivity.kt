package com.dongumen.nickolas.kpiweeks.pages.schedule.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dongumen.nickolas.kpiweeks.R
import com.dongumen.nickolas.kpiweeks.global.extentions.select
import com.dongumen.nickolas.kpiweeks.global.extentions.setOnItemClicked
import com.dongumen.nickolas.kpiweeks.global.viewPager.OnPageSelected
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation.ui.GroupSearchActivity
import com.dongumen.nickolas.kpiweeks.pages.schedule.presentation.WeekFragmentsPageAdapter
import com.dongumen.nickolas.kpiweeks.widgets.FragmentChanger
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import org.jetbrains.anko.defaultSharedPreferences
import org.koin.android.ext.android.get

class ScheduleActivity : AppCompatActivity(), FragmentChanger {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        back_button_text.text = getString(R.string.exit)
        back_button.setOnClickListener {
            clearPreferences()
            startSearch()
        }
        view_pager.adapter = WeekFragmentsPageAdapter(get(), supportFragmentManager)
        view_pager.addOnPageChangeListener(OnPageSelected { selectPage(it) })

        segmented.setOnItemClicked { view_pager.setCurrentItem(it, true) }
    }

    private fun startSearch() {
        val intent = Intent(this, GroupSearchActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun clearPreferences() {
        defaultSharedPreferences.edit().clear().apply()
    }

    private fun selectPage(page: Int) {
        segmented.select(page)
    }

    override fun change() {
        segmented.performClick()
    }
}
