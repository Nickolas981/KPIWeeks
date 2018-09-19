package com.dongumen.nickolas.kpiweeks.widgets.listeners

import android.text.Editable
import android.text.TextWatcher
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation.GroupSearchPresenter
import org.json.JSONObject

internal class TextListener(var presenter: GroupSearchPresenter) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable) {
        presenter.getGroups(JSONObject().put("query", s.toString()).toString())
    }
}