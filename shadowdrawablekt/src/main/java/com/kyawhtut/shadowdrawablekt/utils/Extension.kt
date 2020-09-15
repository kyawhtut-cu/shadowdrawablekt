package com.kyawhtut.shadowdrawablekt.utils

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.ViewCompat
import com.kyawhtut.shadowdrawablekt.view.CircleDrawableShape
import com.kyawhtut.shadowdrawablekt.view.RoundedDrawableShape

/**
 * @author kyawhtut
 * @date 15/09/2020
 */
object Extension {

    infix fun dpToPx(dp: Int): Float {
        return Resources.getSystem().displayMetrics.density * dp + 0.5f
    }

    fun View.setShadowDrawable(drawable: Drawable) {
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        this.background = drawable
//        ViewCompat.setBackground(this, drawable)
    }

    fun View.setBackground(drawable: Drawable) {
        ViewCompat.setBackground(this, drawable)
    }

    fun View.setCircleShadowDrawable(block: CircleDrawableShape.Builder.() -> Unit) {
        this.setShadowDrawable(CircleDrawableShape.Builder().also(block).build())
    }

    fun View.setRoundShadowDrawable(block: RoundedDrawableShape.Builder.() -> Unit) {
        this.setShadowDrawable(RoundedDrawableShape.Builder().also(block).build())
    }

    fun roundedShadowDrawable(block: RoundedDrawableShape.Builder.() -> Unit) =
        RoundedDrawableShape.Builder().also(block)

    fun circleShadowDrawable(block: CircleDrawableShape.Builder.() -> Unit) =
        CircleDrawableShape.Builder().also(block)
}
