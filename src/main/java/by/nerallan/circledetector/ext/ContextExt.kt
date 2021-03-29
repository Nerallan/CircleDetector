package by.nerallan.circledetector

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleableRes

@ColorInt
fun Context.getAttrColor(@AttrRes attr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.data
}

internal fun Context.getAttrResId(@AttrRes attr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.resourceId
}

internal inline fun Context.withStyledAttributes(
    @StyleableRes resourceId: IntArray,
    attrs: AttributeSet,
    block: TypedArray.() -> Unit
) {
    val attributes = obtainStyledAttributes(attrs, resourceId)
    try {
        block(attributes)
    } finally {
        attributes.recycle()
    }
}