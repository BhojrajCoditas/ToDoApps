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
        private val taskName: TextView = itemView.findViewById(R.id.tv_task_name)
        private val taskTime: TextView = itemView.findViewById(R.id.tv_time)

        fun bind(task: Task) {
            taskCheckBox.setOnCheckedChangeListener(null)

            taskName.text = task.name
            taskTime.text = task.time
            taskCheckBox.isChecked = task.isCompleted

            // Apply strike-through and fade effect
            val alpha = if (task.isCompleted) 0.5f else 1f
            val strikeFlag = if (task.isCompleted) Paint.STRIKE_THRU_TEXT_FLAG else 0

            taskName.paintFlags = strikeFlag
            taskTime.paintFlags = strikeFlag
            taskName.alpha = alpha
            taskTime.alpha = alpha

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
