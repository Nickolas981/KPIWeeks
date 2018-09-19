package com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.dongumen.nickolas.kpiweeks.R
import com.dongumen.nickolas.kpiweeks.global.extentions.afterTextChanged
import com.dongumen.nickolas.kpiweeks.global.extentions.setVisibility
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation.GroupSearchPresenter
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation.GroupSearchView
import com.dongumen.nickolas.kpiweeks.widgets.adapters.delegateAdapter.CompositeDelegateAdapter
import com.dongumen.nickolas.kpiweeks.widgets.adapters.text.GroupsAdapter
import kotlinx.android.synthetic.main.activity_group_search.*
import kotlinx.android.synthetic.main.list_card.*

const val NAME = "name"

class GroupSearchActivity : MvpAppCompatActivity(), GroupSearchView {

    @InjectPresenter
    lateinit var presenter: GroupSearchPresenter
    private val adapter by lazy {
        CompositeDelegateAdapter.Builder<Group>()
                .add(GroupsAdapter(this::onGroupClicked))
                .build()
    }

    override fun showPredictions(groups: List<Group>) {
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

    private fun onGroupClicked(group: Group) {
        val intent = Intent()
        intent.putExtra(NAME, group.id)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
