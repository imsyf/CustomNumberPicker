package at.playground.datepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.playground.datepicker.databinding.ItemPickerBinding

class LetterPickerListAdapter(
    private val onLetterChanged: (id: Int, selected: Int) -> Unit,
    private val onDeleteClicked: (id: Int) -> Unit,
) : ListAdapter<LetterPickerModel, LetterPickerListAdapter.ViewHolder>(LetterPickerModel.DIFFER) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPickerBinding.inflate(inflater, parent, false)
        // val binding = ItemTextBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemPickerBinding,
        // private val binding: ItemTextBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: LetterPickerModel): Unit = with(binding) {

            // Log.d("blah", "bind: $model")

            with(picker) {
                setup(model.letters, model.selected, Char::toString) { position, _ ->
                    onLetterChanged(model.id, position)
                }
                setOnClickListener {
                    if (delete.isVisible) {
                        position.toggleVisibility()
                        delete.toggleVisibility()
                    }
                }
            }

            with(position) {
                text = "${model.order}"
                setOnClickListener {
                    toggleVisibility()
                    delete.toggleVisibility()
                }
            }

            with(delete) {
                setOnClickListener {
                    toggleVisibility()
                    position.toggleVisibility()
                    onDeleteClicked(model.id)
                }
            }
        }

        private fun View.toggleVisibility() {
            isVisible = !isVisible
        }
    }
}
