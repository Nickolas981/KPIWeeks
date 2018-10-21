package com.dongumen.nickolas.kpiweeks.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dongumen.nickolas.kpiweeks.R
import com.dongumen.nickolas.kpiweeks.fragments.WeekFragment
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation.ui.GroupSearchActivity
import com.dongumen.nickolas.kpiweeks.utils.DayInformationUtil
import com.dongumen.nickolas.kpiweeks.widgets.FragmentChanger
import com.dongumen.nickolas.kpiweeks.widgets.holders.MyToolbarHolder
import org.jetbrains.anko.defaultSharedPreferences
import org.koin.android.ext.android.inject

class ScheduleActivity : AppCompatActivity(), FragmentChanger {

    private lateinit var myToolbar: MyToolbarHolder
    private val firstInit = Bool()
    private val secondInit = Bool()
    private val dayInformationUtil: DayInformationUtil by inject()
    private val name: String by lazy {
        defaultSharedPreferences.getString("name", "")
    }
    private val fragment1: WeekFragment by lazy {
        WeekFragment.newInstance(0, name)
    }
    private val fragment2: WeekFragment by lazy {
        WeekFragment.newInstance(1, name)
    }
    private lateinit var currentFragment: androidx.fragment.app.Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        myToolbar = MyToolbarHolder(findViewById<View>(android.R.id.content))
        myToolbar.buttonText.text = getString(R.string.exit)
        myToolbar.backButton.setOnClickListener {
            firstInit.b = false
            secondInit.b = false
            fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentById(R.id.frame)).commit()
            clearPreferences()
            startSearch()
        }
        startAction()
    }

    private fun startSearch() {
        val intent = Intent(this, GroupSearchActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun startAction() {
        myToolbar.title.text = name.toUpperCase()
        myToolbar.segmented.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == myToolbar.buttonOne.id) {
                changeFragment(firstInit, fragment1)
            } else {
                changeFragment(secondInit, fragment2)
            }
        }
        val currentWeek = dayInformationUtil.getWeekNumber()
        if (currentWeek == 1) {
            changeFragment(firstInit, fragment1)
            myToolbar.buttonOne.isChecked = true
            myToolbar.buttonTwo.isChecked = false
            fragment1.scroll = true
        } else {
            myToolbar.buttonTwo.isChecked = true
            myToolbar.buttonOne.isChecked = false
            changeFragment(secondInit, fragment2)
            fragment2.scroll = true
        }
    }


    private fun changeFragment(init: Bool, fragment: androidx.fragment.app.Fragment) {
        val fragT = supportFragmentManager.beginTransaction()
        if (init.b) {
            fragT.hide(currentFragment).show(fragment).commitAllowingStateLoss()
        } else {
            fragT.add(R.id.frame, fragment).commitAllowingStateLoss()
            init.b = true
        }
        currentFragment = fragment
    }

    private fun clearPreferences() {
        defaultSharedPreferences.edit().clear().apply()
    }

    override fun change() {
        myToolbar.segmented.performClick()
    }

    private inner class Bool {
        internal var b: Boolean = false
    }


}
