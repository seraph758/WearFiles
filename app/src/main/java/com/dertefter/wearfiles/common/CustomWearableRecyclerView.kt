package com.dertefter.wearfiles.common

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.updatePadding
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.dertefter.wearfiles.R

class CustomWearableRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : WearableRecyclerView(context, attrs, defStyle) {

    private val paddingMultiplier = resources.getInteger(R.integer.top_padding_multiplyer)
    private val extraPadding = resources.getDimension(R.dimen.spacing).toInt()

    init {
        layoutManager = WearableLinearLayoutManager(context, CustomScrollingLayoutCallback())
        addItemDecoration(SpacingItemDecoration(context, R.dimen.spacing))
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (h > 0) {
            val verticalPadding = ((h / 3.2f) * paddingMultiplier).toInt() + extraPadding
            updatePadding(top = verticalPadding, bottom = verticalPadding)
        }
    }
}