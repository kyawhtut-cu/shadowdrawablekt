package com.kyawhtut.shadowdrawablekt.view

import android.graphics.Color
import com.kyawhtut.shadowdrawablekt.utils.ShadowDrawableShape

/**
 * @author kyawhtut
 * @date 15/09/2020
 */
class RoundedDrawableShape private constructor(
    bgColor: IntArray,
    shapeRadius: Float,
    shadowColor: Int,
    shadowRadius: Float,
    offsetX: Float,
    offsetY: Float
) : ShadowDrawable(
    ShadowDrawableShape.ROUND,
    bgColor,
    shapeRadius,
    shadowColor,
    shadowRadius,
    offsetX,
    offsetY
) {

    class Builder {
        var bgColors: MutableList<Int> = mutableListOf()
        var shapeRadius: Float = 0f
        var shadowColor: Int = Color.TRANSPARENT
        var shadowRadius: Float = 0f
        var offsetX: Float = 0f
        var offsetY: Float = 0f

        var bgColor: Int = Color.TRANSPARENT
            set(value) {
                field = value
                bgColors.add(0, value)
            }

        fun build() = RoundedDrawableShape(
            bgColors.toIntArray(),
            shapeRadius,
            shadowColor,
            shadowRadius,
            offsetX,
            offsetY
        )
    }
}
