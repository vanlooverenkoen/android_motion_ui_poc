package com.example.motionuipoc.util.listeners

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.motionuipoc.util.ext.getSnapPosition

class OnSnapListener(
    private val onSnapPositionChangeListener: OnSnapPositionChangeListener,
    private val snapHelper: LinearSnapHelper
) : RecyclerView.OnScrollListener() {
    private var snapPosition = RecyclerView.NO_POSITION

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        maybeNotifySnapPositionChange(recyclerView)
    }

    private fun maybeNotifySnapPositionChange(recyclerView: RecyclerView) {
        val snapPosition = snapHelper.getSnapPosition(recyclerView)
        val snapPositionChanged = this.snapPosition != snapPosition
        if (snapPositionChanged) {
            onSnapPositionChangeListener
                .onSnapPositionChange(snapPosition)
            this.snapPosition = snapPosition
        }
    }
}