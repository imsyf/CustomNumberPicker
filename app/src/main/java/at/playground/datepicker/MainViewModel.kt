package at.playground.datepicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val dataSource: DataSource,
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        _state.value = State(
            letterPickerModels = List(1) {
                LetterPickerModel(
                    id = it,
                    order = it + 1,
                    letters = dataSource.getRandomLetters(),
                    selected = 0,
                )
            },
            callback = dataSource::getPossibleAnswers,
        )
    }

    fun updateSelected(id: Int, selected: Int) {
        mutate {
            copy(
                letterPickerModels = letterPickerModels.map {
                    if (it.id == id) it.copy(selected = selected)
                    else it
                }
            )
        }
    }

    fun add() {
        mutate {
            val last = letterPickerModels.lastOrNull()
            val newId = last?.id?.inc() ?: 0
            val newOrder = last?.order?.inc() ?: 1
            val newLetters = last?.letters ?: emptyList()

            copy(
                letterPickerModels = letterPickerModels + LetterPickerModel(
                    id = newId,
                    order = newOrder,
                    letters = dataSource.getRandomLetters(),
                    selected = 0,
                )
            )
        }
    }

    fun delete(id: Int) {
        mutate {
            copy(
                letterPickerModels = letterPickerModels
                    .filter { it.id != id }
                    .mapIndexed { i, it -> it.copy(order = i + 1) }
            )
        }
    }

    private inline fun mutate(crossinline mutation: State.() -> State) {
        // viewModelScope.launch {
        if (_state.value != null) {
            with(_state.value as State) {
                _state.value = mutation()
            }
        }
        // }
    }

    data class State(
        val letterPickerModels: List<LetterPickerModel>,
        private val callback: (String) -> List<String>,
    ) {
        // Derived property! Inspired by Maverick
        val word: String = letterPickerModels.fold("") { current, it ->
            current + it.letters[it.selected]
        }

        val answers: List<String> = callback(word)

        val canAdd: Boolean =
            if (answers.isEmpty()) false
            else if (answers.size > 1) true
            else answers.first().length != word.length
    }
}
