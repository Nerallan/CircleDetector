package by.nerallan.circledetector.model

import android.content.Context
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import by.nerallan.circledetector.getAttrColor
import java.io.Serializable

sealed class ColorSource : Serializable {

    @androidx.annotation.ColorInt
    abstract fun getColor(context: Context): Int

    data class ResourceAttribute(@AttrRes val backgroundColorAttribute: Int) : ColorSource() {

        override fun getColor(context: Context): Int {
            return context.getAttrColor(backgroundColorAttribute)
        }
    }

    data class ColorInt(@androidx.annotation.ColorInt val backgroundColor: Int) : ColorSource() {

        override fun getColor(context: Context) = backgroundColor
    }

    data class ColorRes(@androidx.annotation.ColorRes val colorRes: Int) : ColorSource() {

        override fun getColor(context: Context): Int = ContextCompat.getColor(context, colorRes)
    }
}