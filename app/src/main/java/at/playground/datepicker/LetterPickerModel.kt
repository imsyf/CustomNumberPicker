package at.playground.datepicker

import at.playground.datepicker.ext.provideItemCallback

data class LetterPickerModel(
    val id: Int,
    val order: Int,
    val letters: List<Char>,
    val selected: Int,
) {
    companion object {
        val DIFFER = provideItemCallback<LetterPickerModel>(
            items = { old, new -> old.id == new.id },
            contents = { old, new -> old.letters == new.letters && old.order == new.order },
        )
    }
}
