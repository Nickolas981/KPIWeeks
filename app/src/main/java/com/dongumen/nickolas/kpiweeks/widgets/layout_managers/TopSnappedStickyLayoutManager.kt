package com.dongumen.nickolas.kpiweeks.widgets.layout_managers

import android.content.Context
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler
import com.brandongogetap.stickyheaders.StickyLayoutManager




class TopSnappedStickyLayoutManager internal constructor(context: Context, headerHandler: StickyHeaderHandler) : StickyLayoutManager(context, headerHandler) {

    override fun scrollToPosition(position: Int) {
        super.scrollToPositionWithOffset(position, 0)
    }
}