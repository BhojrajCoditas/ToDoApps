package com.example.todoapps.ui.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapps.data.Task
import com.example.todoapps.databinding.FragmentAddTaskBinding
import com.example.todoapps.ui.viewmodel.TaskViewModel

class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private val taskViewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnDone.setOnClickListener {
            val name = binding.etTaskName.text.toString()
            val hour = binding.timePicker.hour
            val minute = binding.timePicker.minute
            val time = String.format("%02d:%02d", hour, minute)
            val isToday = binding.switchToday.isChecked
            binding.timePicker.setIs24HourView(true)

            if (name.isNotEmpty()) {
                val task = Task(name = name, time = time, isToday = isToday)
                taskViewModel.insert(task)
                findNavController().popBackStack()
            }
        }
    }
}
