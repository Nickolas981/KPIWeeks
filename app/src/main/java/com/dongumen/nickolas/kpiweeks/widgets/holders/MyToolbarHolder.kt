package com.dongumen.nickolas.kpiweeks.widgets.holders

import android.view.View
import kotlinx.android.synthetic.main.custom_toolbar.view.*


class MyToolbarHolder(var v:View) {
    val backButton  = this.v.back_button
    val title  = this.v.toolbar_title
    val segmented = this.v.segmented
    val buttonOne = this.v.radio_1
    val buttonTwo = this.v.radio_2
    val buttonText = this.v.back_button_text

}