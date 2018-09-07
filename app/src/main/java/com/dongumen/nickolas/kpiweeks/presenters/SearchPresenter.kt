package com.dongumen.nickolas.kpiweeks.presenters

import com.dongumen.nickolas.kpiweeks.model.remote.ISearchDataSource
import com.dongumen.nickolas.kpiweeks.views.GroupSearchView
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.koin.standalone.inject

class SearchPresenter : BasePresenter<GroupSearchView>() {

    private val source: ISearchDataSource by inject()

    fun getGroups(str: String) {
        launch(CommonPool) {
            source.getGroups(str).await()
                    .body()
                    ?.let {
                        launch(UI) {
                            viewState.showPredictions(it)
                        }
                    }
        }
    }

}