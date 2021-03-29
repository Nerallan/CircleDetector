package by.nerallan.circledetector.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.annotation.FloatRange
import by.nerallan.circledetector.drawable.OPAQUE_ALPHA
import kotlin.math.min

class Ripple(
    private val tint: Int,
    private val dBounds: () -> Rect
) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = tint
    }

    private var xCenter = 0F
    private var yCenter = 0F
    private var dynamicRadius = 0F

    @FloatRange(from = .0, to = 1.0, toInclusive = false)
    var progress: Float = 0f
        set(value) {
            field = value
            xCenter = dBounds().exactCenterX()
            yCenter = dBounds().exactCenterY()
            val minimalSize = min(dBounds().width(), dBounds().height()).toFloat()
            dynamicRadius = value * minimalSize / 2

            paint.color = tint.applyAlpha((OPAQUE_ALPHA * (1 - value)).toInt())
        }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(xCenter, yCenter, dynamicRadius, paint)
    }

    private fun Int.applyAlpha(alpha: Int) =
        Color.argb(alpha, Color.red(this), Color.green(this), Color.blue(this))
}