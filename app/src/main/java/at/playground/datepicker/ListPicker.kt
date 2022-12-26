package at.playground.datepicker

import android.content.Context
import android.util.AttributeSet
import android.widget.NumberPicker

class ListPicker(
    context: Context,
    attrs: AttributeSet,
) : NumberPicker(context, attrs) {

    init {
        descendantFocusability = FOCUS_BLOCK_DESCENDANTS
    }

    fun <T> setup(
        items: List<T>,
        initial: Int,
        toString: T.() -> String,
        onValueChanged: (index: Int, current: T) -> Unit,
    ) {
        displayedValues = items.map(toString).toTypedArray()
        maxValue = items.lastIndex
        minValue = 0
        value = initial
        setOnValueChangedListener { _, _, new -> onValueChanged(new, items[new]) }
    }
}
