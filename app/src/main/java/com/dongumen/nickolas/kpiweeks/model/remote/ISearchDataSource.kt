package com.dongumen.nickolas.kpiweeks.model.remote

import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.GroupResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response

/**
 * Created by Nickolas on 15.10.2017.
 */
interface  ISearchDataSource{
    fun getGroups(str: String): Deferred<Response<GroupResponse>>
}