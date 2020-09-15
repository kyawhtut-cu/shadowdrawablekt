package com.kyawhtut.shadowdrawablekt.view

import android.graphics.*
import android.graphics.drawable.Drawable
import com.kyawhtut.shadowdrawablekt.utils.ShadowDrawableShape

/**
 * @author kyawhtut
 * @date 15/09/2020
 */
abstract class ShadowDrawable(
    private val shape: ShadowDrawableShape,
    private val bgColor: IntArray,
    private val shapeRadius: Float,
    private val shadowColor: Int,
    private val shadowRadius: Float,
    private val offsetX: Float,
    private val offsetY: Float
) : Drawable() {

    private val shadowPaint by lazy {
        Paint().apply {
            color = Color.TRANSPARENT
            isAntiAlias = true
            setShadowLayer(shadowRadius, offsetX, offsetY, shadowColor)
            xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_ATOP)
        }
    }

    private val bgPaint by lazy {
        Paint().apply {
            isAntiAlias = true
        }
    }

    private var rect: RectF? = null

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        rect = RectF(
            left + shadowRadius - offsetX,
            top + shadowRadius - offsetY,
            right - shadowRadius - offsetX,
            bottom - shadowRadius - offsetY
        )
    }

    override fun draw(canvas: Canvas) {
        if (rect == null) throw RuntimeException("Rect must not be null.")

        if (bgColor.size == 1) {
            bgPaint.color = bgColor.first()
        } else {
            rect?.let {
                bgPaint.shader = LinearGradient(
                    it.left,
                    it.height() / 2f,
                    it.right,
                    it.height() / 2f,
                    bgColor,
                    null,
                    Shader.TileMode.CLAMP
                )
            }
        }

        when (shape) {
            is ShadowDrawableShape.ROUND -> {
                canvas.drawRoundRect(
                    rect!!,
                    shapeRadius,
                    shadowRadius,
                    shadowPaint
                )
                canvas.drawRoundRect(rect!!, shadowRadius, shadowRadius, bgPaint)
            }
            is ShadowDrawableShape.CIRCLE -> {
                rect?.let {
                    canvas.drawCircle(
                        it.centerX(),
                        it.centerY(),
                        it.width().coerceAtMost(it.height()) / 2,
                        shadowPaint
                    )

                    canvas.drawCircle(
                        it.centerX(),
                        it.centerY(),
                        it.width().coerceAtMost(it.height()) / 2,
                        bgPaint
                    )
                }
            }
        }
    }

    override fun setAlpha(alpha: Int) {
        shadowPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        shadowPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}
