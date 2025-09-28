package com.dertefter.wearfiles.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    context: Context,
    @DimenRes spacingResId: Int,
    private val orientation: Orientation = Orientation.VERTICAL
) : RecyclerView.ItemDecoration() {

    enum class Orientation {
        VERTICAL, HORIZONTAL
    }

    private val spacing: Int = context.resources.getDimensionPixelSize(spacingResId)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        if (orientation == Orientation.VERTICAL) {
            outRect.top = if (position == 0) 0 else spacing
        } else {
            outRect.left = if (position == 0) 0 else spacing
        }
    }
}
