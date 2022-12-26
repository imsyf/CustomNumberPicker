package at.playground.datepicker

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import at.playground.datepicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory {
            initializer {
                MainViewModel(DataSource)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listAdapter = LetterPickerListAdapter(
            onLetterChanged = viewModel::updateSelected,
            onDeleteClicked = viewModel::delete
        )

        with(binding) {
            with(list) {
                adapter = listAdapter
            }
            add.setOnClickListener { viewModel.add() }
        }

        viewModel.state.observe(this) {
            listAdapter.submitList(it.letterPickerModels)
            binding.word.text = it.word
            binding.answer.text = "${it.answers.size}"
            binding.add.isEnabled = it.canAdd
            Log.d("blah", "answers(${it.answers.size}): ${it.answers}")
        }
    }
}
