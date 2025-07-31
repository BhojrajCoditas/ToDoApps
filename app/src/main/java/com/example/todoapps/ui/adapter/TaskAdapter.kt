package com.example.todoapps.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapps.R
import com.example.todoapps.data.Task

class TaskAdapter(
    private val onTaskCheckedChange: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskCheckBox: CheckBox = itemView.findViewById(R.id.cb_task)
        private val taskTime: TextView = itemView.findViewById(R.id.tv_time)

        fun bind(task: Task) {
            // Detach listener temporarily
            taskCheckBox.setOnCheckedChangeListener(null)

            // Set task name and time
            taskCheckBox.text = task.name
            taskTime.text = task.time
            taskCheckBox.isChecked = task.isCompleted

            // Fade effect on both checkbox and time
            val alphaValue = if (task.isCompleted) 0.5f else 1.0f
            taskCheckBox.alpha = alphaValue
            taskTime.alpha = alphaValue

            // Reattach listener after setting checked state
            taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
                onTaskCheckedChange(task.copy(isCompleted = isChecked))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem
    }
}
