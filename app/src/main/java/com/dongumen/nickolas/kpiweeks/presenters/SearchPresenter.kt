package com.dongumen.nickolas.kpiweeks.presenters

import com.arellomobile.mvp.InjectViewState
import com.dongumen.nickolas.kpiweeks.model.remote.ISearchDataSource
import com.dongumen.nickolas.kpiweeks.views.GroupSearchView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.koin.standalone.inject

@InjectViewState
class SearchPresenter : BasePresenter<GroupSearchView>() {

    private val source: ISearchDataSource by inject()

    fun getGroups(str: String) {
        launch {
            val result = source.getGroups(str).await().body()!!
            launch(UI) {
                viewState.showPredictions(result.results)
            }
        }
    }

}