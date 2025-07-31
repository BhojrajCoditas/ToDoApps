package com.example.todoapps.ui.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapps.databinding.FragmentTaskListBinding
import com.example.todoapps.ui.adapter.TaskAdapter
import com.example.todoapps.ui.viewmodel.TaskViewModel


class TaskListFragment : Fragment() {

    private lateinit var binding: FragmentTaskListBinding
    private val taskViewModel: TaskViewModel by activityViewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val todayAdapter = TaskAdapter { updatedTask -> taskViewModel.update(updatedTask) }
        val tomorrowAdapter = TaskAdapter { updatedTask -> taskViewModel.update(updatedTask) }

        binding.recyclerToday.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerToday.adapter = todayAdapter

        binding.recyclerTomorrow.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTomorrow.adapter = tomorrowAdapter

        // Observe Today Tasks
        taskViewModel.todayTasks.observe(viewLifecycleOwner) {
            todayAdapter.submitList(it)
        }

        // Observe Tomorrow Tasks
        taskViewModel.tomorrowTasks.observe(viewLifecycleOwner) {
            tomorrowAdapter.submitList(it)
        }

        binding.fabAdd.setOnClickListener {
            val action = TaskListFragmentDirections.actionTaskListFragmentToAddTaskFragment()
            findNavController(this).navigate(action)
        }
    }


}
