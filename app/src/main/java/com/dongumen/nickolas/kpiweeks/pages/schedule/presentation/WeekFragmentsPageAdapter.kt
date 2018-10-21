package com.dongumen.nickolas.kpiweeks.pages.schedule.presentation

import androidx.fragment.app.FragmentManager
import com.dongumen.nickolas.kpiweeks.global.group.GroupManager
import com.dongumen.nickolas.kpiweeks.global.viewPager.PageAdapter
import com.dongumen.nickolas.kpiweeks.pages.week.presentation.ui.WeekFragment

class WeekFragmentsPageAdapter(groupManager: GroupManager, fm: FragmentManager)
    : PageAdapter(List(2) { WeekFragment.newInstance(it, groupManager.group.name) }, fm)