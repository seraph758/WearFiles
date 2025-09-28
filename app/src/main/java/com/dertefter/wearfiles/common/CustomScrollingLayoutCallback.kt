package com.dertefter.wearfiles.common

import android.view.View
import android.view.animation.PathInterpolator
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.WearableLinearLayoutManager
import com.dertefter.wearfiles.R
import kotlin.math.abs

class CustomScrollingLayoutCallback : WearableLinearLayoutManager.LayoutCallback() {

    private val scaleInterpolator = PathInterpolator(0.25f, 0f, 0.75f, 1f)

    override fun onLayoutFinished(child: View, parent: RecyclerView) {
        val centerY = parent.height / 2f
        val childCenterY = child.y + child.height / 2f
        val distanceFromCenter = abs(centerY - childCenterY)

        // Широкая зона максимального значения
        val maxDistance = centerY * child.context.resources.getInteger(R.integer.max_distance_value) / 100
        val normalized = (distanceFromCenter / maxDistance).coerceIn(0f, 1f)

        // Плавный спад (от 1 до 0), затем интерполяция в диапазон 0.4..1f
        val rawProgress = 1f - normalized * normalized
        val adjustedProgress = 0.3f + rawProgress * 0.7f  // 0.4 + (0..1) * 0.6 = 0.4..1

        val interpolated = scaleInterpolator.getInterpolation(adjustedProgress)
        // Логика pivotY
        when {
            childCenterY < centerY -> {
                child.pivotY = child.height.toFloat()
            }
            childCenterY > centerY -> {
                child.pivotY = 0f
            }
            else -> {
                child.pivotY = 0f
            }
        }

        child.pivotX = child.width / 2f

        child.scaleX = interpolated
        child.scaleY = interpolated
        child.alpha = interpolated
    }



}
