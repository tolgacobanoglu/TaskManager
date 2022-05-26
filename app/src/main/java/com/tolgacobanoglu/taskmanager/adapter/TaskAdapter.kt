package com.tolgacobanoglu.taskmanager.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tolgacobanoglu.taskmanager.R
import com.tolgacobanoglu.taskmanager.model.Task
import com.tolgacobanoglu.taskmanager.view.TasksFragmentDirections
import kotlinx.android.synthetic.main.task_item.view.*
import kotlinx.android.synthetic.main.task_item.view.content
import java.util.*

class TaskAdapter(var data: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.viewHolder>()
{
    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var title = itemView.title
        var content = itemView.content
        var layout = itemView.mylayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        when (data[position].priority.lowercase(Locale.ROOT)) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#ef5350"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#66bb6a"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#ffee58"))
        }
        holder.title.text = data[position].title
        holder.content.text = data[position].content

        holder.itemView.setOnClickListener{
            val action = TasksFragmentDirections.actionTasksFragmentToDetailsFragment(position,data[position].title,data[position].content)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

}