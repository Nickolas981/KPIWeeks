package com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dongumen.nickolas.kpiweeks.R
import com.dongumen.nickolas.kpiweeks.global.adapters.text.GroupsAdapter
import com.dongumen.nickolas.kpiweeks.global.delegateAdapter.CompositeDelegateAdapter
import com.dongumen.nickolas.kpiweeks.global.extentions.afterTextChanged
import com.dongumen.nickolas.kpiweeks.global.extentions.setVisibility
import com.dongumen.nickolas.kpiweeks.global.liteMoxy.MvpAppCompatActivity
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation.GroupSearchEvents
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation.GroupSearchPresenter
import com.dongumen.nickolas.kpiweeks.pages.schedule.presentation.ui.ScheduleActivity
import kotlinx.android.synthetic.main.activity_group_search.*
import kotlinx.android.synthetic.main.fragment_week.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

const val NAME = "name"

class GroupSearchActivity : MvpAppCompatActivity<GroupSearchEvents>() {

    override val presenter by inject<GroupSearchPresenter>()

    override fun update(event: GroupSearchEvents) = when (event) {
        is GroupSearchEvents.ShowPredictions -> showPredictions(event.groups)
        GroupSearchEvents.OpenSchedule -> openSchedule()
    }

    private val adapter by lazy {
        CompositeDelegateAdapter.Builder<Group>()
                .add(GroupsAdapter(this::onGroupClicked))
                .build()
    }

    private fun showPredictions(groups: List<Group>) {
        adapter.swapData(groups)
        groups_list.setVisibility(groups.isNotEmpty())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_search)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        query.run {
            afterTextChanged { presenter.getGroups(it.toString()) }
        }
    }

    private fun openSchedule() {
        startActivity<ScheduleActivity>()
        finish()
    }

    private fun onGroupClicked(group: Group) = presenter.setGroup(group)
}
