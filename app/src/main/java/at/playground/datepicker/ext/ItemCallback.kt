package at.playground.datepicker.ext

import androidx.recyclerview.widget.DiffUtil

inline fun <T : Any> provideItemCallback(
    crossinline items: (old: T, new: T) -> Boolean,
    crossinline contents: (old: T, new: T) -> Boolean,
): DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = items(oldItem, newItem)
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = contents(oldItem, newItem)
}
