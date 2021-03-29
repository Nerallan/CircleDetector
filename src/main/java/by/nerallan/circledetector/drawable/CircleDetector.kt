package by.nerallan.circledetector.drawable

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt
import by.nerallan.circledetector.model.Ripple

const val ANIMATION_DURATION = 3000L
const val OPAQUE_ALPHA = 0xFF
const val CIRCLE_COUNT = 5

class CircleDetector(
    @ColorInt tint: Int
) : Drawable() {


    private val ripples: Array<Ripple> = Array(CIRCLE_COUNT) {
        Ripple(tint) { bounds }
    }
    private val progressStep: Float = 1f / ripples.size

    private val animator = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = ANIMATION_DURATION
        repeatCount = ValueAnimator.INFINITE
        interpolator = LinearInterpolator()
    }

    init {

        animator.addUpdateListener { valueAnimator ->
            var progressOffset = valueAnimator.animatedValue as Float
            repeat(ripples.size) {
                progressOffset = (progressOffset + progressStep).let { result ->
                    if (result >= 1f) result - 1f else result
                }

                ripples[it].progress = progressOffset
            }

            invalidateSelf()
        }
    }

    override fun draw(canvas: Canvas) {
        ripples.forEach { it.draw(canvas) }
    }

    override fun setAlpha(alpha: Int) {/*ignore*/ }

    override fun getOpacity(): Int = PixelFormat.OPAQUE

    override fun setColorFilter(colorFilter: ColorFilter?) {/*ignore*/ }

    override fun setVisible(visible: Boolean, restart: Boolean): Boolean {
        return super.setVisible(visible, restart).also { changed ->
            if (changed) {
                when {
                    visible -> animator.start()
                    else -> animator.cancel()
                }
            }
        }
    }
}