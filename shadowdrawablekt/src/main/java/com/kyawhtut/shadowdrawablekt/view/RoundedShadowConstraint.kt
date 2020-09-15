package com.kyawhtut.shadowdrawablekt.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.kyawhtut.shadowdrawablekt.R
import com.kyawhtut.shadowdrawablekt.utils.Extension.roundedShadowDrawable
import com.kyawhtut.shadowdrawablekt.utils.Extension.setShadowDrawable

/**
 * @author kyawhtut
 * @date 15/09/2020
 */
class RoundedShadowConstraint @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val shape = roundedShadowDrawable {
    }

    init {
        val a = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.ShadowConstraint,
            defStyleAttr,
            0
        )
        try {
            shape.bgColor =
                a.getColor(R.styleable.ShadowConstraint_backgroundColor, Color.TRANSPARENT)
            shape.shadowColor =
                a.getColor(R.styleable.ShadowConstraint_shadowColor, Color.TRANSPARENT)
            shape.offsetX = a.getDimension(R.styleable.ShadowConstraint_offsetX, 0f)
            shape.offsetY = a.getDimension(R.styleable.ShadowConstraint_offsetY, 0f)
            shape.shadowRadius =
                a.getDimension(R.styleable.ShadowConstraint_shadowRadius, 0f)
            shape.shapeRadius = a.getDimension(R.styleable.ShadowConstraint_radius, 0f)
            this.setShadowDrawable(shape.build())
        } finally {
            a.recycle()
        }
    }
}
