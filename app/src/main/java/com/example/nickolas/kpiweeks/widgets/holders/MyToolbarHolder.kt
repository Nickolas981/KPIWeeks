package com.example.nickolas.kpiweeks.widgets.holders

import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.custom_toolbar.view.*


class MyToolbarHolder(var v:View) {
    val backButton  = this.v.back_button
    val title  = this.v.toolbar_title
    val switch = this.v.weeks_switch
    val buttonText = this.v.back_button_text

}